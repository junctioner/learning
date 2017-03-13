package com.jungle.learning.weblearning.service;

import java.sql.SQLException;

import com.jungle.learning.weblearning.dao.UserBaseInfoDao;
import com.jungle.learning.weblearning.exception.BizException;
import com.jungle.learning.weblearning.model.UserInfo;

public class RegisterService {
	/**
	 * 用户注册操作
	 */
	public boolean register(UserInfo userInfo) {
		try {
			return new UserBaseInfoDao().insert(userInfo);
		} catch (SQLException e) {
			throw new BizException("create userinfo", e);
		}
	}
}
