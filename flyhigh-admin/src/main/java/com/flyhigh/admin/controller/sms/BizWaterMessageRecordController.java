package com.flyhigh.admin.controller.sms;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.npoi.ExcelUtil;
import com.flyhigh.sms.domain.vo.waterMessageRecord.*;
import com.flyhigh.sms.service.IBizWaterMessageRecordService;
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
 * 业务消息流水Controller
 *
 * @author flyhigh
 * @date 2022-04-21
 */
@Validated
@Api(value = "业务消息流水控制器", tags = {"业务消息流水管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/waterMessageRecord")
public class BizWaterMessageRecordController extends BaseController {

    @Autowired
    private IBizWaterMessageRecordService bizWaterMessageRecordService;

    /**
     * 查询业务消息流水列表
     */
    @ApiOperation("查询业务消息流水列表")
    @PreAuthorize("@ss.hasPermi('system:waterMessageRecord:list')")
    @GetMapping("/list")
    public TableDataInfo<BizWaterMessageRecordVO> list(@Validated BizWaterMessageRecordReq pageReq) {
        return bizWaterMessageRecordService.queryPageList(pageReq);
    }

    /**
     * 导出业务消息流水列表
     */
    @ApiOperation("导出业务消息流水列表")
    @PreAuthorize("@ss.hasPermi('system:waterMessageRecord:export')")
    @Log(title = "业务消息流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated BizWaterMessageRecordExportReq exportReq, HttpServletResponse response) {
        List<BizWaterMessageRecordExcelVO> list = bizWaterMessageRecordService.queryList(exportReq);
        ExcelUtil.exportExcel(list, "业务消息流水", BizWaterMessageRecordExcelVO.class, response);
    }

    /**
     * 获取业务消息流水详细信息
     */
    @ApiOperation("获取业务消息流水详细信息")
    @PreAuthorize("@ss.hasPermi('system:waterMessageRecord:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(bizWaterMessageRecordService.queryById(id));
    }

    /**
     * 新增业务消息流水
     */
    @ApiOperation("新增业务消息流水")
    @PreAuthorize("@ss.hasPermi('system:waterMessageRecord:add')")
    @Log(title = "业务消息流水", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody BizWaterMessageRecordCreateReq createReq) {
        return toAjax(bizWaterMessageRecordService.createBizWaterMessageRecord(createReq));
    }

    /**
     * 修改业务消息流水
     */
    @ApiOperation("修改业务消息流水")
    @PreAuthorize("@ss.hasPermi('system:waterMessageRecord:edit')")
    @Log(title = "业务消息流水", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody BizWaterMessageRecordUpdateReq updateReq) {
        return toAjax(bizWaterMessageRecordService.updateBizWaterMessageRecord(updateReq));
    }

    /**
     * 删除业务消息流水
     */
    @ApiOperation("删除业务消息流水")
    @PreAuthorize("@ss.hasPermi('system:waterMessageRecord:remove')")
    @Log(title = "业务消息流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam("主键串") @NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(bizWaterMessageRecordService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
