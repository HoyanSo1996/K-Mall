package com.omega.dao.impl;

import com.omega.dao.AdminDAO;
import com.omega.dao.BasicDAO;
import com.omega.entity.Admin;

/**
 * Class AdminDAOImpl
 *
 * @author KennySo
 * @date 2024/3/28
 */
public class AdminDAOImpl extends BasicDAO<Admin> implements AdminDAO {

    @Override
    public Admin selectOneByUsername(String username) {
        String sql = "select " +
                "id, username, password, email, create_time as createTime, update_time as updateTime " +
                "from admin " +
                "where username = ?";
        return querySingle(sql, Admin.class, username);
    }


    @Override
    public Admin selectOneByUsernameAndPassword(String username, String password) {
        String sql = "select " +
                "id, username, password, email, create_time as createTime, update_time as updateTime " +
                "from admin " +
                "where username = ? and password = md5(?)";
        return querySingle(sql, Admin.class, username, password);
    }
}
