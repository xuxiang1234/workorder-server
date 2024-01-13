package com.flyhigh.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.api.dto.messageInfo.BizMessageInfoDeleteDTO;
import com.flyhigh.api.dto.messageInfo.BizMessageUserDetailDTO;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.common.enums.CommDelFlagEnum;
import com.flyhigh.common.enums.MessageApproveEnum;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.sms.domain.BizMessageInfo;
import com.flyhigh.sms.domain.BizMessageUserDetail;
import com.flyhigh.sms.domain.convert.BizMessageInfoConvert;
import com.flyhigh.sms.domain.convert.BizMessageUserDetailConvert;
import com.flyhigh.sms.domain.vo.messageInfo.*;
import com.flyhigh.sms.mapper.BizMessageInfoMapper;
import com.flyhigh.sms.mapper.BizMessageUserDetailMapper;
import com.flyhigh.sms.service.GtService;
import com.flyhigh.sms.service.IBizMessageInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息Service业务层处理
 *
 * @author flyhigh
 * @date 2022-03-13
 */
@Service
@Validated
@Slf4j
public class BizMessageInfoServiceImpl implements IBizMessageInfoService {


    @Resource
    private BizMessageInfoMapper bizMessageInfoMapper;

    @Resource
    private BizMessageUserDetailMapper bizMessageUserDetailMapper;

    @Autowired
    private GtService gtService;

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizMessageInfo entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private void validateBizMessageInfoExists(Long id) {
        if (bizMessageInfoMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }
    }

    /**
     * 查询消息
     *
     * @param id 消息主键
     * @return 消息
     */
    @Override
    public BizMessageInfoVO queryById(Long id) {
        BizMessageInfo bizMessageInfo = bizMessageInfoMapper.selectById(id);
        return BizMessageInfoConvert.INSTANCE.convert(bizMessageInfo);
    }

    /**
     * 查询消息列表
     *
     * @param pageReq 消息
     * @return 消息
     */
    @Override
    public TableDataInfo<BizMessageInfoVO> queryPageList(BizMessageInfoPageReq pageReq) {
        TableDataInfo<BizMessageInfo> bizMessageInfoTableDataInfo = bizMessageInfoMapper.selectPage(pageReq);
        return BizMessageInfoConvert.INSTANCE.convertPage(bizMessageInfoTableDataInfo);
    }

