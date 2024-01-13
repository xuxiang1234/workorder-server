package com.flyhigh.workorder.mapper;

import com.flyhigh.common.core.mapper.BaseMapperPlus;
import com.flyhigh.workorder.domain.entity.WorkOrderProcess;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 工单流程Mapper接口
 *
 * @author flyhigh
 * @date 2022-05-17
 */
public interface WorkOrderProcessMapper extends BaseMapperPlus<WorkOrderProcessMapper, WorkOrderProcess> {

    /**
     * 查询工单流程列表
     *
     * @param workOrderId 工单ID
     * @return 工单流程集合
     */
    List<WorkOrderProcess> selectWorkOrderProcessListByWorkOrderId(Long workOrderId);

    /**
     * 修改工单流程
     *
     * @param workOrderProcess 工单流程
     * @return 结果
     */
    int updateWorkOrderProcess(WorkOrderProcess workOrderProcess);


    /**
     * 根据流程索引删除流程
     *
     * @param nowIndex 流程索引
     * @return 结果
     */
    int deleteWorkOrderProcessByProcessIndex(@Param("workOrderId") Long workOrderId, @Param("nowIndex") Integer nowIndex);

    /**
     * 更新ProcessIndex
     *
     * @param workOrderId
     * @param nowIndex
     * @param addNumber
     */
    void updataProcessIndex(@Param("workOrderId") Long workOrderId, @Param("nowIndex") Integer nowIndex, @Param("addNumber") Integer addNumber);

    /**
     * 查询可以自动确认的工单
     *
     * @param createTime
     * @return
     */
    List<Long> selectAutoOkWorkOrderList(@Param("status") String status, @Param("createTime") String createTime);
}
