package com.omega.service.impl;

import com.omega.dao.AdminDAO;
import com.omega.dao.impl.AdminDAOImpl;
import com.omega.entity.Admin;
import com.omega.service.AdminService;

/**
 * Class AdminServiceImpl
 *
 * @author KennySo
 * @date 2024/3/28
 */
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public Boolean isExistUsername(String username) {
        return adminDAO.selectOneByUsername(username) != null;
    }

    @Override
    public Boolean login(Admin admin) {
        return adminDAO.selectOneByUsernameAndPassword(admin.getUsername(), admin.getPassword()) != null;
    }
}
