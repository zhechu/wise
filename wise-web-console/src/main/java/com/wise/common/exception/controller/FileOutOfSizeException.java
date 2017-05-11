package com.wise.common.exception.controller;

/**
 * 文件过大异常
 * @author lingyuwang
 *
 */
public class FileOutOfSizeException extends UploadFileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5231483519259963878L;

	public FileOutOfSizeException() {
		super();
	}
	
	public FileOutOfSizeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FileOutOfSizeException(String message) {
		super(message);
	}
	
	public FileOutOfSizeException(Throwable cause) {
		super(cause);
	}
}
