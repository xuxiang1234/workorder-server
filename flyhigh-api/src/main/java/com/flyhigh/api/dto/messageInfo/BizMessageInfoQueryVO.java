package com.flyhigh.api.dto.messageInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName BizMessageInfoQueryVO
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 17:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class BizMessageInfoQueryVO {

    /**
     * 业务id
     */
    private Long businessId;

    /**
     * 已读数量
     */
    private Long readNumber;

}
