package com.flyhigh.sms.domain.vo.messageInfo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 消息 Excel VO
 *
 * @author flyhigh
 */
@Data
public class BizMessageInfoExcelVO {

    @ExcelProperty("模块")
    private String moduleType;

    @ExcelProperty("业务主键id")
    private Long businessId;

    @ExcelProperty("消息类型")
    private String messageType;

    @ExcelProperty("组件路径")
    private String component;

    @ExcelProperty("自定义参数")
    private String customParameters;

    @ExcelProperty("消息内容")
    private String content;

    @ExcelProperty("消息发送者")
    private String messageSender;

    @ExcelProperty("备注")
    private String remark;

}
