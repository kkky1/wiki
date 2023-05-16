package com.example.wiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wiki.entity.Content;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper extends BaseMapper<Content> {

    @Insert("insert into content value(#{id},#{content})")
    void insertContent(Content content);
}
