package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
     * 更新商品数量
     */
    public void updateItemCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);
            // 这里要使用cartItem.getCount()来获取数量, 避免上一步setCount()方法中对数量进行了校验, 这样做才符合OOP原则
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    /**
     * 删除商品
     */
    public void deleteItem(Integer id) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            items.remove(id);
        }
    }

    /**
     * 清空购物车
     */
    public void clearItems() {
        items.clear();
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

    /**
     * 获取购物车内商品总价
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        Set<Integer> keys = this.items.keySet();
        for (Integer id : keys) {
            CartItem cartItem = items.get(id);
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }
}
