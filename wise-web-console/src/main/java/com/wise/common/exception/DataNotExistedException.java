package com.wise.common.exception;
/**
 * 业务数据不存在
 * @author 何伟杰
 * @created 2017年4月1日
 */
public class DataNotExistedException extends BaseBusinessException{

	private static final long serialVersionUID = -3991788684117848306L;
	public DataNotExistedException(String businessErrorMsg){ this.businessErrorMsg = businessErrorMsg; }
}
