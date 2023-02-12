package com.example.wiki.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wiki.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<Test> {


}
