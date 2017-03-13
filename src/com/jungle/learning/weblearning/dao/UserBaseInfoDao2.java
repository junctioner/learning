package com.jungle.learning.weblearning.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jungle.learning.weblearning.dao.handle.CmdHandle;
import com.jungle.learning.weblearning.dao.handle.QueryHandle;
import com.jungle.learning.weblearning.model.UserInfo;

public class UserBaseInfoDao2 extends QueryDao<UserInfo> {

	public boolean insert(UserInfo info) throws SQLException {
		return 1 == this.update(new CmdHandle() {
			@Override
			public String getSQL() {
				return "INSERT INTO user_base_info (id, login_name, password) VALUES('?', '?', '?');";
			}

			@Override
			public void setParams(PreparedStatement statement) throws SQLException {
				statement.setString(1, UUID.randomUUID().toString());
				statement.setString(2, info.getName());
				statement.setString(3, info.getPassword());
			}
		});
	}

	public boolean update(UserInfo info) throws SQLException {
		return 1 == this.update(new CmdHandle() {
			@Override
			public String getSQL() {
				return "UPDATE user_base_info SET login_name='?', password='?' WHERE id='?';";
			}

			@Override
			public void setParams(PreparedStatement statement) throws SQLException {
				statement.setString(1, info.getName());
				statement.setString(2, info.getPassword());

				statement.setString(3, info.getId());
			}
		});
	}

	public boolean delete(String id) throws SQLException {
		return 1 == this.update(new CmdHandle() {
			@Override
			public String getSQL() {
				return "DELETE FROM  learning.user_base_info WHERE id='?';";
			}

			@Override
			public void setParams(PreparedStatement statement) throws SQLException {
				statement.setString(1, id);
			}
		});
	}

	public UserInfo query(String id) throws SQLException {
		return this.queryOne(new QueryHandle<UserInfo>() {

			@Override
			public String getSQL() {
				return "SELECT id, login_name, password FROM user_base_info where id=?;";
			}

			@Override
			public void setParams(PreparedStatement statement) throws SQLException {
				statement.setString(1, id);
			}

			@Override
			public UserInfo wrapper(ResultSet result) throws SQLException {
				UserInfo info = new UserInfo();
				info.setId(result.getString(1));
				info.setName(result.getString(2));
				info.setPassword(result.getString(3));
				return info;
			}
		});
	}
}
