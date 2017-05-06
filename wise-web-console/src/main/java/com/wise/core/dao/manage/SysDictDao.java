package com.wise.core.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysDict;
import com.wise.core.dao.BaseDao;

public interface SysDictDao extends BaseDao<SysDict> {

	/**
	 * 获取字典列表
	 * @param type 类型
	 * @param label 标签
	 * @return
	 */
	List<SysDict> selectByTypeAndLabel(@Param("type") String type, @Param("label") String label);
	
	/**
	 * 获取字典列表
	 * @param type 类型
	 * @return
	 */
	List<SysDict> selectByType(@Param("type") String type);
	
	/**
	 * 获取字典列表（模糊查询）
	 * @param remarks 备注
	 * @param status 状态
	 * @return
	 */
	List<SysDict> selectByLike(@Param("remarks") String remarks, @Param("status") Integer status);
	
	/**
	 * 获取字典列表
	 * @param status 状态
	 * @return
	 */
	List<SysDict> selectByStatus(@Param("status") Integer status);
	
	/**
	 * 获取字典列表（模糊查询，数据权限，行级与列级）
	 * @param fields 允许查询的字段，通过逗号拼接，如：id, type, value, label
	 * @param filtrate 过滤条件，where 过滤，如：type = 'status'
	 * @param type 类型
	 * @param status 状态
	 * @return
	 */
	/*List<SysDict> selectByAuth(@Param("fields") String fields, @Param("filtrate") String filtrate, @Param("type") String type, @Param("status") Integer status);*/
	
}