package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class Order
 *
 * @author KennySo
 * @date 2024/4/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;
    private BigDecimal price;
    private Integer status;  // 0:未完成  1:已完成
    private Date createTime;  // java中的Date类型
    private Date updateTime;
    private Integer memberId;
}
