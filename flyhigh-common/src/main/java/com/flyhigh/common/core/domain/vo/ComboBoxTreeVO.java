package com.flyhigh.common.core.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 下拉树列表VO
 *
 * @author Mr.Lai
 * @date 2022/12/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("下拉树列表VO")
public class ComboBoxTreeVO extends ComboBoxVO {

    /**
     * 是否禁用
     */
    @ApiModelProperty(value = "是否禁用")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean disabled;

    /**
     * 子节点
     */
    @ApiModelProperty(value = "子节点")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ComboBoxTreeVO> children;

    public ComboBoxTreeVO(String value, String label) {
        super.setValue(value);
        super.setLabel(label);
    }

    public ComboBoxTreeVO(String value, String label, List<ComboBoxTreeVO> children) {
        super.setValue(value);
        super.setLabel(label);
        this.children = children;
    }

}
