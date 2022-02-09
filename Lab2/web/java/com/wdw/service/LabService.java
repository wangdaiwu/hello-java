package com.wdw.service;

import com.wdw.dao.LabDAO;
import com.wdw.pojo.Person;
import com.wdw.pojo.User;

import java.util.List;

public class LabService {
    private LabDAO labDAO = new LabDAO();

    public void addUser(User user) {
        labDAO.addUser(user);
    }

    public void addPerson(Person person) {
        labDAO.addPerson(person);
    }

    public void deleteUserByUsername(String username) {
        labDAO.deleteUserByUsername(username);
    }

    public void deletePersonByUsername(String username) {
        labDAO.deletePersonByUsername(username);
    }

    public boolean hasUsername(String username) {
        return labDAO.queryUserByUsername(username) != null;
    }

    public boolean hasName(String name) {
        return labDAO.queryPersonByName(name) != null;
    }

    public List<User> queryUserList() {
        return labDAO.queryUserList();
    }

    public List<Person> queryPersonList() {
        return labDAO.queryPersonList();
    }
}
