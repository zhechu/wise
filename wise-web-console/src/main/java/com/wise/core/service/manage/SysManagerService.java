package com.wise.core.service.manage;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.wise.common.dto.PageParam;
import com.wise.common.exception.service.DataNotAllowUpdateException;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.service.BaseService;

public interface SysManagerService extends BaseService<SysManager> {

	/**
	 * 获取用户
	 * @param userName 用户名
	 * @return
	 */
	SysManager findByUserName(String userName);
	
	/**
	 * 分页查询（模糊查询）
	 * @param pageParam 分页参数
	 * @param userName 用户名
	 * @param status 状态
	 * @param email 邮箱
	 * @param sysRoleId 角色主键
	 * @param name 名称
	 * @param createdAtStart 创建开始时间
	 * @param createdAtEnd 创建结束时间
	 * @return
	 */
	PageInfo<SysManager> findPage(PageParam pageParam, String userName, Integer status, String email, Integer sysRoleId, String name, Date createdAtStart, Date createdAtEnd);

	/**
	 * 用户登录
	 * @param userName 用户名
	 * @param pwd 密码
	 * @return
	 * @throws DataNotExistedException
	 * @throws ValueConflictException 
	 */
	SysManager login(String userName, String pwd) throws DataNotExistedException, ValueConflictException;

	/**
	 * 更新密码
	 * @param userName 用户名
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @throws DataNotExistedException 
	 * @throws ValueConflictException 
	 */
	void updatePwd(String loginName, String oldPwd, String newPwd) throws DataNotExistedException, ValueConflictException;
	
	/**
	 * 更新个人资料
	 * @param sysManager
	 * @throws DataNotExistedException
	 * @throws DataNotAllowUpdateException
	 */
	void updateInfo(SysManager sysManager) throws DataNotExistedException, DataNotAllowUpdateException;
	
}
