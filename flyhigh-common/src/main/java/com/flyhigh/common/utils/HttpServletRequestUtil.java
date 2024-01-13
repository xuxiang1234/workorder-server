package com.flyhigh.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.Lai
 * @date 2023/2/2
 */
public class HttpServletRequestUtil {

    /**
     * 获取请求域名
     *
     * @param request request
     * @return 结果
     */
    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
