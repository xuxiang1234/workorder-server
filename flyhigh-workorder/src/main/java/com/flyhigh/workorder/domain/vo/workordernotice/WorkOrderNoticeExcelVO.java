package com.flyhigh.workorder.domain.vo.workordernotice;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 工单消息通知 Excel VO
 *
 * @author flyhigh
 */
@Data
public class WorkOrderNoticeExcelVO {

    @ExcelProperty("工单ID")
    private Long workOrderId;

    @ExcelProperty("工单名称")
    private String workOrderName;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("是否已读")
    private Integer isRead;

}
