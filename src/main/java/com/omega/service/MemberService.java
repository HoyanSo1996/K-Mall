package com.omega.service;

import com.omega.entity.Member;

/**
 * Class MemberService
 *
 * @author KennySo
 * @date 2024/3/27
 */
public interface MemberService {

    Boolean isExistUsername(String username);

    Boolean register(Member member);

    Boolean login(Member member);
}
