package com.wdw.controller;

import com.google.gson.Gson;
import com.wdw.po.Person;
import com.wdw.po.User;
import com.wdw.service.PersonService;
import com.wdw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
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

    @RequestMapping("/queryAll")
    public String queryAll(Model model) {
        List<User> userList = userService.getUserList();
        List<Person> personList = personService.getPersonList();
        model.addAttribute("userList", userList);
        model.addAttribute("personList", personList);
        return "show";
    }

    @RequestMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        personService.deletePersonByUsername(username);
        return "redirect:/queryAll";
    }

    @RequestMapping("/deletePerson/{username}")
    public String deletePerson(@PathVariable String username) {
        personService.deletePersonByUsername(username);
        return "redirect:/queryAll";
    }

    @RequestMapping("/editUser")
    public String editUser() {
        return "editUser";
    }

    @RequestMapping("/editPerson")
    public String editPerson() {
        return "editPerson";
    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public String checkUsername(String username) {
        Map<String, Object> responseData = new HashMap<>();
        if (userService.findUserByUsername(username) != null) {
            responseData.put("existsUsername", true);
        } else {
            responseData.put("existsUsername", false);
        }
        return gson.toJson(responseData);
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public String checkName(String name) {
        Map<String, Object> responseData = new HashMap<>();
        if (personService.findPersonByName(name) != null) {
            responseData.put("existsName", true);
        } else {
            responseData.put("existsName", false);
        }
        return gson.toJson(responseData);
    }

    @RequestMapping("/addUser")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/queryAll";
    }

    @RequestMapping("/addPerson")
    public String addPerson(Person person) {
        personService.addPerson(person);
        return "redirect:/queryAll";
    }
}
