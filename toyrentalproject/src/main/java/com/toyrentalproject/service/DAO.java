package com.toyrentalproject.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "admin";
	private static String url = "jdbc:mysql://localhost/toys";
	
	private DAO() {}
	private static Connection connection;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(connection==null)
		{
			Class.forName(driver);
		    connection = DriverManager.getConnection(url, userName, password);
		}
		return connection;
	}
}