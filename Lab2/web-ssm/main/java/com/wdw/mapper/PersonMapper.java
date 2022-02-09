package com.wdw.mapper;

import com.wdw.po.Person;

import java.util.List;

public interface PersonMapper {
    public void addPerson(Person person);
    public Person findPersonByUsername(String username);
    public Person findPersonByName(String name);
    public void deletePersonByUsername(String username);
    public List<Person> getPersonList();
}
