package com.flyhigh.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.flyhigh.api.dto.smsPush.BizPushBindReq;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.sms.domain.*;
import com.flyhigh.sms.domain.convert.BizPushUserAliasConvert;
import com.flyhigh.sms.domain.convert.BizPushUserCidConvert;
import com.flyhigh.sms.domain.vo.pushTask.PushParam;
import com.flyhigh.sms.mapper.BizPushErrorRecordMapper;
import com.flyhigh.sms.mapper.BizPushTaskMapper;
import com.flyhigh.sms.mapper.BizPushUserAliasMapper;
import com.flyhigh.sms.mapper.BizPushUserCidMapper;
import com.flyhigh.sms.service.GtService;
import com.flyhigh.sms.utils.PushParamHelper;
import com.getui.push.v2.sdk.api.PushApi;
import com.getui.push.v2.sdk.api.UserApi;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.req.Audience;
import com.getui.push.v2.sdk.dto.req.AudienceDTO;
import com.getui.push.v2.sdk.dto.req.CidAliasListDTO;
import com.getui.push.v2.sdk.dto.req.message.PushDTO;
import com.getui.push.v2.sdk.dto.res.TaskIdDTO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GtServiceApiImpl
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/31 14:55
 * @Version 1.0
 */
@Service
@Slf4j
public class GtServiceImpl implements GtService {

    @Autowired
    private PushApi pushApi;

    @Autowired
    private UserApi userApi;

    @Resource
    private BizPushUserCidMapper bizPushUserCidMapper;

    @Resource
    private BizPushUserAliasMapper bizPushUserAliasMapper;

    @Resource
    private BizPushTaskMapper bizPushTaskMapper;

    @Resource
    private BizPushErrorRecordMapper bizPushErrorRecordMapper;

    /**
     * 用户绑定
     *
     * @param pushBindReq
     * @return
     */
    @Override
    @Transactional
    public AjaxResult bind(BizPushBindReq pushBindReq) {
        Long userId = SecurityUtils.getUserId();
        // 查询用户别名是否存在，不存在则需要绑定用户
        BizPushUserAlias bizPushUserAlias = bizPushUserAliasMapper.selectOne(new LambdaQueryWrapperPlus<BizPushUserAlias>().eq(BizPushUserAlias::getUserId, userId));
        if (bizPushUserAlias == null) {
            CidAliasListDTO cidAliasListDTO = new CidAliasListDTO();
            cidAliasListDTO.add(new CidAliasListDTO.CidAlias(pushBindReq.getCid(), String.valueOf(userId)));
            ApiResult<Void> gtResult = userApi.bindAlias(cidAliasListDTO);
            if (!gtResult.isSuccess()) {
                return AjaxResult.error(ErrorCodeConstants.MESSAGE_BIND_ERROR);
            }
            // 插入绑定表
            bizPushUserAliasMapper.insert(BizPushUserAliasConvert.INSTANCE.convert(pushBindReq, userId));
        }
        // 检查设备是否存在记录表若不存在则新增
        BizPushUserCid bizPushUserCid = bizPushUserCidMapper.selectOne(new LambdaQueryWrapperPlus<BizPushUserCid>().eq(BizPushUserCid::getUserId, userId).eq(BizPushUserCid::getCid, pushBindReq.getCid()));
        if (bizPushUserCid == null) {
            // 插入设备表
            bizPushUserCidMapper.insert(BizPushUserCidConvert.INSTANCE.convert(pushBindReq, userId));
        }
        return AjaxResult.success();
    }


