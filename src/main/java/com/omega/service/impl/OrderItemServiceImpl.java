package com.omega.service.impl;

import com.omega.dao.OrderItemDAO;
import com.omega.dao.impl.OrderItemDAOImpl;
import com.omega.entity.OrderItem;
import com.omega.service.OrderItemService;

import java.util.List;

/**
 * Class OrderItemServiceImpl
 *
 * @author KennySo
 * @date 2024/4/14
 */
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    public List<OrderItem> getOrderItemByOrderId(String orderId) {
        return orderItemDAO.selectListByOrderId(orderId);
    }
}
