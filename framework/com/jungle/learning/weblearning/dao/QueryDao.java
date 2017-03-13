package com.jungle.learning.weblearning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jungle.learning.weblearning.dao.handle.QueryHandle;

public abstract class QueryDao<T> extends CmdDao {

	public T queryOne(QueryHandle<T> handle) throws SQLException {

		Connection connection = this.getConnection();

		PreparedStatement statement = null;

		T info = null;
		try {
			statement = connection.prepareStatement(handle.getSQL());

			handle.setParams(statement);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				info = handle.wrapper(result);
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

	public List<T> query(QueryHandle<T> handle) throws SQLException {

		Connection connection = this.getConnection();

		PreparedStatement statement = null;

		List<T> list = new ArrayList<T>();
		try {
			statement = connection.prepareStatement(handle.getSQL());

			handle.setParams(statement);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				list.add(handle.wrapper(result));
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
		return list;
	}
}
