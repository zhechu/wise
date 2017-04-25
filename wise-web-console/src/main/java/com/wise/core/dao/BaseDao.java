package com.wise.core.dao;

import com.wise.core.bean.manage.SysDict;

/**
 * dao 基类
 * @author lingyuwang
 *
 */
public interface BaseDao<T> {
	
	int deleteByPrimaryKey(Integer id);

    int insert(T t);

    int insertSelective(T t);

    SysDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
}
