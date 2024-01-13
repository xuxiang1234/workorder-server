package com.flyhigh.common.core.domain.model;

/**
 * 个人重置密码对象
 *
 * @author flyhigh
 */
public class ProfileUpdatePwdBody {

    /**
     * 用户信息
     * reverse(base64Encode(oldPassword).base64Encode(newPassword))
     */
    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}
