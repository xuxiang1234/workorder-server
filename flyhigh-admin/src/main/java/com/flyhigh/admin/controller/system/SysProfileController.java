package com.flyhigh.admin.controller.system;

import com.alibaba.excel.util.StringUtils;
import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.config.FlyhighConfig;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.core.domain.model.LoginUser;
import com.flyhigh.common.core.domain.model.ProfileUpdatePwdBody;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.file.FileUploadUtils;
import com.flyhigh.framework.web.service.SysLoginService;
import com.flyhigh.framework.web.service.TokenService;
import com.flyhigh.system.domain.vo.sysUser.UserAvatarVo;
import com.flyhigh.system.domain.vo.sysUser.UserProfileVo;
import com.flyhigh.system.service.ISysProfileService;
import com.flyhigh.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 个人信息 业务处理
 *
 * @author flyhigh
 */
@Api(value = "个人信息管理", tags = {"个人信息管理"})
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {

    /**
     * 分隔符
     */
    private static final String SEPARATOR = "\\.";

    /**
     * 登录参数长度
     */
    private static final Integer AUTH_PAYLOAD = 2;


    @Autowired
    private SysLoginService loginService;
    @Autowired
    private ISysProfileService sysProfileService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserService userService;

    /**
     * 个人信息
     */
    @GetMapping("/getInfo")
    public AjaxResult<UserProfileVo> profile() {
        UserProfileVo userProfileVo = sysProfileService.profile();
        return AjaxResult.success(userProfileVo);
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("updateProfile")
    public AjaxResult<String> updateProfile(@RequestBody SysUser user) {
        boolean success = sysProfileService.updateProfile(user);
        if (success) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            SysUser sysUser = loginUser.getUser();
            // 更新缓存用户信息
            sysUser.setNickName(user.getNickName());
            sysUser.setPhonenumber(user.getPhonenumber());
            sysUser.setEmail(user.getEmail());
            sysUser.setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success("个人信息修改成功！");
        }
        return AjaxResult.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult<Boolean> updatePwd(@RequestBody ProfileUpdatePwdBody updatePwdBody) {
        LoginUser loginUser = getLoginUser();
        String payload = updatePwdBody.getPayload();
        if (StringUtils.isBlank(payload)) {
            throw new IllegalArgumentException("参数错误");
        }
        // 字符串倒置
        String reversePayload = new StringBuffer(payload).reverse().toString();
        // 截取分隔符，去空
        List<String> pwdInfo = Arrays.stream(reversePayload.split(SEPARATOR))
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .collect(Collectors.toList());
        if (pwdInfo.size() != AUTH_PAYLOAD) {
            throw new IllegalArgumentException("参数错误");
        }
        // 旧密码
        String oldPassword = loginService.base64Decoding(pwdInfo.get(0));
        // 新密码
        String newPassword = loginService.base64Decoding(pwdInfo.get(1));
        boolean success = sysProfileService.resetUserPwd(oldPassword, newPassword);
        if (success) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult<UserAvatarVo> avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload(FlyhighConfig.getAvatarPath(), file);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar)) {
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return AjaxResult.success(new UserAvatarVo(avatar));
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
}
