package com.flyhigh.system.domain.vo.sysUser;

import com.flyhigh.common.core.domain.entity.SysRole;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.system.domain.SysPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户详情信息vo
 *
 * @author Mr.Lai
 */
@Data
@ApiModel("用户详情信息vo")
public class UserDetailVo {

    /**
     * 角色集合
     */
    @ApiModelProperty("角色集合")
    private List<SysRole> roles;

    /**
     * 岗位列表
     */
    @ApiModelProperty("岗位列表")
    private List<SysPost> posts;

    /**
     * 用户
     */
    @ApiModelProperty("用户")
    private SysUser data;

    /**
     * 岗位ID集合
     */
    @ApiModelProperty("岗位ID集合")
    private List<Integer> postIds;

    /**
     * 角色ID集合
     */
    @ApiModelProperty("角色ID集合")
    private List<Long> roleIds;

}
