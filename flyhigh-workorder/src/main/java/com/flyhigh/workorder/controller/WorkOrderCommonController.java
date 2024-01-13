package com.flyhigh.workorder.controller;

import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.workorder.service.ICommonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Mr.Lai
 * @date 2023/4/18
 */
@RestController
@RequestMapping("/workorder/common")
public class WorkOrderCommonController extends BaseController {

    @Autowired
    private ICommonService commonService;
    /**
     * 字典查询
     */
    @ApiOperation("字典查询")
    @GetMapping("/queryDictList")
    public AjaxResult<List<String>> queryDictList(@ApiParam("字典类型") @NotBlank(message = "字典类型不能为空") @RequestParam String dictType) {
        return AjaxResult.success(commonService.queryDictList(dictType));
    }

}
