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
    public List<Furniture> selectListByBeginNoAndPageSize(Integer beginNo, Integer pageSize) {
        String sql = "select id, name, manufacturer, price, sales, stock, img_path as imgPath, create_time as createTime, update_time as updateTime " +
                "from furniture " +
                "limit ?, ?";
        return queryMany(sql, Furniture.class, beginNo, pageSize);
    }

    @Override
    public List<Furniture> selectListByBeginNoAndPageSizeAndName(Integer beginNo, Integer pageSize, String name) {
        String sql = "select id, name, manufacturer, price, sales, stock, img_path as imgPath, create_time as createTime, update_time as updateTime " +
                "from furniture " +
                "where name like ?" +
                "limit ?, ?";
        name = "%" + name + "%";
        return queryMany(sql, Furniture.class, name, beginNo, pageSize);
    }

    @Override
    public Furniture selectOneById(Integer id) {
        String sql = "select id, name, manufacturer, price, sales, stock, img_path as imgPath, create_time as createTime, update_time as updateTime " +
                "from furniture " +
                "where id = ?";
        return querySingle(sql, Furniture.class, id);
    }


    @Override
    public Integer insert(Furniture furniture) {
        String sql = "insert into furniture value(null, ?, ?, ?, ?, ?, ?, now(), now())";
        return update(sql, furniture.getName(), furniture.getManufacturer(), furniture.getPrice(),
                furniture.getSales(), furniture.getStock(), furniture.getImgPath());
    }


    @Override
    public Integer update(Furniture furniture) {
        String sql = "update furniture " +
                "set name = ?, manufacturer = ?, price = ?, sales = ?, stock = ?, update_time = now() " +
                "where id = ?;";
        return update(sql, furniture.getName(), furniture.getManufacturer(), furniture.getPrice(),
                furniture.getSales(), furniture.getStock(), furniture.getId());
    }


    @Override
    public Integer delete(Furniture furniture) {
        String sql = "delete from furniture " +
                "where id = ?";
        return update(sql, furniture.getId());
    }


    @Override
    public Integer countAll() {
        String sql = "select count(*) from furniture";
        // 这里不能直接转换成 integer, 否则会报 ClassCastException, 因为它的编译类型是 Long
        // return (Integer) queryScalar(sql);
        return ((Number) queryScalar(sql)).intValue();
    }

    @Override
    public Integer countByName(String name) {
        String sql = "select count(*) from furniture " +
                "where name like ?";
        name = "%" + name + "%";
        return ((Number) queryScalar(sql, name)).intValue();
    }
}
