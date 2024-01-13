package com.flyhigh.admin.controller.system;

import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.domain.entity.SysMenu;
import com.flyhigh.common.core.domain.entity.SysUser;
import com.flyhigh.common.core.domain.model.LoginAuthBody;
import com.flyhigh.common.core.domain.model.LoginBody;
import com.flyhigh.common.utils.SecurityUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.framework.web.service.SysLoginService;
import com.flyhigh.framework.web.service.SysPermissionService;
import com.flyhigh.system.domain.vo.RouterVo;
import com.flyhigh.system.domain.vo.sysUser.TokenVo;
import com.flyhigh.system.domain.vo.sysUser.UserInfoVo;
import com.flyhigh.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录验证
 *
 * @author flyhigh
 */
@Api(value = "登录管理", tags = {"登录管理"})
@RestController
public class SysLoginController {

    /**
     * 分隔符
     */
    private static final String SEPARATOR = "\\.";

    /**
     * 登录参数长度
     */
    private static final Integer AUTH_PAYLOAD = 3;

    /**
     * 过期时间：30秒
     */
    private static final Integer EXPIRE_TIME_MILLIS = 30000;

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("登录方法")
    @PostMapping("/login")
    public AjaxResult<TokenVo> login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid(), StringUtils.isBlank(loginBody.getPlatform()));
        TokenVo tokenVo = new TokenVo();
        tokenVo.setToken(token);
        return AjaxResult.success(tokenVo);
    }

    /**
     * 登录方法-auth
     * 1. 字符串倒置
     * 2. base64解码
     * 3. 用户名、密码和时间戳（时间戳验证-30S内有效）
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("登录方法-auth")
    @PostMapping("/auth")
    public AjaxResult<TokenVo> auth(@RequestBody LoginAuthBody loginBody) {
        String payload = loginBody.getPayload();
        if (StringUtils.isBlank(payload)) {
            throw new IllegalArgumentException("参数错误");
        }
        // 字符串倒置
        String reversePayload = new StringBuffer(payload).reverse().toString();
        // 截取分隔符，去空
        List<String> loginInfo = Arrays.stream(reversePayload.split(SEPARATOR))
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .collect(Collectors.toList());
        if (loginInfo.size() != AUTH_PAYLOAD) {
            throw new IllegalArgumentException("参数错误");
        }
        // 用户名
        String username = loginService.base64Decoding(loginInfo.get(0));
        // 密码
        String password = loginService.base64Decoding(loginInfo.get(1));
        // 时间戳
//        String timeStamp = loginService.base64Decoding(loginInfo.get(2));
        // 验证时间戳
//        String timeStampStr = StringUtils.isNotBlank(timeStamp) && StringUtils.isNumeric(timeStamp) ? timeStamp : "0";
//        Long timeStampValue = new Long(timeStampStr);
//        // 有效期内
//        if (System.currentTimeMillis() - timeStampValue >= EXPIRE_TIME_MILLIS) {
//            throw new IllegalArgumentException("参数错误");
//        }

        // 生成令牌
        String token = loginService.login(username, password, loginBody.getCode(), loginBody.getUuid(), StringUtils.isBlank(loginBody.getPlatform()));
        TokenVo tokenVo = new TokenVo();
        tokenVo.setToken(token);
        return AjaxResult.success(tokenVo);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("getInfo")
    public AjaxResult<UserInfoVo> getInfo() {
        UserInfoVo userInfoVo = new UserInfoVo();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        userInfoVo.setUser(user);
        userInfoVo.setRoles(roles);
        userInfoVo.setPermissions(permissions);
        userInfoVo.setWhetherDispatcherUser(SecurityUtils.whetherDispatcherUser());
        return AjaxResult.success(userInfoVo);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @ApiOperation("获取路由信息")
    @GetMapping("getRouters")
    public AjaxResult<List<RouterVo>> getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 注销用户
     */
    @PostMapping("/logoff")
    public AjaxResult<Boolean> logoff() {
        return AjaxResult.success(loginService.logoff());
    }

}
