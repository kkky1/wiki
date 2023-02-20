package com.example.wiki.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wiki.entity.User;
import com.example.wiki.request.UserReq;
import com.example.wiki.respose.UserResp;

import java.util.List;

/**
 * 电子书(User)表服务接口
 *
 * @author makejava
 * @since 2023-02-12 15:53:11
 */
public interface UserService extends IService<User> {

    List<User> getUserList(User user);

    List<UserResp> getLikeList(UserResp user, String name);

    IPage<User> getPageUser(User user, int current, int pagesize);

//    进行数据回显
    User showUserDetail(User user,Long id);
//    进行数据的修改
    boolean editUser(User user);
//    进行删除操作
    Boolean deleteUser(Long id);
}
