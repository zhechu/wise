package com.wise.common.exception.service;

import com.wise.common.exception.WiseException;

/**
 * 业务基础异常类
 * @author lingyuwang
 *
 */
public class ServiceException extends WiseException {
	
	private static final long serialVersionUID = -3476902131757451124L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
