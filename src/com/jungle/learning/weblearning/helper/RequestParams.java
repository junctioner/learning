package com.jungle.learning.weblearning.helper;

import javax.servlet.http.HttpServletRequest;

public class RequestParams {
	private HttpServletRequest request;

	public RequestParams(HttpServletRequest request) {
		this.request = request;
	}

	public String getString(String name) {
		return this.request.getParameter(name);
	}
	public Integer getInteger(String name) {
		String value = this.getString(name);
		if (null == value) {
			return null;
		}
		return Integer.decode(value);
	}
	public Boolean getBoolean(String name) {
		String value = this.getString(name);
		return new Boolean(value);
	}
}
