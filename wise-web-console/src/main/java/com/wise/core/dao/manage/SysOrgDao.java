package com.wise.core.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysOrg;
import com.wise.core.dao.TreeDao;

public interface SysOrgDao extends TreeDao<SysOrg> {

	/**
	 * 获取所有机构
	 * @return
	 */
	List<SysOrg> select();
	
	/**
	 * 获取机构列表
	 * @return
	 */
	List<SysOrg> selectByStatus(@Param("status") Integer status);
	
}