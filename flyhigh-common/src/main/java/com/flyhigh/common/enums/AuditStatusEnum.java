package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 审批状态
 *
 * @author Mr.Lai
 * @date 2023/4/7
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {

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

    public static AuditStatusEnum getByStatus(String status){
        return Stream.of(AuditStatusEnum.values()).filter(item -> item.getStatus().equals(status)).findFirst().orElse(null);
    }
}
