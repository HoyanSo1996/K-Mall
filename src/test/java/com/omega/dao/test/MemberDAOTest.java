package com.omega.dao.test;

import com.omega.dao.MemberDAO;
import com.omega.dao.impl.MemberDAOImpl;
import com.omega.entity.Member;
import org.junit.jupiter.api.Test;

/**
 * Class MemberDAOTest
 *
 * @author KennySo
 * @date 2024/3/28
 */
public class MemberDAOTest {

    private final MemberDAO memberDAO = new MemberDAOImpl();

    @Test
    public void testSelectOneByUsername() {
        Member member = memberDAO.selectOneByUsername("KennySo");
        System.out.println(member);
    }

    @Test
    public void testSelectOneByUsernameAndPassword() {
        Member member = memberDAO.selectOneByUsernameAndPassword("KennySo", "KennySo");
        System.out.println(member);
    }

    @Test
    public void testInsert() {
        Member member = new Member(null, "KennySo", "KennySo", "KennySo@163.com", null, null);
        Integer count = memberDAO.insert(member);
        System.out.println(count > 0 ? "添加数据成功." : "添加数据失败.");
    }
}
