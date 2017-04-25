package com.wise.common.exception;

/**
 * 系统通用异常
 * @author lingyuwang
 *
 */
public class UException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5732396005727646575L;

	public UException() {
		super();
	}
	
	public UException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UException(String message) {
		super(message);
	}
	
	public UException(Throwable cause) {
		super(cause);
	}
}
