package com.flyhigh.system.service.impl;

import com.flyhigh.common.constant.UserConstants;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.utils.MaskUtils;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.system.domain.vo.sysUser.UserProfileVo;
import com.flyhigh.system.service.ISysDeptService;
import com.flyhigh.system.service.ISysProfileService;
import com.flyhigh.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class
SysProfileServiceImpl implements ISysProfileService {

    private final ISysUserService userService;

    private final ISysDeptService deptService;

    @Override
    public UserProfileVo profile() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        user.setPhonenumber(MaskUtils.maskByMobile(user.getPhonenumber()));
        user.setLoginIp(MaskUtils.maskByIp(user.getLoginIp()));

        UserProfileVo userProfileVo = new UserProfileVo();
        userProfileVo.setRoleGroup(userService.selectUserRoleGroup(loginUser.getUsername()));
        userProfileVo.setPostGroup(userService.selectUserPostGroup(loginUser.getUsername()));
        userProfileVo.setCompany(deptService.selectDeptsByUserId(loginUser.getUserId()));
        userProfileVo.setUser(user);
        userProfileVo.setWhetherDispatcherUser(SecurityUtils.whetherDispatcherUser());
        return userProfileVo;
    }

    @Override
    public boolean updateProfile(SysUser user) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser sysUser = loginUser.getUser();
        user.setUserName(sysUser.getUserName());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            throw new ServiceException("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            throw new ServiceException("修改用户'\" + user.getUserName() + \"'失败，邮箱账号已存在");
        }
        user.setUserId(sysUser.getUserId());
        user.setPassword(null);
        return userService.updateUserProfile(user) > 0;
    }

    @Override
    public boolean resetUserPwd(String oldPassword, String newPassword) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            throw new ServiceException("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            throw new ServiceException("新密码不能与旧密码相同");
        }
        return userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0;
    }
}
