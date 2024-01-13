package com.flyhigh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否是审批消息
 *
 * @ClassName MessageReceiveStatusEnum
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 13:56
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
public enum MessageApproveEnum {
    /**
     * 否
     */
    NO(0),
    /**
     * 是
     */
    YES(1);

    private Integer code;
}
