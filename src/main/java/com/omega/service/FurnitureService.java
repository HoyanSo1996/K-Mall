package com.omega.service;

import com.omega.entity.Furniture;

import java.util.List;

/**
 * Class FurnitureService
 *
 * @author KennySo
 * @date 2024/3/29
 */
public interface FurnitureService {

    List<Furniture> listAll();

    Boolean add(Furniture furniture);
}
