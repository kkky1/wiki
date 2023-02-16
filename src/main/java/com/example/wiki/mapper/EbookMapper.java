package com.example.wiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wiki.entity.Ebook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EbookMapper extends BaseMapper<Ebook> {

    @Select("select* from ebook")
    public List<Ebook> getBookList();

}
