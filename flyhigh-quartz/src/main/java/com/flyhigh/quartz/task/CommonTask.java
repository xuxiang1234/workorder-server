package com.flyhigh.quartz.task;

import com.flyhigh.api.service.UploadFileServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 公共任务
 *
 * @author Mr.Lai
 * @date 2023/3/22
 */
@Slf4j
@Component("commonTask")
public class CommonTask {

    @Autowired
    private UploadFileServiceApi uploadFileServiceApi;

    /**
     * 清空历史文件
     */
    @Async("asyncTaskExecutor")
    public void cleanUploadFile() {
        log.info("定时任务清空历史文件");
        uploadFileServiceApi.cleanUploadFile(null, null);
    }

    /**
     * 清空历史文件
     *
     * @param timeUnit 时间颗粒度
     * @param timeout  时间
     */
    @Async("asyncTaskExecutor")
    public void cleanUploadFile(String timeUnit, Integer timeout) {
        log.info("定时任务清空历史文件");
        uploadFileServiceApi.cleanUploadFile(timeUnit, timeout);
    }

}
