package com.wdw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdw.entity.Person;
import com.wdw.response.Result;
import com.wdw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab2/person")
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/{current}/{size}")
    public Result getPersonList(@PathVariable long current,
                                @PathVariable long size) {
        Page<Person> page = new Page<>(current, size);
        personService.page(page);
        long total = page.getTotal();
        List<Person> records = page.getRecords();
        return Result.ok().data("total", total).data("personList", records);
    }

    @GetMapping("/checkName/{name}")
    public Result checkName(@PathVariable String name) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        if (personService.getOne(queryWrapper) != null) {
            return Result.ok().data("existName", true);
        } else {
            return Result.ok().data("existName", false);
        }
    }

    @PostMapping("/")
    public Result addPerson(@RequestBody Person person) {
        boolean isOK = personService.save(person);
        return isOK ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{username}")
    public Result deletePerson(@PathVariable String username) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        personService.remove(queryWrapper);
        return Result.ok();
    }

    @GetMapping("/{name}")
    public Result getPersonByName(@PathVariable String name) {
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        Person person = personService.getOne(queryWrapper);
        return Result.ok().data("person", person);
    }

    @PutMapping("/")
    public Result updatePerson(@RequestBody Person person) {
        UpdateWrapper<Person> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", person.getName());
        boolean isOK = personService.saveOrUpdate(person, updateWrapper);
        return isOK ? Result.ok() : Result.error();
    }
}
