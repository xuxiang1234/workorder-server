package com.flyhigh.sms.domain.vo.pushErrorRecord;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 推送异常视图对象 biz_push_error_record
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Data
@ApiModel("推送异常视图对象")
@ExcelIgnoreUnannotated
public class BizPushErrorRecordVO {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ExcelProperty(value = "${comment}", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "$column.readConverterExp()")
    @ApiModelProperty("$column.columnComment")
    private Long id;

    /**
     * $column.columnComment
     */
    @ExcelProperty(value = "${comment}", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "$column.readConverterExp()")
    @ApiModelProperty("$column.columnComment")
    private Long messageId;

    /**
     * 消息发送人数量
     */
    @ExcelProperty(value = "消息发送人数量")
    @ApiModelProperty("消息发送人数量")
    private Long userNumber;

    /**
     * 重发次数
     */
    @ExcelProperty(value = "重发次数")
    @ApiModelProperty("重发次数")
    private Long tryTimes;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 是否删除 0 否 1 是
     */
    @ExcelProperty(value = "是否删除 0 否 1 是")
    @ApiModelProperty("是否删除 0 否 1 是")
    private Integer delFlag;

}
