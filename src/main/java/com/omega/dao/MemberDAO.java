package com.omega.dao;

import com.omega.entity.Member;

/**
 * Class MemberDAO
 *
 * @author KennySo
 * @date 2024/3/26
 */
public interface MemberDAO {

    Member selectOneByUsername(String username);

    Member selectOneByUsernameAndPassword(String username, String password);

    Integer insert(Member member);
}
