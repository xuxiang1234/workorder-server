package com.flyhigh.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目配置
 *
 * @author Mr.Lai
 * @date 2023/2/14
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    /**
     * SM4 key
     */
    private static String sm4Key;

    /**
     * 新增安全-登录接口
     */
    private static String safetyLoginUrl;

    /**
     * 新增安全-登录接口
     */
    private static String wxTemplateBillUrl;

    public static String getSm4Key() {
        return sm4Key;
    }

    public void setSm4Key(String sm4Key) {
        AppConfig.sm4Key = sm4Key;
    }

    public static String getSafetyLoginUrl() {
        return safetyLoginUrl;
    }

    public void setSafetyLoginUrl(String safetyLoginUrl) {
        AppConfig.safetyLoginUrl = safetyLoginUrl;
    }

    public static String getWxTemplateBillUrl() {
        return wxTemplateBillUrl;
    }

    public void setWxTemplateBillUrl(String wxTemplateBillUrl) {
        AppConfig.wxTemplateBillUrl = wxTemplateBillUrl;
    }
}
