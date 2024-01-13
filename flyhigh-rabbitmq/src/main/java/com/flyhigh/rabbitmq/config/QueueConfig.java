package com.flyhigh.rabbitmq.config;

import com.flyhigh.rabbitmq.base.ExchangeNameEnum;
import com.flyhigh.rabbitmq.base.QueueName;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Lai
 * @date 2023/4/24
 */
@Configuration
public class QueueConfig {

    /**
     * 创建工单
     * @return
     */
    @Bean
    public FanoutExchange fanoutWorkOrderCreateFanoutExchange() {
        return new FanoutExchange(ExchangeNameEnum.WORK_ORDER_CREATE.name());
    }

    /**
     * 完成工单
     * @return
     */
    @Bean
    public FanoutExchange fanoutWorkOrderCompleteFanoutExchange() {
        return new FanoutExchange(ExchangeNameEnum.WORK_ORDER_COMPLETE.name());
    }

    /**
     * 评价工单
     * @return
     */
    @Bean
    public FanoutExchange fanoutWorkOrderEvaluateFanoutExchange() {
        return new FanoutExchange(ExchangeNameEnum.WORK_ORDER_EVALUATE.name());
    }

    @Bean
    public Queue fanoutWorkOrderCreateQueue() {
        return new Queue(QueueName.FANOUT_WORK_ORDER_CREATE_QUEUE, true);
    }

    @Bean
    public Queue fanoutWorkOrderCompleteQueue() {
        return new Queue(QueueName.FANOUT_WORK_ORDER_COMPLETE_QUEUE, true);
    }

    @Bean
    public Queue fanoutWorkOrderEvaluateQueue() {
        return new Queue(QueueName.FANOUT_WORK_ORDER_EVALUATE_QUEUE, true);
    }

    @Bean
    public Binding workOrderCreateQueueBinding() {
        return BindingBuilder
                .bind(fanoutWorkOrderCreateQueue())
                .to(fanoutWorkOrderCreateFanoutExchange());
    }

    @Bean
    public Binding workOrderCompleteQueueBinding() {
        return BindingBuilder
                .bind(fanoutWorkOrderCompleteQueue())
                .to(fanoutWorkOrderCompleteFanoutExchange());
    }

    @Bean
    public Binding workOrderEvaluateQueueBinding() {
        return BindingBuilder
                .bind(fanoutWorkOrderEvaluateQueue())
                .to(fanoutWorkOrderEvaluateFanoutExchange());
    }

}
