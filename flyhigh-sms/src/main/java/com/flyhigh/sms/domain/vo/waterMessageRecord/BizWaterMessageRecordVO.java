package com.flyhigh.sms.domain.vo.waterMessageRecord;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 业务消息流水视图对象 biz_water_message_record
 *
 * @author flyhigh
 * @date 2022-04-21
 */
@Data
@ApiModel("业务消息流水视图对象")
@ExcelIgnoreUnannotated
public class BizWaterMessageRecordVO {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ExcelProperty(value = "${comment}", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "$column.readConverterExp()")
    @ApiModelProperty("$column.columnComment")
    private Long id;

    /**
     * 消息内容
     */
    @ExcelProperty(value = "消息内容")
    @ApiModelProperty("消息内容")
    private String content;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Date createTime;

    /**
     * 模块描述
     */
    @ApiModelProperty("模块描述")
    private String moduleTypeDesc;

    /**
     * 模块类型
     */
    @ApiModelProperty("模块类型")
    private Long moduleType;

}
