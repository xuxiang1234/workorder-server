package com.flyhigh.workorder.mapper;

import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.workorder.domain.entity.WorkOrderNotice;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeExportReq;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticePageReq;

import java.util.List;


/**
 * 工单消息通知Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-21
 */
public interface WorkOrderNoticeMapper extends BaseMapperPlus<WorkOrderNoticeMapper, WorkOrderNotice> {

    /**
     * 查询工单消息通知
     *
     * @param id 工单消息通知主键
     * @return 工单消息通知
     */
    WorkOrderNotice selectWorkOrderNoticeById(Long id);

    /**
     * 查询工单消息通知列表
     *
     * @param workOrderNotice 工单消息通知
     * @return 工单消息通知集合
     */
    List<WorkOrderNotice> selectWorkOrderNoticeList(WorkOrderNotice workOrderNotice);

    /**
     * 新增工单消息通知
     *
     * @param workOrderNotice 工单消息通知
     * @return 结果
     */
    int insertWorkOrderNotice(WorkOrderNotice workOrderNotice);

    /**
     * 修改工单消息通知
     *
     * @param workOrderNotice 工单消息通知
     * @return 结果
     */
    int updateWorkOrderNotice(WorkOrderNotice workOrderNotice);

    /**
     * 删除工单消息通知
     *
     * @param id 工单消息通知主键
     * @return 结果
     */
    int deleteWorkOrderNoticeById(Long id);

    /**
     * 批量删除工单消息通知
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteWorkOrderNoticeByIds(Long[] ids);

    /**
     * 获取工单消息列表
     *
     * @param req
     * @return
     */
    List<WorkOrderNotice> selectList(WorkOrderNoticePageReq req);

    /**
    * 分页查询
     * @param req
     * @return
     */
    default TableDataInfo<WorkOrderNotice> selectPage(WorkOrderNoticePageReq req) {
        LoginUser loginUser = SecurityUtils.getLoginUser();

        return selectPage(req, new LambdaQueryWrapperPlus<WorkOrderNotice>()
        .eqIfPresent(WorkOrderNotice::getWorkOrderId, req.getWorkOrderId())
        .likeIfPresent(WorkOrderNotice::getWorkOrderName, req.getWorkOrderName())
        .eq(WorkOrderNotice::getUserId, loginUser.getUserId())
        .eqIfPresent(WorkOrderNotice::getIsRead, req.getIsRead())
                .orderByDesc(WorkOrderNotice::getId));
    }

    /**
     * 导出列表查询
     * @param req
     * @return
     */
    default List<WorkOrderNotice> selectList(WorkOrderNoticeExportReq req) {
        LoginUser loginUser = SecurityUtils.getLoginUser();

        return selectList(new LambdaQueryWrapperPlus<WorkOrderNotice>()
        .eqIfPresent(WorkOrderNotice::getWorkOrderId, req.getWorkOrderId())
        .likeIfPresent(WorkOrderNotice::getWorkOrderName, req.getWorkOrderName())
        .eq(WorkOrderNotice::getUserId, loginUser.getUserId())
        .eqIfPresent(WorkOrderNotice::getIsRead, req.getIsRead())
                .orderByDesc(WorkOrderNotice::getId));
    }

}
