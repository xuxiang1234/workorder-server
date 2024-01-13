package com.flyhigh.framework.web.service;

import com.flyhigh.common.constant.DeptConstants;
import com.flyhigh.common.core.domain.entity.SysDept;
import com.flyhigh.common.utils.collection.CollectionUtils;
import com.flyhigh.system.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.enums.UserStatus;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.system.service.ISysUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户验证处理
 *
 * @author flyhigh
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysDeptService deptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        // 将属于安全生产管理部的 用户，所属的deptId 修改成集团的id,部门名称也修改成集团的id
        if (user.getDeptId() != null && DeptConstants.SAFETY_MANAGEMENT_DEPT_ID.equals(user.getDeptId())) {
            log.info("当前登录人：{},所属用户部门 安全生产管理部 ", username);
            SysDept sysDept = deptService.selectDeptById(DeptConstants.GROUP_DEPT_ID);
            user.setDept(sysDept);
            user.setDeptName(sysDept.getDeptName());
            user.setDeptId(DeptConstants.SAFETY_MANAGEMENT_DEPT_ID);
        }
        //查询用户的数据权限的部门节点
        List<SysDept> sysDepts = deptService.selectDeptsByUserId(user.getUserId());
        user.setDataScopeDeptIds(sysDepts.stream().map(SysDept::getDeptId).collect(Collectors.toList()));

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
