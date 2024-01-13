package com.flyhigh.form.controller;

import com.alibaba.excel.util.StringUtils;
import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseCreateReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehousePageReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseUpdateReq;
import com.flyhigh.api.vo.formWarehouse.FormWarehouseVO;
import com.flyhigh.form.service.IFormWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 表单仓库Controller
 *
 * @author flyhigh
 * @date 2022-08-06
 */
@Validated
@Api(value = "表单仓库控制器", tags = {"表单仓库控制器"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/form/warehouse")
public class FormWarehouseController extends BaseController {

    @Autowired
    private IFormWarehouseService formWarehouseService;

    /**
     * 查询表表单仓库列表
     */
    @ApiOperation("查询表表单仓库列表")
    // @PreAuthorize("@ss.hasPermi('system:warehouse:list')")
    @GetMapping("/list")
    public TableDataInfo<FormWarehouseVO> list(@Validated FormWarehousePageReq pageReq) {
        // 默认查询工单系统的预设表单
        if(StringUtils.isBlank(pageReq.getSystem())) {
            pageReq.setSystem(SystemIdentification.WORK_ORDER_SYSTEM.name());
        }
        return formWarehouseService.queryPageList(pageReq);
    }

    /**
     * 获取表表单仓库详细信息
     */
    @ApiOperation("获取表表单仓库详细信息")
    // @PreAuthorize("@ss.hasPermi('system:warehouse:query')")
    @GetMapping("/{id}")
    public AjaxResult<FormWarehouseVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(formWarehouseService.queryById(id));
    }

    /**
     * 新增表表单仓库
     */
    @ApiOperation("新增表表单仓库")
    // @PreAuthorize("@ss.hasPermi('system:warehouse:add')")
    @Log(title = "表表单仓库", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult<Boolean> add(@Validated @RequestBody FormWarehouseCreateReq createReq) {
        // 默认新增工单系统的预设表单
        if(StringUtils.isBlank(createReq.getSystem())) {
            createReq.setSystem(SystemIdentification.WORK_ORDER_SYSTEM.name());
        }
        return toAjax(formWarehouseService.createFormWarehouse(createReq));
    }

    /**
     * 修改表表单仓库
     */
    @ApiOperation("修改表表单仓库")
    // @PreAuthorize("@ss.hasPermi('system:warehouse:edit')")
    @Log(title = "表表单仓库", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult<Boolean> edit(@Validated @RequestBody FormWarehouseUpdateReq updateReq) {
        return toAjax(formWarehouseService.updateFormWarehouse(updateReq));
    }

    /**
     * 删除表表单仓库
     */
    @ApiOperation("删除表表单仓库")
    // @PreAuthorize("@ss.hasPermi('system:warehouse:remove')")
    @Log(title = "表表单仓库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult<Boolean> remove(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable Long id) {
        return toAjax(formWarehouseService.deleteById(id));
    }
}
