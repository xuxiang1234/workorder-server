package com.flyhigh.admin.controller.sms;

import com.flyhigh.common.annotation.RepeatSubmit;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.sms.domain.vo.messageUserDetail.*;
import com.flyhigh.sms.service.IBizMessageUserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 消息中心-接收人 Controller
 *
 * @author flyhigh
 * @date 2022-03-13
 */
@Validated
@Api(value = "消息中心控制器", tags = {"消息中心管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/sms/message")
public class MessageController extends BaseController {

    @Autowired
    private IBizMessageUserDetailService bizMessageUserDetailService;

    /**
     * 查询消息列表
     */
    @ApiOperation("查询消息接收人列表")
    @GetMapping("/list")
    public TableDataInfo<BizMessageUserDetailVO> list(@Validated BizMessageUserDetailPageReq pageReq) {
        return bizMessageUserDetailService.queryPageList(pageReq);
    }

    /**
     * 修改消息已读
     */
    @ApiOperation("修改消息已读")
    @RepeatSubmit()
    @PostMapping("/edit")
    public AjaxResult<Boolean> edit(@Validated @RequestBody BizMessageUserDetailUpdateReq updateReq) {
        return toAjax(bizMessageUserDetailService.updateBizMessageUserDetail(updateReq));
    }

    /**
     * 消息全部已读
     */
    @ApiOperation("消息全部已读")
    @PostMapping("/allRead")
    public AjaxResult<Boolean> allRead() {
        return toAjax(bizMessageUserDetailService.allRead());
    }

}
