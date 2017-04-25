package com.wise.common.exception;

/**
 * 基础业务异常类,用于区分系统的异常和业务流程中的异常，具体异常分类通过后续的继承来区分
 * @author 何伟杰
 * @created 2017年4月1日
 */
public class BaseBusinessException extends Exception{
	
	private static final long serialVersionUID = -3476902131757451124L;
	
	public BaseBusinessException(){}
	/**
	 * @param businessErrorMsg 业务异常信息
	 */ 
	public BaseBusinessException(String businessErrorMsg){ this.businessErrorMsg = businessErrorMsg;}
	
	/** 业务报错信息 */
	protected String businessErrorMsg;
	
	/** 获取业务报错信息 */
	public String getBusinessErrorMsg() {
		return businessErrorMsg;
	}
	/** 设置业务报错信息 */
	public void setBusinessErrorMsg(String businessErrorMsg) {
		this.businessErrorMsg = businessErrorMsg;
	}
	
	public void printBusinessException(){
		System.err.println(this.getBusinessErrorMsg());
	}
	
}
