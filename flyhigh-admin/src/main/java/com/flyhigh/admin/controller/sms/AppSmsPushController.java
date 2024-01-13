package com.flyhigh.admin.controller.sms;

import com.flyhigh.api.dto.smsPush.BizPushBindReq;
import com.flyhigh.api.service.GtServiceApi;
import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName SmsPushController
 * @Description 个推控制器
 * @Author huangjinhui
 * @Date 2022/3/31 17:36
 * @Version 1.0
 */
@Validated
@Api(value = "app个推消息控制器", tags = {"app个推消息控制器"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/smsPush")
public class AppSmsPushController extends BaseController {

    @Autowired
    private GtServiceApi gtServiceApi;

    /**
     * 用户绑定cid
     */
    @ApiOperation("用户绑定cid")
    @Log(title = "用户绑定cid", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/bind")
    public AjaxResult bind(@Validated @RequestBody BizPushBindReq pushBindReq) {
        return gtServiceApi.bind(pushBindReq);
    }

}
