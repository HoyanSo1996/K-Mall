package com.omega.service;

import com.omega.entity.OrderItem;

import java.util.List;

/**
 * Class OrderItemService
 *
 * @author KennySo
 * @date 2024/4/14
 */
public interface OrderItemService {

    List<OrderItem> getOrderItemByOrderId(String orderId);
}
