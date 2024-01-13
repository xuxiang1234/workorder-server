package com.flyhigh.workorder.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.flyhigh.api.service.WorkOrderServiceApi;
import com.flyhigh.api.vo.workorder.*;
import com.flyhigh.api.vo.workorderprocess.WorkOrderProcessHistoryVO;
import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.npoi.ExcelUtil;
import com.flyhigh.framework.config.ServerConfig;
import com.flyhigh.api.vo.workorder.WorkOrderEvaluateReq;
import com.flyhigh.workorder.domain.vo.workorder.WorkOrderEvaluateVO;
import com.flyhigh.workorder.domain.vo.workorder.WorkOrderHandleReq;
import com.flyhigh.workorder.service.IWorkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 工单Controller
 *
 * @author flyhigh
 * @date 2022-05-17
 */
@Validated
@Api(value = "工单控制器", tags = {"工单管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/workorder/order")
public class WorkOrderController extends BaseController {

    private final IWorkOrderService workOrderService;

    private final WorkOrderServiceApi workOrderServiceApi;

    /**
     * 查询工单列表
     */
    @ApiOperation("查询工单列表")
    @GetMapping("/list")
    public TableDataInfo<WorkOrderVO> list(@Validated WorkOrderPageReq pageReq) {
        return workOrderService.queryPageList(pageReq);
    }

    @ApiOperation("导出工单列表")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated @RequestBody WorkOrderExportReq exportReq, HttpServletResponse response) {
        List<WorkOrderExcelVO> list = workOrderService.queryList(exportReq);

        BigDecimal offerSum = list.stream().filter(e -> StringUtils.isNotBlank(e.getOffer())).map(e -> new BigDecimal(e.getOffer())).reduce(BigDecimal.ZERO, BigDecimal::add);
        WorkOrderExcelVO sumObj = new WorkOrderExcelVO();
        sumObj.setDescription("报价总数");
        sumObj.setOffer(offerSum.toPlainString());
        list.add(sumObj);

        ExcelUtil.exportExcel(list, "运维工单", WorkOrderExcelVO.class, response);
    }

    /**
     * 获取工单详细信息
     */
    @ApiOperation("获取工单详细信息")
    @GetMapping("/{id}")
    public AjaxResult<WorkOrderVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id,
                                           HttpServletRequest request) {
        String domain = ServerConfig.getDomain(request);
        WorkOrderVO workOrderVO = workOrderService.queryById(id);
        // 转换工单图片地址
        workOrderVO.setPicUrl(workOrderServiceApi.convertWorkOrderPicUrl(domain, workOrderVO.getPicUrl()));

        return AjaxResult.success(workOrderVO);
    }

    /**
     * 获取工单历史
     */
    @ApiOperation("获取工单历史")
    @GetMapping("/history/{id}")
    public AjaxResult<List<WorkOrderProcessHistoryVO>> getHistoryInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(workOrderService.getHistoryInfo(id));
    }

    /**
     * 新增工单
     */
    @ApiOperation("新增工单")
    @Log(title = "工单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult<WorkOrderVO> add(@Validated @RequestBody WorkOrderCreateReq createReq) {
        return AjaxResult.success(workOrderService.createWorkOrder(createReq));
    }

    /**
     * 处理工单
     */
    @ApiOperation("处理工单")
    @Log(title = "处理工单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/handle")
    public AjaxResult<Boolean> handle(@Validated @RequestBody WorkOrderHandleReq handleReq) {
        return toAjax(workOrderService.handle(handleReq));
    }

    /**
     * 工单评价
     */
    @ApiOperation("工单评价")
    @RepeatSubmit()
    @PostMapping("/evaluate")
    public AjaxResult<Boolean> evaluate(@Validated @RequestBody WorkOrderEvaluateReq evaluateReq) {
        return toAjax(workOrderService.evaluate(evaluateReq));
    }

    /**
     * 工单确认
     */
    @ApiOperation("工单确认")
    @RepeatSubmit()
    @PostMapping("/autoOkWorkOrder")
    public AjaxResult<Boolean> evaluate(@ApiParam("主键") @NotNull(message = "主键不能为空") Long id) {
        return toAjax(workOrderService.autoOkWorkOrder(id));
    }

    /**
     * Dashbord统计
     */
    @ApiOperation("Dashbord统计")
    @GetMapping("/dashBoradSummary")
    public AjaxResult<WorkOrderEvaluateVO> dashBoradSummary() {
        return AjaxResult.success(workOrderService.dashBoradSummary());
    }

}
