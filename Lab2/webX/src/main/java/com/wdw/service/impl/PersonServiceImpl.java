package com.wdw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdw.entity.Person;
import com.wdw.mapper.PersonMapper;
import com.wdw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
