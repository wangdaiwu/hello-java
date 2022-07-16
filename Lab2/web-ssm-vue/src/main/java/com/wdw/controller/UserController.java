package com.wdw.controller;

import com.wdw.po.User;
import com.wdw.response.Result;
import com.wdw.service.PersonService;
import com.wdw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;

    @GetMapping("/{current}/{size}")
    public Result getUserList(@PathVariable int current,
                              @PathVariable int size) {
        return Result.ok()
                .data("total", userService.count())
                .data("userList", userService.page(current, size));
    }

    @GetMapping("/checkUsername/{username}")
    public Result checkUsername(@PathVariable String username) {
        return Result.ok().data("existUsername", userService.getOneByUsername(username) != null);
    }

    @PutMapping("/")
    public Result updateUser(@RequestBody User user) {
        boolean isOK = userService.updateByUsername(user) != 0;
        return isOK ? Result.ok() : Result.error();
    }

    @PostMapping("/")
    public Result addUser(@RequestBody User user) {
        boolean isOK = userService.save(user) != 0;
        return isOK ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{username}")
    public Result deleteUser(@PathVariable String username) {
        userService.removeByUsername(username);
        personService.removeByUsername(username);
        return Result.ok();
    }
}
