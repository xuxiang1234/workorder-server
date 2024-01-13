package com.flyhigh.system.domain.vo.sysUser;

import com.flyhigh.common.core.domain.entity.SysRole;
import com.flyhigh.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户授权角色vo
 *
 * @author Mr.Lai
 */
@Data
@ApiModel("用户授权角色vo")
public class UserAuthRoleVo {

    /**
     * 岗位列表
     */
    @ApiModelProperty("岗位列表")
    private SysUser user;

    /**
     * 角色集合
     */
    @ApiModelProperty("角色集合")
    private List<SysRole> roles;

}
