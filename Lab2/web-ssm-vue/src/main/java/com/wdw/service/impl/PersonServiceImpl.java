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
    public List<Person> page(int current, int size) {
        return personMapper.page((current - 1) * size, size);
    }

    @Override
    public int count() {
        return personMapper.count();
    }

    @Override
    public Person getOneByName(String name) {
        return personMapper.getOneByName(name);
    }

    @Override
    public int updateByName(Person person) {
        return personMapper.updateByName(person);
    }

    @Override
    public int save(Person person) {
        return personMapper.save(person);
    }

    @Override
    public int removeByUsername(String username) {
        return personMapper.removeByUsername(username);
    }
}
