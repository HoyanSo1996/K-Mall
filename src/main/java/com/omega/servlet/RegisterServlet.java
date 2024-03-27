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
 * Class MemberServlet
 *
 * @author KennySo
 * @date 2024/3/27
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    private final MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // Todo 在后端使用正则表达式校验数据格式..

        // 判断用户名是否被占用
        if (memberService.isExistUsername(username)) {
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        } else {
            // 注册用户
            Member member = new Member(null, username, password, email, null, null);
            if (memberService.register(member)) {
                request.getRequestDispatcher("/views/member/register_ok.html").forward(request, response);
            } else {
                request.getRequestDispatcher("/views/member/register_fail.html").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