    /**
     * 查询消息列表
     *
     * @param exportReq 消息
     * @return 消息
     */
    @Override
    public List<BizMessageInfoExcelVO> queryList(BizMessageInfoExportReq exportReq) {
        List<BizMessageInfo> bizMessageInfoLists = bizMessageInfoMapper.selectList(exportReq);
        return BizMessageInfoConvert.INSTANCE.convertListExcel(bizMessageInfoLists);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<BizMessageInfo> buildQueryWrapper(BizMessageInfo bizMessageInfo) {
        LambdaQueryWrapper<BizMessageInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bizMessageInfo.getModuleType()), BizMessageInfo::getModuleType, bizMessageInfo.getModuleType());
        lqw.eq(bizMessageInfo.getBusinessId() != null, BizMessageInfo::getBusinessId, bizMessageInfo.getBusinessId());
        lqw.eq(StringUtils.isNotBlank(bizMessageInfo.getMessageType()), BizMessageInfo::getMessageType, bizMessageInfo.getMessageType());
        lqw.eq(StringUtils.isNotBlank(bizMessageInfo.getComponent()), BizMessageInfo::getComponent, bizMessageInfo.getComponent());
        lqw.eq(StringUtils.isNotBlank(bizMessageInfo.getCustomParameters()), BizMessageInfo::getCustomParameters, bizMessageInfo.getCustomParameters());
        lqw.eq(StringUtils.isNotBlank(bizMessageInfo.getContent()), BizMessageInfo::getContent, bizMessageInfo.getContent());
        lqw.eq(StringUtils.isNotBlank(bizMessageInfo.getMessageSender()), BizMessageInfo::getMessageSender, bizMessageInfo.getMessageSender());
        return lqw;
    }

    /**
     * 新增消息
     *
     * @return 结果
     */
    @Async("threadPoolTaskExecutor")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean createBizMessageInfo(BizMessageInfo bizMessageInfo, List<Long> userIds) {
        bizMessageInfoMapper.insert(bizMessageInfo);
        // 新增主表结束
        List<BizMessageUserDetail> users = Lists.newArrayList();
        userIds.forEach(u -> {
            BizMessageUserDetail bizMessageUserDetail = BizMessageUserDetailConvert.INSTANCE.convert(u, bizMessageInfo.getId());
            bizMessageUserDetail.setCreateBy(bizMessageInfo.getUpdateBy());
            bizMessageUserDetail.setCreateTime(bizMessageInfo.getCreateTime());
            bizMessageUserDetail.setBusinessId(bizMessageInfo.getBusinessId());
            bizMessageUserDetail.setMessageType(bizMessageInfo.getMessageType());
            users.add(bizMessageUserDetail);
        });
        bizMessageUserDetailMapper.insertBatch(users);
        // 处理推送app通知
        try {
            gtService.sendAppMsg(bizMessageInfo, userIds);
        } catch (Exception e) {
            log.error("发送消息失败：{}", e);
        }
        return bizMessageInfo.getId() > 0;
    }

    /**
     * 修改消息
     *
     * @param updateReq 消息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBizMessageInfo(BizMessageInfoUpdateReq updateReq) {
        // 校验存在
        //  validateBizMessageInfoExists(updateReq.getId());
        // 更新
        BizMessageInfo updateObj = BizMessageInfoConvert.INSTANCE.convert(updateReq);
        validEntityBeforeSave(updateObj);
        return bizMessageInfoMapper.updateById(updateObj) > 0;
    }

    /**
     * 批量删除消息
     *
     * @param ids 需要删除的消息主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        return bizMessageInfoMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过ids批量查询消息
     *
     * @param ids 需要查询的消息主键
     * @return 结果
     */
    @Override
    public List<BizMessageInfoVO> getBizMessageInfoList(Collection<Long> ids) {
        List<BizMessageInfo> bizMessageInfoLists = bizMessageInfoMapper.selectBatchIds(ids);
        return BizMessageInfoConvert.INSTANCE.convertList(bizMessageInfoLists);
    }

    /**
     * 删除消息
     *
     * @param bizMessageInfoDeleteDTO
     */
    @Async("threadPoolTaskExecutor")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMessage(BizMessageInfoDeleteDTO bizMessageInfoDeleteDTO) {
        LambdaQueryWrapper<BizMessageInfo> queryWrapper = new LambdaQueryWrapper<BizMessageInfo>()
                .eq(BizMessageInfo::getMessageType, bizMessageInfoDeleteDTO.getMessageTemplate().getType())
                .eq(BizMessageInfo::getBusinessId, bizMessageInfoDeleteDTO.getBusinessId())
                .eq(BizMessageInfo::getDelFlag, CommDelFlagEnum.NOT_DELETE.getType());
        List<BizMessageInfo> bizMessageInfos = bizMessageInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(bizMessageInfos)) {
            return;
        }
        BizMessageInfo bizMessageInfo = bizMessageInfos.get(0);
        // 删除子表
        bizMessageUserDetailMapper.delete(new LambdaQueryWrapperPlus<BizMessageUserDetail>().eq(BizMessageUserDetail::getMessageId, bizMessageInfo.getId()));
        // 删除主表
        bizMessageInfoMapper.deleteById(bizMessageInfo.getId());
    }

    /**
     * 修改消息
     *
     * @param bizMessageInfo
     * @param userIds
     * @param messageTemplate
     * @param whetherCascadeUser
     */
    @Async("threadPoolTaskExecutor")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editBizMessageInfo(BizMessageInfo bizMessageInfo, List<Long> userIds, MessageTemplateEnum messageTemplate, boolean whetherCascadeUser) {
        LambdaQueryWrapper<BizMessageInfo> queryWrapper = new LambdaQueryWrapper<BizMessageInfo>()
                .eq(BizMessageInfo::getMessageType, messageTemplate.getType())
                .eq(BizMessageInfo::getBusinessId, bizMessageInfo.getBusinessId())
                .eq(BizMessageInfo::getDelFlag, CommDelFlagEnum.NOT_DELETE.getType());
        List<BizMessageInfo> bizMessageInfos = bizMessageInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(bizMessageInfos)) {
            return;
        }
        bizMessageInfo.setId(bizMessageInfos.get(0).getId());
        // 修改主表
        bizMessageInfoMapper.updateById(bizMessageInfo);
        if (whetherCascadeUser) {
            // 删除子表
            bizMessageUserDetailMapper.delete(new LambdaQueryWrapperPlus<BizMessageUserDetail>().eq(BizMessageUserDetail::getMessageId, bizMessageInfo.getId()));
            // 再次新增子表
            List<BizMessageUserDetail> users = Lists.newArrayList();
            userIds.forEach(u -> {
                BizMessageUserDetail bizMessageUserDetail = BizMessageUserDetailConvert.INSTANCE.convert(u, bizMessageInfo.getId());
                bizMessageUserDetail.setCreateBy(bizMessageInfo.getUpdateBy());
                bizMessageUserDetail.setCreateTime(bizMessageInfo.getUpdateTime());
                bizMessageUserDetail.setBusinessId(bizMessageInfo.getBusinessId());
                bizMessageUserDetail.setMessageType(bizMessageInfos.get(0).getMessageType());
                users.add(bizMessageUserDetail);
            });
            bizMessageUserDetailMapper.insertBatch(users);
        }

    }

    /**
     * 根据业务id消息接收人
     *
     * @param messageTemplateEnum
     * @param businessId
     * @return
     */
    @Override
    public AjaxResult<List<BizMessageUserDetailDTO>> getMessageRecipients(MessageTemplateEnum messageTemplateEnum, Long businessId) {
        List<BizMessageUserDetail> bizMessageUserDetails = bizMessageUserDetailMapper.selectList(new LambdaQueryWrapperPlus<BizMessageUserDetail>()
                .eq(BizMessageUserDetail::getMessageType, messageTemplateEnum.getType())
                .eq(BizMessageUserDetail::getBusinessId, businessId)
                .orderByAsc(BizMessageUserDetail::getId));
        return AjaxResult.success(BizMessageUserDetailConvert.INSTANCE.convertDTOList(bizMessageUserDetails));
    }

    /**
     * 删除审批消息
     *
     * @param businessId
     * @param uids
     * @param messageTemplateEnum
     */
    @Override
    public void deleteProcessMsg(Long businessId, List<String> uids, MessageTemplateEnum messageTemplateEnum) {
        // 查询主表
        LambdaQueryWrapper<BizMessageInfo> queryWrapper = new LambdaQueryWrapper<BizMessageInfo>()
                .eq(BizMessageInfo::getMessageType, messageTemplateEnum.getType())
                .eq(BizMessageInfo::getBusinessId, businessId)
                .in(BizMessageInfo::getCustomParameters, uids)
                .eq(BizMessageInfo::getDelFlag, CommDelFlagEnum.NOT_DELETE.getType());
        List<BizMessageInfo> bizMessageInfos = bizMessageInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(bizMessageInfos)) {
            return;
        }
        List<Long> messageIds = bizMessageInfos.stream().map(BizMessageInfo::getId).collect(Collectors.toList());
        // 删除子表
        bizMessageUserDetailMapper.delete(new LambdaQueryWrapperPlus<BizMessageUserDetail>().in(BizMessageUserDetail::getMessageId, messageIds));
        // 删除主表
        bizMessageInfoMapper.delete(new LambdaQueryWrapperPlus<BizMessageInfo>().in(BizMessageInfo::getId, messageIds));
    }

    /**
     * 将审批消息修改成已审批
     *
     * @param businessId
     * @param flowNodeUid
     * @param messageTemplateEnum
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateApproveMsg(Long businessId, Long flowNodeUid, MessageTemplateEnum messageTemplateEnum) {
        LambdaQueryWrapper<BizMessageInfo> queryWrapper = new LambdaQueryWrapper<BizMessageInfo>()
                .eq(BizMessageInfo::getMessageType, messageTemplateEnum.getType())
                .eq(BizMessageInfo::getBusinessId, businessId)
                .eq(BizMessageInfo::getCustomParameters, flowNodeUid)
                .eq(BizMessageInfo::getDelFlag, CommDelFlagEnum.NOT_DELETE.getType());
        List<BizMessageInfo> bizMessageInfos = bizMessageInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(bizMessageInfos)) {
            return null;
        }
        List<Long> messageIds = bizMessageInfos.stream().map(BizMessageInfo::getId).collect(Collectors.toList());
        BizMessageUserDetail update = new BizMessageUserDetail();
        update.setWhetherApprove(MessageApproveEnum.YES.getCode());
        bizMessageUserDetailMapper.update(update, new LambdaQueryWrapperPlus<BizMessageUserDetail>().in(BizMessageUserDetail::getMessageId, messageIds));
        return bizMessageInfos.get(0).getMessageSender();
    }

}
