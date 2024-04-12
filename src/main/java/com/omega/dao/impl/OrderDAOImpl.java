package com.omega.dao.impl;

import com.omega.dao.BasicDAO;
import com.omega.dao.OrderDAO;
import com.omega.entity.Order;

import java.util.List;

/**
 * Class OrderDAOImpl
 *
 * @author KennySo
 * @date 2024/4/11
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {

    @Override
    public List<Order> selectListByMemberId(Integer memberId) {
        String sql = "select id, price, status, create_time as createTime, update_time as updateTime, member_id as memberId " +
                "from `order` " +
                "where member_id = ?";
        return queryMany(sql, Order.class, memberId);
    }

    @Override
    public Order selectOneByOrderId(String orderId) {
        String sql = "select id, price, status, create_time as createTime, update_time as updateTime, member_id as memberId " +
                "from `order` " +
                "where id = ?";
        return querySingle(sql, Order.class, orderId);
    }

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
