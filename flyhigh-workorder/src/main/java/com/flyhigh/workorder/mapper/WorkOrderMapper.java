package com.flyhigh.workorder.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flyhigh.api.vo.workorder.WorkOrderExportReq;
import com.flyhigh.api.vo.workorder.WorkOrderPageReq;
import com.flyhigh.api.vo.workorder.WorkOrderVO;
import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.common.core.query.LambdaQueryWrapperPlus;
import com.flyhigh.workorder.domain.entity.WorkOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 工单Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-17
 */
public interface WorkOrderMapper extends BaseMapperPlus<WorkOrderMapper, WorkOrder> {


    /**
     * 查询工单列表
     *
     * @param workOrder 工单
     * @return 工单集合
     */
    List<WorkOrder> selectWorkOrderList(Page page, @Param("workOrder") WorkOrder workOrder, @Param("pageReq") WorkOrderPageReq pageReq);


    /**
     * 查询工单列表
     * 未完成 = 我创建的+我处理过的
     *
     * @param page
     * @param userId
     * @return
     */
    List<WorkOrder> selectWorkOrderListByStatus(Page<WorkOrderVO> page, @Param("status") String status, @Param("userId") Long userId, @Param("pageReq") WorkOrderPageReq pageReq);

    /**
     * 修改工单
     *
     * @param workOrder 工单
     * @return 结果
     */
    int updateWorkOrder(WorkOrder workOrder);

    /**
     * 查询工单列表
     * 根据状态和时间且未评价
     *
     * @param status
     * @param startTime
     * @return
     */
    List<WorkOrder> selectListByStatusAndStartTime(@Param("status") String status, @Param("startTime") String startTime);

    /**
     * 导出列表查询
     *
     * @param req
     * @return
     */
    default List<WorkOrder> selectList(WorkOrderExportReq req) {
        return selectList(new LambdaQueryWrapperPlus<WorkOrder>()
                .inIfPresent(WorkOrder::getId, req.getWorkOrderIdList())
                .eqIfPresent(WorkOrder::getWorkOrderType, req.getWorkOrderType())
                .orderByDesc(WorkOrder::getId));
    }

}
