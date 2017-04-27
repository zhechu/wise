package com.wise.core.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysRole;
import com.wise.core.dao.BaseDao;

public interface SysRoleDao extends BaseDao<SysRole> {
    
	/**
	 * 获取角色列表
	 * @param name 名称
	 * @return
	 */
	List<SysRole> select(@Param("name") String name);
	
}