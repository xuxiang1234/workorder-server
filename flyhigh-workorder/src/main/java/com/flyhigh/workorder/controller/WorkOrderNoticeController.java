package com.flyhigh.workorder.controller;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.npoi.ExcelUtil;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeExcelVO;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeExportReq;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticePageReq;
import com.flyhigh.workorder.domain.vo.workordernotice.WorkOrderNoticeVO;
import com.flyhigh.workorder.service.IWorkOrderNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 工单消息通知Controller
 *
 * @author flyhigh
 * @date 2022-05-21
 */
@Validated
@Api(value = "工单消息通知控制器", tags = {"工单消息通知管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/workorder/notice")
public class WorkOrderNoticeController extends BaseController {

    private final IWorkOrderNoticeService workOrderNoticeService;

    /**
     * 查询工单消息通知列表
     */
    @ApiOperation("查询工单消息通知列表")
    @GetMapping("/list")
    public TableDataInfo<WorkOrderNoticeVO> list(@Validated WorkOrderNoticePageReq pageReq) {
        return workOrderNoticeService.queryPageList(pageReq);
    }

    /**
     * 导出工单消息通知列表
     */
    @ApiOperation("导出工单消息通知列表")
    @Log(title = "工单消息通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated WorkOrderNoticeExportReq exportReq, HttpServletResponse response) {
        List<WorkOrderNoticeExcelVO> list = workOrderNoticeService.queryList(exportReq);
        ExcelUtil.exportExcel(list, "工单消息通知", WorkOrderNoticeExcelVO.class, response);
    }

    /**
     * 获取工单消息通知详细信息
     */
    @ApiOperation("获取工单消息通知详细信息")
    @GetMapping("/{id}")
    public AjaxResult<WorkOrderNoticeVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(workOrderNoticeService.queryById(id));
    }

    /**
     * 工单消息设置已读
     */
    @ApiOperation("工单消息设置已读")
    @GetMapping("/read/{id}")
    public AjaxResult<Boolean> noticeRead(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(workOrderNoticeService.noticeRead(id));
    }

}
