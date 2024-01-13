package com.flyhigh.sms.config;

import com.getui.push.v2.sdk.ApiHelper;
import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.api.PushApi;
import com.getui.push.v2.sdk.api.UserApi;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName GtMsgConfig
 * @Description 个推配置类
 * @Author huangjinhui
 * @Date 2022/3/31 13:24
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "gt")
@Setter
public class GtMsgConfig {

    private String appId;

    private String appKey;

    private String masterSecret;

    /**
     * 离线消息安卓手机 包名
     **/
    public static String androidPackage;

    public void setAndroidPackage(String androidPackage) {
        GtMsgConfig.androidPackage = androidPackage;
    }

    /**
     * 配置 apiHelper
     *
     * @return
     */
    @Bean(name = "apiHelper")
    public ApiHelper apiHelper() {
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        apiConfiguration.setAppId(appId);
        apiConfiguration.setAppKey(appKey);
        apiConfiguration.setMasterSecret(masterSecret);
        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        return apiHelper;
    }

    /**
     * JDK动态代理类
     *
     * @return
     */
    @Bean(name = "pushApi")
    public PushApi pushApi() {
        return apiHelper().creatApi(PushApi.class);
    }

    /**
     * JDK动态代理类
     *
     * @return
     */
    @Bean(name = "userApi")
    public UserApi userApi() {
        return apiHelper().creatApi(UserApi.class);
    }

}
