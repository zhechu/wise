package com.wise.core.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysRoleResource;
import com.wise.core.dao.BaseDao;

public interface SysRoleResourceDao extends BaseDao<SysRoleResource> {

	/**
	 * 获取资源主键列表
	 * @param sysRoleId 角色主键
	 * @return
	 */
	List<Integer> selectSysResourceIdsBySysRoleId(@Param("sysRoleId") Integer sysRoleId);
	
	/**
	 * 删除角色资源
	 * @param sysRoleId 角色主键
	 */
	void deleteBySysRoleId(@Param("sysRoleId") Integer sysRoleId);
	
	/**
	 * 删除角色资源
	 * @param sysResourceId 角色主键
	 */
	void deleteBySysResourceId(@Param("sysResourceId") Integer sysResourceId);
	
}