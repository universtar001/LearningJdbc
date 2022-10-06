package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream in = jdbcUtils.class.getClassLoader()
                .getResourceAsStream("dbconfig.properties");
        //一个类需要动态的获取某个文件的位置，从而能够获取此文件的资源
        /*
        * 第一：前面有 “   / ” 代表了工程的根目录，例如工程名叫做myproject，“ / ”代表了myproject 第二：前面没有 “   / ” 代表当前类的目录
        * */
        Properties props = new Properties();
        props.load(in);//加载配置文件
        Class.forName(props.getProperty("driverClassName"));
        return DriverManager.getConnection(props.getProperty("url"),
                props.getProperty("username"),props.getProperty("password"));
    }
}
