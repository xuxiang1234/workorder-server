package com.flyhigh.sms.domain.vo.messageInfo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 消息视图对象 biz_message_info
 *
 * @author flyhigh
 * @date 2022-03-13
 */
@Data
@ApiModel("消息视图对象")
@ExcelIgnoreUnannotated
public class BizMessageInfoVO {

    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ExcelProperty(value = "${comment}", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "$column.readConverterExp()")
    @ApiModelProperty("$column.columnComment")
    private Long id;

    /**
     * 模块
     */
    @ExcelProperty(value = "模块")
    @ApiModelProperty("模块")
    private String moduleType;

    /**
     * 业务主键id
     */
    @ExcelProperty(value = "业务主键id")
    @ApiModelProperty("业务主键id")
    private Long businessId;

    /**
     * 消息类型
     */
    @ExcelProperty(value = "消息类型")
    @ApiModelProperty("消息类型")
    private String messageType;

    /**
     * 组件路径
     */
    @ExcelProperty(value = "组件路径")
    @ApiModelProperty("组件路径")
    private String component;

    /**
     * 自定义参数
     */
    @ExcelProperty(value = "自定义参数")
    @ApiModelProperty("自定义参数")
    private String customParameters;

    /**
     * 消息内容
     */
    @ExcelProperty(value = "消息内容")
    @ApiModelProperty("消息内容")
    private String content;

    /**
     * 消息发送者
     */
    @ExcelProperty(value = "消息发送者")
    @ApiModelProperty("消息发送者")
    private String messageSender;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    @ApiModelProperty("更新者")
    private String updateBy;

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
