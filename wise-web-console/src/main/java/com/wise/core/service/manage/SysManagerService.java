package com.wise.core.service.manage;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wise.common.exception.service.DataNotAllowUpdateException;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.dto.PageParam;
import com.wise.core.dto.SysManagerParam;
import com.wise.core.service.BaseService;

public interface SysManagerService extends BaseService<SysManager> {

	/**
	 * 批量添加
	 * @param sysManagerList 用户列表
	 * @throws ValueConflictException
	 * @throws DataNotExistedException
	 */
	void createBatch(List<SysManager> sysManagerList) throws ValueConflictException, DataNotExistedException;
	
	/**
	 * 获取用户
	 * @param userName 用户名
	 * @return
	 */
	SysManager findByUserName(String userName);
	
	/**
	 * 分页查询（模糊查询）
	 * @param pageParam 分页参数
	 * @param sysManagerParam 用户参数
	 * @return
	 */
	PageInfo<SysManager> findPage(PageParam pageParam, SysManagerParam sysManagerParam);
	
	/**
	 * 模糊查询
	 * @param sortName 排序字段
	 * @param sortOrder 排序方式
	 * @param sysManagerParam 用户参数
	 * @return
	 */
	List<SysManager> find(String sortName, String sortOrder, SysManagerParam sysManagerParam);

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
