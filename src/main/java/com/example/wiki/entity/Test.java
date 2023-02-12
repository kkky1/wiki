package com.example.wiki.entity;

import java.io.Serializable;

/**
 * 测试(Test)实体类
 *
 * @author makejava
 * @since 2023-02-12 15:18:49
 */
public class Test implements Serializable {
    private static final long serialVersionUID = 143301839126866616L;
    /**
     * id
     */
    private Long id;
    /**
     * 名称
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

