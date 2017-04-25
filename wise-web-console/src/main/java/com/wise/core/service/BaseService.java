package com.wise.core.service;

import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;

/**
 * 业务抽象基类
 * @author lingyuwang
 *
 * @param <T>
 */
public interface BaseService<T> {
	
	/**
	 * 添加
	 * @param t 实体对象
	 * @throws ValueConflictException
	 */
	void create(T t) throws ValueConflictException;
	
	/**
	 * 删除
	 * @param id 主键
	 * @throws DataNotExistedException
	 */
	void deleteById(Integer id) throws DataNotExistedException;
	
	/**
	 * 批量删除
	 * @param ids 主键列表
	 * @throws DataNotExistedException
	 */
	void delete(Integer[] ids) throws DataNotExistedException;
	
	/**
	 * 更新
	 * @param t 实体对象
	 * @throws DataNotExistedException
	 * @throws ValueConflictException
	 */
	void update(T t) throws DataNotExistedException, ValueConflictException;

	/**
	 * 获取实体对象
	 * @param id 主键
	 * @return
	 */
	T selectById(Integer id);
	
}
