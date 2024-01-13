package com.flyhigh.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.sms.domain.BizPushTask;
import com.flyhigh.sms.domain.convert.BizPushTaskConvert;
import com.flyhigh.sms.domain.vo.pushTask.*;
import com.flyhigh.sms.mapper.BizPushTaskMapper;
import com.flyhigh.sms.service.IBizPushTaskService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 推送任务Service业务层处理
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Service
@Validated
public class BizPushTaskServiceImpl implements IBizPushTaskService {

    @Resource
    private BizPushTaskMapper bizPushTaskMapper;

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(BizPushTask entity) {
        // 做一些数据校验,如唯一约束
    }

    /**
     * 校验是否存在
     */
    private void validateBizPushTaskExists(Long id) {
        if (bizPushTaskMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }
    }

    /**
     * 查询推送任务
     *
     * @param id 推送任务主键
     * @return 推送任务
     */
    @Override
    public BizPushTaskVO queryById(Long id) {
        BizPushTask bizPushTask = bizPushTaskMapper.selectById(id);
        return BizPushTaskConvert.INSTANCE.convert(bizPushTask);
    }

    /**
     * 查询推送任务列表
     *
     * @param pageReq 推送任务
     * @return 推送任务
     */
    @Override
    public TableDataInfo<BizPushTaskVO> queryPageList(BizPushTaskPageReq pageReq) {
        TableDataInfo<BizPushTask> bizPushTaskTableDataInfo = bizPushTaskMapper.selectPage(pageReq);
        return BizPushTaskConvert.INSTANCE.convertPage(bizPushTaskTableDataInfo);
    }

    /**
     * 查询推送任务列表
     *
     * @param exportReq 推送任务
     * @return 推送任务
     */
    @Override
    public List<BizPushTaskExcelVO> queryList(BizPushTaskExportReq exportReq) {
        List<BizPushTask> bizPushTaskLists = bizPushTaskMapper.selectList(exportReq);
        return BizPushTaskConvert.INSTANCE.convertListExcel(bizPushTaskLists);
    }

    /**
     * 构建原生mybatis-plus 查询wrapper
     *
     * @return
     */
    private LambdaQueryWrapper<BizPushTask> buildQueryWrapper(BizPushTask bizPushTask) {
        LambdaQueryWrapper<BizPushTask> lqw = Wrappers.lambdaQuery();
        lqw.eq(bizPushTask.getMessageId() != null, BizPushTask::getMessageId, bizPushTask.getMessageId());
        lqw.eq(StringUtils.isNotBlank(bizPushTask.getTaskId()), BizPushTask::getTaskId, bizPushTask.getTaskId());
        lqw.eq(StringUtils.isNotBlank(bizPushTask.getGtCode()), BizPushTask::getGtCode, bizPushTask.getGtCode());
        lqw.eq(bizPushTask.getUserNumber() != null, BizPushTask::getUserNumber, bizPushTask.getUserNumber());
        return lqw;
    }

    /**
     * 新增推送任务
     *
     * @param reateReq 推送任务
     * @return 结果
     */
    @Override
    public Boolean createBizPushTask(BizPushTaskCreateReq reateReq) {
        BizPushTask bizPushTask = BizPushTaskConvert.INSTANCE.convert(reateReq);
        validEntityBeforeSave(bizPushTask);
        bizPushTaskMapper.insert(bizPushTask);
        return bizPushTask.getId() > 0;
    }

    /**
     * 修改推送任务
     *
     * @param updateReq 推送任务
     * @return 结果
     */
    @Override
    public Boolean updateBizPushTask(BizPushTaskUpdateReq updateReq) {
        // 校验存在
        validateBizPushTaskExists(updateReq.getId());
        // 更新
        BizPushTask updateObj = BizPushTaskConvert.INSTANCE.convert(updateReq);
        validEntityBeforeSave(updateObj);
        return bizPushTaskMapper.updateById(updateObj) > 0;
    }

    /**
     * 批量删除推送任务
     *
     * @param ids 需要删除的推送任务主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        return bizPushTaskMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过ids批量查询推送任务
     *
     * @param ids 需要查询的推送任务主键
     * @return 结果
     */
    @Override
    public List<BizPushTaskVO> getBizPushTaskList(Collection<Long> ids) {
        List<BizPushTask> bizPushTaskLists = bizPushTaskMapper.selectBatchIds(ids);
        return BizPushTaskConvert.INSTANCE.convertList(bizPushTaskLists);
    }
}
