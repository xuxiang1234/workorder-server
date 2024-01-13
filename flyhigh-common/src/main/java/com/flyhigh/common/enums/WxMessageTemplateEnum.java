package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @ClassName MessageTemplateEnum
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 10:18
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum WxMessageTemplateEnum {

    /**
     * 代办事项-安全检查
     */
    AUTH_PROCESS_SUCCESS_FIRST("审核结果通过通知-标题", "auth_process_success_first"),

    /**
     * 审核结果不通过通知-标题
     */
    AUTH_PROCESS_FAILED_FIRST("审核结果不通过通知-标题", "auth_process_failed_first"),

    /**
     * 租金缴费通知-标题
     */
    RENT_PAY_FIRST("租金缴费通知-标题", "rent_pay_first"),

    /**
     * 租金缴费通知-说明
     */
    RENT_PAY_REMARK("租金缴费通知-说明", "rent_pay_remark"),

    /**
     * 账单支付提醒-标题
     */
    BILL_PAY_FIRST("账单支付提醒-标题", "bill_pay_first"),

    /**
     * 账单支付提醒-说明
     */
    BILL_PAY_REMARK("账单支付提醒-说明", "bill_pay_remark"),

    /**
     * 出账通知-标题
     */
    BILL_NOTICE_FIRST("出账通知-标题", "bill_notice_first"),

    /**
     * 出账通知-说明
     */
    BILL_NOTICE_REMARK("出账通知-说明", "bill_notice_remark"),

    /**
     * 退租待确认通知-标题
     */
    LEASE_NOTICE_FIRST("退租待确认通知-标题", "lease_notice_first"),

    /**
     * 退租待确认通知-说明
     */
    LEASE_NOTICE_REMARK("退租待确认通知-说明", "lease_notice_remark"),

    /**
     * 房租催缴通知-标题
     */
    LEASE_CALL_NOTICE_FIRST("房租催缴通知-标题", "lease_call_notice_first"),

    /**
     * 房租催缴通知-说明
     */
    LEASE_CALL_NOTICE_REMARK("房租催缴通知-说明", "lease_call_notice_remark"),

    ;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 字典value
     */
    private String dictValue;

}
