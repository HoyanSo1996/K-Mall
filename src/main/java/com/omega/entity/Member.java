package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Class Member
 *
 * @author KennySo
 * @date 2024/3/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private Timestamp createTime;  // java.sql.Timestamp
    private Timestamp updateTime;
}
