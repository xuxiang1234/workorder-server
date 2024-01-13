package com.flyhigh.common.core.domain.model;

import lombok.Data;

/**
 * 用户注册对象
 *
 * @author flyhigh
 */
@Data
public class RegisterBody extends LoginBody {

    /**
     * 平台：app （不为空则取消验证码）
     */
    private String platform;

}
