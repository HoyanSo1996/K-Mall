package com.omega.dao;

import com.omega.entity.Order;

import java.util.List;

/**
 * Class OrderDAO
 *
 * @author KennySo
 * @date 2024/4/11
 */
public interface OrderDAO {

    List<Order> selectListByMemberId(Integer memberId);

    Order selectOneByOrderId(String orderId);

    Integer insert(Order order);
}
