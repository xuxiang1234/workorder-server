package com.flyhigh.common.core.domain.entity;

import com.flyhigh.common.annotation.Excel;
import com.flyhigh.common.annotation.Excel.ColumnType;
import com.flyhigh.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 字典数据表 sys_dict_data
 *
 * @author flyhigh
 */
@Data
@ToString(callSuper = true)
public class SysDictData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @Excel(name = "字典编码", cellType = ColumnType.NUMERIC)
    private Long dictCode;

    /**
     * 字典排序
     */
    @Excel(name = "字典排序", cellType = ColumnType.NUMERIC)
    private Long dictSort;

    /**
     * 字典标签
     */
    @Excel(name = "字典标签")
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
    private String dictLabel;

    /**
     * 字典键值
     */
    @Excel(name = "字典键值")
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
    private String dictValue;

    /**
     * 字典类型
     */
    @Excel(name = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    @Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
    private String cssClass;

    /**
     * 表格字典样式
     */
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    @Excel(name = "是否默认", readConverterExp = "Y=是,N=否")
    private String isDefault;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 层级，第一级=1，每级递增
     */
    @Excel(name = "层级", readConverterExp = "第一级=1，每级递增")
    private Integer level;

    /**
     * 父级id
     */
    @Excel(name = "父级id")
    private Long parentDictCode;

    /**
     * 祖级id集合，以[,]隔开
     */
    @Excel(name = "祖级id集合", readConverterExp = "以[,]隔开")
    private String ancestorDictCodes;

}
