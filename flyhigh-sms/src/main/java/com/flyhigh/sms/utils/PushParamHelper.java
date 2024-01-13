package com.flyhigh.sms.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.flyhigh.sms.config.GtMsgConfig;
import com.flyhigh.sms.domain.vo.pushTask.PushParam;
import com.getui.push.v2.sdk.dto.req.Audience;
import com.getui.push.v2.sdk.dto.req.Settings;
import com.getui.push.v2.sdk.dto.req.Strategy;
import com.getui.push.v2.sdk.dto.req.message.PushChannel;
import com.getui.push.v2.sdk.dto.req.message.PushDTO;
import com.getui.push.v2.sdk.dto.req.message.PushMessage;
import com.getui.push.v2.sdk.dto.req.message.android.AndroidDTO;
import com.getui.push.v2.sdk.dto.req.message.android.ThirdNotification;
import com.getui.push.v2.sdk.dto.req.message.android.Ups;
import com.getui.push.v2.sdk.dto.req.message.ios.Alert;
import com.getui.push.v2.sdk.dto.req.message.ios.Aps;
import com.getui.push.v2.sdk.dto.req.message.ios.IosDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * 创建push message body
 */
@Slf4j
public class PushParamHelper {


    public static PushDTO<Audience> buildPushDTO(PushParam pushParam) {
        PushDTO<Audience> pushDTO = new PushDTO<>();

        // 设置推送参数，requestid需要每次变化唯一
        pushDTO.setRequestId(System.currentTimeMillis() + "");
        pushDTO.setGroupName(pushParam.getGroupName());
        Settings settings = getSettings();
        pushDTO.setSettings(settings);

        // 在线走个推通道时推送的消息体
        PushMessage pushMessage = getPushMessage(pushParam);
        pushDTO.setPushMessage(pushMessage);

        // 设置离线推送时的消息体
        PushChannel pushChannel = new PushChannel();

        //安卓离线厂商通道推送的消息体
        AndroidDTO androidDTO = getAndroidDTO(pushParam);
        pushChannel.setAndroid(androidDTO);

        // ios离线apn通道推送的消息体
        getIOSDTO(pushParam, pushChannel);
        pushDTO.setPushChannel(pushChannel);
        return pushDTO;
    }

    private static PushMessage getPushMessage(PushParam pushParam) {
        PushMessage pushMessage = new PushMessage();

        //此格式的透传消息由 unipush 做了特殊处理，会自动展示通知栏。开发者也可自定义其它格式，在客户端自己处理。
        PushTransmission pushTransmission = PushTransmission.builder().title(pushParam.getTitle()).content(pushParam.getBody())
                .payload(new PushPayload(String.valueOf(pushParam.getBusinessId()), pushParam.getMessageTemplateEnum().getDictValue())).build();

        // 设置透传消息
        pushMessage.setTransmission(JSON.toJSONString(pushTransmission));
        log.info("设置透传消息内容：{}", pushMessage.getTransmission());
        return pushMessage;
    }

    private static Settings getSettings() {
        Settings settings = new Settings();

        // 消息有效期，走厂商消息必须设置该值
        settings.setTtl(3600000 * 24 * 3);
        Strategy strategy = new Strategy();
        strategy.setSt(1);
        settings.setStrategy(strategy);
        return settings;
    }

    private static void getIOSDTO(PushParam pushParam, PushChannel pushChannel) {
        Alert alert = new Alert();

        // 苹果离线通知栏标题
        alert.setTitle(pushParam.getTitle());

        // 苹果离线通知栏内容
        alert.setBody(pushParam.getBody());

        Aps aps = new Aps();
        aps.setContentAvailable(0);
        aps.setSound("default");
        aps.setAlert(alert);
        IosDTO iosDTO = new IosDTO();
        iosDTO.setAps(aps);
        iosDTO.setType("notify");
        pushChannel.setIos(iosDTO);
    }

    private static AndroidDTO getAndroidDTO(PushParam pushParam) {
        AndroidDTO androidDTO = new AndroidDTO();
        Ups ups = new Ups();
        ThirdNotification thirdNotification = new ThirdNotification();
        ups.setNotification(thirdNotification);

        // 安卓离线展示的标题
        thirdNotification.setTitle(pushParam.getTitle());

        // 安卓离线展示的内容
        thirdNotification.setBody(pushParam.getBody());

        // 注意：intent参数必须按下方文档（特殊参数说明）要求的固定格式传值，intent错误会导致客户端无法收到消息 请填写固定格式的intent
        thirdNotification.setClickType("intent");

        String intentBody = StrUtil.format("intent://io.dcloud.unipush/?#Intent;scheme=unipush;launchFlags=0x4000000;component=io.dcloud.HBuilder/{};S.UP-OL-SU=true;S.title={};S.content={};S.payload={};end"
                , GtMsgConfig.androidPackage, pushParam.getTitle(), pushParam.getBody(), JSON.toJSONString(new PushPayload(String.valueOf(pushParam.getBusinessId()), pushParam.getMessageTemplateEnum().getDictValue())));
        log.info("安卓离线消息intent内容：{}", intentBody);
        thirdNotification.setIntent(intentBody);
        androidDTO.setUps(ups);
        return androidDTO;
    }

}
