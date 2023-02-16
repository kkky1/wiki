package com.example.wiki.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wiki.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
