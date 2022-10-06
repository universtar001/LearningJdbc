package com.jdbc;

import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class demo1 {
    @Test
    public void function1() throws SQLException, ClassNotFoundException {
        String driverClassName = "com.mysql.jdbc.Driver";//注意时mysql.jdbc
        String url = "jdbc:mysql://localhost:3306/db_user";
        String username = "root";
        String password = "12345";
        Class.forName(driverClassName);//抛出异常，代表着没有导入包，重新导入一下，此方法必须要抛出异常
        //新版本不需要加载驱动，但编程时要兼容旧版
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement statement = conn.createStatement();
        String sql = "";
        int r = statement.executeUpdate(sql);
        System.out.println(r);
    }

    public void function2() throws ClassNotFoundException, SQLException {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_user";
        String username = "root";
        String password = "12345";
        Class.forName(driverClassName);
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement smt =conn.createStatement();
        ResultSet rs = smt.executeQuery("SELECT");//获得一个结果集


        if(rs.next()){//next方法 返回布尔值
            int i = rs.getInt(1);//通过列编号获取值
            /***
             * 关闭资源，先得后关
              */
        rs.close();
        smt.close();
        conn.close();//必须关闭
        }
    }
    public void function3() throws ClassNotFoundException, SQLException {
        Connection conn = null;//定义引用
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/db_user";
            String username = "root";
            String password = "12345";
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            String sql = "select * from emp";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                System.out.println(rs.getObject(1) + "," + rs.getString("ename"));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) conn.close();
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }
    @Test
//    public void function4() throws ClassNotFoundException, SQLException {
//        /*
//        preparedStatement 预注入sql，简而言之使查询的sql username啊 password啊设定为一个类型
//
//        * */
//        String driverClassName = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/db_user";
//        String username = "root";
//        String password = "12345";
//        Class.forName(driverClassName);
//        Connection conn = DriverManager.getConnection(url, username, password);
//        PreparedStatement pstmt = conn.prepareStatement("select * from TABLE where name" +
//                " =?and type = ?");
//        pstmt.setString(1,"zhangsan");
//        pstmt.setInt(2,5);
//        ResultSet rs = pstmt.executeQuery();
//        return rs.next();
//
//    }
    public void function5() throws ClassNotFoundException, SQLException, IOException {
        Connection conn = jdbcUtils.getConnection();
        System.out.println(conn);

    }
    
}