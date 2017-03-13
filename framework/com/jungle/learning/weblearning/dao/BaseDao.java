package com.jungle.learning.weblearning.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.jungle.learning.weblearning.config.PropertiesManager;

public abstract class BaseDao {

	public Connection getConnection() throws SQLException {
		return createConnection();
	}

	private Connection createConnection() throws SQLException {
		Properties prop = PropertiesManager.getInstance().getJdbcConfig();
		String url = prop.getProperty("jdbc.url");
		String user = prop.getProperty("jdbc.user");
		String password = prop.getProperty("jdbc.password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("createConnection", e);
		}
		return DriverManager.getConnection(url, user, password);
	}
}
