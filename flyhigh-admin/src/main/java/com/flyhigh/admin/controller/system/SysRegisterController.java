package com.flyhigh.admin.controller.system;

import com.flyhigh.common.enums.SysConfigEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.domain.model.RegisterBody;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.framework.web.service.SysRegisterService;
import com.flyhigh.system.service.ISysConfigService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 注册验证
 *
 * @author flyhigh
 */
@ApiIgnore
@RestController
public class SysRegisterController extends BaseController {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey(SysConfigEnum.REGISTER_USER.getKey())))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
