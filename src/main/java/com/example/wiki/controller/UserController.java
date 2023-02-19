package com.example.wiki.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.wiki.entity.User;
import com.example.wiki.respose.UserResp;
import com.example.wiki.respose.CommonResponse;
import com.example.wiki.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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


    @GetMapping("/list")
    public CommonResponse<User> getList(User user) {
        return new CommonResponse(true, "null", userService.getBookList(user));
    }

    @GetMapping("/list/{name}")
    public CommonResponse<UserResp> getLikeBookList(UserResp user, @PathVariable String name) {
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
    public CommonResponse<IPage> getPageBookList(UserResp user, @PathVariable() Integer current, @PathVariable() Integer pagesize) {
        return new CommonResponse<IPage>(true, null, userService.getPageBook(user, current, pagesize));
    }

    //    进行数据回显
    @GetMapping("/detail/{id}")
    public CommonResponse showDetail(UserResp user, @PathVariable int id) {
        if (userService.showBookDetail(user, id) != null) {
            return new CommonResponse<>(true, null, userService.showBookDetail(user, id));
        } else {
            return new CommonResponse(false, null, "操作失败");
        }
    }

    //    进行数据的修改
    @PostMapping("/updateBook")
    public CommonResponse<Boolean> editBook(@RequestBody UserResp user) {
        if (userService.editBook(user))
            return new CommonResponse<Boolean>(true, "修改成功", userService.editBook(user));
        else
            return new CommonResponse<Boolean>(false, "该用户名已经被注册", null);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Boolean> deleteBook(@PathVariable Long id) {
        return new CommonResponse<>(true, null, userService.deleteBook(id));
    }

    @GetMapping("/getParentId/{id}")
    public User getParentId(User user, @PathVariable Long id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getId, id);
        return userService.getOne(queryWrapper);
    }
}

