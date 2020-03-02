package com.example.securitytest.sqltest;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.pool.DruidPooledPreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruidTest {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true;characterEncoding=UTF-8&serverTimezone=UTC";
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(0);
        dataSource.setKeepAlive(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxActive(10);
        DruidPooledConnection connection = dataSource.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from activity where activity_id=?");
        preparedStatement.setInt(1, 1);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            System.out.print(rs.getInt(1) + '\t');
            System.out.print(rs.getString(2));
        }
        rs.close();
        connection.close();
    }

}
