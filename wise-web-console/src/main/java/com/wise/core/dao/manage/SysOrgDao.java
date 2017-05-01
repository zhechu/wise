package com.wise.core.dao.manage;

import java.util.List;

import com.wise.core.bean.manage.SysOrg;
import com.wise.core.dao.TreeDao;

public interface SysOrgDao extends TreeDao<SysOrg> {

	/**
	 * 获取所有机构
	 * @return
	 */
	List<SysOrg> select();
	
}