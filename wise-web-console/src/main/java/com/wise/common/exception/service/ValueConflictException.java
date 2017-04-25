package com.wise.common.exception.service;
/**
 * 业务数据冲突
 * @author lingyuwang
 *
 */
public class ValueConflictException extends ServiceException{

	private static final long serialVersionUID = -3991788684117848306L;
	
	public ValueConflictException() {
		super();
	}
	
	public ValueConflictException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ValueConflictException(String message) {
		super(message);
	}
	
	public ValueConflictException(Throwable cause) {
		super(cause);
	}
}
