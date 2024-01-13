package com.flyhigh.form.controller;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.form.domain.enums.FormBizName;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.element.FormElementCreateReq;
import com.flyhigh.api.vo.element.FormElementVO;
import com.flyhigh.api.vo.form.AddUserFormCreateReq;
import com.flyhigh.api.vo.form.FormCreateReq;
import com.flyhigh.api.vo.form.FormVO;
import com.flyhigh.form.service.IFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 表单Controller
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Validated
@Api(value = "表单控制器", tags = {"表单管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/form/form")
public class FormController extends BaseController {

    private final IFormService formService;

    /**
     * 新增表单
     */
    @ApiOperation("新增表单")
    //@PreAuthorize("@ss.hasPermi('form:form:add')")
    @Log(title = "表单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/createForm")
    public AjaxResult<Boolean> createForm(@Validated @RequestBody List<FormCreateReq> createReq) {
        return toAjax(formService.createForm(createReq));
    }

    /**
     * 指定人新增表单
     */
    @ApiOperation("指定人新增表单")
    //@PreAuthorize("@ss.hasPermi('form:form:add')")
    @Log(title = "表单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/addUserCreateForm")
    public AjaxResult<Boolean> addUserCreateForm(@Validated @RequestBody AddUserFormCreateReq createReq) {
        return toAjax(formService.addUserCreateForm(createReq));
    }

    /**
     * 查询表单是否存在
     *
     * @return
     */
    @ApiOperation("查询表单是否存在")
    //@PreAuthorize("@ss.hasPermi('form:form:query')")
    @GetMapping("/queryFormExist")
    public AjaxResult<List<Long>> queryForm(@Validated @NotEmpty(message = "关联ID不能为空") @RequestParam(value = "contactIds") List<Long> contactIds, @NotNull(message = "系统") SystemIdentification system) {
        return AjaxResult.success(formService.queryFormExist(contactIds, system));
    }

    /**
     * 查询表单
     */
    @ApiOperation("查询表单")
    //@PreAuthorize("@ss.hasPermi('form:form:query')")
    @GetMapping("/queryForm")
    public AjaxResult<FormVO> queryForm(@Validated @NotNull(message = "关联ID不能为空") Long contactId, @NotNull(message = "系统") SystemIdentification system) {
        return AjaxResult.success(formService.queryForm(contactId, system));
    }

    /**
     * 查询统计表单
     */
    @ApiOperation("查询统计表单")
    //@PreAuthorize("@ss.hasPermi('form:form:query')")
    @GetMapping("/queryStatisticsForm")
    public AjaxResult<List<FormElementVO>> queryStatisticsForm(@Validated @NotEmpty(message = "关联ID不能为空") List<Long> contactIds, FormBizName formBizName, @NotNull(message = "系统") SystemIdentification system) {
        return AjaxResult.success(formService.queryStatisticsForm(contactIds, formBizName, system));
    }

    /**
     * 填写表单内容
     */
    @ApiOperation("填写表单内容")
    //@PreAuthorize("@ss.hasPermi('form:form:add')")
    @RepeatSubmit()
    @PostMapping("/addFormContent")
    public AjaxResult<Boolean> addFormContent(@Validated @RequestBody List<List<FormElementCreateReq>> createReqList) {
        return toAjax(formService.addFormContent(createReqList));
    }

}
