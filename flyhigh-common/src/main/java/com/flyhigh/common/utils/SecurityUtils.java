package com.flyhigh.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.flyhigh.common.constant.DeptConstants;
import com.flyhigh.common.constant.HttpStatus;
import com.flyhigh.common.constant.UserConstants;
import com.flyhigh.common.core.domain.entity.SysDept;
import com.flyhigh.common.core.domain.entity.SysRole;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.enums.UserRoleTypeEnum;
import com.flyhigh.common.enums.UserStatus;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.utils.collection.CollectionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 安全服务工具类
 *
 * @author flyhigh
 */
public class SecurityUtils {
    /**
     * 用户ID
     **/
    public static Long getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new ServiceException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取部门ID
     **/
    public static Long getDeptId() {
        try {
            return getLoginUser().getDeptId();
        } catch (Exception e) {
            throw new ServiceException("获取部门ID异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    public static String getUsernameIfPresent() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户所在部门
     *
     * @return
     */
    public static String getDeptName() {
        try {
            return getLoginUser().getUser().getDept().getDeptName();
        } catch (Exception e) {
            throw new ServiceException("获取用户部门异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 是否为部门管理员
     *
     * @return 结果
     */
    public static boolean isDeptAdmin() {
        SysDept sysDept = getLoginUser().getUser().getDept();
        return sysDept != null && ObjectUtil.equals(sysDept.getParentId(), 0L);
    }


    /**
     * 判断是否是子公司
     *
     * @return
     */
    public static boolean isSubsidiary() {
        try {
            return getRoleType().equals(UserRoleTypeEnum.SUBSIDIARY);
        } catch (Exception e) {
            throw new ServiceException("判断是否是子公司信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前登录人的最大角色类型（roleType越小级别越高）
     *
     * @return
     */
    public static UserRoleTypeEnum getRoleType() {
        try {
            if (getLoginUser().getUser().isAdmin()) {
                return UserRoleTypeEnum.ADMIN;
            }
            return UserRoleTypeEnum.getByRoleType(getLoginUser().getUser().getRoles().stream()
                    .filter(SecurityUtils::isEffective)
                    .map(SecurityUtils::findRoleType).min(Comparator.comparingLong((item) -> item)).orElse(null));
        } catch (Exception e) {
            throw new ServiceException("获取当前登录人的最大角色类型信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 若是超管或者集团角色则返回 null
     * 否则返回真实的部门id
     *
     * @return
     */
    public static List<Long> getSafeCheckDeptId(Long deptId) {
        try {
            // 如果传了deptId 则返回传入的
            if (deptId != null) {
                return Arrays.asList(deptId);
            }
            if (getLoginUser().getUser().isAdmin()) {
                return null;
            }
            UserRoleTypeEnum byRoleType = UserRoleTypeEnum.getByRoleType(getLoginUser().getUser().getRoles().stream()
                    .filter(SecurityUtils::isEffective)
                    .map(SecurityUtils::findRoleType).min(Comparator.comparingLong((item) -> item)).orElse(null));
            if (byRoleType == null) {
                throw new ServiceException("用户角色缺失", HttpStatus.UNAUTHORIZED);
            }

            if (byRoleType.equals(UserRoleTypeEnum.GROUP_COMPANY)) {
                return null;
            } else {
                List<Long> dataScopeDeptIds = getLoginUser().getUser().getDataScopeDeptIds();
                // 不是超管或者集团管理员需要把集团数据过滤掉
                if (!byRoleType.equals(UserRoleTypeEnum.ADMIN) && !byRoleType.equals(UserRoleTypeEnum.GROUP_COMPANY)) {
                    dataScopeDeptIds.removeIf(item -> {
                        return item.equals(DeptConstants.GROUP_DEPT_ID);
                    });
                }
                if (CollectionUtils.isEmpty(dataScopeDeptIds)) {
                    throw new ServiceException("用户角色缺失,请联系管理员", HttpStatus.UNAUTHORIZED);
                }
                return dataScopeDeptIds;
            }
        } catch (Exception e) {
            throw new ServiceException("获取当前登录人的最大角色类型信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户所有的数据权限
     * @return
     */
    public static Set<String> getLoginUserDataScopes() {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();

        return user.getRoles().stream().map(SysRole::getDataScope).collect(Collectors.toSet());
    }

    /**
     * 判断是否是调度员
     * @return
     */
    public static boolean whetherDispatcherUser(){
        return getLoginUserDataScopes().contains("6");
    }

    /**
     * 有效的角色
     *
     * @param role
     * @return
     */
    private static boolean isEffective(SysRole role) {
        return StringUtils.isNotEmpty(role.getRoleType()) &&
                Objects.equals(role.getStatus(), UserStatus.OK.getCode()) && Objects.equals(role.getDelFlag(), UserStatus.OK.getCode());
    }

    private static Long findRoleType(SysRole role) {
        if (!Objects.isNull(role.getWhetherThirdManager()) && role.getWhetherThirdManager().equals(UserConstants.WHETHERTHIRDMANAGER_THIRD)) {
            return UserConstants.WHETHERTHIRDMANAGER_THIRD_ROLETYPE;
        }
        return Long.valueOf(role.getRoleType());
    }


}
