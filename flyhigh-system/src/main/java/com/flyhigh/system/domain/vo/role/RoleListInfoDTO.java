package com.flyhigh.system.domain.vo.role;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName RoleListInfoDTO
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/18 13:36
 * @Version 1.0
 */
@ApiModel("用户角色列表")
@Data
public class RoleListInfoDTO {

    /**
     * 用户角色名称
     */
    private String roleNames;

    /**
     * 用户角色id
     */
    private String roleIds;

    /**
     * 用户id
     */
    private Long userId;
}
