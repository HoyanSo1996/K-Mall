package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Class OrderItem
 *
 * @author KennySo
 * @date 2024/4/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;

    private String orderId;
}
