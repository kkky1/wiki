package com.example.wiki.entity;

import java.io.Serializable;

/**
 * 文档内容(Content)实体类
 *
 * @author makejava
 * @since 2023-02-20 14:38:02
 */
public class Content implements Serializable {
    private static final long serialVersionUID = 820886836017882498L;
    /**
     * 文档id
     */
    private Long id;
    /**
     * 内容
     */
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

