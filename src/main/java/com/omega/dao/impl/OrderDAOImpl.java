package com.omega.dao.impl;

import com.omega.dao.BasicDAO;
import com.omega.dao.OrderDAO;
import com.omega.entity.Order;

/**
 * Class OrderDAOImpl
 *
 * @author KennySo
 * @date 2024/4/11
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {

    @Override
    public Integer insert(Order order) {
        String sql = "insert into `order`(id, price, status, create_time, update_time, member_id) value(?, ?, ?, now(), now(), ?)";
        return update(sql,
                order.getId(),
                order.getPrice(),
                order.getStatus(),
                order.getMemberId());
    }
}
