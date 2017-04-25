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
	
	List<SysDict> select();
	
}