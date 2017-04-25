package com.wise.common.exception.service;

/**
 * 业务数据已经存在
 * @author lingyuwang
 *
 */
public class DataExistedException extends ServiceException{
	
	private static final long serialVersionUID = -3991788684117848306L;

	public DataExistedException() {
		super();
	}
	
	public DataExistedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataExistedException(String message) {
		super(message);
	}
	
	public DataExistedException(Throwable cause) {
		super(cause);
	}
}
