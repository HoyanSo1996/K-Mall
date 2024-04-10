package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Set;

/**
 * Class Cart
 *
 * @author KennySo
 * @date 2024/4/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private HashMap<Integer, CartItem> items = new HashMap<>();

    /**
     * 添加家居到购物车
     */
    public void addItem(CartItem item) {
        // 判断购物车中有没有同类商品
        CartItem cartItem = items.get(item.getId());
        if (cartItem == null) {
            items.put(item.getId(), item);
        } else {
            // 叠加数量和价格
            cartItem.setCount(cartItem.getCount() + item.getCount());
            cartItem.setTotalPrice(cartItem.getTotalPrice().add(item.getPrice()));
        }
    }

    /**
     * 获取购物车内商品数量
     */
    public Integer getTotalCount() {
        int totalCount = 0;
        Set<Integer> keys = items.keySet();
        for (Integer key : keys) {
            CartItem cartItem = items.get(key);
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }
}
