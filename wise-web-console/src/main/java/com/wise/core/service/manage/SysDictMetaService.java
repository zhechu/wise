package com.wise.core.service.manage;

import java.util.List;

import com.wise.core.bean.manage.SysDictMeta;
import com.wise.core.service.BaseService;

public interface SysDictMetaService extends BaseService<SysDictMeta> {

	/**
	 * 获取字典元数据
	 * @return
	 */
	List<SysDictMeta> find();
	
	/**
	 * 获取字典元数据
	 * @param code 编码
	 * @return
	 */
	SysDictMeta findByCode(String code);
	
}
