package com.wdw.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public abstract class BaseDAO {
    private static QueryRunner queryRunner = new QueryRunner();

    /**
     * @Description 增删改
     * @Author 王呆物
     * @Date 19:56 2021/9/25
     * @param [java.sql.Connection, java.lang.String, java.lang.Object...]
     * @return int
     **/
    public static int update(Connection conn, String sql, Object... params) {
        int count = 0;
        try {
            count = queryRunner.update(conn, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * @Description 单条查询
     * @Author 王呆物
     * @Date 19:56 2021/9/25
     * @param [java.sql.Connection, java.lang.Class<T>, java.lang.String, java.lang.Object...]
     * @return T
     **/
    public static <T> T queryForOne(Connection conn, Class<T> clazz, String sql, Object... params) {
        T t = null;
        try {
            t = queryRunner.query(conn, sql, new BeanHandler<>(clazz), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * @Description 无填充占位符 多条查询
     * @Author 王呆物
     * @Date 19:51 2021/9/25
     * @param [java.sql.Connection, java.lang.Class<T>, java.lang.String]
     * @return java.util.List<T>
     **/
    public static <T> List<T> queryForList(Connection conn, Class<T> clazz, String sql) {
        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<>(clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Description 有填充占位符 多条查询
     * @Author 王呆物
     * @Date 19:51 2021/9/25
     * @param [java.sql.Connection, java.lang.Class<T>, java.lang.String, java.lang.Object...]
     * @return java.util.List<T>
     **/
    public static <T> List<T> queryForList(Connection conn, Class<T> clazz, String sql, Object... params) {
        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<>(clazz), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
