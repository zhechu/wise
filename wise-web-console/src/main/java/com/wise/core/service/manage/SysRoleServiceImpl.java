package com.wise.core.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.dao.manage.SysRoleDao;

/**
 * 角色
 * @author lingyuwang
 *
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{

	@Autowired
	private SysRoleDao sysRoleDao;

	@Transactional
	@Override
	public void create(SysRole sysRole) throws ValueConflictException {
		List<SysRole> sysRoleList = sysRoleDao.select(sysRole.getName());
		if (!sysRoleList.isEmpty())
			throw new ValueConflictException("角色已存在，名称不能重复");
		sysRoleDao.insertSelective(sysRole);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysRole sysRole = sysRoleDao.selectByPrimaryKey(id);
		if (sysRole == null)
			throw new DataNotExistedException("角色不存在");
		sysRoleDao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			SysRole sysRole = sysRoleDao.selectByPrimaryKey(id);
			if (sysRole == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("角色不存在");
			}
			sysRoleDao.deleteByPrimaryKey(id);
		}
	}

	@Transactional
	@Override
	public void update(SysRole sysRole) throws DataNotExistedException, ValueConflictException {
		SysRole sysRoleSource = sysRoleDao.selectByPrimaryKey(sysRole.getId());
		if (sysRoleSource == null)
			throw new DataNotExistedException("角色不存在");
		// 判断角色的名称是否有更新，有则检查是否已存在
		if (sysRole.getName() != null && !sysRole.getName().equals(sysRoleSource.getName())) {
			List<SysRole> sysRoleList = sysRoleDao.select(sysRole.getName());
			if (!sysRoleList.isEmpty())
				throw new ValueConflictException("角色已存在，名称不能重复");
		}
		sysRoleDao.updateByPrimaryKeySelective(sysRole);
	}

	@Override
	public SysRole findById(Integer id) {
		return sysRoleDao.selectByPrimaryKey(id);
	}

}
