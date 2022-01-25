package com.wdw.service;

import com.wdw.po.Person;

public interface PersonService {
    public void addPerson(Person person);
    public Person findPersonByUsername(String username);
    public Person findPersonByName(String name);
}
