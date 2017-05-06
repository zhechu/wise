package com.wise.core.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysDictMeta;
import com.wise.core.dao.BaseDao;

public interface SysDictMetaDao extends BaseDao<SysDictMeta> {
    
	/**
	 * 获取字典元数据列表
	 * @return
	 */
	List<SysDictMeta> select();
	
	/**
	 * 获取字典元数据
	 * @param code 编码
	 * @return
	 */
	SysDictMeta selectByCode(@Param("code") String code);
	
}