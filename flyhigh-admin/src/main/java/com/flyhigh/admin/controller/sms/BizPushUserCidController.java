package com.flyhigh.admin.controller.sms;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.npoi.ExcelUtil;
import com.flyhigh.sms.domain.vo.pushUserCid.*;
import com.flyhigh.sms.service.IBizPushUserCidService;
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
 * 用户个推cid关系绑定Controller
 *
 * @author flyhigh
 * @date 2022-03-31
 */
@Validated
@Api(value = "用户个推cid关系绑定控制器", tags = {"用户个推cid关系绑定管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/sms/pushUserCid")
public class BizPushUserCidController extends BaseController {

    @Autowired
    private IBizPushUserCidService iBizPushUserCidService;

    /**
     * 查询用户个推cid关系绑定列表
     */
    @ApiOperation("查询用户个推cid关系绑定列表")
    @PreAuthorize("@ss.hasPermi('sms:pushUserCid:list')")
    @GetMapping("/list")
    public TableDataInfo<BizPushUserCidVO> list(@Validated BizPushUserCidPageReq pageReq) {
        return iBizPushUserCidService.queryPageList(pageReq);
    }

    /**
     * 导出用户个推cid关系绑定列表
     */
    @ApiOperation("导出用户个推cid关系绑定列表")
    @PreAuthorize("@ss.hasPermi('sms:pushUserCid:export')")
    @Log(title = "用户个推cid关系绑定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated BizPushUserCidExportReq exportReq, HttpServletResponse response) {
        List<BizPushUserCidExcelVO> list = iBizPushUserCidService.queryList(exportReq);
        ExcelUtil.exportExcel(list, "用户个推cid关系绑定", BizPushUserCidExcelVO.class, response);
    }

    /**
     * 获取用户个推cid关系绑定详细信息
     */
    @ApiOperation("获取用户个推cid关系绑定详细信息")
    @PreAuthorize("@ss.hasPermi('sms:pushUserCid:query')")
    @GetMapping("/{id}")
    public AjaxResult<BizPushUserCidVO> getInfo(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(iBizPushUserCidService.queryById(id));
    }

    /**
     * 新增用户个推cid关系绑定
     */
    @ApiOperation("新增用户个推cid关系绑定")
    @PreAuthorize("@ss.hasPermi('sms:pushUserCid:add')")
    @Log(title = "用户个推cid关系绑定", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody BizPushUserCidCreateReq createReq) {
        return toAjax(iBizPushUserCidService.createBizPushUserCid(createReq));
    }

    /**
     * 修改用户个推cid关系绑定
     */
    @ApiOperation("修改用户个推cid关系绑定")
    @PreAuthorize("@ss.hasPermi('sms:pushUserCid:edit')")
    @Log(title = "用户个推cid关系绑定", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody BizPushUserCidUpdateReq updateReq) {
        return toAjax(iBizPushUserCidService.updateBizPushUserCid(updateReq));
    }

    /**
     * 删除用户个推cid关系绑定
     */
    @ApiOperation("删除用户个推cid关系绑定")
    @PreAuthorize("@ss.hasPermi('sms:pushUserCid:remove')")
    @Log(title = "用户个推cid关系绑定", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam("主键串") @NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(iBizPushUserCidService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
