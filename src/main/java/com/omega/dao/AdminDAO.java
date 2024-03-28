package com.omega.dao;

import com.omega.entity.Admin;

/**
 * Class AdminDAO
 *
 * @author KennySo
 * @date 2024/3/28
 */
public interface AdminDAO {

    Admin selectOneByUsername(String username);

    Admin selectOneByUsernameAndPassword(String username, String password);
}
