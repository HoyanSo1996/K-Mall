package com.omega.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Class WebUtils
 *
 * @author KennySo
 * @date 2024/4/22
 */
public class WebUtils {

    /**
     * 判断是否是Ajax请求
     * @return 是/否
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
