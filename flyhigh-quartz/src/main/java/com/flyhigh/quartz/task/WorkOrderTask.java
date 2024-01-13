package com.flyhigh.quartz.task;

import com.flyhigh.api.service.WorkOrderServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 工单任务
 *
 * @author Mr.Lai
 * @date 2023/4/19
 */
@Slf4j
@Component("workOrderTask")
public class WorkOrderTask {

    @Autowired
    private WorkOrderServiceApi workOrderServiceApi;

    /**
     * 开始工单
     */
    @Async("asyncTaskExecutor")
    public void startWorkOrder() {
        log.info("定时任务开始工单");
    }

}
