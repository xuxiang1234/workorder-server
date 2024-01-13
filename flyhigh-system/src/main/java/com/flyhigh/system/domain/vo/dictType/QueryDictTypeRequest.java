package com.flyhigh.system.domain.vo.dictType;

import com.flyhigh.common.annotation.Excel;
import com.flyhigh.common.core.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * 查询字典列表请求实体
 *
 * @author flyhigh
 */
@Data
@ApiModel("查询字典列表请求实体")
public class QueryDictTypeRequest extends PageQuery {

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    private String dictName;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 时间范围
     */
    private Map<String, String> params;
}
