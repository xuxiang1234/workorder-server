package com.flyhigh.api.vo.element;

import com.flyhigh.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@ApiModel("表单元素新增接口 Request")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormElementCreateReq extends FormElementBaseReq {

    private List<String> values;

    @ApiModelProperty(value = "元素值字符串", required = true)
    private String valueString;

    public void setValueString(String valueString) {
        this.valueString = valueString;
        if (StringUtils.isNotBlank(valueString)) {
            this.values = Arrays.asList(valueString.split("%x%"));
        }
    }
}
