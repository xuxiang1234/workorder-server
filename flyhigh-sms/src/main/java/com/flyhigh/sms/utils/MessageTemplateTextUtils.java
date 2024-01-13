package com.flyhigh.sms.utils;

import com.flyhigh.api.service.DictTypeServiceApi;
import com.flyhigh.common.enums.DictTypeEnum;
import com.flyhigh.common.enums.MessageTemplateEnum;
import com.flyhigh.common.enums.WxMessageTemplateEnum;
import com.flyhigh.common.utils.spring.SpringUtils;

/**
 * @ClassName MessageTemplateTextUtils
 * @Description
 * @Author huangjinhui
 * @Date 2022/4/21 11:06
 * @Version 1.0
 */
public class MessageTemplateTextUtils {

    /**
     * 根据字典值获取字典里面的模板内容
     *
     * @param messageTemplateEnum
     * @return
     */
    public static String getTemplateText(MessageTemplateEnum messageTemplateEnum) {
        return SpringUtils.getBean(DictTypeServiceApi.class).getDictRemark(DictTypeEnum.MESSAGE_TEMPLATE.getType(), messageTemplateEnum.getDictValue());
    }

    /**
     * 根据字典值获取字典里面的模板内容
     *
     * @param wxMessageTemplateEnum
     * @return
     */
    public static String getTemplateText(WxMessageTemplateEnum wxMessageTemplateEnum) {
        return SpringUtils.getBean(DictTypeServiceApi.class).getDictRemark(DictTypeEnum.WX_MESSAGE_TEMPLATE.getType(), wxMessageTemplateEnum.getDictValue());
    }

}
