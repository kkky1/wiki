package com.example.wiki.respose;

import com.example.wiki.entity.Doc;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * 文档(Doc)实体类
 *
 * @author makejava
 * @since 2023-02-20 14:27:06
 */
public class DocResp extends Doc implements Serializable {
    private static final long serialVersionUID = -14203743544243543L;
    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 电子书id
     */
    private Long ebookId;
    /**
     * 父id
     */
    private Long parent;
    /**
     * 名称
     */
    private String name;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 阅读数
     */
    private Integer viewCount;
    /**
     * 点赞数
     */
    private Integer voteCount;

    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEbookId() {
        return ebookId;
    }

    public void setEbookId(Long ebookId) {
        this.ebookId = ebookId;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

