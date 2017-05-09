package com.wise.core.service.manage;

import java.util.List;

import com.wise.core.bean.manage.SysOrg;
import com.wise.core.service.TreeService;

public interface SysOrgService extends TreeService<SysOrg> {

	/**
	 * 获取所有机构
	 * @return
	 */
	List<SysOrg> find();
	
	/**
	 * 获取所有机构（可用）
	 * @return
	 */
	List<SysOrg> findValid();

}
