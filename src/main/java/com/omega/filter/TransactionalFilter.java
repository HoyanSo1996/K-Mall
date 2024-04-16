package com.omega.filter;

import com.omega.util.JdbcUtilsByDruid;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Class TransactionalFilter
 *
 * @author KennySo
 * @date 2024/4/16
 */
@WebFilter("/*")
public class TransactionalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            chain.doFilter(request, response);
            JdbcUtilsByDruid.commit();  // 统一提交
        } catch (Exception e) {
            JdbcUtilsByDruid.rollback();    // 出现异常, 统一回滚
            e.printStackTrace();
        }
    }
}
