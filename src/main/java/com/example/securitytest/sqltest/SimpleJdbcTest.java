package com.example.securitytest.sqltest;

import java.sql.*;

public class SimpleJdbcTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true;characterEncoding=UTF-8&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        PreparedStatement ps = conn.prepareStatement("select * from activity where activity_id=?");
        ps.setInt(1, 1);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData data = rs.getMetaData();
        System.out.println(data.getColumnName(1));
        System.out.println(data.getColumnName(2));
        while (rs.next()){
            System.out.print(rs.getInt(1) + '\t');
            System.out.print(rs.getString(2));
        }
        rs.close();
        conn.close();
    }

}
