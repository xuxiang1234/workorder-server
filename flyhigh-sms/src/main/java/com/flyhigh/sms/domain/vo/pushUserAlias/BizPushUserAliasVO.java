package com.flyhigh.sms.domain.vo.pushUserAlias;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.flyhigh.common.annotation.ExcelDictFormat;
import com.flyhigh.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 用户个推别名视图对象 biz_push_user_alias
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Data
@ApiModel("用户个推别名视图对象")
@ExcelIgnoreUnannotated
public class BizPushUserAliasVO {

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
    private Long userId;

    /**
     * 用户别名
     */
    @ExcelProperty(value = "用户别名")
    @ApiModelProperty("用户别名")
    private String userAlias;

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
