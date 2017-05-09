package com.wise.common.exception.controller;

/**
 * 上传文件异常
 * @author lingyuwang
 *
 */
public class UploadFileException extends ControllerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5231483519259963878L;

	public UploadFileException() {
		super();
	}
	
	public UploadFileException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UploadFileException(String message) {
		super(message);
	}
	
	public UploadFileException(Throwable cause) {
		super(cause);
	}
}
