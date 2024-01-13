package com.flyhigh.sms.domain.vo.pushTask;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 推送任务 Excel VO
 *
 * @author flyhigh
 */
@Data
public class BizPushTaskExcelVO {

    @ExcelProperty("${column.columnComment}")
    private Long messageId;

    @ExcelProperty("个推task_id")
    private String taskId;

    @ExcelProperty("个推code")
    private String gtCode;

    @ExcelProperty("消息发送人数量")
    private Long userNumber;

    @ExcelProperty("备注")
    private String remark;

}
