package com.flyhigh.system.service;

import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.system.domain.vo.sysUser.UserProfileVo;

/**
 * 个人信息服务
 */
public interface ISysProfileService {

    /**
     * 获取当前登录人信息
     *
     * @return
     */
    UserProfileVo profile();

    /**
     * 修改信息
     *
     * @param user
     */
    boolean updateProfile(SysUser user);

    /**
     * 重置密码
     *
     * @param oldPassword
     * @param newPassword
     */
    boolean resetUserPwd(String oldPassword, String newPassword);
}
