package com.jungle.learning.weblearning.dao.handle;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface CmdHandle {

	String getSQL();

	void setParams(PreparedStatement statement) throws SQLException;
}
