package com.wdw.service.impl;

import com.wdw.mapper.PersonMapper;
import com.wdw.po.Person;
import com.wdw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonMapper personMapper;

    @Override
    public void addPerson(Person person) {
        personMapper.addPerson(person);
    }

    @Override
    public Person findPersonByUsername(String username) {
        Person person = personMapper.findPersonByUsername(username);
        return person;
    }

    @Override
    public Person findPersonByName(String name) {
        Person person = personMapper.findPersonByName(name);
        return person;
    }

    @Override
    public void deletePersonByUsername(String username) {
        personMapper.deletePersonByUsername(username);
    }

    @Override
    public List<Person> getPersonList() {
        List<Person> personList = personMapper.getPersonList();
        return personList;
    }
}
