package com.wise.core.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wise.core.bean.manage.SysManager;
import com.wise.core.dao.BaseDao;
import com.wise.core.dto.SysManagerParam;

public interface SysManagerDao extends BaseDao<SysManager> {
    
	/**
	 * 查询系统用户
	 * @param userName 用户名
	 * @return
	 */
	SysManager selectByUserName(@Param("userName") String userName);
	
	/**
	 * 查询系统用户（模糊查询）
	 * @param companyId 公司主键
	 * @param sysManagerParam 用户参数
	 * @return
	 */
	List<SysManager> select(SysManagerParam sysManagerParam);
	
}