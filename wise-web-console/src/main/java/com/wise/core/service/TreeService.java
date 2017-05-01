package com.wise.core.service;

import java.util.List;

import com.wise.core.bean.TreeBean;

/**
 * 业务抽象树基类
 * @author lingyuwang
 *
 * @param <T>
 */
public interface TreeService<T extends TreeBean<T>> extends BaseService<T> {

	/**
	 * 获取子节点
	 * @param parentId 父主键
	 * @return
	 */
	List<T> findByParentId(Integer parentId);
	
}
