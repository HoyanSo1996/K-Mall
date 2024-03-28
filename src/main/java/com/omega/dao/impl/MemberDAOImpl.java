package com.omega.dao.impl;

import com.omega.dao.BasicDAO;
import com.omega.dao.MemberDAO;
import com.omega.entity.Member;

/**
 * Class MemberDAOImpl
 *
 * @author KennySo
 * @date 2024/3/26
 */
public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO {

    @Override
    public Member selectOneByUsername(String username) {
        String sql = "select " +
                "id, username, password, email, create_time as createTime, update_time as updateTime " +
                "from member " +
                "where username = ?";
        return querySingle(sql, Member.class, username);
    }


    @Override
    public Member selectOneByUsernameAndPassword(String username, String password) {
        String sql = "select " +
                "id, username, password, email, create_time as createTime, update_time as updateTime " +
                "from member " +
                "where username = ? and password = md5(?)";
        return querySingle(sql, Member.class, username, password);
    }


    @Override
    public Integer insert(Member member) {
        String sql = "insert into member value(null, ?, md5(?), ?, now(), now())";
        return update(sql, member.getUsername(), member.getPassword(), member.getEmail());
    }
}
