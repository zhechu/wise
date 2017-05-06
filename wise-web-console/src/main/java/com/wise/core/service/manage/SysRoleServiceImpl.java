package com.wise.core.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.bean.manage.SysRoleResource;
import com.wise.core.config.Global;
import com.wise.core.dao.manage.SysRoleDao;
import com.wise.core.dao.manage.SysRoleManagerDao;
import com.wise.core.dao.manage.SysRoleResourceDao;
import com.wise.core.service.BaseServiceImpl;

/**
 * 角色
 * @author lingyuwang
 *
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRole> implements SysRoleService{

	@Autowired
	private SysRoleResourceDao sysRoleResourceDao;

	@Autowired
	private SysRoleManagerDao sysRoleManagerDao;

	@Transactional
	@Override
	public void create(SysRole sysRole) throws ValueConflictException {
		List<SysRole> sysRoleList = dao.select(null, sysRole.getName());
		if (!sysRoleList.isEmpty())
			throw new ValueConflictException("角色已存在，名称不能重复");
		dao.insertSelective(sysRole);
		
		// 添加角色资源关系
		List<Integer> sysResourceIdList = sysRole.getSysResourceIdList();
		for (Integer sysResourceId : sysResourceIdList) {
			SysRoleResource sysRoleResource = new SysRoleResource();
			sysRoleResource.setRoleId(sysRole.getId());
			sysRoleResource.setSysResourceId(sysResourceId);
			sysRoleResourceDao.insertSelective(sysRoleResource);
		}
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysRole sysRole = dao.selectByPrimaryKey(id);
		if (sysRole == null)
			throw new DataNotExistedException("角色不存在");
		dao.deleteByPrimaryKey(id);
		
		// 删除角色资源关系
		sysRoleResourceDao.deleteBySysRoleId(id);
		
		// 删除用户角色关系
		sysRoleManagerDao.deleteBySysRoleId(id);
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			SysRole sysRole = dao.selectByPrimaryKey(id);
			if (sysRole == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("角色不存在");
			}
			dao.deleteByPrimaryKey(id);

			// 删除角色资源关系
			sysRoleResourceDao.deleteBySysRoleId(id);
			
			// 删除用户角色关系
			sysRoleManagerDao.deleteBySysRoleId(id);
		}
	}

	@Transactional
	@Override
	public void update(SysRole sysRole) throws DataNotExistedException, ValueConflictException {
		SysRole sysRoleSource = dao.selectByPrimaryKey(sysRole.getId());
		if (sysRoleSource == null)
			throw new DataNotExistedException("角色不存在");
		// 判断角色的名称是否有更新，有则检查是否已存在
		if (sysRole.getName() != null && !sysRole.getName().equals(sysRoleSource.getName())) {
			List<SysRole> sysRoleList = dao.select(null, sysRole.getName());
			if (!sysRoleList.isEmpty())
				throw new ValueConflictException("角色已存在，名称不能重复");
		}
		dao.updateByPrimaryKeySelective(sysRole);
		
		// 删除角色资源关系
		sysRoleResourceDao.deleteBySysRoleId(sysRole.getId());
		
		// 添加角色资源关系
		List<Integer> sysResourceIdList = sysRole.getSysResourceIdList();
		for (Integer sysResourceId : sysResourceIdList) {
			SysRoleResource sysRoleResource = new SysRoleResource();
			sysRoleResource.setRoleId(sysRole.getId());
			sysRoleResource.setSysResourceId(sysResourceId);
			sysRoleResourceDao.insertSelective(sysRoleResource);
		}
	}

	@Override
	public SysRole findById(Integer id) {
		SysRole sysRole = dao.selectByPrimaryKey(id);
		if (sysRole != null) {
			// 查询并设置资源主键列表
			List<Integer> sysResourceIdList = sysRoleResourceDao.selectSysResourceIdsBySysRoleId(sysRole.getId());
			sysRole.setSysResourceIdList(sysResourceIdList);
		}
		return sysRole;
	}

	@Override
	public List<SysRole> find() {
		return dao.select(null, null);
	}

	@Override
	public List<SysRole> findValid() {
		return dao.select(Global.NORMAL, null);
	}

}
