package com.wise.core.service.manage;

import java.util.List;

import com.wise.core.bean.manage.SysArea;
import com.wise.core.service.TreeService;

public interface SysAreaService extends TreeService<SysArea> {

	/**
	 * 获取所有区域
	 * @return
	 */
	List<SysArea> find();

}
