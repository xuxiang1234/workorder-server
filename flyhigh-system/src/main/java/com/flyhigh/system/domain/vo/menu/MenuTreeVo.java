package com.flyhigh.system.domain.vo.menu;

import com.flyhigh.common.core.domain.TreeSelect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色菜单列表树vo
 *
 * @author Mr.Lai
 */
@Data
@ApiModel("角色菜单列表树vo")
public class MenuTreeVo {

    /**
     * 部门列表
     */
    @ApiModelProperty("部门列表")
    private List<Long> checkedKeys;

    /**
     * 下拉树结构列表
     */
    @ApiModelProperty("下拉树结构列表")
    private List<TreeSelect> menus;

}
