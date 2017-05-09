package com.wise.common.exception.controller;

import com.wise.common.exception.WiseException;

/**
 * 控制器基础异常类
 * @author lingyuwang
 *
 */
public class ControllerException extends WiseException {
	
	private static final long serialVersionUID = -3476902131757451124L;

	public ControllerException() {
		super();
	}
	
	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException(Throwable cause) {
		super(cause);
	}
}
