package com.flyhigh.sms.domain.vo.waterMessageRecord;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 业务消息流水 Excel VO
 *
 * @author flyhigh
 */
@Data
public class BizWaterMessageRecordExcelVO {

    @ExcelProperty("模块描述")
    private String moduleTypeDesc;

    @ExcelProperty("模块类型")
    private Long moduleType;

    @ExcelProperty("业务主键id")
    private Long businessId;

    @ExcelProperty("消息类型")
    private Long messageType;

    @ExcelProperty("消息类型描述，防止一个模块多个消息类型")
    private String messageDesc;

    @ExcelProperty("自定义参数")
    private String customParameters;

    @ExcelProperty("消息内容")
    private String content;

    @ExcelProperty("消息发送者")
    private String messageSender;

    @ExcelProperty("创建人id")
    private Long createUserId;

    @ExcelProperty("部门id")
    private Long deptId;

    @ExcelProperty("部门名称")
    private String deptName;

    @ExcelProperty("备注")
    private String remark;

}
