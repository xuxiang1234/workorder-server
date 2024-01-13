package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @ClassName FlowStatusEnum
 * @Description 审批流状态
 * @Author huangjinhui
 * @Date 2022/3/21 13:16
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum FlowStatusEnum {

    /**
     * 撤回审批
     */
    WITHDRAWAL_APPROVAL("0"),

    /**
     * 待审批
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

    public static FlowStatusEnum getByStatus(String status) {
        return Stream.of(FlowStatusEnum.values()).filter(item -> item.getStatus().equals(status)).findFirst().orElse(null);
    }
}
