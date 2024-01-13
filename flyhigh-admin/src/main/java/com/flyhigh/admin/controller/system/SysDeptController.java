package com.flyhigh.admin.controller.system;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.flyhigh.common.core.domain.TreeSelect;
import com.flyhigh.common.core.domain.vo.SimpleDeptVO;
import com.flyhigh.system.domain.vo.dept.DeptTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.constant.UserConstants;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.domain.entity.SysDept;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.system.service.ISysDeptService;

/**
 * 部门信息
 *
 * @author flyhigh
 */

@RestController
@RequestMapping("/system/dept")
@Api(value = "部门信息管理", tags = {"部门信息管理"})
public class SysDeptController extends BaseController {
    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @ApiOperation("获取部门列表")
//    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public AjaxResult<List<SysDept>> list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(depts);
    }

    @ApiOperation("获取部门列表")
    @GetMapping("/simpleDeptlist")
    public AjaxResult<List<SimpleDeptVO>> simpleDeptlist(SysDept dept) {
        return AjaxResult.success(deptService.simpleDeptlist(dept));
    }

    /**
     * 查询部门列表（排除节点）
     */
    @ApiOperation("查询部门列表（排除节点）")
    @ApiImplicitParam(name = "deptId", value = "部门id", dataType = "Long", paramType = "path", example = "1024", required = true)
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list/exclude/{deptId}")
    public AjaxResult<List<SysDept>> excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        Iterator<SysDept> it = depts.iterator();
        while (it.hasNext()) {
            SysDept d = (SysDept) it.next();
            if (d.getDeptId().intValue() == deptId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + "")) {
                it.remove();
            }
        }
        return AjaxResult.success(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @ApiOperation("根据部门编号获取详细信息")
    @ApiImplicitParam(name = "deptId", value = "部门id", dataType = "Long", paramType = "path", example = "1024", required = true)
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult<SysDept> getInfo(@PathVariable Long deptId) {
        deptService.checkDeptDataScope(deptId);
        SysDept sysDept = deptService.selectDeptById(deptId);
        // 父级部门名称
        if (null != sysDept && null != sysDept.getParentId() && 0 != sysDept.getParentId().intValue()) {
            SysDept parentDept = Optional.ofNullable(deptService.selectDeptById(sysDept.getParentId())).orElse(new SysDept());
            sysDept.setParentName(parentDept.getDeptName());
        }
        return AjaxResult.success(sysDept);
    }

    /**
     * 获取部门下拉树列表
     */
    @ApiOperation("获取部门下拉树列表")
    @GetMapping("/treeselect")
    public AjaxResult<List<TreeSelect>> treeselect(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTreeSelect(depts));
    }

    /**
     * 获取部门树列表
     */
    @ApiOperation("获取部门树列表")
    @GetMapping("/treelist")
    public AjaxResult<List<SysDept>> treelist(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTree(depts));
    }

    /**
     * 加载对应角色部门列表树
     */
    @ApiOperation("加载对应角色部门列表树")
    @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "Long", paramType = "path", example = "1024", required = true)
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult<DeptTreeVo> roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        DeptTreeVo result = new DeptTreeVo();
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        result.setCheckedKeys(deptService.selectDeptListByRoleId(roleId));
        result.setDepts(deptService.buildDeptTreeSelect(depts));
        return AjaxResult.success(result);
    }

    /**
     * 新增部门
     */
    @ApiOperation("新增部门")
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult<Boolean> add(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(getUsername());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改部门
     */
    @ApiOperation("修改部门")
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult<Boolean> edit(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            return AjaxResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        } else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0) {
            return AjaxResult.error("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(getUsername());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @ApiOperation("删除部门")
    @ApiImplicitParam(name = "deptId", value = "部门id", dataType = "Long", paramType = "path", example = "1024", required = true)
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult<Boolean> remove(@PathVariable Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return AjaxResult.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return AjaxResult.error("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }
}
