package com.flyhigh.admin.controller.sms;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.npoi.ExcelUtil;
import com.flyhigh.sms.domain.vo.pushErrorRecord.*;
import com.flyhigh.sms.service.IBizPushErrorRecordService;
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
 * 推送异常Controller
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Validated
@Api(value = "推送异常控制器", tags = {"推送异常管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/sms/record")
public class BizPushErrorRecordController extends BaseController {

    @Autowired
    private IBizPushErrorRecordService iBizPushErrorRecordService;

    /**
     * 查询推送异常列表
     */
    @ApiOperation("查询推送异常列表")
    @PreAuthorize("@ss.hasPermi('sms:record:list')")
    @GetMapping("/list")
    public TableDataInfo<BizPushErrorRecordVO> list(@Validated BizPushErrorRecordPageReq pageReq) {
        return iBizPushErrorRecordService.queryPageList(pageReq);
    }

    /**
     * 导出推送异常列表
     */
    @ApiOperation("导出推送异常列表")
    @PreAuthorize("@ss.hasPermi('sms:record:export')")
    @Log(title = "推送异常", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated BizPushErrorRecordExportReq exportReq, HttpServletResponse response) {
        List<BizPushErrorRecordExcelVO> list = iBizPushErrorRecordService.queryList(exportReq);
        ExcelUtil.exportExcel(list, "推送异常", BizPushErrorRecordExcelVO.class, response);
    }

    /**
     * 获取推送异常详细信息
     */
    @ApiOperation("获取推送异常详细信息")
    @PreAuthorize("@ss.hasPermi('sms:record:query')")
    @GetMapping("/{id}")
    public AjaxResult<BizPushErrorRecordVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(iBizPushErrorRecordService.queryById(id));
    }

    /**
     * 新增推送异常
     */
    @ApiOperation("新增推送异常")
    @PreAuthorize("@ss.hasPermi('sms:record:add')")
    @Log(title = "推送异常", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody BizPushErrorRecordCreateReq createReq) {
        return toAjax(iBizPushErrorRecordService.createBizPushErrorRecord(createReq));
    }

    /**
     * 修改推送异常
     */
    @ApiOperation("修改推送异常")
    @PreAuthorize("@ss.hasPermi('sms:record:edit')")
    @Log(title = "推送异常", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody BizPushErrorRecordUpdateReq updateReq) {
        return toAjax(iBizPushErrorRecordService.updateBizPushErrorRecord(updateReq));
    }

    /**
     * 删除推送异常
     */
    @ApiOperation("删除推送异常")
    @PreAuthorize("@ss.hasPermi('sms:record:remove')")
    @Log(title = "推送异常", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam("主键串") @NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(iBizPushErrorRecordService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
