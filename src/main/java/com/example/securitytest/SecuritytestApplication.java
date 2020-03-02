package com.example.securitytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
//@EnableTransactionManagement
//@MapperScan("com.example.securitytest.dao")
public class SecuritytestApplication {

//	DriverManager
//	DataSource
//	Connection
//	Statement
//	PreparedStatement
//	ResultSet

//	PlatformTransactionManager
	public static void main(String[] args) {
		SpringApplication.run(SecuritytestApplication.class, args);
	}

}
