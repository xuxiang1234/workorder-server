package com.flyhigh.api.service;

import com.flyhigh.api.dto.smsPush.BizPushBindReq;
import com.flyhigh.common.core.domain.AjaxResult;

/**
 * @ClassName GtServiceApi
 * @Description 个推消息接口
 * @Author huangjinhui
 * @Date 2022/3/31 14:54
 * @Version 1.0
 */
public interface GtServiceApi {

    /**
     * 用户绑定cid
     *
     * @param pushBindReq
     * @return
     */
    AjaxResult bind(BizPushBindReq pushBindReq);

}
