package com.flyhigh.system.service;

import java.util.List;

/**
 * 通用 数据权限 服务
 */
public interface SysDataScopeService {

    /**
     * 获取角色自定义权限
     *
     * @param roleId 角色id
     * @return 部门id组
     */
    String getRoleCustom(Long roleId);

    /**
     * 获取部门及以下权限
     *
     * @param deptId 部门id
     * @return 部门id组
     */
    String getDeptAndChild(Long deptId);

    /**
     * 获取部门及以下权限
     *
     * @param deptId 部门id
     * @return 部门id组
     */
    List<String> getDeptAndChildList(Long deptId);

    /**
     * 获取角色部门ID
     *
     * @return 部门id组
     */
    List<String> getRoleDept();

    /**
     * 获取角色资产ID
     *
     * @return 部门id组
     */
    List<Long> getRoleAssetsIds();

}
