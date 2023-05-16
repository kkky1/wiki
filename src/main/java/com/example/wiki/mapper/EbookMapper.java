package com.example.wiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wiki.entity.Ebook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EbookMapper extends BaseMapper<Ebook> {

    @Select("select* from ebook")
    public List<Ebook> getBookList();

    int updateViewCountInt(@Param("id") Long id);

    int updateLike(@Param("id") Long id);

}
