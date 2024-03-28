package com.omega.servlet;

import com.omega.entity.Member;
import com.omega.service.MemberService;
import com.omega.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.omega.util.CommonUtils.*;

/**
 * Class MemberServlet
 *
 * @author KennySo
 * @date 2024/3/28
 */
@WebServlet("/memberServlet")
public class MemberServlet extends BasicServlet {

    private final MemberService memberService = new MemberServiceImpl();

    /**
     * 注册方法
     * @param request request
     * @param response response
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // Todo 在后端使用正则表达式校验数据格式..

        // 判断用户名是否被占用
        if (memberService.isExistUsername(username)) {
            request.getRequestDispatcher(MEMBER_LOGIN_PATH).forward(request, response);

        } else {
            // 注册用户
            Member member = new Member(null, username, password, email, null, null);
            if (memberService.register(member)) {
                request.getRequestDispatcher(MEMBER_REGISTRATION_SUCCEED_PATH).forward(request, response);
            } else {
                request.getRequestDispatcher(MEMBER_REGISTRATION_FAIL_PATH).forward(request, response);
            }
        }
    }


    /**
     * 登录方法
     * @param request request
     * @param response response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Todo 在后端使用正则表达式校验数据格式..

        // 判断用户是否存在
        if (!memberService.isExistUsername(username)) {
            request.setAttribute("error_msg", "用户名不存在");
            request.setAttribute("username", username);
            request.getRequestDispatcher(MEMBER_LOGIN_PATH).forward(request, response);

        } else {
            // 登录
            Member member = new Member(null, username, password, null, null, null);
            if (memberService.login(member)) {
                request.getRequestDispatcher(MEMBER_LOGIN_SUCCEED_PATH).forward(request, response);

            } else {
                request.setAttribute("error_msg", "密码错误");
                request.setAttribute("username", username);
                request.getRequestDispatcher(MEMBER_LOGIN_PATH).forward(request, response);
            }
        }
    }
}
