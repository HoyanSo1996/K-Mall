package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Class Furniture
 *
 * @author KennySo
 * @date 2024/3/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Furniture {

    private Integer id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String imgPath;
    private Timestamp createTime;
    private Timestamp updateTime;
}