    /**
     * 发送消息
     *
     * @param bizMessageInfo
     * @param userIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void sendAppMsg(BizMessageInfo bizMessageInfo, List<Long> userIds) {

        try {
            // 根据userIds查询这些用户绑定的别名
            List<BizPushUserAlias> bizPushUserAliases = bizPushUserAliasMapper.selectList(new LambdaQueryWrapperPlus<BizPushUserAlias>().in(BizPushUserAlias::getUserId, userIds));
            if (CollectionUtils.isEmpty(bizPushUserAliases)) {
                return;
            }
            // 按每500个一组分割
            List<List<BizPushUserAlias>> userAlias = Lists.partition(bizPushUserAliases, 500);
            for (List<BizPushUserAlias> batchUsers : userAlias) {
                // 创建消息
                PushParam pushParam = PushParam.builder()
                        // 通知消息内容，长度 ≤ 256
                        .body(StrUtil.split(bizMessageInfo.getContent(), 256)[0])
                        // 通知消息标题，长度 ≤ 50
                        .title(StrUtil.split(MessageTemplateEnum.getMessageType(bizMessageInfo.getMessageType()), 50)[0])
                        // 模板类型
                        .messageTemplateEnum(MessageTemplateEnum.getMessageByType(bizMessageInfo.getMessageType()))
                        // 业务主键id
                        .businessId(bizMessageInfo.getBusinessId())
                        .build();
                // 构造message body
                PushDTO<Audience> pushDTO = PushParamHelper.buildPushDTO(pushParam);
                // 创建 task
                ApiResult<TaskIdDTO> createMsgRes = pushApi.createMsg(pushDTO);
                if (!createMsgRes.isSuccess()) {
                    createErrorMsg(bizMessageInfo, userIds, createMsgRes.getMsg());
                }
                // 指定发送
                AudienceDTO audienceDTO = new AudienceDTO();
                audienceDTO.setTaskid(createMsgRes.getData().getTaskId());
                Audience audience = new Audience();
                for (BizPushUserAlias alias : batchUsers) {
                    audience.addAlias(alias.getUserAlias());
                }
                audienceDTO.setAudience(audience);
                ApiResult<Map<String, Map<String, String>>> mapApiResult = pushApi.pushListByAlias(audienceDTO);
                if (!mapApiResult.isSuccess()) {
                    createErrorMsg(bizMessageInfo, userIds, mapApiResult.getMsg());
                    continue;
                }
                // 记录任务表
                bizPushTaskMapper.insert(buildTaskRecord(bizMessageInfo, batchUsers, createMsgRes));
            }
        } catch (Exception e) {
            // 发送异常插入异常记录表
            createErrorMsg(bizMessageInfo, userIds, e.getMessage());
        }
    }

    /**
     * 构建任务记录
     *
     * @param bizMessageInfo
     * @param batchUsers
     * @param createMsgRes
     * @return
     */
    public BizPushTask buildTaskRecord(BizMessageInfo bizMessageInfo, List<BizPushUserAlias> batchUsers, ApiResult<TaskIdDTO> createMsgRes) {
        BizPushTask bizPushTask = new BizPushTask();
        bizPushTask.setCreateTime(new Date());
        bizPushTask.setUpdateTime(new Date());
        bizPushTask.setGtCode(StringUtils.EMPTY);
        bizPushTask.setMessageId(bizMessageInfo.getId());
        bizPushTask.setTaskId(createMsgRes.getData().getTaskId());
        bizPushTask.setUserNumber(Long.valueOf(batchUsers.size()));
        return bizPushTask;
    }

    /**
     * 保存异常的记录
     *
     * @param bizMessageInfo
     * @param userIds
     */
    public void createErrorMsg(BizMessageInfo bizMessageInfo, List<Long> userIds, String msg) {
        BizPushErrorRecord bizPushErrorRecord = buildErrorRecord(bizMessageInfo, userIds);
        bizPushErrorRecord.setRemark(msg);
        bizPushErrorRecordMapper.insert(bizPushErrorRecord);
    }

    /**
     * 构造异常消息体
     *
     * @param bizMessageInfo
     * @param userIds
     * @return
     */
    private BizPushErrorRecord buildErrorRecord(BizMessageInfo bizMessageInfo, List<Long> userIds) {
        BizPushErrorRecord bizPushErrorRecord = new BizPushErrorRecord();
        bizPushErrorRecord.setMessageId(bizMessageInfo.getId());
        bizPushErrorRecord.setUserNumber(Long.valueOf(userIds.size()));
        bizPushErrorRecord.setTryTimes(NumberUtils.INTEGER_ZERO.longValue());
        bizPushErrorRecord.setCreateTime(new Date());
        bizPushErrorRecord.setUpdateTime(new Date());
        return bizPushErrorRecord;
    }

}
