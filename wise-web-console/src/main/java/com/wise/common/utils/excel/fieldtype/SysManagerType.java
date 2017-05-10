package com.wise.common.utils.excel.fieldtype;

import com.wise.core.bean.manage.SysManager;

/**
 * 字段类型转换
 * @author lingyuwang
 *
 */
public class SysManagerType {

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((SysManager)val).getName() != null){
			return ((SysManager)val).getName();
		}
		return "";
	}
	
}
