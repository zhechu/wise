package com.wise.common.exception.service;

/**
 * 业务数据不存在
 * @author lingyuwang
 *
 */
public class DataNotExistedException extends ServiceException{

	private static final long serialVersionUID = -3991788684117848306L;

	public DataNotExistedException() {
		super();
	}
	
	public DataNotExistedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataNotExistedException(String message) {
		super(message);
	}
	
	public DataNotExistedException(Throwable cause) {
		super(cause);
	}
}
