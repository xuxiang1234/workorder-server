package com.flyhigh.workorder.service;


import com.flyhigh.api.vo.workorder.*;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessHistoryVO;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.workorder.domain.entity.WorkOrder;
import com.flyhigh.workorder.domain.entity.WorkOrderProcess;
import com.flyhigh.api.vo.workorder.WorkOrderEvaluateReq;
import com.flyhigh.workorder.domain.vo.workorder.WorkOrderEvaluateVO;
import com.flyhigh.workorder.domain.vo.workorder.WorkOrderHandleReq;

import java.util.List;
import java.util.Map;

/**
 * 工单Service接口
 *
 * @author flyhigh
 * @date 2022-05-17
 */
public interface IWorkOrderService {

    /**
     * 查询工单
     *
     * @param id 工单主键
     * @return 工单
     */
    WorkOrderVO queryById(Long id);

    /**
     * 获取工单历史
     *
     * @param id
     * @return
     */
    List<WorkOrderProcessHistoryVO> getHistoryInfo(Long id);

    /**
     * 查询工单列表
     *
     * @param pageVO 工单
     * @return 工单集合
     */
    TableDataInfo<WorkOrderVO> queryPageList(WorkOrderPageReq pageVO);

    /**
     * 导出工单列表
     *
     * @param exportReq
     * @return
     */
    List<WorkOrderVO> queryListVO(WorkOrderExportReq exportReq);

    /**
     * 导出工单列表
     *
     * @param exportReq
     * @return
     */
    List<WorkOrderExcelVO> queryList(WorkOrderExportReq exportReq);

    /**
     * 创建工单
     *
     * @param createReq 工单
     * @return 结果
     */
    WorkOrderVO createWorkOrder(WorkOrderCreateReq createReq);

    /**
     * 更新工单
     *
     * @param updateReq
     * @return
     */
    Boolean updateWorkOrder(WorkOrderUpdateReq updateReq);

    /**
     * 根据工单编号查询模板流程
     *
     * @param workOrderId
     * @return
     */
    List<WorkOrderProcess> queryWorkOrderProcessList(Long workOrderId);

    /**
     * 处理工单
     *
     * @param handleReq
     * @return
     */
    Boolean handle(WorkOrderHandleReq handleReq);

    /**
     * 工单评价
     *
     * @param evaluateReq
     * @return
     */
    Boolean evaluate(WorkOrderEvaluateReq evaluateReq);

    /**
     * Dashbord统计
     *
     * @return
     */
    WorkOrderEvaluateVO dashBoradSummary();

    /**
     * 工单确认
     *
     * @param workOrderId
     */
    Boolean autoOkWorkOrder(Long workOrderId);

    /**
     * 开启工单
     *
     * @param id
     * @return
     */
    Boolean start(Long id);

    /**
     * 发送工单消息
     *
     * @param workOrder 工单信息
     * @param templateEnum 消息类型
     * @param templateParam 参数
     */
    void sendWorkOrderMsg(WorkOrder workOrder, MessageTemplateEnum templateEnum, Map<String,String> templateParam);

    /**
     * 发送工单消息
     *
     * @param workOrderId 工单ID
     * @param userIds 用户ID
     * @param templateEnum 消息类型
     * @param templateParam 参数
     */
    void sendWorkOrderMsg(Long workOrderId, List<Long> userIds, MessageTemplateEnum templateEnum, Map<String,String> templateParam);

}
