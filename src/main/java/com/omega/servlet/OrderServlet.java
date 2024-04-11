package com.omega.servlet;

import com.omega.entity.Cart;
import com.omega.entity.Member;
import com.omega.service.OrderService;
import com.omega.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.omega.util.CommonUtils.MEMBER_LOGIN_PATH;
import static com.omega.util.CommonUtils.ORDER_CHECKOUT_PATH;

/**
 * Class OrderServlet
 *
 * @author KennySo
 * @date 2024/4/11
 */
@WebServlet("/orderServlet")
public class OrderServlet extends BasicServlet {

    private final OrderService orderService = new OrderServiceImpl();

    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // 增加个逻辑, 判断session存在但里面没商品, 避免生成空订单.
        if (cart == null || cart.isEmpty()) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            request.getRequestDispatcher(MEMBER_LOGIN_PATH).forward(request, response);
            return;
        }

        String orderId = orderService.addOrder(cart, member.getId());
        session.setAttribute("orderId", orderId);
        // 一般都用请求转发，如果是刷新浏览器会造成重复提交表单的请求就用重定向
        response.sendRedirect(request.getContextPath() + ORDER_CHECKOUT_PATH);
    }
}
