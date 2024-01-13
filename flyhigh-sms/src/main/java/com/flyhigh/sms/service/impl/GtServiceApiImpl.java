package com.flyhigh.sms.service.impl;

import com.flyhigh.api.dto.smsPush.BizPushBindReq;
import com.flyhigh.api.service.GtServiceApi;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.sms.service.GtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName GtServiceApiImpl
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/31 14:55
 * @Version 1.0
 */
@Service
@Slf4j
public class GtServiceApiImpl implements GtServiceApi {

    @Autowired
    private GtService gtService;

    /**
     * 用户绑定cid
     *
     * @param pushBindReq
     * @return
     */
    @Override
    public AjaxResult bind(BizPushBindReq pushBindReq) {
        return gtService.bind(pushBindReq);
    }

}
