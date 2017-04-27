package com.wise.core.service.manage;

import java.util.List;

import com.wise.core.bean.manage.SysResource;
import com.wise.core.service.BaseService;

public interface SysResourceService extends BaseService<SysResource> {

	/**
	 * 获取资源
	 * @param parentId 父资源主键
	 * @return
	 */
	List<SysResource> findByParentId(Integer parentId);
	
	/**
	 * 获取菜单树（可用）
	 * @return
	 */
	List<SysResource> findValidMenuTree();
	
}
