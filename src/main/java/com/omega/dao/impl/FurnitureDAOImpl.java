package com.omega.dao.impl;

import com.omega.dao.BasicDAO;
import com.omega.dao.FurnitureDAO;
import com.omega.entity.Furniture;

import java.util.List;

/**
 * Class FurnitureDAOImpl
 *
 * @author KennySo
 * @date 2024/3/29
 */
public class FurnitureDAOImpl extends BasicDAO<Furniture> implements FurnitureDAO {

    @Override
    public List<Furniture> selectAll() {
        String sql = "select id, name, manufacturer, price, sales, stock, img_path as imgPath, create_time as createTime, update_time as updateTime " +
                "from furniture";
        return queryMany(sql, Furniture.class);
    }


    @Override
    public Integer insert(Furniture furniture) {
        String sql = "insert into furniture value(null, ?, ?, ?, ?, ?, ?, now(), now())";
        return update(sql, furniture.getName(), furniture.getManufacturer(), furniture.getPrice(),
                furniture.getSales(), furniture.getStock(), furniture.getImgPath());
    }
}
