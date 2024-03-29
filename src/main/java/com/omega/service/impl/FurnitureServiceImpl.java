package com.omega.service.impl;

import com.omega.dao.FurnitureDAO;
import com.omega.dao.impl.FurnitureDAOImpl;
import com.omega.entity.Furniture;
import com.omega.service.FurnitureService;

import java.util.List;

/**
 * Class FurnitureServiceImpl
 *
 * @author KennySo
 * @date 2024/3/29
 */
public class FurnitureServiceImpl implements FurnitureService {

    private final FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Override
    public List<Furniture> listAll() {
        return furnitureDAO.selectAll();
    }

    @Override
    public Furniture getFurnitureById(Integer id) {
        return furnitureDAO.selectOneById(id);
    }

    @Override
    public Boolean add(Furniture furniture) {
        return furnitureDAO.insert(furniture) > 0;
    }

    @Override
    public Boolean modifyFurniture(Furniture furniture) {
        return furnitureDAO.update(furniture) > 0;
    }

    @Override
    public Boolean removeFurniture(Furniture furniture) {
        return furnitureDAO.delete(furniture) > 0;
    }
}
