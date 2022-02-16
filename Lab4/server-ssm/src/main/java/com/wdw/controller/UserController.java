package com.wdw.controller;

import com.google.gson.Gson;
import com.wdw.po.Person;
import com.wdw.po.User;
import com.wdw.service.PersonService;
import com.wdw.service.UserService;
import com.wdw.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private Gson gson;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/signUp")
    public String signUp(@RequestParam Map<String, Object> requestData) {
        User user = BeanUtil.mapToBean(new User(), requestData);
        Person person = BeanUtil.mapToBean(new Person(), requestData);
        Map<String, Object> responseData = new HashMap<>();

        if (userService.findUserByUsername(user.getUsername()) != null) {
            responseData.put("result", "username already exists");
        } else if (personService.findPersonByName(person.getName()) != null) {
            responseData.put("result", "name already exists");
        } else {
            userService.addUser(user);
            personService.addPerson(person);
            responseData.put("result", "success");
        }
        return gson.toJson(responseData);
    }

    @RequestMapping("/signIn")
    public String signIn(User user) {
        Map<String, Object> responseData = new HashMap<>();
        if (userService.findUser(user) != null) {
            responseData.put("result", "success");
        } else {
            responseData.put("result", "unknown username or bad password");
        }
        return gson.toJson(responseData);
    }

    @RequestMapping("/welcome")
    public String welcome(String username){
        Person person = personService.findPersonByUsername(username);
        return gson.toJson(person);
    }
}
