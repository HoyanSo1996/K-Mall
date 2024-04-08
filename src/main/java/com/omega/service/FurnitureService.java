package com.omega.service;

import com.omega.entity.Furniture;
import com.omega.entity.Page;

import java.util.List;

/**
 * Class FurnitureService
 *
 * @author KennySo
 * @date 2024/3/29
 */
public interface FurnitureService {

    List<Furniture> listAll();

    Furniture getFurnitureById(Integer id);

    Boolean add(Furniture furniture);

    Boolean modifyFurniture(Furniture furniture);

    Boolean removeFurniture(Furniture furniture);

    Page<Furniture> pageFurniture(Integer pageNo, Integer pageSize);

    Page<Furniture> pageFurnitureByName(Integer pageNo, Integer pageSize, String name);
}
