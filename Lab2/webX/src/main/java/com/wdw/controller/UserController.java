package com.wdw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdw.entity.Person;
import com.wdw.entity.User;
import com.wdw.response.Result;
import com.wdw.service.PersonService;
import com.wdw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab2/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;

    @GetMapping("/{current}/{size}")
    public Result getUserList(@PathVariable long current,
                              @PathVariable long size) {
        Page<User> page = new Page<>(current, size);
        userService.page(page);
        long total = page.getTotal();
        List<User> records = page.getRecords();
        return Result.ok().data("total", total).data("userList", records);
    }

    @GetMapping("/checkUsername/{username}")
    public Result checkUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        if (userService.getOne(queryWrapper) != null) {
            return Result.ok().data("existUsername", true);
        } else {
            return Result.ok().data("existUsername", false);
        }
    }

    @DeleteMapping("/{username}")
    public Result deleteUser(@PathVariable String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        userService.remove(userQueryWrapper);
        QueryWrapper<Person> personQueryWrapper = new QueryWrapper<>();
        personQueryWrapper.eq("username", username);
        personService.remove(personQueryWrapper);
        return Result.ok();
    }

    @PostMapping("/")
    public Result addUser(@RequestBody User user) {
        boolean isOK = userService.save(user);
        return isOK ? Result.ok() : Result.error();
    }

    @GetMapping("/{username}")
    public Result getUserByUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        return Result.ok().data("user", user);
    }

    @PutMapping("/")
    public Result updateUser(@RequestBody User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", user.getUsername());
        boolean isOK = userService.saveOrUpdate(user, updateWrapper);
        return isOK ? Result.ok() : Result.error();
    }
}
