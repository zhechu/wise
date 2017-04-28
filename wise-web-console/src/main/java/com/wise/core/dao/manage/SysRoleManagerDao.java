package com.wise.core.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysRoleManager;
import com.wise.core.dao.BaseDao;

public interface SysRoleManagerDao extends BaseDao<SysRoleManager> {
    
	/**
	 * 删除系统用户角色
	 * @param managerId 系统用户主键
	 */
	void deleteByManagerId(@Param("managerId") Integer managerId);

	/**
	 * 删除系统用户角色
	 * @param sysRoleId 角色主键
	 */
	void deleteBySysRoleId(@Param("sysRoleId") Integer sysRoleId);
	
}