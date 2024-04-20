package com.omega.servlet;

import com.google.gson.Gson;
import com.omega.entity.Cart;
import com.omega.entity.CartItem;
import com.omega.entity.Furniture;
import com.omega.service.FurnitureService;
import com.omega.service.impl.FurnitureServiceImpl;
import com.omega.util.DataUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class CartServlet
 *
 * @author KennySo
 * @date 2024/4/10
 */
@WebServlet("/cartServlet")
public class CartServlet extends BasicServlet {

    private final FurnitureService furnitureService = new FurnitureServiceImpl();

    // public void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //     String id = request.getParameter("id");
    //     String referer = request.getHeader("Referer");
    //     // 验证参数
    //     if (!DataUtils.transformStringToInteger(id)) {
    //         id = "0";
    //     }
    //
    //     Furniture furniture = furnitureService.getFurnitureById(Integer.valueOf(id));
    //     if (furniture == null) {
    //         response.sendRedirect(referer);
    //         return;
    //     }
    //
    //     // 封装 cartItem
    //     CartItem cartItem = new CartItem(furniture.getId(), furniture.getName(), furniture.getPrice(), 1, furniture.getPrice());
    //     // 从 session 中获取购物车对象
    //     Cart cart = (Cart) request.getSession().getAttribute("cart");
    //     if (cart == null) {
    //         cart = new Cart();
    //         request.getSession().setAttribute("cart", cart);
    //     }
    //     // 将 carItem 加入 cart 中
    //     cart.addItem(cartItem);
    //
    //     // 重定向回原页面
    //     response.sendRedirect(referer);
    // }


    /**
     * 使用 Ajax 来添加购物车
     * @param request request
     * @param response response
     */
    public void addItemByAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String referer = request.getHeader("Referer");
        // 验证参数
        if (!DataUtils.transformStringToInteger(id)) {
            id = "0";
        }

        // 从 session 中获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        // 判断家居是否存在
        Furniture furniture = furnitureService.getFurnitureById(Integer.valueOf(id));
        if (furniture != null) {
            // 封装 cartItem
            CartItem cartItem = new CartItem(furniture.getId(), furniture.getName(), furniture.getPrice(), 1, furniture.getPrice());
            // 将 carItem 加入 cart 中
            cart.addItem(cartItem);
        }

        // 封装响应结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("cartTotalCount", cart.getTotalCount());
        Gson gson = new Gson();
        String resultJson = gson.toJson(resultMap);

        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(resultJson);
    }


    public void updateItemCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String count = request.getParameter("count");
        String referer = request.getHeader("Referer");
        // 验证参数
        if (!DataUtils.transformStringToInteger(id, count)) {
            id = "0";
        }

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 更新购物车
        if (cart != null) {
            cart.updateItemCount(Integer.valueOf(id), Integer.valueOf(count));
        }

        // 重定向回原页面
        response.sendRedirect(referer);
    }


    public void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String referer = request.getHeader("Referer");
        // 验证参数
        if (!DataUtils.transformStringToInteger(id)) {
            id = "0";
        }

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 删除商品
        if (cart != null) {
            cart.deleteItem(Integer.valueOf(id));
        }

        // 重定向回原页面
        response.sendRedirect(referer);
    }

    public void clearItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = request.getHeader("Referer");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clearItems();
        }

        response.sendRedirect(referer);
    }
}

