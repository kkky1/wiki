package com.example.wiki.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wiki.entity.Category;
import com.example.wiki.entity.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DocMapper extends BaseMapper<Doc> {

    int updateViewCountInt(@Param("id") Long id);

    int updateLike(@Param("id") Long id);

}
