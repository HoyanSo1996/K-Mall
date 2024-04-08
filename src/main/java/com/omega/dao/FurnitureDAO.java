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

    List<Furniture> selectListByBeginNoAndPageSize(Integer beginNo, Integer pageSize);

    List<Furniture> selectListByBeginNoAndPageSizeAndName(Integer beginNo, Integer pageSize, String name);

    Furniture selectOneById(Integer id);

    Integer insert(Furniture furniture);

    Integer update(Furniture furniture);

    Integer delete(Furniture furniture);

    Integer countAll();

    Integer countByName(String name);
}
