package com.flyhigh.system.domain.vo.dictType;

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
@ApiModel("新增字典类型请求实体")
@Data
public class ModifyDictTypeRequest {

    /**
     * 字典主键，新增场景不传，编辑场景必传
     */
    @ApiModelProperty("字典主键，新增场景不传，编辑场景必传")
    private Long dictId;

    /**
     * 字典名称
     */
    @ApiModelProperty("字典名称")
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty("字典类型")
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    private String remark;

}
