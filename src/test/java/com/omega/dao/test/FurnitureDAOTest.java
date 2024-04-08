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
    public void testSelectListByBeginNoAndPageSize() {
        List<Furniture> furnitureList = furnitureDAO.selectListByBeginNoAndPageSize(0, 2);
        for (Furniture furniture : furnitureList) {
            System.out.println(furniture);
        }
    }

    @Test
    public void testSelectListByBeginNoAndPageSizeAndName() {
        List<Furniture> furnitureList = furnitureDAO.selectListByBeginNoAndPageSizeAndName(2, 2, "子");
        for (Furniture furniture : furnitureList) {
            System.out.println(furniture);
        }
    }


    @Test
    public void testSelectOneById() {
        Furniture furniture = furnitureDAO.selectOneById(2);
        System.out.println(furniture);
    }


    @Test
    public void testInsert() {
        Furniture furniture = new Furniture(null, "不会坏的凳子", "宜家家居", new BigDecimal("999.99"),
                100, 500, "assets/images/product-image/default.jpg", null, null);
        Integer count = furnitureDAO.insert(furniture);
        System.out.println(count > 0 ? "添加数据成功." : "添加数据失败.");
    }


    @Test
    public void testUpdate() {
        Furniture furniture = new Furniture(19, "不会坏的凳子33", "宜家家居", new BigDecimal("999.99"),
                100, 500, null, null, null);
        Integer count = furnitureDAO.update(furniture);
        System.out.println(count > 0 ? "修改数据成功." : "修改数据失败.");
    }


    @Test
    public void testDelete() {
        Furniture furniture = new Furniture();
        furniture.setId(19);
        Integer count = furnitureDAO.delete(furniture);
        System.out.println(count > 0 ? "删除数据成功." : "删除数据失败.");
    }


    @Test
    public void testCountAll() {
        Integer totalRow = furnitureDAO.countAll();
        System.out.println("家居总数 = " + totalRow);
    }

    @Test
    public void testCountByName() {
        String name = "子";
        Integer totalRow = furnitureDAO.countByName(name);
        System.out.println("包含\"" + name + "\"关键字的数据数 = " + totalRow);
    }
}
