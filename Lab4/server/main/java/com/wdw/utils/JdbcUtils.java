package com.wdw.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource dataSource;

    static {
        try {
            Properties props = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            props.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 使用druid 连接MySQL中的lab数据库
     * @Author 王呆物
     * @Date 17:18 2021/9/25
     * @param []
     * @return java.sql.Connection
     **/
    public static Connection getConnection() {
        try {
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 关闭ResultSet
     * @Author 王呆物
     * @Date 17:19 2021/9/25
     * @param [java.sql.ResultSet]
     * @return void
     **/
    public static void closeResultSet(ResultSet rs) {
        DbUtils.closeQuietly(rs);
    }

    /**
     * @Description 关闭PreparedStatement
     * @Author 王呆物
     * @Date 17:19 2021/9/25
     * @param [java.sql.PreparedStatement]
     * @return void
     **/
    public static void closePreparedStatement(PreparedStatement ps) {
        DbUtils.closeQuietly(ps);
    }

    /**
     * @Description 关闭Connection
     * @Author 王呆物
     * @Date 17:20 2021/9/25
     * @param [java.sql.Connection]
     * @return void
     **/
    public static void closeConnection(Connection conn) {
        DbUtils.closeQuietly(conn);
    }
}