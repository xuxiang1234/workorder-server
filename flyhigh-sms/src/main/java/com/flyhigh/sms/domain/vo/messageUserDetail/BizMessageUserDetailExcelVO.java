package com.flyhigh.sms.domain.vo.messageUserDetail;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 消息接收人 Excel VO
 *
 * @author flyhigh
 */
@Data
public class BizMessageUserDetailExcelVO {

    @ExcelProperty("消息id")
    private Long messageId;

    @ExcelProperty("用户id")
    private Long userId;

    @ExcelProperty(" 状态 0未读 1已读")
    private Integer receiveStatus;

    @ExcelProperty("是否处理审批 0 否 1是")
    private Integer whetherApprove;

}
