package com.omega.dao;

import com.omega.entity.Furniture;

import java.util.List;

/**
 * Class FurnitureDAO
 *
 * @author KennySo
 * @date 2024/3/29
 */
public interface FurnitureDAO {

    List<Furniture> selectAll();

    Furniture selectOneById(Integer id);

    Integer insert(Furniture furniture);

    Integer update(Furniture furniture);

    Integer delete(Furniture furniture);
}
