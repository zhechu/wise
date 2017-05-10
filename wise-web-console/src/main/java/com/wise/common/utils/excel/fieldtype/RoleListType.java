package com.wise.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import com.wise.common.utils.Collections3;
import com.wise.common.utils.StringUtils;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.service.manage.SysRoleService;
import com.wise.web.utils.SpringContextHolder;

/**
 * 字段类型转换
 * @author lingyuwang
 *
 */
public class RoleListType {

	private static SysRoleService sysRoleService = SpringContextHolder.getBean(SysRoleService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<SysRole> roleList = Lists.newArrayList();
		List<SysRole> allRoleList = sysRoleService.findValid();
		for (String s : StringUtils.split(val, ",")){
			for (SysRole e : allRoleList){
				if (StringUtils.trimToEmpty(s).equals(e.getName())){
					roleList.add(e);
				}
			}
		}
		return roleList.size()>0?roleList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<SysRole> roleList = (List<SysRole>)val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}
	
}
