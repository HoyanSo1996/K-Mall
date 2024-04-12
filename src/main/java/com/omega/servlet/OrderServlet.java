package com.omega.servlet;

import com.omega.entity.Cart;
import com.omega.entity.Member;
import com.omega.entity.Order;
import com.omega.entity.OrderItem;
import com.omega.service.OrderItemService;
import com.omega.service.OrderService;
import com.omega.service.impl.OrderItemServiceImpl;
import com.omega.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.omega.util.CommonUtils.*;

/**
 * Class OrderServlet
 *
 * @author KennySo
 * @date 2024/4/11
 */
@WebServlet("/orderServlet")
public class OrderServlet extends BasicServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final OrderItemService orderItemService = new OrderItemServiceImpl();


    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // 增加个逻辑, 判断session存在但里面没商品, 避免生成空订单.
        if (cart == null || cart.isEmpty()) {
            // 一般都用请求转发，如果是刷新浏览器会造成重复提交表单的请求就用重定向
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            request.getRequestDispatcher(MEMBER_LOGIN_PATH).forward(request, response);
            return;
        }

        String orderId = orderService.addOrder(cart, member.getId());
        session.setAttribute("orderId", orderId);
        // 重定向
        response.sendRedirect(request.getContextPath() + ORDER_CHECKOUT_PATH);
    }


    public void queryOrderByMemberId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            request.getRequestDispatcher(MEMBER_LOGIN_PATH).forward(request, response);
            return;
        }

        List<Order> orderList = orderService.getOrderByMemberId(member.getId());
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher(ORDER_PATH).forward(request, response);
    }


    public void queryOrderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            request.getRequestDispatcher(MEMBER_LOGIN_PATH).forward(request, response);
            return;
        }

        Order order = orderService.getOrderById(orderId);
        List<OrderItem> orderItemList = orderItemService.getOrderItemByOrderId(orderId);
        request.setAttribute("order", order);
        request.setAttribute("orderItemList", orderItemList);
        request.setAttribute("orderItemCount", orderItemList.size());
        request.getRequestDispatcher(ORDER_DETAIL_PATH).forward(request, response);
    }
}
