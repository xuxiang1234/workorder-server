package com.flyhigh.workorder.controller;

import com.flyhigh.api.vo.template.TemplatePageReq;
import com.flyhigh.api.vo.template.TemplateSimplifyVO;
import com.flyhigh.api.vo.template.TemplateVO;
import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.workorder.domain.vo.template.TemplateCreateReq;
import com.flyhigh.workorder.domain.vo.template.TemplateUpdateReq;
import com.flyhigh.workorder.service.ITemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 模板Controller
 *
 * @author flyhigh
 * @date 2022-05-14
 */
@Validated
@Api(value = "模板控制器", tags = {"模板管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/workorder/template")
public class TemplateController extends BaseController {

    private final ITemplateService templateService;


    /**
     * 查询模板列表
     */
    @ApiOperation("查询模板列表")
    @GetMapping("/list")
    public TableDataInfo<TemplateVO> list(@Validated TemplatePageReq pageReq) {
        return templateService.queryPageList(pageReq);
    }

    /**
     * 查询模板列表-简化接口
     */
    @ApiOperation("查询模板列表-简化接口")
    @GetMapping("/simplifyList")
    public AjaxResult<List<TemplateSimplifyVO>> simplifyList(String templateName) {
        return AjaxResult.success(templateService.simplifyPageList(templateName));
    }


    /**
     * 获取模板详细信息
     */
    @ApiOperation("获取模板详细信息")
    @GetMapping("/{id}")
    public AjaxResult<TemplateVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(templateService.queryById(id));
    }

    /**
     * 新增模板
     */
    @ApiOperation("新增模板")
    @Log(title = "模板", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult<Boolean> add(@Validated @RequestBody TemplateCreateReq createReq) {
        return toAjax(templateService.createTemplate(createReq));
    }

    /**
     * 修改模板
     */
    @ApiOperation("修改模板")
    @Log(title = "模板", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult<Boolean> edit(@Validated @RequestBody TemplateUpdateReq updateReq) {
        return toAjax(templateService.updateTemplate(updateReq));
    }

    /**
     * 删除模板
     */
    @ApiOperation("删除模板")
    @Log(title = "删除模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult<Boolean> remove(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable Long id) {
        return toAjax(templateService.deleteById(id));
    }

}
