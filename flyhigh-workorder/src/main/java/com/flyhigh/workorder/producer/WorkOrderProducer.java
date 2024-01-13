package com.flyhigh.workorder.producer;
import com.flyhigh.api.vo.workorder.WorkOrderEvaluateReq;
import com.flyhigh.api.vo.workorder.WorkOrderVO;
import com.flyhigh.rabbitmq.base.ExchangeNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Mr.Lai
 * @date 2023/4/24
 */
@Slf4j
@Component
public class WorkOrderProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 创建工单
     *
     * @param workOrderVO
     */
    public void sendCreateWorkOrder(WorkOrderVO workOrderVO) {
        log.debug("工单系统-创建工单 {}", workOrderVO.getId());
        this.rabbitTemplate.convertAndSend(ExchangeNameEnum.WORK_ORDER_CREATE.name(), "", workOrderVO);
    }


    /**
     * 评价工单
     *
     * @param workOrderEvaluateReq
     */
    public void sendEvaluateWorkOrder(WorkOrderEvaluateReq workOrderEvaluateReq) {
        log.debug("工单系统-评价工单 {}", workOrderEvaluateReq.getWorkOrderId());
        this.rabbitTemplate.convertAndSend(ExchangeNameEnum.WORK_ORDER_EVALUATE.name(), "", workOrderEvaluateReq);
    }

}
