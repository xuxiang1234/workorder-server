package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CheckSubTypeEnum {

    /**
     * 检查清单
     */
    CHECK_LIST((short) 1),

    /**
     * 检查附件
     */
    CHECK_DATA((short) 2),

    /**
     * 新建检查的文件
     */
    CHECK_FILE((short) 3),

    /**
     * 整改记录相关文件
     */
    RECTIFICATION_FILE((short) 4);

    private short code;
}
