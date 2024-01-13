package com.flyhigh.framework.security.filter;

import com.alibaba.fastjson.JSON;
import com.flyhigh.common.constant.HttpStatus;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.utils.ServletUtils;
import com.flyhigh.common.utils.StringUtils;
import com.flyhigh.common.helper.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mr.Lai
 * @date 2023/3/11
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/wx/*", filterName = "jwtAuthFilter")
public class JwtAuthFilter implements Filter {

    /**
     * 取消过滤的路径
     */
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/wx/login", "/wx", "/wx/validate")));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        String wxPrefix = "/wx";
        String wxCompanyPrefix = "/wx/company";
        if (!request.getRequestURI().startsWith(wxPrefix) || request.getRequestURI().startsWith(wxCompanyPrefix) || allowedPath) {
            filterChain.doFilter(request, servletResponse);
            return;
        }

        // 请求参数
        String token = request.getHeader(JwtHelper.JWT_TOKEN);
        if (StringUtils.isNotBlank(token)) {
            log.info("请求链接:{} 参数：{} ", request.getRequestURI(), token);
        }

        if (StringUtils.isBlank(token)) {
            this.unauthorizedHandler(request, response);
            return;
        }

        String openId = JwtHelper.decryptToken(token);
        log.info("openId {}", openId);

        // 放行
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }

    private void unauthorizedHandler(HttpServletRequest request, HttpServletResponse response) {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }

}
