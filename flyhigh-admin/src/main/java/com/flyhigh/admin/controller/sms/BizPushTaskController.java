package com.flyhigh.admin.controller.sms;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.npoi.ExcelUtil;
import com.flyhigh.sms.domain.vo.pushTask.*;
import com.flyhigh.sms.service.IBizPushTaskService;
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
 * 推送任务Controller
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Validated
@Api(value = "推送任务控制器", tags = {"推送任务管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/sms/pushTask")
public class BizPushTaskController extends BaseController {

    @Autowired
    private IBizPushTaskService bizPushTaskService;

    /**
     * 查询推送任务列表
     */
    @ApiOperation("查询推送任务列表")
    @PreAuthorize("@ss.hasPermi('sms:pushTask:list')")
    @GetMapping("/list")
    public TableDataInfo<BizPushTaskVO> list(@Validated BizPushTaskPageReq pageReq) {
        return bizPushTaskService.queryPageList(pageReq);
    }

    /**
     * 导出推送任务列表
     */
    @ApiOperation("导出推送任务列表")
    @PreAuthorize("@ss.hasPermi('sms:pushTask:export')")
    @Log(title = "推送任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated BizPushTaskExportReq exportReq, HttpServletResponse response) {
        List<BizPushTaskExcelVO> list = bizPushTaskService.queryList(exportReq);
        ExcelUtil.exportExcel(list, "推送任务", BizPushTaskExcelVO.class, response);
    }

    /**
     * 获取推送任务详细信息
     */
    @ApiOperation("获取推送任务详细信息")
    @PreAuthorize("@ss.hasPermi('sms:pushTask:query')")
    @GetMapping("/{id}")
    public AjaxResult<BizPushTaskVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(bizPushTaskService.queryById(id));
    }

    /**
     * 新增推送任务
     */
    @ApiOperation("新增推送任务")
    @PreAuthorize("@ss.hasPermi('sms:pushTask:add')")
    @Log(title = "推送任务", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody BizPushTaskCreateReq createReq) {
        return toAjax(bizPushTaskService.createBizPushTask(createReq));
    }

    /**
     * 修改推送任务
     */
    @ApiOperation("修改推送任务")
    @PreAuthorize("@ss.hasPermi('sms:pushTask:edit')")
    @Log(title = "推送任务", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody BizPushTaskUpdateReq updateReq) {
        return toAjax(bizPushTaskService.updateBizPushTask(updateReq));
    }

    /**
     * 删除推送任务
     */
    @ApiOperation("删除推送任务")
    @PreAuthorize("@ss.hasPermi('sms:pushTask:remove')")
    @Log(title = "推送任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam("主键串") @NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(bizPushTaskService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
