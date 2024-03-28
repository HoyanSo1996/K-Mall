package com.omega.service;

import com.omega.entity.Admin;

/**
 * Class AdminService
 *
 * @author KennySo
 * @date 2024/3/28
 */
public interface AdminService {

    Boolean isExistUsername(String username);

    Boolean login(Admin admin);
}
