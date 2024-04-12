package com.omega.dao.impl;

import com.omega.dao.BasicDAO;
import com.omega.dao.OrderItemDAO;
import com.omega.entity.OrderItem;

import java.util.List;

/**
 * Class OrderItemDAOImpl
 *
 * @author KennySo
 * @date 2024/4/12
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {

    @Override
    public List<OrderItem> selectListByOrderId(String orderId) {
        String sql = "select id, name, price, count, total_price as totalPrice, order_id as orderId " +
                "from `order_item` " +
                "where order_id = ?";
        return queryMany(sql, OrderItem.class, orderId);
    }

    @Override
    public Integer insert(OrderItem orderItem) {
        String sql = "insert into `order_item`(id, name, price, count, total_price, order_id) value(null, ?, ?, ?, ?, ?)";
        return update(sql,
                orderItem.getName(),
                orderItem.getPrice(),
                orderItem.getCount(),
                orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }
}
