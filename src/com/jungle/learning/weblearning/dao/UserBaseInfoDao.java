package com.jungle.learning.weblearning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jungle.learning.weblearning.model.UserInfo;

public class UserBaseInfoDao extends BaseDao {

	public boolean insert(UserInfo info) throws SQLException {
		String sql = "INSERT INTO user_base_info (id, login_name, password) VALUES('?', '?', '?');";
		Connection connection = this.getConnection();
		boolean flag = false;
		try {
			connection.setAutoCommit(false);

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, info.getName());
			statement.setString(3, info.getPassword());
			flag = (1 == statement.executeUpdate());

			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return flag;
	}

	public boolean update(UserInfo info) throws SQLException {
		String sql = "UPDATE user_base_info SET login_name='?', password='?' WHERE id='?';";
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		boolean result = false;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);

			statement.setString(1, info.getName());
			statement.setString(2, info.getPassword());

			statement.setString(3, info.getId());

			result = (1 == statement.executeUpdate());

			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	public boolean delete(String id) throws SQLException {
		String sql = "DELETE FROM  learning.user_base_info WHERE id='?';";
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		boolean result = false;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);

			statement.setString(1, id);

			result = (1 == statement.executeUpdate());

			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	public UserInfo query(String id) throws SQLException {
		String sql = "SELECT id, login_name, password FROM user_base_info where id=?;";
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		UserInfo info = null;
		try {
			statement = connection.prepareStatement(sql);

			statement.setString(1, id);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				info = new UserInfo();
				info.setId(result.getString(1));
				info.setName(result.getString(2));
				info.setPassword(result.getString(3));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return info;
	}

}
