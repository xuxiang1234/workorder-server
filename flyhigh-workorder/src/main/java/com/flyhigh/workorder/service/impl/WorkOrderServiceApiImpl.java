package com.flyhigh.workorder.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.flyhigh.api.service.WorkOrderServiceApi;
import com.flyhigh.api.vo.workorder.*;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessHistoryVO;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.workorder.domain.convert.WorkOrderConvert;
import com.flyhigh.workorder.domain.entity.WorkOrder;
import com.flyhigh.workorder.mapper.WorkOrderMapper;
import com.flyhigh.workorder.service.IWorkOrderService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.Lai
 * @date 2023/4/19
 */
@Slf4j
@Service
public class WorkOrderServiceApiImpl implements WorkOrderServiceApi {

    @Resource
    private WorkOrderMapper workOrderMapper;

    @Autowired
    private IWorkOrderService workOrderService;


    /**
     * 开始工单
     *
     * @param id 工单ID
     * @return
     */
    @Override
    public Boolean startOrder(Long id) {
        return workOrderService.start(id);
    }

    /**
     * 转换工单图片地址
     *
     * @param domain 请求域名
     * @param picUrl 图片地址
     */
    @Override
    public String convertWorkOrderPicUrl(String domain, String picUrl) {
        if (StringUtils.isBlank(picUrl)) {
            return StringUtil.EMPTY_STRING;
        }
        StringJoiner sj = new StringJoiner(",");

        Stream.of(picUrl.split(",")).forEach(item -> sj.add(domain + item));
        return sj.toString();
    }

    /**
     * 创建工单
     *
     * @param createReq
     * @return
     */
    @Override
    public WorkOrderVO createWorkOrder(WorkOrderCreateReq createReq) {
        return workOrderService.createWorkOrder(createReq);
    }

    /**
     * 更新工单
     *
     * @param updateReq
     * @return
     */
    @Override
    public Boolean updateWorkOrder(WorkOrderUpdateReq updateReq) {
        return workOrderService.updateWorkOrder(updateReq);
    }

    /**
     * 获取工单历史
     *
     * @param id
     * @return
     */
    @Override
    public List<WorkOrderProcessHistoryVO> getHistoryInfo(Long id) {
        return workOrderService.getHistoryInfo(id);
    }

    /**
     * 查询工单列表
     *
     * @param workOrderIdList 工单ID列表
     * @return
     */
    @Override
    public List<WorkOrderVO> queryListByIds(List<Long> workOrderIdList) {
        if (CollectionUtils.isEmpty(workOrderIdList)) {
            return Lists.newArrayList();
        }
        WorkOrderExportReq exportReq = new WorkOrderExportReq();
        exportReq.setWorkOrderIdList(workOrderIdList);
        List<WorkOrder> workOrders = workOrderMapper.selectList(exportReq);
        return WorkOrderConvert.INSTANCE.convertListExcelVO(workOrders);
    }

    /**
     * 获取工单评分
     *
     * @param workOrderIds
     * @return
     */
    @Override
    public Map<Long, Integer> getWorkOrderScore(List<Long> workOrderIds) {
        List<WorkOrderVO> workOrderVOList = this.queryListByIds(workOrderIds);
        return workOrderVOList.stream().filter(item -> null != item.getScore()).collect(Collectors.toMap(WorkOrderVO::getId, WorkOrderVO::getScore));
    }

    /**
     * 获取工单评分
     *
     * @param workOrderId
     * @return
     */
    @Override
    public Integer getWorkOrderScore(Long workOrderId) {
        WorkOrder workOrder = workOrderMapper.selectById(workOrderId);
        if (null == workOrder) {
            return 0;
        }
        return Optional.ofNullable(workOrder.getScore()).orElse(0);
    }

    /**
     * 获取工单信息
     *
     * @param workOrderId
     * @return
     */
    @Override
    public WorkOrderVO getWorkOrder(Long workOrderId) {
        WorkOrder workOrder = workOrderMapper.selectById(workOrderId);
        return WorkOrderConvert.INSTANCE.convert(workOrder);
    }

    /**
     * 工单评价
     *
     * @param evaluateReq
     * @return
     */
    @Override
    public Boolean evaluate(WorkOrderEvaluateReq evaluateReq) {
        return workOrderService.evaluate(evaluateReq);
    }

    /**
     * 发送工单消息
     *
     * @param workOrderId 工单ID
     * @param userIds 用户ID
     * @param templateEnum 消息类型
     * @param templateParam 参数
     */
    @Override
    public void sendWorkOrderMsg(Long workOrderId, List<Long> userIds, MessageTemplateEnum templateEnum, Map<String, String> templateParam) {
        workOrderService.sendWorkOrderMsg(workOrderId, userIds, templateEnum, templateParam);
    }

}
