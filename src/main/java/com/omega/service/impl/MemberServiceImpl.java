package com.omega.service.impl;

import com.omega.dao.MemberDAO;
import com.omega.dao.impl.MemberDAOImpl;
import com.omega.entity.Member;
import com.omega.service.MemberService;

/**
 * Class MemberServiceImpl
 *
 * @author KennySo
 * @date 2024/3/27
 */
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public Boolean isExistUsername(String username) {
        return memberDAO.selectOneByUsername(username) != null;
    }

    @Override
    public Boolean register(Member member) {
        return memberDAO.insert(member) > 0;
    }

    @Override
    public Boolean login(Member member) {
        return memberDAO.selectOneByUsernameAndPassword(member.getUsername(), member.getPassword()) != null;
    }
}
