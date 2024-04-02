package com.omega.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Class Furniture
 *
 * @author KennySo
 * @date 2024/3/29
 */
@Data
public class Furniture {

    private Integer id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String imgPath = "assets/images/product-image/default.jpg";
    private Timestamp createTime;
    private Timestamp updateTime;

    public Furniture() {
    }

    public Furniture(Integer id, String name, String manufacturer, BigDecimal price, Integer sales, Integer stock, String imgPath, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        // 为了我们手动创建furniture对象时, 不小心传入null进来替换掉默认值
        if (!(imgPath == null || imgPath.isEmpty())) {
            this.imgPath = imgPath;
        }
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
