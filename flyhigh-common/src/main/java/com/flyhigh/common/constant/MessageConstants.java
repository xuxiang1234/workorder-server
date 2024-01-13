package com.flyhigh.common.constant;

/**
 * 消息相关常量
 *
 * @ClassName MessageConstants
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 15:08
 * @Version 1.0
 */
public interface MessageConstants {

    /**
     * 默认消息发送人
     */
    String DEFAULT_MESSAGE_SENDER = "系统消息";

    /**
     * 默认审批消息发送人
     */
    String DEFAULT_AUDIT_MESSAGE_SENDER = "系统提示";
    /**
     * 占位符替换未成功
     */
    String MESSAGE_TEMPLATE_PARSER_NULL = "null";

    /**
     * 教育基地
     */
    String MESSAGE_ADDRESS_CONTANTS = "教育基地";

    /**
     * 教育基地消息标记
     */
    String MESSAGE_EDU_FLAG = "edu";

    /**
     * 前端查询教育基地
     */
    Integer MESSAGE_EDU_Type = 2;
}
