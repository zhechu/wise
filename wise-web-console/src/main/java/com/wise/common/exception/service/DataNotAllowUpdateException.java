package com.wise.common.exception.service;

/**
 * 数据不允许更新
 * @author lingyuwang
 *
 */
public class DataNotAllowUpdateException extends ServiceException{

	private static final long serialVersionUID = -3991788684117848306L;

	public DataNotAllowUpdateException() {
		super();
	}
	
	public DataNotAllowUpdateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataNotAllowUpdateException(String message) {
		super(message);
	}
	
	public DataNotAllowUpdateException(Throwable cause) {
		super(cause);
	}
}
