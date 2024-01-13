package com.flyhigh.api.service;

import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.enums.WxMessageTemplateEnum;

/**
 * @author Mr.Lai
 */
public interface MessageTemplateTextApi {

    /**
     * 根据字典值获取字典里面的模板内容
     *
     * @param messageTemplateEnum
     * @return
     */
    String getTemplateText(MessageTemplateEnum messageTemplateEnum);

    /**
     * 根据字典值获取字典里面的模板内容
     *
     * @param wxMessageTemplateEnum
     * @return
     */
    String getTemplateText(WxMessageTemplateEnum wxMessageTemplateEnum);

}
