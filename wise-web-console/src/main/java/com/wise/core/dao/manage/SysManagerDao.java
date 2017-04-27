package com.wise.core.dao.manage;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysManager;
import com.wise.core.dao.BaseDao;

public interface SysManagerDao extends BaseDao<SysManager> {
    
	/**
	 * 查询系统用户
	 * @param userName 用户名
	 * @return
	 */
	SysManager selectByUserName(@Param("userName") String userName);
	
	/**
	 * 查询系统用户（模糊查询）
	 * @param userName 用户名
	 * @param status 状态
	 * @param email 邮箱
	 * @param name 名称
	 * @param createdAtStart 创建开始时间
	 * @param createdAtEnd 创建结束时间
	 * @return
	 */
	List<SysManager> select(@Param("userName") String userName, @Param("status") Integer status, @Param("email") String email, @Param("name") String name, @Param("createdAtStart") Date createdAtStart, @Param("createdAtEnd") Date createdAtEnd);
	
}