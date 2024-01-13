package com.flyhigh.form.controller;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.api.enums.SystemIdentification;
import com.flyhigh.api.vo.formtemplate.FormTemplateCreateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateUpdateReq;
import com.flyhigh.api.vo.formtemplate.FormTemplateVO;
import com.flyhigh.form.service.IFormTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 表单模板Controller
 *
 * @author flyhigh
 * @date 2022-05-29
 */
@Validated
@Api(value = "表单模板控制器", tags = {"表单模板管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/form/template")
public class FormTemplateController extends BaseController {

    private final IFormTemplateService formTemplateService;

    /**
     * 新增表单模板
     */
    @ApiOperation("新增表单模板")
    //@PreAuthorize("@ss.hasPermi('form:template:add')")
    @Log(title = "表单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/createFormtemplate")
    public AjaxResult<Boolean> createFormtemplate(@Validated @RequestBody List<FormTemplateCreateReq> createReq) {
        return toAjax(formTemplateService.createFormtemplate(createReq));
    }

    /**
     * 修改表单模板
     */
    @ApiOperation("修改表单模板")
    //@PreAuthorize("@ss.hasPermi('form:template:edit')")
    @Log(title = "表单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/editFormtemplate")
    public AjaxResult<Boolean> editFormtemplate(@Validated @RequestBody List<FormTemplateUpdateReq> updateReq) {
        return toAjax(formTemplateService.updateFormtemplate(updateReq));
    }

    /**
     * 查询表单
     */
    @ApiOperation("查询表单模板")
    //@PreAuthorize("@ss.hasPermi('form:template:query')")
    @RepeatSubmit()
    @GetMapping("/queryFormtemplate")
    public AjaxResult<FormTemplateVO> queryFormtemplate(@Validated @NotNull(message = "关联ID不能为空") Long contactId, @NotNull(message = "系统") SystemIdentification system) {
        return AjaxResult.success(formTemplateService.queryFormtemplate(contactId, system));
    }

}
