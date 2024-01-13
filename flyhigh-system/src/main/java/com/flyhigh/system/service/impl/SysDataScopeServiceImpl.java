package com.flyhigh.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.common.core.domain.entity.SysDept;
import com.flyhigh.common.core.domain.entity.SysRole;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.enums.CommDelFlagEnum;
import com.flyhigh.common.permission.DataScopeType;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.system.domain.SysRoleDept;
import com.flyhigh.system.mapper.SysDeptMapper;
import com.flyhigh.system.mapper.SysRoleDeptMapper;
import com.flyhigh.system.service.SysDataScopeService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("sdss")
public class SysDataScopeServiceImpl implements SysDataScopeService {

    @Resource
    private SysRoleDeptMapper roleDeptMapper;
    @Resource
    private SysDeptMapper deptMapper;

    @Override
    public String getRoleCustom(Long roleId) {
        // 针对非自定义的
        if (roleId != null) {
            List<SysRoleDept> list = roleDeptMapper.selectList(
                    new LambdaQueryWrapper<SysRoleDept>()
                            .select(SysRoleDept::getDeptId)
                            .eq(SysRoleDept::getRoleId, roleId));
            if (CollUtil.isNotEmpty(list)) {
                return list.stream().map(rd -> Convert.toStr(rd.getDeptId())).collect(Collectors.joining(","));
            }
        } else {
            // 自定义的数据权限查询时，user的roleId是null
            // 查询用户的所有roleId
            List<Long> roleIds = SecurityUtils.getLoginUser().getUser().getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(roleIds)) {
                List<SysRoleDept> list = roleDeptMapper.selectList(
                        new LambdaQueryWrapper<SysRoleDept>()
                                .select(SysRoleDept::getDeptId)
                                .in(SysRoleDept::getRoleId, roleIds));
                if (CollUtil.isNotEmpty(list)) {
                    return list.stream().map(rd -> Convert.toStr(rd.getDeptId())).collect(Collectors.joining(","));
                }
            }
        }

        return "-1";
    }

    @Override
    public String getDeptAndChild(Long deptId) {
        List<String> deptAndChildList = this.getDeptAndChildList(deptId);
        if (CollUtil.isNotEmpty(deptAndChildList)) {
            return String.join(",", deptAndChildList);
        }
        return null;
    }

    @Override
    public List<String> getDeptAndChildList(Long deptId) {
        List<SysDept> list = deptMapper.selectList(new LambdaQueryWrapper<SysDept>()
                .select(SysDept::getDeptId)
                .eq(SysDept::getDeptId, deptId)
                .or()
                .apply("find_in_set({0},ancestors)", deptId));
        if (CollUtil.isNotEmpty(list)) {
            return list.stream().map(d -> Convert.toStr(d.getDeptId())).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    @Override
    public List<String> getRoleDept() {
        List<String> deptIds = Lists.newArrayList();
        SysUser user;
        try {
            user = SecurityUtils.getLoginUser().getUser();
            if (null == user) {
                return Lists.newArrayList();
            }
        } catch (Exception e) {
            return Lists.newArrayList();
        }
        // 管理员角色获取所有数据
        if (SecurityUtils.isAdmin(user.getUserId())) {
            return null;
        }

        for (SysRole role : user.getRoles()) {
            String dataScope = role.getDataScope();
            // 所有数据权限
            if (DataScopeType.ALL.getCode().equals(dataScope)) {
                deptIds.clear();
                break;
            } else if (DataScopeType.CUSTOM.getCode().equals(dataScope)) {
                // 自定数据权限
                List<SysRoleDept> list = roleDeptMapper.selectList(
                        new LambdaQueryWrapper<SysRoleDept>()
                                .select(SysRoleDept::getDeptId)
                                .eq(SysRoleDept::getRoleId, role.getRoleId()));
                if (CollUtil.isNotEmpty(list)) {
                    deptIds.addAll(list.stream().map(rd -> Convert.toStr(rd.getDeptId())).collect(Collectors.toList()));
                }
            } else if (DataScopeType.DEPT.getCode().equals(dataScope)) {
                // 部门数据权限
                deptIds.add(String.valueOf(user.getDeptId()));
            } else if (DataScopeType.DEPT_AND_CHILD.getCode().equals(dataScope)) {
                // 部门及以下数据权限
                deptIds.addAll(this.getDeptAndChildList(user.getDeptId()));
            } else if (DataScopeType.SELF.getCode().equals(dataScope)) {
                // 仅本人数据权限（场景不存在），数据权限为仅本人不查询任何数据
                deptIds.add("0");
            }
        }
        // 去重
        return deptIds.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取角色资产ID
     *
     * @return 部门id组
     */
    @Override
    public List<Long> getRoleAssetsIds() {
        List<String> roleDept = this.getRoleDept();
        return Lists.newArrayList();
    }

}
