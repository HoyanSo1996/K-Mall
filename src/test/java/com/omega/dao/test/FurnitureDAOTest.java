package com.omega.dao.test;

import com.omega.dao.FurnitureDAO;
import com.omega.dao.impl.FurnitureDAOImpl;
import com.omega.entity.Furniture;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class FurnitureDAOTest
 *
 * @author KennySo
 * @date 2024/3/29
 */
public class FurnitureDAOTest {

    private final FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Test
    public void testSelectAll() {
        List<Furniture> furnitureList = furnitureDAO.selectAll();
        for (Furniture furniture : furnitureList) {
            System.out.println(furniture);
        }
    }

    @Test
    public void testInsert() {
        Furniture furniture = new Furniture(null, "不会坏的凳子", "宜家家居", new BigDecimal("999.99"),
                100, 500, "assets/images/product-image/default.jpg", null, null);
        Integer count = furnitureDAO.insert(furniture);
        System.out.println(count > 0 ? "添加数据成功." : "添加数据失败.");
    }
}
