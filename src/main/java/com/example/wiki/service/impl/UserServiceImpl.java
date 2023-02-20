package com.example.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wiki.Util.CopyUtil;
import com.example.wiki.Util.MD5Utils;
import com.example.wiki.Util.SnowId;
import com.example.wiki.entity.User;
import com.example.wiki.exception.BusinessException;
import com.example.wiki.exception.BusinessExceptionCode;
import com.example.wiki.mapper.UserMapper;
import com.example.wiki.request.UserReq;
import com.example.wiki.respose.UserResp;
import com.example.wiki.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 电子书(User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-12 15:53:11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private UserService userService;

    @Resource
    private SnowId snowId;

    @Override
    public List<User> getUserList(User user) {
        return userService.list();
    }

    @Override
    public List<UserResp> getLikeList(UserResp user, String name) {
        LambdaQueryWrapper<User> userList = new LambdaQueryWrapper<>();
        userList.like(User::getName, name);
        List<User> users = userService.getUserList(user);
//      将进行对象的拷贝
        ArrayList<UserResp> userResps = new ArrayList<>();
        for (User user1 : users) {
            UserResp userResp = new UserResp();
            BeanUtils.copyProperties(user1, userResp);
            userResps.add(userResp);
        }
        return userResps;
    }

    @Override
    public IPage<User> getPageUser(User user, int current, int pagesize) {
        IPage<User> page = new Page<>(current, pagesize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        return userService.page(page);
    }

    @Override
    public User showUserDetail(User user, Long id) {
        return userService.getById(id);
    }

    @Override
    public boolean editUser(User user) {
        try {
            if (ObjectUtils.isEmpty(user.getId())) {
                log.info("进行数据的添加");
                user.setId(snowId.nextId());
                user.setPassword(MD5Utils.string2MD5(user.getPassword()));
                return userService.save(user);
            } else {
                LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
                if (selectUserName(user.getLoginName()) != null) {
                    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
                    return userService.updateById(user);
                }
            }
        } catch (DuplicateKeyException e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean deleteUser(@PathVariable Long id) {
        return userService.removeById(id);
    }

    //    检测用户名是否重复
    public User selectUserName(String loginName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getLoginName, loginName);
        User userName = userService.getOne(queryWrapper);
        if (userName != null) {
            return userName;
        } else {
            return null;
        }
    }

    //    进行用户的登录
    public UserResp userLogin(UserReq userReq) {
        User user = selectUserName(userReq.getLoginName());
        if (user != null) {
            if (MD5Utils.string2MD5(userReq.getPassword()).equals(user.getPassword())) {
                UserResp userResp = CopyUtil.copy(user, UserResp.class);
                log.info("登录密码为：{}", MD5Utils.string2MD5(userReq.getPassword()));
                return userResp;
            }
            else throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
    }



}
