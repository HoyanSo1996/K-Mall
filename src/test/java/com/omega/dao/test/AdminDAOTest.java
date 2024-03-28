package com.omega.dao.test;

import com.omega.dao.AdminDAO;
import com.omega.dao.impl.AdminDAOImpl;
import com.omega.entity.Admin;
import org.junit.jupiter.api.Test;

/**
 * Class AdminDAOTest
 *
 * @author KennySo
 * @date 2024/3/28
 */
public class AdminDAOTest {

    private final AdminDAO adminDAO = new AdminDAOImpl();

    @Test
    public void testSelectOneByUsername() {
        Admin admin = adminDAO.selectOneByUsername("admin");
        System.out.println(admin);
    }


    @Test
    public void testSelectOneByUsernameAndPassword() {
        Admin admin = adminDAO.selectOneByUsernameAndPassword("admin", "admin");
        System.out.println(admin);
    }
}
