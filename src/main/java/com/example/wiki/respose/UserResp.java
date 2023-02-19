package com.example.wiki.respose;

import com.example.wiki.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * 用户(User)实体类
 *
 * @author makejava
 * @since 2023-02-19 10:01:29
 */
public class UserResp extends User implements Serializable {
    private static final long serialVersionUID = 591732294265053030L;
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 登陆名
     */
    private String loginName;
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

