package com.flyhigh.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.CommDelFlagEnum;
import com.flyhigh.common.enums.MessageReceiveStatusEnum;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.sms.domain.BizMessageInfo;
import com.flyhigh.sms.domain.BizMessageUserDetail;
import com.flyhigh.sms.domain.convert.BizMessageUserDetailConvert;
import com.flyhigh.sms.domain.vo.messageUserDetail.*;
import com.flyhigh.sms.mapper.BizMessageInfoMapper;
import com.flyhigh.sms.mapper.BizMessageUserDetailMapper;
import com.flyhigh.sms.service.IBizMessageUserDetailService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 消息接收人Service业务层处理
 *
 * @author flyhigh
 * @date 2022-03-13
 */
@Service
@Validated
public class BizMessageUserDetailServiceImpl implements IBizMessageUserDetailService {

    @Resource
    private BizMessageUserDetailMapper bizMessageUserDetailMapper;

    @Resource
    private BizMessageInfoMapper bizMessageInfoMapper;
    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizMessageUserDetail entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private BizMessageUserDetail validateBizMessageUserDetailExists(Long id) {
        BizMessageUserDetail bizMessageUserDetail = bizMessageUserDetailMapper.selectById(id);
        if (bizMessageUserDetail == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.DATA_ERROR);
        }
        return bizMessageUserDetail;
    }

    /**
     * 查询消息接收人
     *
     * @param id 消息接收人主键
     * @return 消息接收人
     */
    @Override
    public BizMessageUserDetailVO queryById(Long id) {
        BizMessageUserDetail bizMessageUserDetail = bizMessageUserDetailMapper.selectById(id);
        return BizMessageUserDetailConvert.INSTANCE.convert(bizMessageUserDetail);
    }

    /**
     * 查询消息接收人列表
     *
     * @param pageReq 消息接收人
     * @return 消息接收人
     */
    @Override
    public TableDataInfo<BizMessageUserDetailVO> queryPageList(BizMessageUserDetailPageReq pageReq) {
        TableDataInfo<BizMessageUserDetail> bizMessageUserDetailTableDataInfo = bizMessageUserDetailMapper.selectPage(pageReq);
        TableDataInfo<BizMessageUserDetailVO> tableDataInfo = BizMessageUserDetailConvert.INSTANCE.convertPage(bizMessageUserDetailTableDataInfo);
        List<BizMessageUserDetailVO> rows = tableDataInfo.getRows();
        if (CollectionUtils.isEmpty(rows)) {
            return tableDataInfo;
        }
        List<Long> messageIds = rows.stream().map(BizMessageUserDetailVO::getMessageId).distinct().collect(Collectors.toList());
        List<BizMessageInfo> bizMessageInfos = bizMessageInfoMapper.selectList(new LambdaQueryWrapper<BizMessageInfo>().in(BizMessageInfo::getId, messageIds));
        if (CollectionUtils.isEmpty(bizMessageInfos)) {
            return tableDataInfo;
        }
        Map<Long, BizMessageInfo> bizMessageInfoMap = bizMessageInfos.stream().collect(Collectors.toMap(BizMessageInfo::getId, Function.identity(), (o1, o2) -> o2));
        rows.forEach(data -> {
            if (bizMessageInfoMap != null && bizMessageInfoMap.get(data.getMessageId()) != null) {
                BizMessageInfo bizMessageInfo = bizMessageInfoMap.get(data.getMessageId());
                data.setMessageSender(bizMessageInfo.getMessageSender());
                data.setMessageType(bizMessageInfo.getMessageType());
                data.setMessageClassify(MessageTemplateEnum.getMessageClassify(bizMessageInfo.getMessageType()));
                data.setContent(bizMessageInfo.getContent());
                data.setRemark(bizMessageInfo.getRemark());
            }
        });
        return tableDataInfo;
    }

    /**
     * 查询消息接收人列表
     *
     * @param exportReq 消息接收人
     * @return 消息接收人
     */
    @Override
    public List<BizMessageUserDetailExcelVO> queryList(BizMessageUserDetailExportReq exportReq) {
        List<BizMessageUserDetail> bizMessageUserDetailLists = bizMessageUserDetailMapper.selectList(exportReq);
        return BizMessageUserDetailConvert.INSTANCE.convertListExcel(bizMessageUserDetailLists);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<BizMessageUserDetail> buildQueryWrapper(BizMessageUserDetail bizMessageUserDetail) {
        LambdaQueryWrapper<BizMessageUserDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bizMessageUserDetail.getMessageId() != null, BizMessageUserDetail::getMessageId, bizMessageUserDetail.getMessageId());
        lqw.eq(bizMessageUserDetail.getUserId() != null, BizMessageUserDetail::getUserId, bizMessageUserDetail.getUserId());
        lqw.eq(bizMessageUserDetail.getReceiveStatus() != null, BizMessageUserDetail::getReceiveStatus, bizMessageUserDetail.getReceiveStatus());
        lqw.eq(bizMessageUserDetail.getWhetherApprove() != null, BizMessageUserDetail::getWhetherApprove, bizMessageUserDetail.getWhetherApprove());
        return lqw;
    }

    /**
     * 新增消息接收人
     *
     * @param reateReq 消息接收人
     * @return 结果
     */
    @Override
    public Boolean createBizMessageUserDetail(BizMessageUserDetailCreateReq reateReq) {
        BizMessageUserDetail bizMessageUserDetail = BizMessageUserDetailConvert.INSTANCE.convert(reateReq);
        validEntityBeforeSave(bizMessageUserDetail);
        bizMessageUserDetailMapper.insert(bizMessageUserDetail);
        return bizMessageUserDetail.getId() > 0;
    }

    /**
     * 修改消息接收人
     *
     * @param updateReq 消息接收人
     * @return 结果
     */
    @Override
    public Boolean updateBizMessageUserDetail(BizMessageUserDetailUpdateReq updateReq) {
        List<BizMessageUserDetail> updateObj = Lists.newArrayList();
        updateReq.getIds().forEach(id -> {
            BizMessageUserDetail userDetail = new BizMessageUserDetail();
            userDetail.setId(id);
            userDetail.setReceiveStatus(MessageReceiveStatusEnum.Read.getCode());
            updateObj.add(userDetail);
        });

        return bizMessageUserDetailMapper.updateBatchById(updateObj);
    }

    /**
     * 批量删除消息接收人
     *
     * @param ids 需要删除的消息接收人主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        return bizMessageUserDetailMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 消息全部已读
     *
     * @return 结果
     */
    @Override
    public Boolean allRead() {
        Long userId = SecurityUtils.getUserId();

        List<BizMessageUserDetail> bizMessageUserDetails = bizMessageUserDetailMapper.selectList(new LambdaQueryWrapper<BizMessageUserDetail>()
                .eq(BizMessageUserDetail::getDelFlag, CommDelFlagEnum.NOT_DELETE.getType())
                .eq(BizMessageUserDetail::getUserId, userId)
                .eq(BizMessageUserDetail::getReceiveStatus, MessageReceiveStatusEnum.Unread.getCode()));
        if (CollectionUtils.isEmpty(bizMessageUserDetails)) {
            return true;
        }

        bizMessageUserDetails.forEach(item -> item.setReceiveStatus(MessageReceiveStatusEnum.Read.getCode()));
        return bizMessageUserDetailMapper.updateBatchById(bizMessageUserDetails);
    }

}
