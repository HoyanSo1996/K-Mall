package com.omega.servlet;

import com.omega.entity.Admin;
import com.omega.service.AdminService;
import com.omega.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.omega.util.CommonUtils.*;

/**
 * Class AdminServlet
 *
 * @author KennySo
 * @date 2024/3/28
 */
@WebServlet("/adminServlet")
public class AdminServlet extends BasicServlet {

    private final AdminService adminService = new AdminServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Todo 在后端使用正则表达式校验数据格式..

        // 判断用户是否存在
        if (!adminService.isExistUsername(username)) {
            request.setAttribute("error_msg", "用户名不存在");
            request.setAttribute("username", username);
            request.getRequestDispatcher(ADMIN_LOGIN_PATH).forward(request, response);

        } else {
            // 登录
            Admin admin = new Admin(null, username, password, null, null, null);
            if (adminService.login(admin)) {
                request.getRequestDispatcher(ADMIN_MANAGE_MENU_PATH).forward(request, response);

            } else {
                request.setAttribute("error_msg", "密码错误");
                request.setAttribute("username", username);
                request.getRequestDispatcher(ADMIN_LOGIN_PATH).forward(request, response);
            }
        }
    }
}
