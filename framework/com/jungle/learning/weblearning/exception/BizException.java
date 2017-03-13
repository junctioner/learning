package com.jungle.learning.weblearning.exception;

public class BizException extends RuntimeException {
	private static final long serialVersionUID = -6813835578949228240L;

	public BizException() {
		super();
	}

	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

}
