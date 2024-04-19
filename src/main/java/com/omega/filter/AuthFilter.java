package com.omega.filter;

import com.omega.entity.Member;
import com.omega.util.CommonUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.omega.util.CommonUtils.*;

/**
 * Class AuthFilter
 *
 * @author KennySo
 * @date 2024/4/16
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    private List<String> excludeUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String[] urls = new String[] {
                "/assets/",
                "/script/",
                ADMIN_LOGIN_PATH,
                CUSTOMER_FURNITURE_PATH,
                MEMBER_LOGIN_PATH, MEMBER_LOGIN_SUCCEED_PATH, MEMBER_REGISTRATION_SUCCEED_PATH, MEMBER_REGISTRATION_SUCCEED_PATH,
                INDEX_PAGE,
                "/adminServlet",
                "/customerFurnitureServlet",
                "/memberServlet",
                "/kaptchaServlet"
        };
        excludeUrls = Arrays.asList(urls);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // HttpServletRequest 才有获取 url 的 API.
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getServletPath();

        // 判断是否是放行资源, 是就放行
        for (String excludeUrl : excludeUrls) {
            if (url.contains(excludeUrl)) {
                chain.doFilter(request, servletResponse);
                return;   // 这里一定要加return
            }
        }

        Member member = (Member) request.getSession().getAttribute("member");
        if (member == null) {
            // 请求转发跳转去资源时, 是不会经过过滤器的.(相当于下面的地址放行了)
            request.getRequestDispatcher(CommonUtils.MEMBER_LOGIN_PATH).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

}
