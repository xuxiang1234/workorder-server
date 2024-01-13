package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除标记 0 否 1 是
 *
 * @author flyhigh
 */
@AllArgsConstructor
@Getter
public enum CommDelFlagEnum {

    /**
     * 未删除
     */
    NOT_DELETE(0),

    /**
     * 已删除
     */
    DELETED(1);


    private Integer type;

}