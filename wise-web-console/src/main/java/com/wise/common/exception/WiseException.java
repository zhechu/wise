package com.wise.common.exception;

/**
 * 系统通用异常
 * @author lingyuwang
 *
 */
public class WiseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5732396005727646575L;

	public WiseException() {
		super();
	}
	
	public WiseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public WiseException(String message) {
		super(message);
	}
	
	public WiseException(Throwable cause) {
		super(cause);
	}
}
