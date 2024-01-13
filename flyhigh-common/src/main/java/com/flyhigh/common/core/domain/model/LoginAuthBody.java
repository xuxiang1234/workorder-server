package com.flyhigh.common.core.domain.model;

/**
 * 用户登录授权对象
 *
 * @author flyhigh
 */
public class LoginAuthBody {

    /**
     * 用户信息
     * reverse(base64Encode(username).base64Encode(password).base64Encode(timeStamp))
     */
    private String payload;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";

    /**
     * 平台：app （不为空则取消验证码）
     */
    private String platform;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
