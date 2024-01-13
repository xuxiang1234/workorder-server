package com.flyhigh.system.domain.vo.sysUser;

import com.flyhigh.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 用户信息vo
 *
 * @author flyhigh
 * @date 2022-02-22
 */
@Data
@ApiModel("用户信息vo")
public class UserInfoVo {

    /**
     * 用户
     */
    @ApiModelProperty("用户")
    private SysUser user;

    /**
     * 角色集合
     */
    @ApiModelProperty("角色集合")
    private Set<String> roles;

    /**
     * 权限集合
     */
    @ApiModelProperty("权限集合")
    private Set<String> permissions;

    /**
     * 是否调度员
     */
    private Boolean whetherDispatcherUser;

}
