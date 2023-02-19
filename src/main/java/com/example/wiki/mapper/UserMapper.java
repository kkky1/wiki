package com.example.wiki.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wiki.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
