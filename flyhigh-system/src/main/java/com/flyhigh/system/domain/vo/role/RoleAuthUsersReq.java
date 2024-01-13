package com.flyhigh.system.domain.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 角色授权用户
 */
@ApiModel("角色授权用户 Request")
@Data
public class RoleAuthUsersReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    @ApiModelProperty(value = "角色ID", example = "1024", required = true)
    private Long roleId;

    /**
     * 用户ID集合
     */
    @NotEmpty(message = "用户Ids不能为空")
    @ApiModelProperty(value = "用户Ids集合", example = "[1,2,3]", required = true)
    private Long[] userIds;
}
