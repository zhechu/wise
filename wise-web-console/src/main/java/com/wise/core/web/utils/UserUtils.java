package com.wise.core.web.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wise.core.bean.manage.SysRole;
import com.wise.core.web.shiro.LoginUser;

/**
 * 用户工具类
 * @author lingyuwang
 *
 */
public class UserUtils {

	private UserUtils() {}

	/**
	 * 获取 shiro session 保存的用户对象
	 * @return
	 */
	public static LoginUser getLoginUser() {
		Subject user = SecurityUtils.getSubject();
		if (user != null) 
			return (LoginUser) user.getPrincipal();
		return null;
	}
	
	/**
	 * 获取登录用户角色主键列表
	 * @return
	 */
	public static Integer[] getLoginUserRoleIds() {
		LoginUser loginUser = getLoginUser();
		if (loginUser != null) {
			List<SysRole> sysRoleList = loginUser.getRoleList();
			int size = sysRoleList.size();
			Integer[] sysRoleIds = new Integer[size];
			for (int i = 0; i < size; i++) {
				sysRoleIds[i] = sysRoleList.get(i).getId();
			}
			return sysRoleIds;
		}
		return null;
	}
	
}
