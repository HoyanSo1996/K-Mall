package com.omega.servlet;

import com.omega.entity.Member;
import com.omega.service.MemberService;
import com.omega.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class LoginServlet
 *
 * @author KennySo
 * @date 2024/3/28
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private final MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 判断用户是否存在
        if (memberService.isExistUsername(username)) {
            // 登录
            Member member = new Member(null, username, password, null, null, null);
            if (memberService.login(member)) {
                request.getRequestDispatcher("/views/member/login_ok.html").forward(request, response);
            } else {
                request.setAttribute("error_msg", "密码错误");
                request.setAttribute("username", username);
                request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error_msg", "用户名不存在");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
