package com.flyhigh.api.service;

/**
 * 文件上传
 *
 * @author Mr.Lai
 * @date 2023/3/22
 */
public interface UploadFileServiceApi {

    /**
     * 清空历史文件
     *
     * @param timeUnit 时间颗粒度
     * @param timeout  时间
     */
    void cleanUploadFile(String timeUnit, Integer timeout);

}
