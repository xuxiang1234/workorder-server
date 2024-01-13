package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DictStatusEnum {

    /**
     * 正常
     */
    RUN("0", "正常"),

    /**
     * 停用
     */
    STOP("1", "停用");


    private String status;

    private String desc;
}
