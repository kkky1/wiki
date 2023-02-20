package com.example.wiki.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.wiki.Util.SnowId;
import com.example.wiki.entity.User;
import com.example.wiki.request.UserReq;
import com.example.wiki.respose.UserResp;
import com.example.wiki.respose.CommonResponse;
import com.example.wiki.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 电子书(User)表控制层
 *
 * @author makejava
 * @since 2023-02-12 15:53:09
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("/list")
    public CommonResponse<User> getList(User user) {
        return new CommonResponse(true, "null", userService.getUserList(user));
    }

    @GetMapping("/list/{name}")
    public CommonResponse<UserResp> getLikeUserList(UserResp user, @PathVariable String name) {
        return new CommonResponse(true, null, userService.getLikeList(user, name));
    }

    /**
     * @param user
     * @param current
     * @param pagesize
     * @return
     */
//    分页查询
    @GetMapping("/list/{current}/{pagesize}")
    public CommonResponse<IPage> getPageUserList(UserResp user, @PathVariable() Integer current, @PathVariable() Integer pagesize) {
        return new CommonResponse<IPage>(true, null, userService.getPageUser(user, current, pagesize));
    }

    //    进行数据回显
    @GetMapping("/detail/{id}")
    public CommonResponse showDetail(UserResp user, @PathVariable Long id) {
        if (userService.showUserDetail(user, id) != null) {
            return new CommonResponse<>(true, null, userService.showUserDetail(user, id));
        } else {
            return new CommonResponse(false, null, "操作失败");
        }
    }

    //    进行数据的修改
    @PostMapping("/updateUser")
    public CommonResponse<Boolean> editUser(@RequestBody UserResp user) {
        if (userService.editUser(user))
            return new CommonResponse<Boolean>(true, "修改成功", userService.editUser(user));
        else
            return new CommonResponse<Boolean>(false, "该用户名已经被注册", null);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Boolean> deleteUser(@PathVariable Long id) {
        return new CommonResponse<>(true, null, userService.deleteUser(id));
    }

    @GetMapping("/getParentId/{id}")
    public User getParentId(User user, @PathVariable Long id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getId, id);
        return userService.getOne(queryWrapper);
    }

    @PostMapping("/login")
    public CommonResponse<UserResp> userLogin(@RequestBody UserReq userReq){
        UserResp userLogin = userService.userLogin(userReq);
        SnowId snowId = new SnowId();
        long token = snowId.nextId();
//        userLogin.setToken(token);
        log.info("登录信息已经放入redis中：{}",token);
        log.info("登录用户为{}",userLogin);
        return new CommonResponse(true,"登录成功",userLogin);
    }



}

