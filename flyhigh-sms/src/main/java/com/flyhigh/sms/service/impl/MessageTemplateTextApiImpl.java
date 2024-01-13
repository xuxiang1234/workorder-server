package com.flyhigh.sms.service.impl;

import com.flyhigh.api.service.MessageTemplateTextApi;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.enums.WxMessageTemplateEnum;
import com.flyhigh.sms.utils.MessageTemplateTextUtils;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Lai
 * @date 2023/2/22
 */
@Service
public class MessageTemplateTextApiImpl implements MessageTemplateTextApi {

    /**
     * 根据字典值获取字典里面的模板内容
     *
     * @param messageTemplateEnum
     * @return
     */
    @Override
    public String getTemplateText(MessageTemplateEnum messageTemplateEnum) {
        return MessageTemplateTextUtils.getTemplateText(messageTemplateEnum);
    }

    /**
     * 根据字典值获取字典里面的模板内容
     *
     * @param wxMessageTemplateEnum
     * @return
     */
    @Override
    public String getTemplateText(WxMessageTemplateEnum wxMessageTemplateEnum) {
        return MessageTemplateTextUtils.getTemplateText(wxMessageTemplateEnum);
    }
}
