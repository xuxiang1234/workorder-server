package com.flyhigh.api.service;


import com.flyhigh.api.dto.messageInfo.*;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.enums.MessageTemplateEnum;

import java.util.List;
import java.util.Map;

/**
 * 消息相关
 *
 * @ClassName MessageBizService
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 11:13
 * @Version 1.0
 */
public interface MessageBizServiceApi {

    /**
     * 发送消息
     *
     * @param id           业务ID
     * @param userIds      用户ID
     * @param templateEnum 消息类型
     * @return
     */
    AjaxResult sendMessage(Long id, List<Long> userIds, MessageTemplateEnum templateEnum);

    /**
     * 发送消息
     *
     * @param id            业务ID
     * @param userIds       用户ID
     * @param templateEnum  消息类型
     * @param templateParam 参数
     * @return
     */
    AjaxResult sendMessage(Long id, List<Long> userIds, MessageTemplateEnum templateEnum, Map<String, String> templateParam);

    /**
     * 新增消息
     *
     * @param bizMessageInfoCreateDTO
     * @return
     */
    AjaxResult createMessage(BizMessageInfoCreateDTO bizMessageInfoCreateDTO);

    /**
     * 删除消息通知
     *
     * @param bizMessageInfoDeleteDTO
     * @return
     */
    AjaxResult deleteMessage(BizMessageInfoDeleteDTO bizMessageInfoDeleteDTO);

    /**
     * 修改消息
     *
     * @param updateReq
     * @return
     */
    AjaxResult editMessage(BizMessageInfoUpdateDTO updateReq);

    /**
     * 根据业务id消息接收人
     *
     * @param messageTemplateEnum
     * @param businessId
     * @return
     */
    AjaxResult<List<BizMessageUserDetailDTO>> getMessageRecipients(MessageTemplateEnum messageTemplateEnum, Long businessId);

}
