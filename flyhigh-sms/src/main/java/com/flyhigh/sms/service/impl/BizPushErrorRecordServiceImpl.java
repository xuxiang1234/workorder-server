package com.flyhigh.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.sms.domain.BizPushErrorRecord;
import com.flyhigh.sms.domain.convert.BizPushErrorRecordConvert;
import com.flyhigh.sms.domain.vo.pushErrorRecord.*;
import com.flyhigh.sms.mapper.BizPushErrorRecordMapper;
import com.flyhigh.sms.service.IBizPushErrorRecordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 推送异常Service业务层处理
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Service
@Validated
public class BizPushErrorRecordServiceImpl implements IBizPushErrorRecordService {

    @Resource
    private BizPushErrorRecordMapper bizPushErrorRecordMapper;

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizPushErrorRecord entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private void validateBizPushErrorRecordExists(Long id) {
        if (bizPushErrorRecordMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }
    }

    /**
     * 查询推送异常
     *
     * @param id 推送异常主键
     * @return 推送异常
     */
    @Override
    public BizPushErrorRecordVO queryById(Long id) {
        BizPushErrorRecord bizPushErrorRecord = bizPushErrorRecordMapper.selectById(id);
        return BizPushErrorRecordConvert.INSTANCE.convert(bizPushErrorRecord);
    }

    /**
     * 查询推送异常列表
     *
     * @param pageReq 推送异常
     * @return 推送异常
     */
    @Override
    public TableDataInfo<BizPushErrorRecordVO> queryPageList(BizPushErrorRecordPageReq pageReq) {
        TableDataInfo<BizPushErrorRecord> bizPushErrorRecordTableDataInfo = bizPushErrorRecordMapper.selectPage(pageReq);
        return BizPushErrorRecordConvert.INSTANCE.convertPage(bizPushErrorRecordTableDataInfo);
    }

    /**
     * 查询推送异常列表
     *
     * @param exportReq 推送异常
     * @return 推送异常
     */
    @Override
    public List<BizPushErrorRecordExcelVO> queryList(BizPushErrorRecordExportReq exportReq) {
        List<BizPushErrorRecord> bizPushErrorRecordLists = bizPushErrorRecordMapper.selectList(exportReq);
        return BizPushErrorRecordConvert.INSTANCE.convertListExcel(bizPushErrorRecordLists);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<BizPushErrorRecord> buildQueryWrapper(BizPushErrorRecord bizPushErrorRecord) {
        LambdaQueryWrapper<BizPushErrorRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bizPushErrorRecord.getMessageId() != null, BizPushErrorRecord::getMessageId, bizPushErrorRecord.getMessageId());
        lqw.eq(bizPushErrorRecord.getUserNumber() != null, BizPushErrorRecord::getUserNumber, bizPushErrorRecord.getUserNumber());
        lqw.eq(bizPushErrorRecord.getTryTimes() != null, BizPushErrorRecord::getTryTimes, bizPushErrorRecord.getTryTimes());
        return lqw;
    }

    /**
     * 新增推送异常
     *
     * @param reateReq 推送异常
     * @return 结果
     */
    @Override
    public Boolean createBizPushErrorRecord(BizPushErrorRecordCreateReq reateReq) {
        BizPushErrorRecord bizPushErrorRecord = BizPushErrorRecordConvert.INSTANCE.convert(reateReq);
        validEntityBeforeSave(bizPushErrorRecord);
        bizPushErrorRecordMapper.insert(bizPushErrorRecord);
        return bizPushErrorRecord.getId() > 0;
    }

    /**
     * 修改推送异常
     *
     * @param updateReq 推送异常
     * @return 结果
     */
    @Override
    public Boolean updateBizPushErrorRecord(BizPushErrorRecordUpdateReq updateReq) {
        // 校验存在
        validateBizPushErrorRecordExists(updateReq.getId());
        // 更新
        BizPushErrorRecord updateObj = BizPushErrorRecordConvert.INSTANCE.convert(updateReq);
        validEntityBeforeSave(updateObj);
        return bizPushErrorRecordMapper.updateById(updateObj) > 0;
    }

    /**
     * 批量删除推送异常
     *
     * @param ids 需要删除的推送异常主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        return bizPushErrorRecordMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过ids批量查询推送异常
     *
     * @param ids 需要查询的推送异常主键
     * @return 结果
     */
    @Override
    public List<BizPushErrorRecordVO> getBizPushErrorRecordList(Collection<Long> ids) {
        List<BizPushErrorRecord> bizPushErrorRecordLists = bizPushErrorRecordMapper.selectBatchIds(ids);
        return BizPushErrorRecordConvert.INSTANCE.convertList(bizPushErrorRecordLists);
    }
}
