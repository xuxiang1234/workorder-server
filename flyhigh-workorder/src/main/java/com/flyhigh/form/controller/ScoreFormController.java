package com.flyhigh.form.controller;

import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.api.vo.formWarehouse.ScoreFormPageReq;
import com.flyhigh.api.vo.formWarehouse.ScoreFormVO;
import com.flyhigh.form.service.IScoreFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 评分表单
 *
 * @author Mr.Lai
 * @date 2023/5/5
 */
@Validated
@Api(value = "评分表单控制器", tags = {"评分表单管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/form/score")
public class ScoreFormController extends BaseController {

    @Autowired
    private IScoreFormService scoreFormService;

    /**
     * 查询评分表单列表
     */
    @ApiOperation("查询评分表单列表")
    @GetMapping("/list")
    public TableDataInfo<ScoreFormVO> list(@Validated ScoreFormPageReq pageReq) {
        return scoreFormService.queryPageList(pageReq);
    }

    /**
     * 激活评分表单
     */
    @ApiOperation("激活评分表单")
    @PostMapping("/activateForm/{id}")
    public AjaxResult<Boolean> activateForm(@ApiParam("主键") @NotNull(message = "主键不能为空") @PathVariable("id") Long id) {
        return AjaxResult.success(scoreFormService.activateForm(id));
    }

}
