package com.flyhigh.framework.web.service;

import javax.annotation.Resource;

import com.flyhigh.common.utils.*;
import com.flyhigh.common.utils.sign.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.flyhigh.common.constant.Constants;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.core.redis.RedisCache;
import com.flyhigh.common.exception.ServiceException;
import com.flyhigh.common.exception.user.CaptchaException;
import com.flyhigh.common.exception.user.CaptchaExpireException;
import com.flyhigh.common.exception.user.UserPasswordNotMatchException;
import com.flyhigh.common.utils.ip.IpUtils;
import com.flyhigh.framework.manager.AsyncManager;
import com.flyhigh.framework.manager.factory.AsyncFactory;
import com.flyhigh.system.service.ISysConfigService;
import com.flyhigh.system.service.ISysUserService;

import java.util.Optional;

/**
 * 登录校验方法
 *
 * @author flyhigh
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录验证
     *
     * @param username    用户名
     * @param password    密码
     * @param code        验证码
     * @param uuid        唯一标识
     * @param captchaFlag 是否开启验证码
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid, Boolean captchaFlag) {
        boolean captchaOnOff = configService.selectCaptchaOnOff();
        // 验证码开关
//        if (captchaOnOff && captchaFlag) {
//            validateCaptcha(username, code, uuid);
//        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }

    /**
     * Base64解码，补全符合（=）
     *
     * @param base64Value
     * @return
     */
    public String base64Decoding(String base64Value) {
        // base64四位不足时补全=
        int base64Length = 4;
        int surplus = base64Value.length() % base64Length;
        if (surplus != 0) {
            // 最后需要补全的长度：3补1
            String substring = base64Value.substring(base64Value.length() - surplus);
            // 补全符号
            base64Value += StringUtils.rightPad(StringUtils.EMPTY, base64Length - substring.length(), "=");
        }
        return new String(Optional.ofNullable(Base64.decode(base64Value)).orElse(new byte[0]));
    }

    /**
     * 注销用户
     *
     * @return
     */
    public Boolean logoff() {
        return userService.deleteUserById(SecurityUtils.getUserId()) > 0;
    }

}
