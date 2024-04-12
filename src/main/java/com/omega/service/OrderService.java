package com.omega.service;

import com.omega.entity.Cart;
import com.omega.entity.Order;

import java.util.List;

/**
 * Class OrderService
 *
 * @author KennySo
 * @date 2024/4/11
 */
public interface OrderService {

    String addOrder(Cart cart, Integer memberId);

    List<Order> getOrderByMemberId(Integer memberId);

    Order getOrderById(String orderId);
}
