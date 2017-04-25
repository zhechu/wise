package com.wise.common.exception;
/**
 * 业务数据冲突
 * @author 何伟杰
 * @created 2017年4月1日
 */
public class ValueConflictException extends BaseBusinessException{

	private static final long serialVersionUID = -3991788684117848306L;
	
	public ValueConflictException(){};
	/**
	 * @param dataAttr 判断数据值冲突的属性
	 * @param businessErrorMsg 业务数据错误信息
	 */
	public ValueConflictException(String dataAttr,String businessErrorMsg){this.dataAttr = dataAttr; this.businessErrorMsg = businessErrorMsg;};
	/** 判断数据值冲突的属性 */
	protected String dataAttr;
	
	/** 获取判断数据值冲突的属性 */
	public String getDataAttr() {
		return dataAttr;
	}
	/** 设置判断数据值冲突的属性 */
	public void setDataAttr(String dataAttr) {
		this.dataAttr = dataAttr;
	}
	@Override
	public  void printBusinessException() {
		System.out.println("数据冲突判断依据字段属性："+this.dataAttr);
		super.printBusinessException();
	}
	
	
	
	

}
