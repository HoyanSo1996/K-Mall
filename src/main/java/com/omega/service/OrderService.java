package com.omega.service;

import com.omega.entity.Cart;

/**
 * Class OrderService
 *
 * @author KennySo
 * @date 2024/4/11
 */
public interface OrderService {

    String addOrder(Cart cart, Integer memberId);

}
