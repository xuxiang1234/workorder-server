package com.flyhigh.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.common.constant.MessageConstants;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.common.enums.CommDelFlagEnum;
import com.flyhigh.common.enums.WaterMessageTemplateEnum;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.DateUtils;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.sms.domain.BizWaterMessageRecord;
import com.flyhigh.sms.domain.convert.BizWaterMessageRecordConvert;
import com.flyhigh.sms.domain.vo.waterMessageRecord.*;
import com.flyhigh.sms.mapper.BizWaterMessageRecordMapper;
import com.flyhigh.sms.service.IBizWaterMessageRecordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 业务消息流水Service业务层处理
 *
 * @author flyhigh
 * @date 2022-04-21
 */
@Service
@Validated
public class BizWaterMessageRecordServiceImpl implements IBizWaterMessageRecordService {

    @Resource
    private BizWaterMessageRecordMapper bizWaterMessageRecordMapper;

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizWaterMessageRecord entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private void validateBizWaterMessageRecordExists(Long id) {
        if (bizWaterMessageRecordMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }
    }

    /**
     * 查询业务消息流水
     *
     * @param id 业务消息流水主键
     * @return 业务消息流水
     */
    @Override
    public BizWaterMessageRecordVO queryById(Long id) {
        BizWaterMessageRecord bizWaterMessageRecord = bizWaterMessageRecordMapper.selectById(id);
        return BizWaterMessageRecordConvert.INSTANCE.convert(bizWaterMessageRecord);
    }

    /**
     * 查询业务消息流水列表
     *
     * @param pageReq 业务消息流水
     * @return 业务消息流水
     */
    @Override
    public TableDataInfo<BizWaterMessageRecordVO> queryPageList(BizWaterMessageRecordReq pageReq) {
        return null;
    }

