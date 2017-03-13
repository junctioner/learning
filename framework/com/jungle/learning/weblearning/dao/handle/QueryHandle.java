package com.jungle.learning.weblearning.dao.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryHandle<T> extends CmdHandle {

	T wrapper(ResultSet resultSet) throws SQLException;
}
