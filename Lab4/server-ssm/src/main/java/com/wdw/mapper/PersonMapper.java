package com.wdw.mapper;

import com.wdw.po.Person;

public interface PersonMapper {
    public void addPerson(Person person);
    public Person findPersonByUsername(String username);
    public Person findPersonByName(String name);
}
