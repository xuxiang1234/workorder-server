package com.flyhigh.sms.domain.vo.pushTask;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 推送任务视图对象 biz_push_task
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Data
@ApiModel("推送任务视图对象")
@ExcelIgnoreUnannotated
public class BizPushTaskVO {

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
     * 个推task_id
     */
    @ExcelProperty(value = "个推task_id")
    @ApiModelProperty("个推task_id")
    private String taskId;

    /**
     * 个推code
     */
    @ExcelProperty(value = "个推code")
    @ApiModelProperty("个推code")
    private String gtCode;

    /**
     * 消息发送人数量
     */
    @ExcelProperty(value = "消息发送人数量")
    @ApiModelProperty("消息发送人数量")
    private Long userNumber;

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
