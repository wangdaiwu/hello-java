package com.wdw.service;

import com.wdw.po.Person;

import java.util.List;

public interface PersonService {
    List<Person> page(int current, int size);

    int count();

    Person getOneByName(String name);

    int updateByName(Person person);

    int save(Person person);

    int removeByUsername(String username);
}
