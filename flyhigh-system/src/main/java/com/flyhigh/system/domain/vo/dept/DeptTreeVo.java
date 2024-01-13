package com.flyhigh.system.domain.vo.dept;

import com.flyhigh.common.core.domain.TreeSelect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色部门列表树vo
 *
 * @author Mr.Lai
 */
@Data
@ApiModel("角色部门列表树vo")
public class DeptTreeVo {

    /**
     * 部门列表
     */
    @ApiModelProperty("部门列表")
    private List<Integer> checkedKeys;

    /**
     * 下拉树结构列表
     */
    @ApiModelProperty("下拉树结构列表")
    private List<TreeSelect> depts;

}
