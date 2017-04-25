package com.wise.common.exception;
/**
 * 业务数据已经存在
 * @author 何伟杰
 * @created 2017年4月1日
 */
public class DataExistedException extends BaseBusinessException{
	private static final long serialVersionUID = -3991788684117848306L;
	
	public DataExistedException(String businessErrorMsg){this.businessErrorMsg = businessErrorMsg;};
	
}
