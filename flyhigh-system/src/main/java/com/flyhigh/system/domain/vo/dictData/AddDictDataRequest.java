package com.flyhigh.system.domain.vo.dictData;

import com.flyhigh.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 新增字典类型请求实体
 *
 * @description
 * @author: flyhigh
 * @create: 2022-02-23 08:57
 **/
@Data
@ApiModel("新增字典请求实体")
public class AddDictDataRequest {

    /**
     * 字典排序
     */
    @ApiModelProperty("字典排序")
    private Long dictSort;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
    @ApiModelProperty("字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
    @ApiModelProperty("字典键值")
    private String dictValue;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    @ApiModelProperty("字典类型")
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    @Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
    @ApiModelProperty("样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 表格字典样式
     */
    @ApiModelProperty("表格字典样式")
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    @ApiModelProperty("是否默认（Y是 N否）")
    private String isDefault;

    /**
     * 父级字典id
     */
    @ApiModelProperty("父级字典id")
    private Long parentDictCode;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;


    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;


}
