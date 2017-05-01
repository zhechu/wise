package com.wise.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.TreeBean;

/**
 * dao 树基类
 * @author lingyuwang
 *
 */
public interface TreeDao<T extends TreeBean<T>> extends BaseDao<T> {

	/**
	 * 获取子节点
	 * @param parentId 父主键
	 * @return
	 */
	List<T> selectByParentId(@Param("parentId") Integer parentId);

	/**
	 * 获取子孙节点
	 * @param parentIds 父主键串
	 * @return
	 */
	List<T> selectByLikeParentIds(@Param("parentIds") String parentIds);
	
}
