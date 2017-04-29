package com.wise.core.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysRoleResource;
import com.wise.core.dao.BaseDao;

public interface SysRoleResourceDao extends BaseDao<SysRoleResource> {

	/**
	 * 删除角色资源
	 * @param sysRoleId
	 */
	void deleteBySysRoleId(@Param("sysRoleId") Integer sysRoleId);
	
	/**
	 * 删除角色资源
	 * @param sysResourceId
	 */
	void deleteBySysResourceId(@Param("sysResourceId") Integer sysResourceId);
	
}