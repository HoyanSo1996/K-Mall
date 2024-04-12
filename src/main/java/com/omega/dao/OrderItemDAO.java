package com.omega.dao;

import com.omega.entity.OrderItem;

import java.util.List;

/**
 * Class OrderItemDAO
 *
 * @author KennySo
 * @date 2024/4/12
 */
public interface OrderItemDAO {

    List<OrderItem> selectListByOrderId(String orderId);

    Integer insert(OrderItem orderItem);
}
