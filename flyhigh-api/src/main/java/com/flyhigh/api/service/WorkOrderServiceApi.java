package com.flyhigh.api.service;

import com.flyhigh.api.vo.workorder.*;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessHistoryVO;
import com.flyhigh.common.enums.MessageTemplateEnum;

import java.util.List;
import java.util.Map;

/**
 * 巡查工单
 *
 * @author Mr.Lai
 * @date 2023/2/23
 */
public interface WorkOrderServiceApi {
    /**
     * 开始工单
     *
     * @param id 工单ID
     * @return
     */
    Boolean startOrder(Long id);

    /**
     * 转换工单图片地址
     *
     * @param domain 请求域名
     * @param picUrl 图片地址
     */
    String convertWorkOrderPicUrl(String domain, String picUrl);

    /**
     * 创建工单
     *
     * @param createReq
     * @return
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
     * 获取工单历史
     *
     * @param id
     * @return
     */
    List<WorkOrderProcessHistoryVO> getHistoryInfo(Long id);

    /**
     * 查询工单列表
     *
     * @param workOrderIdList 工单ID列表
     * @return
     */
    List<WorkOrderVO> queryListByIds(List<Long> workOrderIdList);

    /**
     * 获取工单评分
     *
     * @param workOrderIds
     * @return
     */
    Map<Long, Integer> getWorkOrderScore(List<Long> workOrderIds);

    /**
     * 获取工单评分
     *
     * @param workOrderId
     * @return
     */
    Integer getWorkOrderScore(Long workOrderId);

    /**
     * 获取工单信息
     *
     * @param workOrderId
     * @return
     */
    WorkOrderVO getWorkOrder(Long workOrderId);

    /**
     * 工单评价
     *
     * @param evaluateReq
     * @return
     */
    Boolean evaluate(WorkOrderEvaluateReq evaluateReq);

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
