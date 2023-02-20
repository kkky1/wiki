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
     * 登陆名
     */
    private String loginName;
    /**
     * 昵称
     */
    private String name;

    private Long token;

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

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserResp{" +
                "loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", token=" + token +
                '}';
    }
}

