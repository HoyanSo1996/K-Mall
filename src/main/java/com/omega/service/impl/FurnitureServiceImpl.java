package com.omega.service.impl;

import com.omega.dao.FurnitureDAO;
import com.omega.dao.impl.FurnitureDAOImpl;
import com.omega.entity.Furniture;
import com.omega.entity.Page;
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

    @Override
    public Page<Furniture> pageFurniture(Integer pageNo, Integer pageSize) {
        Page<Furniture> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        // 获取总页数
        Integer totalRow = furnitureDAO.countAll();
        page.setTotalRow(totalRow);

        // 获取分页信息 (有一个坑)
        List<Furniture> furnitureList = furnitureDAO.selectListByBeginNoAndPageSize((pageNo - 1) * pageSize, pageSize);
        page.setItems(furnitureList);

        // 计算总页数
        Integer totalSize = totalRow % pageSize == 0 ? (totalRow / pageSize) : (totalRow / pageSize + 1);
        page.setTotalSize(totalSize);
        return page;
    }
}
