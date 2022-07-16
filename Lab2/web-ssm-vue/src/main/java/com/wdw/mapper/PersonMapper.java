package com.wdw.mapper;

import com.wdw.po.Person;

import java.util.List;

public interface PersonMapper {
    List<Person> page(int start, int size);

    int count();

    Person getOneByName(String name);

    int updateByName(Person person);

    int save(Person person);

    int removeByUsername(String username);
}
