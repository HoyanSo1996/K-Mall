package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Class Cart
 *
 * @author KennySo
 * @date 2024/4/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;
}
