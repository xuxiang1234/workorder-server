package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName MessageTemplateEnum
 * @Description 消息模板
 * @Author huangjinhui
 * @Date 2022/3/15 10:18
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum MessageTemplateEnum {

    /**
     * 系统消息
     */
    NOTICE_SYSTEM_INFO("系统消息-系统提醒", "notice_system_info", "1", "1"),

    /**
     * 合同到期
     */
    LEASING_CONTRACT_EXPIRE("合同提醒-合同到期", "leasing_contract_expire", "2", "2"),

    /**
     * 缴费提醒-租金
     */
    NOW_PAYMENT_RENTAL("缴费提醒-租金", "now_payment_rental", "3", "3"),

    /**
     * 缴费提醒-押金
     */
    NOW_PAYMENT_DEPOSIT("缴费提醒-押金", "now_payment_deposit", "4", "3"),

    /**
     * 缴费提醒-水电
     */
    NOW_PAYMENT_WATER_ELECTRIC("缴费提醒-水电", "now_payment_water_electric", "5", "3"),

    /**
     * 缴费提醒-退租
     */
    NOW_PAYMENT_TERMINATE("缴费提醒-退租", "now_payment_terminate", "6", "3"),

    /**
     * 开票提醒-开票提醒
     */
    INVOICING_INFO("开票提醒-开票提醒", "invoicing_info", "7", "4"),

    /**
     * 退租提醒-退租信息
     */
    TERMINATE_INFO("退租提醒-退租流程提醒", "terminate_info", "8", "5"),

    /**
     * 巡检提醒-日常巡查
     */
    PATROL_WORK_ORDER("巡检提醒-日常巡查", "patrol_work_order", "9", "6"),

    /**
     * 维修提醒-维修申请
     * 待审核提醒
     */
    REPAIR_WORK_ORDER_TREATY("维修提醒-工单待审核", "repair_work_order_treaty", "10", "7"),

    /**
     * 维修提醒-维修申请
     * 工单完成提醒提醒
     */
    REPAIR_WORK_ORDER_OK("维修提醒-工单待审核", "repair_work_order_ok", "11", "7"),

    /**
     * 维修提醒-维修申请
     * 开始工单
     */
    REPAIR_WORK_ORDER_START("维修提醒-开始工单", "repair_work_order_start", "12", "7"),

    /**
     * 维修提醒-维修申请
     * 工单审批结束
     */
    REPAIR_WORK_ORDER_TREATY_END("维修提醒-工单审批结束", "repair_work_order_treaty_end", "13", "7"),

    /**
     * 退租提醒-退租流程
     * 退租审核
     */
    TERMINATE_INFO_CREATE("退租提醒-退租流程-退租审核", "terminate_info_create", "14", "5"),

    /**
     * 退租提醒-退租流程
     * 退租审核结果
     */
    TERMINATE_INFO_CHECK_APPROVAL("退租提醒-退租流程-退租审核结果", "terminate_info_check_approval", "15", "5"),

    /**
     * 退租提醒-退租流程
     * 生成费用清单
     */
    TERMINATE_INFO_EXPENSE_LIST("退租提醒-退租流程-生成费用清单", "terminate_info_expense_list", "16", "5"),

    /**
     * 退租提醒-退租流程
     * 账单审批
     */
    TERMINATE_INFO_BILL_APPROVE("退租提醒-退租流程-账单审批", "terminate_info_bill_approve", "17", "5"),

    /**
     * 退租提醒-退租流程
     * 账单审批结束
     */
    TERMINATE_INFO_BILL_APPROVE_AUDIT("退租提醒-退租流程-账单审批结束", "terminate_info_bill_approve_audit", "18", "5"),

    /**
     * 退租提醒-退租流程
     * 账单审批通过
     */
    TERMINATE_INFO_BILL_APPROVE_AUDIT_PASSED("退租提醒-退租流程-账单审批通过", "terminate_info_bill_approve_audit_passed", "19", "5"),

    /**
     * 抄表提醒
     */
    METER_READING_NOTICE("抄表提醒", "meter_reading_notice", "20", "8"),

    /**
     * 缴费提醒-物业费
     */
    NOW_PAYMENT_PROPERTY("缴费提醒-物业费", "now_payment_property", "21", "3"),

    /**
     * 保养提醒-保养管理
     */
    UPKEEP_WORK_ORDER_OK("保养提醒-保养管理", "upkeep_work_order_ok", "22", "9"),

    ;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 字典value
     */
    private String dictValue;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 1任务提醒 2 审核事项提醒 3 发文 4 通知消息
     */
    private String messageClassify;

    /**
     * 根据数字类型换取
     *
     * @param type
     * @return
     */
    public static MessageTemplateEnum getMessageByType(String type) {
        return Stream.of(MessageTemplateEnum.values()).filter(template -> template.type.equals(type)).findFirst().orElse(null);
    }

    /**
     * 根据数字类型换取中文类型
     *
     * @param type
     * @return
     */
    public static String getMessageType(String type) {
        return Stream.of(MessageTemplateEnum.values()).filter(template -> template.type.equals(type)).map(item -> item.getName().split("-")[0]).findFirst().orElse(null);
    }

    /**
     * 根据数字类型换取中文类型
     *
     * @param dictValue
     * @return
     */
    public static String getMessageByValue(String dictValue) {
        return Stream.of(MessageTemplateEnum.values()).filter(template -> template.dictValue.equals(dictValue)).map(item -> item.getName().split("-")[0]).findFirst().orElse(null);
    }

    /**
     * 获取消息分类
     *
     * @param type
     * @return
     */
    public static String getMessageClassify(String type) {
        return Stream.of(MessageTemplateEnum.values()).filter(template -> template.type.equals(type)).map(MessageTemplateEnum::getMessageClassify).findFirst().orElse(null);
    }

}
