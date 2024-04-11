package com.omega.dao.test;

import com.omega.dao.OrderDAO;
import com.omega.dao.impl.OrderDAOImpl;
import com.omega.entity.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Class OrderDAOTest
 *
 * @author KennySo
 * @date 2024/4/11
 */
public class OrderDAOTest {

    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void testInsert() {
        Order order = new Order();
        order.setId("s002");
        order.setPrice(new BigDecimal("1000.00"));
        order.setStatus(0);
        order.setMemberId(12);

        Integer count = orderDAO.insert(order);
        System.out.println(count > 0 ? "插入订单成功" : "插入订单失败");
    }
}
