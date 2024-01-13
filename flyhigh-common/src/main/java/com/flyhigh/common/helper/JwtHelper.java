package com.flyhigh.common.helper;

import com.flyhigh.common.config.AppConfig;
import com.flyhigh.common.constant.HttpStatus;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.utils.SM4Util;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.Lai
 * @date 2023/3/11
 */
@Component
public class JwtHelper {

    public final static String JWT_TOKEN = "x-auth-token";

    @Resource
    private HttpServletRequest request;

    /**
     * 获取OpenID
     *
     * @return OpenID
     */
    public String getOpenId() {
        return decryptToken(request.getHeader(JWT_TOKEN));
    }

    public static String decryptToken(String token) {
        try {
            return SM4Util.decryptEcb(AppConfig.getSm4Key(), token);
        } catch (Exception e) {
            throw new ServiceException("认证失败", HttpStatus.UNAUTHORIZED);
        }
    }

}
