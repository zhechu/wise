package com.wise.core.dao;

/**
 * dao 基类
 * @author lingyuwang
 *
 */
public interface BaseDao<T> {
	
	int deleteByPrimaryKey(Integer id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
}
