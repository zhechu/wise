package com.wise.core.dao.manage;

import java.util.List;

import com.wise.core.bean.manage.SysArea;
import com.wise.core.dao.TreeDao;

public interface SysAreaDao extends TreeDao<SysArea> {

	/**
	 * 获取所有区域
	 * @return
	 */
	List<SysArea> select();
	
}