package com.omega.service.impl;

import com.omega.dao.FurnitureDAO;
import com.omega.dao.OrderDAO;
import com.omega.dao.OrderItemDAO;
import com.omega.dao.impl.FurnitureDAOImpl;
import com.omega.dao.impl.OrderDAOImpl;
import com.omega.dao.impl.OrderItemDAOImpl;
import com.omega.entity.*;
import com.omega.service.OrderService;

import java.util.HashMap;
import java.util.Set;

/**
 * Class OrderServiceImpl
 *
 * @author KennySo
 * @date 2024/4/11
 */
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private final FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    /**
     * 保存订单
     * @param cart      购物车
     * @param memberId  会员号
     * @return          生成的订单号
     *
     * Tips:
     *   1.每做一个接口, 都写个Test测试下, 方便和前端联调时出错后的校验
     *   2.先写完一个能成功的运行的程序后, 再考虑其中参数的校验, 逻辑的校验
     */
    @Override
    public String addOrder(Cart cart, Integer memberId) {
        // 1.操作order表
        // 封装order
        String orderId = System.currentTimeMillis() + "" + memberId;
        Order order = new Order();
        order.setId(orderId);
        order.setPrice(cart.getTotalPrice());
        order.setStatus(0);
        order.setMemberId(memberId);
        // 插入order表
        orderDAO.insert(order);

        // 2.操作orderItem表
        HashMap<Integer, CartItem> cartItems = cart.getItems();
        Set<Integer> keys = cartItems.keySet();
        for (Integer id : keys) {
            CartItem cartItem = cartItems.get(id);
            OrderItem orderItem = new OrderItem(null,
                    cartItem.getName(),
                    cartItem.getPrice(),
                    cartItem.getCount(),
                    cartItem.getTotalPrice(),
                    orderId);
            // 插入orderItem表
            orderItemDAO.insert(orderItem);

            // 3.操作furniture表
            Furniture furniture = furnitureDAO.selectOneById(id);
            furniture.setStock(furniture.getStock() - cartItem.getCount());
            furniture.setSales(furniture.getSales() + cartItem.getCount());
            furnitureDAO.update(furniture);
        }

        // 4.清空购物车
        cart.clearItems();
        return orderId;
    }
}