    /**
     * 查询业务消息流水列表
     *
     * @param exportReq 业务消息流水
     * @return 业务消息流水
     */
    @Override
    public List<BizWaterMessageRecordExcelVO> queryList(BizWaterMessageRecordExportReq exportReq) {
        List<BizWaterMessageRecord> bizWaterMessageRecordLists = bizWaterMessageRecordMapper.selectList(exportReq);
        return BizWaterMessageRecordConvert.INSTANCE.convertListExcel(bizWaterMessageRecordLists);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<BizWaterMessageRecord> buildQueryWrapper(BizWaterMessageRecord bizWaterMessageRecord) {
        LambdaQueryWrapper<BizWaterMessageRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bizWaterMessageRecord.getModuleTypeDesc()), BizWaterMessageRecord::getModuleTypeDesc, bizWaterMessageRecord.getModuleTypeDesc());
        lqw.eq(bizWaterMessageRecord.getModuleType() != null, BizWaterMessageRecord::getModuleType, bizWaterMessageRecord.getModuleType());
        lqw.eq(bizWaterMessageRecord.getBusinessId() != null, BizWaterMessageRecord::getBusinessId, bizWaterMessageRecord.getBusinessId());
        lqw.eq(bizWaterMessageRecord.getMessageType() != null, BizWaterMessageRecord::getMessageType, bizWaterMessageRecord.getMessageType());
        lqw.eq(StringUtils.isNotBlank(bizWaterMessageRecord.getMessageDesc()), BizWaterMessageRecord::getMessageDesc, bizWaterMessageRecord.getMessageDesc());
        lqw.eq(StringUtils.isNotBlank(bizWaterMessageRecord.getCustomParameters()), BizWaterMessageRecord::getCustomParameters, bizWaterMessageRecord.getCustomParameters());
        lqw.eq(StringUtils.isNotBlank(bizWaterMessageRecord.getContent()), BizWaterMessageRecord::getContent, bizWaterMessageRecord.getContent());
        lqw.eq(StringUtils.isNotBlank(bizWaterMessageRecord.getMessageSender()), BizWaterMessageRecord::getMessageSender, bizWaterMessageRecord.getMessageSender());
        lqw.eq(bizWaterMessageRecord.getCreateUserId() != null, BizWaterMessageRecord::getCreateUserId, bizWaterMessageRecord.getCreateUserId());
        lqw.eq(bizWaterMessageRecord.getDeptId() != null, BizWaterMessageRecord::getDeptId, bizWaterMessageRecord.getDeptId());
        lqw.like(StringUtils.isNotBlank(bizWaterMessageRecord.getDeptName()), BizWaterMessageRecord::getDeptName, bizWaterMessageRecord.getDeptName());
        return lqw;
    }

    /**
     * 新增业务消息流水
     *
     * @param reateReq 业务消息流水
     * @return 结果
     */
    @Override
    public Boolean createBizWaterMessageRecord(BizWaterMessageRecordCreateReq reateReq) {
        BizWaterMessageRecord bizWaterMessageRecord = BizWaterMessageRecordConvert.INSTANCE.convert(reateReq);
        validEntityBeforeSave(bizWaterMessageRecord);
        bizWaterMessageRecordMapper.insert(bizWaterMessageRecord);
        return bizWaterMessageRecord.getId() > 0;
    }

    /**
     * 修改业务消息流水
     *
     * @param updateReq 业务消息流水
     * @return 结果
     */
    @Override
    public Boolean updateBizWaterMessageRecord(BizWaterMessageRecordUpdateReq updateReq) {
        // 校验存在
        validateBizWaterMessageRecordExists(updateReq.getId());
        // 更新
        BizWaterMessageRecord updateObj = BizWaterMessageRecordConvert.INSTANCE.convert(updateReq);
        validEntityBeforeSave(updateObj);
        return bizWaterMessageRecordMapper.updateById(updateObj) > 0;
    }

    /**
     * 批量删除业务消息流水
     *
     * @param ids 需要删除的业务消息流水主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        return bizWaterMessageRecordMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过ids批量查询业务消息流水
     *
     * @param ids 需要查询的业务消息流水主键
     * @return 结果
     */
    @Override
    public List<BizWaterMessageRecordVO> getBizWaterMessageRecordList(Collection<Long> ids) {
        List<BizWaterMessageRecord> bizWaterMessageRecordLists = bizWaterMessageRecordMapper.selectBatchIds(ids);
        return BizWaterMessageRecordConvert.INSTANCE.convertList(bizWaterMessageRecordLists);
    }

    /**
     * 查询列表
     *
     * @param req
     * @return
     */
    @Override
    public AjaxResult<List<BizWaterMessageRecordVO>> waterList(BizWaterMessageRecordReq req) {
        LambdaQueryWrapperPlus<BizWaterMessageRecord> objectLambdaQueryWrapperPlus = new LambdaQueryWrapperPlus<>();
        objectLambdaQueryWrapperPlus.eq(BizWaterMessageRecord::getDelFlag, CommDelFlagEnum.NOT_DELETE.getType());
        List<Integer> messagesModuleType = WaterMessageTemplateEnum.getMessages(String.valueOf(req.getType()));
        objectLambdaQueryWrapperPlus.in(BizWaterMessageRecord::getModuleType, messagesModuleType);
        if (req.getType().equals(MessageConstants.MESSAGE_EDU_Type)) {
            objectLambdaQueryWrapperPlus.eq(BizWaterMessageRecord::getMessageType, 1);
        }
        objectLambdaQueryWrapperPlus.gt(BizWaterMessageRecord::getCreateTime, DateUtils.getMonthFirstDayDateTime());
        objectLambdaQueryWrapperPlus.lt(BizWaterMessageRecord::getCreateTime, DateUtils.getMonthLastDayDateTime());
        List<Long> safeCheckDeptId = SecurityUtils.getSafeCheckDeptId(req.getDeptId());
        objectLambdaQueryWrapperPlus.inIfPresent(BizWaterMessageRecord::getDeptId, safeCheckDeptId);
        objectLambdaQueryWrapperPlus.last(String.format("limit 0,%d", req.getTopN()));
        return AjaxResult.success(BizWaterMessageRecordConvert.INSTANCE.convertList(bizWaterMessageRecordMapper.selectList(objectLambdaQueryWrapperPlus)));
    }
}
