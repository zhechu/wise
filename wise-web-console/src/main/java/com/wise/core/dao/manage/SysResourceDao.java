package com.wise.core.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysResource;
import com.wise.core.dao.BaseDao;

public interface SysResourceDao extends BaseDao<SysResource> {
    
	/**
	 * 获取资源
	 * @param status 状态
	 * @param type 类型
	 * @param parentId 父资源主键
	 * @return
	 */
	List<SysResource> select(@Param("status") Integer status, @Param("type") Integer type, @Param("parentId") Integer parentId);
	
}