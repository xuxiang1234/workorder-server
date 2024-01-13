package com.flyhigh.admin.controller.sms;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.npoi.ExcelUtil;
import com.flyhigh.sms.domain.vo.pushUserAlias.*;
import com.flyhigh.sms.service.IBizPushUserAliasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 用户个推别名Controller
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Validated
@Api(value = "用户个推别名控制器", tags = {"用户个推别名管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/sms/pushUserAlias")
public class BizPushUserAliasController extends BaseController {

    @Autowired
    private IBizPushUserAliasService iBizPushUserAliasService;

    /**
     * 查询用户个推别名列表
     */
    @ApiOperation("查询用户个推别名列表")
    @PreAuthorize("@ss.hasPermi('sms:pushUserAlias:list')")
    @GetMapping("/list")
    public TableDataInfo<BizPushUserAliasVO> list(@Validated BizPushUserAliasPageReq pageReq) {
        return iBizPushUserAliasService.queryPageList(pageReq);
    }

    /**
     * 导出用户个推别名列表
     */
    @ApiOperation("导出用户个推别名列表")
    @PreAuthorize("@ss.hasPermi('sms:pushUserAlias:export')")
    @Log(title = "用户个推别名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated BizPushUserAliasExportReq exportReq, HttpServletResponse response) {
        List<BizPushUserAliasExcelVO> list = iBizPushUserAliasService.queryList(exportReq);
        ExcelUtil.exportExcel(list, "用户个推别名", BizPushUserAliasExcelVO.class, response);
    }

    /**
     * 获取用户个推别名详细信息
     */
    @ApiOperation("获取用户个推别名详细信息")
    @PreAuthorize("@ss.hasPermi('sms:pushUserAlias:query')")
    @GetMapping("/{id}")
    public AjaxResult<BizPushUserAliasVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(iBizPushUserAliasService.queryById(id));
    }

    /**
     * 新增用户个推别名
     */
    @ApiOperation("新增用户个推别名")
    @PreAuthorize("@ss.hasPermi('sms:pushUserAlias:add')")
    @Log(title = "用户个推别名", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody BizPushUserAliasCreateReq createReq) {
        return toAjax(iBizPushUserAliasService.createBizPushUserAlias(createReq));
    }

    /**
     * 修改用户个推别名
     */
    @ApiOperation("修改用户个推别名")
    @PreAuthorize("@ss.hasPermi('sms:pushUserAlias:edit')")
    @Log(title = "用户个推别名", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody BizPushUserAliasUpdateReq updateReq) {
        return toAjax(iBizPushUserAliasService.updateBizPushUserAlias(updateReq));
    }

    /**
     * 删除用户个推别名
     */
    @ApiOperation("删除用户个推别名")
    @PreAuthorize("@ss.hasPermi('sms:pushUserAlias:remove')")
    @Log(title = "用户个推别名", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam("主键串") @NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(iBizPushUserAliasService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
