package com.wdw.dao;

import com.wdw.pojo.Person;
import com.wdw.pojo.User;
import com.wdw.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LabTest {
    private LabDAO dao = new LabDAO();

    /**
     * @Description 连接MySQL 创建 lab数据库 user表 person表
     * @Author 王呆物
     * @Date 9:41 2021/9/25
     * @param []
     * @return void
     **/
    @Test
    public void initialize() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = null;

            sql = "drop database if exists lab";
            ps = conn.prepareStatement(sql);
            ps.execute();

            sql = "create database lab";
            ps = conn.prepareStatement(sql);
            ps.execute();

            sql = "use lab";
            ps = conn.prepareStatement(sql);
            ps.execute();

            sql = "create table user(username varchar(10) primary key, password varchar(8))";
            ps = conn.prepareStatement(sql);
            ps.execute();

            sql = "create table person(username varchar(10) not null, name varchar(20) primary key," +
                    "age int, teleno char(11))";
            ps = conn.prepareStatement(sql);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closePreparedStatement(ps);
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * @Description user表插入数据
     * @Author 王呆物
     * @Date 9:42 2021/9/25
     * @param []
     * @return void
     **/
    @Test
    public void addUser() {
        dao.addUser(new User("yl", "123456"));
        dao.addUser(new User("liming", "345678"));
        dao.addUser(new User("test", "11111"));
        dao.addUser(new User("test1", "12345"));
        printAll();
    }

    /**
     * @Description person表插入数据
     * @Author 王呆物
     * @Date 9:43 2021/9/25
     * @param []
     * @return void
     **/
    @Test
    public void addPerson() {
        dao.addPerson(new Person("ly", "王五", null, null));
        dao.addPerson(new Person("test2", "测试用户2", null, null));
        dao.addPerson(new Person("test1", "测试用户1", 33, null));
        dao.addPerson(new Person("test", "张三", 23, "18877009966"));
        dao.addPerson(new Person("admin", "admin", null, null));
        printAll();
    }

    /**
     * @Description 删除user表和person表中username以test开头的数据
     * @Author 王呆物
     * @Date 9:43 2021/9/25
     * @param []
     * @return void
     **/
    @Test
    public void deleteUserByUsernameStartWithTest() throws Exception {
        Connection conn = JdbcUtils.getConnection();
        dao.deleteUserByUsernameStartWithTest(conn);
        JdbcUtils.closeConnection(conn);
        printAll();
    }

    @Test
    public void printAll() {
        dao.queryAll();
    }
}

