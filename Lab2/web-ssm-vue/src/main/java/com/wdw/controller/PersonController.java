package com.wdw.controller;

import com.wdw.po.Person;
import com.wdw.response.Result;
import com.wdw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/{current}/{size}")
    public Result getPersonList(@PathVariable int current,
                                @PathVariable int size) {
        return Result.ok()
                .data("total", personService.count())
                .data("personList", personService.page(current, size));
    }

    @GetMapping("/checkName/{name}")
    public Result checkName(@PathVariable String name) {
        return Result.ok().data("existName", personService.getOneByName(name) != null);
    }

    @PutMapping("/")
    public Result updatePerson(@RequestBody Person person) {
        boolean isOK = personService.updateByName(person) != 0;
        return isOK ? Result.ok() : Result.error();
    }

    @PostMapping("/")
    public Result addPerson(@RequestBody Person person) {
        boolean isOK = personService.save(person) != 0;
        return isOK ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{username}")
    public Result deletePerson(@PathVariable String username) {
        personService.removeByUsername(username);
        return Result.ok();
    }
}
