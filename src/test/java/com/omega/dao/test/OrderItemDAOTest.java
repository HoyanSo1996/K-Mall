package com.omega.dao.test;

import com.omega.dao.OrderItemDAO;
import com.omega.dao.impl.OrderItemDAOImpl;
import com.omega.entity.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class OrderItemDAOTest
 *
 * @author KennySo
 * @date 2024/4/12
 */
public class OrderItemDAOTest {

    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Test
    public void testSelectListByOrderId() {
        List<OrderItem> orderItemList = orderItemDAO.selectListByOrderId("17129362948901");
        for (OrderItem orderItem : orderItemList) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void testInsert() {
        OrderItem orderItem = new OrderItem();
        orderItem.setName("大桌子");
        orderItem.setCount(1);
        orderItem.setPrice(new BigDecimal("1000.00"));
        orderItem.setTotalPrice(orderItem.getPrice().multiply(new BigDecimal(orderItem.getCount())));
        orderItem.setOrderId("s003");

        Integer count = orderItemDAO.insert(orderItem);
        System.out.println(count > 0 ? "插入订单子项成功" : "插入订单子项失败");
    }
}
