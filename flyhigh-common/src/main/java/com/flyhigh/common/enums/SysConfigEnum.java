package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参数配置枚举
 *
 * @author Mr.Lai
 */
@AllArgsConstructor
@Getter
public enum SysConfigEnum {

    /**
     * 账号初始密码
     */
    INIT_PASSWORD("sys.user.initPassword", "账号初始密码"),

    /**
     * 验证码开关
     */
    CAPTCHA_ON_OFF("sys.account.captchaOnOff", "验证码开关"),

    /**
     * 是否开启用户注册功能
     */
    REGISTER_USER("sys.account.registerUser", "是否开启用户注册功能"),

    /**
     * 租赁账单出账日
     */
    BILL_PAY_DATE("sys.lease.billPayDate", "租赁账单出账日"),

    /**
     * 管理员角色
     */
    ROLE_ADMIN("sys.role.admin", "管理员角色"),

    /**
     * 租赁单元评分表单
     */
    OPERATIONAL_LEASE_UNIT_SCORE_FORM("operational.leaseUnit.scoreFormId", "租赁单元评分表单ID"),

    /**
     * 商户评分表单
     */
    OPERATIONAL_TENANT_SCORE_FORM("operational.tenant.scoreFormId", "商户评分表单ID"),

    ;

    private String key;

    private String desc;
}
