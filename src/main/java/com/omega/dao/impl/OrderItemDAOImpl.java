package com.omega.dao.impl;

import com.omega.dao.BasicDAO;
import com.omega.dao.OrderItemDAO;
import com.omega.entity.OrderItem;

/**
 * Class OrderItemDAOImpl
 *
 * @author KennySo
 * @date 2024/4/12
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {

    @Override
    public Integer insert(OrderItem orderItem) {
        String sql = "insert into `order_item`(id, name, price, count, total_price, order_id) value(null, ?, ?, ?, ?, ?)";
        return update(sql,
                orderItem.getName(),
                orderItem.getCount(),
                orderItem.getPrice(),
                orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }
}
