package com.flyhigh.sms.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.sms.domain.BizPushTask;
import com.flyhigh.sms.domain.vo.pushTask.BizPushTaskExportReq;
import com.flyhigh.sms.domain.vo.pushTask.BizPushTaskPageReq;

import java.util.List;


/**
 * 推送任务Mapper接口
 *
 * @author flyhigh
 * @date 2022-03-31
 */
public interface BizPushTaskMapper extends BaseMapperPlus<BizPushTaskMapper, BizPushTask> {

    /**
     * 查询推送任务
     *
     * @param id 推送任务主键
     * @return 推送任务
     */
    BizPushTask selectBizPushTaskById(Long id);

    /**
     * 查询推送任务列表
     *
     * @param bizPushTask 推送任务
     * @return 推送任务集合
     */
    List<BizPushTask> selectBizPushTaskList(BizPushTask bizPushTask);

    /**
     * 新增推送任务
     *
     * @param bizPushTask 推送任务
     * @return 结果
     */
    int insertBizPushTask(BizPushTask bizPushTask);

    /**
     * 修改推送任务
     *
     * @param bizPushTask 推送任务
     * @return 结果
     */
    int updateBizPushTask(BizPushTask bizPushTask);

    /**
     * 删除推送任务
     *
     * @param id 推送任务主键
     * @return 结果
     */
    int deleteBizPushTaskById(Long id);

    /**
     * 批量删除推送任务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBizPushTaskByIds(Long[] ids);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    default TableDataInfo<BizPushTask> selectPage(BizPushTaskPageReq req) {
        return selectPage(req, new LambdaQueryWrapperPlus<BizPushTask>()
                .eqIfPresent(BizPushTask::getMessageId, req.getMessageId())
                .eqIfPresent(BizPushTask::getTaskId, req.getTaskId())
                .eqIfPresent(BizPushTask::getGtCode, req.getGtCode())
                .eqIfPresent(BizPushTask::getUserNumber, req.getUserNumber())
                .orderByDesc(BizPushTask::getId));
    }

    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<BizPushTask> selectList(BizPushTaskExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<BizPushTask>()
                .eqIfPresent(BizPushTask::getMessageId, req.getMessageId())
                .eqIfPresent(BizPushTask::getTaskId, req.getTaskId())
                .eqIfPresent(BizPushTask::getGtCode, req.getGtCode())
                .eqIfPresent(BizPushTask::getUserNumber, req.getUserNumber())
                .orderByDesc(BizPushTask::getId));
    }

}
