package com.wise.core.service.manage;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wise.common.dto.PageParam;
import com.wise.common.exception.service.DataNotAllowUpdateException;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.common.utils.SecureUtil;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.bean.manage.SysRoleManager;
import com.wise.core.dao.manage.SysManagerDao;
import com.wise.core.dao.manage.SysRoleManagerDao;

/**
 * 系统用户
 * @author lingyuwang
 *
 */
@Service("sysManagerService")
public class SysManagerServiceImpl implements SysManagerService{

	@Autowired
	private SysManagerDao sysManagerDao;

	@Autowired
	private SysRoleManagerDao sysRoleManagerDao;

	@Transactional
	@Override
	public void create(SysManager sysManager) throws ValueConflictException {
		SysManager sysManagerSource = sysManagerDao.selectByUserName(sysManager.getUserName());
		if (sysManagerSource != null)
			throw new ValueConflictException("用户已存在，用户名不能重复");
		// 密码加盐
		String salt = SecureUtil.generateSalt();
		String pwd = SecureUtil.encryptByMd5(sysManager.getPwd(), salt);
		sysManager.setSalt(salt);
		sysManager.setPwd(pwd);
		sysManagerDao.insertSelective(sysManager);
		
		// 添加用户角色
		List<SysRole> sysRoleList = sysManager.getSysRoleList();
		for (SysRole sysRole : sysRoleList) {
			SysRoleManager sysRoleManager = new SysRoleManager();
			sysRoleManager.setManagerId(sysManager.getId());
			sysRoleManager.setRoleId(sysRole.getId());
			sysRoleManagerDao.insertSelective(sysRoleManager);
		}
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysManager sysManager = sysManagerDao.selectByPrimaryKey(id);
		if (sysManager == null)
			throw new DataNotExistedException("用户不存在");
		// 删除用户角色关系
		sysRoleManagerDao.deleteByManagerId(id);
		// 删除用户
		sysManagerDao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			SysManager sysManager = sysManagerDao.selectByPrimaryKey(id);
			if (sysManager == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("用户不存在");
			}
			// 删除用户角色关系
			sysRoleManagerDao.deleteByManagerId(id);
			// 删除用户
			sysManagerDao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void update(SysManager sysManager) throws DataNotExistedException, DataNotAllowUpdateException {
		SysManager sysManagerSource = sysManagerDao.selectByPrimaryKey(sysManager.getId());
		if (sysManagerSource == null)
			throw new DataNotExistedException("用户不存在");
		// 判断用户名是否有更新，有则抛出异常（用户名不能编辑）
		if (sysManager.getUserName() != null && !sysManager.getUserName().equals(sysManagerSource.getUserName()))
			throw new DataNotAllowUpdateException("不能修改用户名");
		sysManagerDao.updateByPrimaryKeySelective(sysManager);
	}

	@Override
	public SysManager findById(Integer id) {
		return sysManagerDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<SysManager> findPage(PageParam pageParam, String userName, Integer status, String email, String name,
			Date createdAtStart, Date createdAtEnd) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize()).setOrderBy(pageParam.getOrderBy("createdAt", "asc"));
        List<SysManager> list = sysManagerDao.select(userName, status, email, name, createdAtStart, createdAtEnd);
		return new PageInfo<SysManager>(list);
	}

}
