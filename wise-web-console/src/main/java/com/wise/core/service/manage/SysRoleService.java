package com.wise.core.service.manage;

import java.util.List;

import com.wise.core.bean.manage.SysRole;
import com.wise.core.service.BaseService;

public interface SysRoleService extends BaseService<SysRole> {

	/**
	 * 获取角色列表
	 * @return
	 */
	List<SysRole> find();
	
}
