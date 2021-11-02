package com.wdw.service;

import com.wdw.dao.LabDAO;
import com.wdw.pojo.Person;
import com.wdw.pojo.User;

public class LabService {
    private LabDAO labDAO = new LabDAO();

    public void addUser(User user) {
        labDAO.addUser(user);
    }

    public void addPerson(Person person) {
        labDAO.addPerson(person);
    }

    public boolean hasUser(User user) {
        return labDAO.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword()) != null;
    }

    public boolean hasUsername(String username) {
        return labDAO.queryUserByUsername(username) != null;
    }

    public boolean hasName(String name) {
        return labDAO.queryPersonByName(name) != null;
    }

    public Person queryPersonByUsername(String username) {
        return labDAO.queryPersonByUsername(username);
    }

//    @Test
//    public void testContext() throws InvocationTargetException, IllegalAccessException {
//        Map<String, String[]> map = new HashMap();
//        map.put("username", new String[]{"1233", "12345678"});
//        map.put("password", new String[]{"123456"});
//        //labDAO.addUser(new User((String) map.get("username"), (String) map.get("password")));
//        User user = WebUtils.copyParamToBean(new User(), map);
//        labDAO.addUser(user);
////        BeanUtils.populate(new User(), map);
//        //System.out.println(user);
//    }
}
