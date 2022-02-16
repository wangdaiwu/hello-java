package com.wdw.dao;

import com.wdw.pojo.Person;
import com.wdw.pojo.User;
import com.wdw.utils.JdbcUtils;

import java.sql.Connection;
import java.util.List;

public class LabDAO extends BaseDAO {

    public void addUser(User user) {
        Connection conn = null;
        String sql = "insert into user(username,password) values(?,?)";
        try {
            conn = JdbcUtils.getConnection();
            update(conn, sql, user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    public void addPerson(Person person) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String selectForUserSQL = "select username from user where username=?";
            User user = queryForOne(conn, User.class, selectForUserSQL, person.getUsername());
            if (user == null) {
                String insertForUserSQL = "insert into user(username,password) values(?,?)";
                update(conn, insertForUserSQL, person.getUsername(), "888888");
            }
            String insertForPersonSQL = "insert into person(username, name, age, teleno) values(?,?,?,?)";
            update(conn, insertForPersonSQL, person.getUsername(), person.getName(), person.getAge(), person.getTeleno());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    public void deleteUserByUsername(String username) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from user where username=?";
            update(conn, sql, username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    public void deletePersonByUsername(String username) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from person where username=?";
            update(conn, sql, username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    public void deleteUserByUsernameStartWithTest(Connection conn) {
        try {
            String queryTestUserSQL = "select username from user where username like 'test%'";
            List<User> list = queryForList(conn, User.class, queryTestUserSQL);
            for (User user : list) {
                deleteUserByUsername(user.getUsername());
                deletePersonByUsername(user.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User queryUserByUsername(String username) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select username,password from user where username=?";
            User user = queryForOne(conn, User.class, sql, username);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
        return null;
    }

    public Person queryPersonByName(String name) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select username,name,age,teleno from person where name=?";
            Person person = queryForOne(conn, Person.class, sql, name);
            return person;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
        return null;
    }

    public Person queryPersonByUsername(String username) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select username,name,age,teleno from person where username=?";
            Person person = queryForOne(conn, Person.class, sql, username);
            return person;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
        return null;
    }

    public User queryUserByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select username,password from user where username=? and password=?";
            User user = queryForOne(conn, User.class, sql, username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
        return null;
    }

    public List<User> queryUserList() {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select username,password from user";
            List<User> userList = queryForList(conn, User.class, sql);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
        return null;
    }

    public List<Person> queryPersonList() {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select username,name,age,teleno from person";
            List<Person> personList = queryForList(conn, Person.class, sql);
            return personList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
        }
        return null;
    }

    public void queryAll() {
        try {
            System.out.println("table:user");
            List<User> userList = queryUserList();
            userList.forEach(System.out::println);

            System.out.println("\ntable:person");
            List<Person> personList = queryPersonList();
            personList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}