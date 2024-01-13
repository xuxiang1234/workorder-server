package com.flyhigh.sms.service;

import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.sms.domain.BizMessageInfo;
import com.flyhigh.api.dto.smsPush.BizPushBindReq;

import java.util.List;

/**
 * @ClassName GtService
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/31 18:45
 * @Version 1.0
 */
public interface GtService {

    /**
     * 发送消息
     *
     * @param bizMessageInfo
     * @param userIds
     */
    void sendAppMsg(BizMessageInfo bizMessageInfo, List<Long> userIds);

    /**
     * 绑定用户
     *
     * @param pushBindReq
     * @return
     */
    AjaxResult bind(BizPushBindReq pushBindReq);

}
