package com.flyhigh.sms.service.impl;


import com.alibaba.excel.util.MapUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.api.dto.messageInfo.*;
import com.flyhigh.api.service.MessageBizServiceApi;
import com.flyhigh.common.constant.MessageConstants;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.utils.BracesTemplateParser;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.sms.domain.BizMessageInfo;
import com.flyhigh.sms.domain.convert.BizMessageInfoConvert;
import com.flyhigh.sms.domain.convert.BizMessageInfoCreateConvert;
import com.flyhigh.sms.domain.vo.messageInfo.BizMessageInfoCreateReq;
import com.flyhigh.sms.domain.vo.messageInfo.BizMessageInfoUpdateReq;
import com.flyhigh.sms.service.IBizMessageInfoService;
import com.flyhigh.sms.utils.MessageTemplateTextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageBizServiceImpl
 * @Description
 * @Author huangjinhui
 * @Date 2022/3/15 11:13
 * @Version 1.0
 */
@Service
@Validated
@Slf4j
public class MessageBizServiceImpl implements MessageBizServiceApi {

    @Autowired
    private IBizMessageInfoService iBizMessageInfoService;

    /**
     * 发送消息
     *
     * @param id           业务ID
     * @param userIds      用户ID
     * @param templateEnum 消息类型
     * @return
     */
    @Override
    public AjaxResult sendMessage(Long id, List<Long> userIds, MessageTemplateEnum templateEnum) {
        return this.sendMessage(id, userIds, templateEnum, MapUtils.newHashMap());
    }

    /**
     * 发送消息
     *
     * @param id            业务ID
     * @param userIds       用户ID
     * @param templateEnum  消息类型
     * @param templateParam 参数
     * @return
     */
    @Override
    public AjaxResult sendMessage(Long id, List<Long> userIds, MessageTemplateEnum templateEnum, Map<String, String> templateParam) {
        BizMessageInfoCreateDTO bizMessageInfoCreateDTO = new BizMessageInfoCreateDTO();
        bizMessageInfoCreateDTO.setMessageTemplate(templateEnum);
        bizMessageInfoCreateDTO.setBusinessId(id);
        bizMessageInfoCreateDTO.setUserIds(userIds);
        bizMessageInfoCreateDTO.setMessageSender(MessageTemplateEnum.getMessageByValue(templateEnum.getDictValue()));
        bizMessageInfoCreateDTO.setTemplateParam(templateParam);

        return this.createMessage(bizMessageInfoCreateDTO);
    }

    /**
     * 创建消息
     *
     * @param bizMessageInfoCreateDTO
     * @return
     */
    @Override
    public AjaxResult createMessage(BizMessageInfoCreateDTO bizMessageInfoCreateDTO) {
        BizMessageInfoCreateReq bizMessageInfoCreateReq = BizMessageInfoCreateConvert.INSTANCE.convert(bizMessageInfoCreateDTO);
        String templateText = MessageTemplateTextUtils.getTemplateText(bizMessageInfoCreateReq.getMessageTemplate());
        if (StringUtils.isEmpty(templateText)) {
            return AjaxResult.error(ErrorCodeConstants.MESSAGE_TEMPLATE_NOT_EXISTS);
        }
        if (CollectionUtils.isEmpty(bizMessageInfoCreateReq.getUserIds())) {
            return AjaxResult.error(ErrorCodeConstants.MESSAGE_NOT_USERS);
        }
        // 解析参数
        String parseResult = BracesTemplateParser.parse(templateText, bizMessageInfoCreateReq.getTemplateParam());
        // 验证解析结果
        if (StringUtils.contains(parseResult, MessageConstants.MESSAGE_TEMPLATE_PARSER_NULL)) {
            return AjaxResult.error(ErrorCodeConstants.MESSAGE_TEMPLATE_PARSER_ERROR);
        }
        if (Strings.isEmpty(bizMessageInfoCreateReq.getMessageSender())) {
            bizMessageInfoCreateReq.setMessageSender(MessageConstants.DEFAULT_MESSAGE_SENDER);
        }
        // 异步处理
        BizMessageInfo bizMessageInfo = BizMessageInfoConvert.INSTANCE.convert(bizMessageInfoCreateReq);
        bizMessageInfo.setMessageType(bizMessageInfoCreateReq.getMessageTemplate().getType());
        bizMessageInfo.setModuleType(bizMessageInfoCreateReq.getMessageTemplate().getDictValue());
        bizMessageInfo.setComponent(Strings.EMPTY);
        bizMessageInfo.setContent(parseResult);
        iBizMessageInfoService.createBizMessageInfo(bizMessageInfo, bizMessageInfoCreateReq.getUserIds());
        return AjaxResult.success();
    }

    /**
     * 删除消息通知
     *
     * @param bizMessageInfoDeleteDTO
     * @return
     */
    @Override
    public AjaxResult deleteMessage(BizMessageInfoDeleteDTO bizMessageInfoDeleteDTO) {
        String templateText = MessageTemplateTextUtils.getTemplateText(bizMessageInfoDeleteDTO.getMessageTemplate());
        if (StringUtils.isEmpty(templateText)) {
            return AjaxResult.error(ErrorCodeConstants.MESSAGE_TEMPLATE_NOT_EXISTS);
        }
        // 异步处理
        iBizMessageInfoService.deleteMessage(bizMessageInfoDeleteDTO);
        return AjaxResult.success();
    }

    /**
     * 修改消息
     *
     * @param updateReq
     * @return
     */
    @Override
    public AjaxResult editMessage(BizMessageInfoUpdateDTO updateReq) {
        BizMessageInfoUpdateReq bizMessageInfoUpdateReq = BizMessageInfoCreateConvert.INSTANCE.convert(updateReq);
        String templateText = MessageTemplateTextUtils.getTemplateText(bizMessageInfoUpdateReq.getMessageTemplate());
        if (StringUtils.isEmpty(templateText)) {
            return AjaxResult.error(ErrorCodeConstants.MESSAGE_TEMPLATE_NOT_EXISTS);
        }
        if (updateReq.isWhetherCascadeUser() && CollectionUtils.isEmpty(bizMessageInfoUpdateReq.getUserIds())) {
            return AjaxResult.error(ErrorCodeConstants.MESSAGE_NOT_USERS);
        }
        // 解析参数
        String parseResult = BracesTemplateParser.parse(templateText, bizMessageInfoUpdateReq.getTemplateParam());
        // 验证解析结果
        if (StringUtils.contains(parseResult, MessageConstants.MESSAGE_TEMPLATE_PARSER_NULL)) {
            return AjaxResult.error(ErrorCodeConstants.MESSAGE_TEMPLATE_PARSER_ERROR);
        }
        // 异步处理
        BizMessageInfo bizMessageInfo = BizMessageInfoConvert.INSTANCE.convert(bizMessageInfoUpdateReq);
        bizMessageInfo.setContent(parseResult);
        iBizMessageInfoService.editBizMessageInfo(bizMessageInfo, updateReq.getUserIds(), updateReq.getMessageTemplate(), updateReq.isWhetherCascadeUser());
        return AjaxResult.success();
    }

    /**
     * 根据业务id消息接收人
     *
     * @param messageTemplateEnum
     * @param businessId
     * @return
     */
    @Override
    public AjaxResult<List<BizMessageUserDetailDTO>> getMessageRecipients(MessageTemplateEnum messageTemplateEnum, Long businessId) {
        return iBizMessageInfoService.getMessageRecipients(messageTemplateEnum, businessId);
    }
}
