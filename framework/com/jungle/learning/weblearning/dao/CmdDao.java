package com.jungle.learning.weblearning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jungle.learning.weblearning.dao.handle.CmdHandle;

public abstract class CmdDao extends BaseDao {

	public int update(CmdHandle dao) throws SQLException {
		Connection connection = this.getConnection();
		PreparedStatement statement = null;
		int result = 0;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(dao.getSQL());
			dao.setParams(statement);

			result = statement.executeUpdate();

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
}
