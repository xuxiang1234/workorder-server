package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName FlowNodeStatusEnum
 * @Description 审批流状态
 * @Author huangjinhui
 * @Date 2022/3/21 13:16
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum FlowNodeStatusEnum {
    /**
     * 流转中
     */
    IN_CIRCULATION("0"),
    /**
     * 待该用户审核
     */
    PENDING_APPROVAL("1"),

    /**
     * 审批通过
     */
    APPROVED("2"),

    /**
     * 审批驳回
     */
    APPROVAL_REJECTION("3");

    private String status;
}
