package com.wise.core.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wise.common.exception.service.DataNotAllowUpdateException;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.common.utils.SecureUtil;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.bean.manage.SysRoleManager;
import com.wise.core.dao.manage.SysManagerDao;
import com.wise.core.dao.manage.SysRoleManagerDao;
import com.wise.core.dto.PageParam;
import com.wise.core.dto.SysManagerParam;
import com.wise.core.service.BaseServiceImpl;

import tk.mybatis.orderbyhelper.OrderByHelper;

/**
 * 系统用户
 * @author lingyuwang
 *
 */
@Service("sysManagerService")
public class SysManagerServiceImpl extends BaseServiceImpl<SysManagerDao, SysManager> implements SysManagerService{

	@Autowired
	private SysRoleManagerDao sysRoleManagerDao;

	@Transactional
	@Override
	public void create(SysManager sysManager) throws ValueConflictException {
		SysManager sysManagerSource = dao.selectByUserName(sysManager.getUserName());
		if (sysManagerSource != null)
			throw new ValueConflictException("用户已存在，用户名不能重复");
		// 密码加盐
		String salt = SecureUtil.generateSalt();
		String pwd = SecureUtil.encryptByMd5(sysManager.getPwd(), salt);
		sysManager.setSalt(salt);
		sysManager.setPwd(pwd);
		dao.insertSelective(sysManager);
		
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
		SysManager sysManager = dao.selectByPrimaryKey(id);
		if (sysManager == null)
			throw new DataNotExistedException("用户不存在");
		// 删除用户角色关系
		sysRoleManagerDao.deleteByManagerId(id);
		// 删除用户
		dao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			SysManager sysManager = dao.selectByPrimaryKey(id);
			if (sysManager == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("用户不存在");
			}
			// 删除用户角色关系
			sysRoleManagerDao.deleteByManagerId(id);
			// 删除用户
			dao.deleteByPrimaryKey(id);
		}
	}

	@Transactional
	@Override
	public void update(SysManager sysManager) throws DataNotExistedException, DataNotAllowUpdateException {
		SysManager sysManagerSource = dao.selectByPrimaryKey(sysManager.getId());
		if (sysManagerSource == null)
			throw new DataNotExistedException("用户不存在");
		// 判断用户名是否有更新，有则抛出异常（用户名不能编辑）
		if (sysManager.getUserName() != null && !sysManager.getUserName().equals(sysManagerSource.getUserName()))
			throw new DataNotAllowUpdateException("不能修改用户名");
		dao.updateByPrimaryKeySelective(sysManager);

		// 更新用户角色
		// 先删除用户角色
		sysRoleManagerDao.deleteByManagerId(sysManager.getId());
		// 再添加用户角色
		List<SysRole> sysRoleList = sysManager.getSysRoleList();
		for (SysRole sysRole : sysRoleList) {
			SysRoleManager sysRoleManager = new SysRoleManager();
			sysRoleManager.setManagerId(sysManager.getId());
			sysRoleManager.setRoleId(sysRole.getId());
			sysRoleManagerDao.insertSelective(sysRoleManager);
		}
	}

	@Override
	public PageInfo<SysManager> findPage(PageParam pageParam, SysManagerParam sysManagerParam) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
		OrderByHelper.orderBy(pageParam.getOrderBy("createdAt", "desc"));
        List<SysManager> list = dao.select(sysManagerParam);
		return new PageInfo<SysManager>(list);
	}

	@Override
	public SysManager findByUserName(String userName) {
		return dao.selectByUserName(userName);
	}

	@Override
	public SysManager login(String userName, String pwd) throws DataNotExistedException, ValueConflictException {
		SysManager sysManager = dao.selectByUserName(userName);
		if (sysManager == null) 
			throw new DataNotExistedException("用户不存在");
		// 密码加盐
		String salt = sysManager.getSalt();
		pwd = SecureUtil.encryptByMd5(pwd, salt);
		// 判断密码是否有误
		String sourcePwd = sysManager.getPwd();
		if (!pwd.equals(sourcePwd)) 
			throw new ValueConflictException("密码有误");
		return sysManager;
	}

	@Transactional
	@Override
	public void updatePwd(String loginName, String oldPwd, String newPwd) throws DataNotExistedException, ValueConflictException {
		SysManager sysManager = dao.selectByUserName(loginName);
		if (sysManager == null) 
			throw new DataNotExistedException("用户不存在");
		// 密码加盐
		String salt = sysManager.getSalt();
		oldPwd = SecureUtil.encryptByMd5(oldPwd, salt);
		// 判断密码是否有误
		String sourcePwd = sysManager.getPwd();
		if (!oldPwd.equals(sourcePwd)) 
			throw new ValueConflictException("旧密码有误");
		// 更新密码
		newPwd = SecureUtil.encryptByMd5(newPwd, salt);
		sysManager.setPwd(newPwd);
		// 更新用户
		dao.updateByPrimaryKeySelective(sysManager);
	}

	@Transactional
	@Override
	public void updateInfo(SysManager sysManager) throws DataNotExistedException, DataNotAllowUpdateException {
		SysManager sysManagerSource = dao.selectByPrimaryKey(sysManager.getId());
		if (sysManagerSource == null)
			throw new DataNotExistedException("用户不存在");
		// 判断用户名是否有更新，有则抛出异常（用户名不能编辑）
		if (sysManager.getUserName() != null && !sysManager.getUserName().equals(sysManagerSource.getUserName()))
			throw new DataNotAllowUpdateException("不能修改用户名");
		dao.updateByPrimaryKeySelective(sysManager);
	}

}
