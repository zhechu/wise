package com.wise.common.utils.excel.fieldtype;

import com.wise.common.utils.StringUtils;
import com.wise.core.bean.manage.SysOrg;
import com.wise.core.service.manage.SysOrgService;
import com.wise.web.utils.SpringContextHolder;

/**
 * 字段类型转换
 * @author lingyuwang
 *
 */
public class SysOrgType {

	private static SysOrgService sysOrgService = SpringContextHolder.getBean(SysOrgService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (SysOrg e : sysOrgService.findValid()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((SysOrg)val).getName() != null){
			return ((SysOrg)val).getName();
		}
		return "";
	}
	
}
