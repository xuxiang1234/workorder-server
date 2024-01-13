package com.flyhigh.sms.domain.vo.pushErrorRecord;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 推送异常 Excel VO
 *
 * @author flyhigh
 */
@Data
public class BizPushErrorRecordExcelVO {

    @ExcelProperty("${column.columnComment}")
    private Long messageId;

    @ExcelProperty("消息发送人数量")
    private Long userNumber;

    @ExcelProperty("重发次数")
    private Long tryTimes;

    @ExcelProperty("备注")
    private String remark;

}
