package com.flyhigh.sms.domain.vo.pushTask;

import com.flyhigh.common.enums.MessageTemplateEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushParam {

    private String groupName;

    private String logoUrl;

    private String title;

    private String body;

    private String url;

    private MessageTemplateEnum messageTemplateEnum;

    private Long businessId;

}
