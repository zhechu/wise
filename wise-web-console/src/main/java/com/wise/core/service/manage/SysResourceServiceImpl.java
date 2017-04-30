package com.wise.core.service.manage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wise.common.exception.service.DataNotExistedException;
import com.wise.core.bean.manage.SysResource;
import com.wise.core.config.Global;
import com.wise.core.dao.manage.SysResourceDao;
import com.wise.core.dao.manage.SysRoleResourceDao;

/**
 * 系统资源
 * @author lingyuwang
 *
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService{

	@Autowired
	private SysRoleResourceDao sysRoleResourceDao;
	
	@Autowired
	private SysResourceDao sysResourceDao;

	@Transactional
	@Override
	public void create(SysResource sysResource) {
		sysResourceDao.insertSelective(sysResource);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysResource sysResource = sysResourceDao.selectByPrimaryKey(id);
		if (sysResource == null)
			throw new DataNotExistedException("资源不存在");
		
		// 级联删除子资源
		deleteChildren(id);
		
		// 删除角色资源关系
		sysRoleResourceDao.deleteBySysResourceId(id);
		
		// 删除资源
		sysResourceDao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 级联删除子资源
	 * @param parentId 父资源主键
	 */
	private void deleteChildren(Integer parentId) {
		// 判断是否存在子资源，若存在，则删除子资源
		List<SysResource> sysResourceChildrenList = sysResourceDao.select(null, null, parentId, null);
		if (!sysResourceChildrenList.isEmpty()) {
			for (SysResource sysResource : sysResourceChildrenList) {
				Integer id = sysResource.getId();
				deleteChildren(id);
				// 删除角色资源关系
				sysRoleResourceDao.deleteBySysResourceId(id);
				// 删除资源
				sysResourceDao.deleteByPrimaryKey(id);
			}
		}
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			SysResource sysResource = sysResourceDao.selectByPrimaryKey(id);
			if (sysResource == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("资源不存在");
			}

			// 级联删除子资源
			deleteChildren(id);
			
			// 删除角色资源关系
			sysRoleResourceDao.deleteBySysResourceId(id);
			
			// 删除资源
			sysResourceDao.deleteByPrimaryKey(id);
		}
	}

	@Transactional
	@Override
	public void update(SysResource sysResource) throws DataNotExistedException {
		SysResource sysResourceSource = sysResourceDao.selectByPrimaryKey(sysResource.getId());
		if (sysResourceSource == null)
			throw new DataNotExistedException("资源不存在");
		// 若资源的状态有更新，则级联更改子资源的状态
		if (!sysResourceSource.getStatus().equals(sysResource.getStatus()))
			updateChildrenStatus(sysResource);
		sysResourceDao.updateByPrimaryKeySelective(sysResource);
	}
	
	/**
	 * 级联更新子资源状态
	 * @param parentSysResource 父资源
	 */
	private void updateChildrenStatus(SysResource parentSysResource) {
		// 判断是否存在子资源，若存在，则更新子资源状态
		List<SysResource> sysResourceChildrenList = sysResourceDao.select(null, null, parentSysResource.getId(), null);
		if (!sysResourceChildrenList.isEmpty()) {
			Integer status = parentSysResource.getStatus();
			for (SysResource sysResource : sysResourceChildrenList) {
				updateChildrenStatus(sysResource);
				sysResource.setStatus(status);
				sysResourceDao.updateByPrimaryKeySelective(sysResource);
			}
		}
	}

	@Override
	public SysResource findById(Integer id) {
		return sysResourceDao.selectByPrimaryKey(id);
	}

	@Override
	public List<SysResource> findByParentId(Integer parentId) {
		return sysResourceDao.select(null, null, parentId, null);
	}

	@Override
	public List<SysResource> findValidMenuTree() {
		List<SysResource> sysResourceList = sysResourceDao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, Global.DEFAULT_PARENT_ID, null);
		setValidMenuChildrenList(sysResourceList); // 递归查询及设置子菜单
		return sysResourceList;
	}

	@Override
	public List<SysResource> findValidMenuTree(Integer[] sysRoleIds) {
		List<SysResource> sysResourceList = sysResourceDao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, Global.DEFAULT_PARENT_ID, sysRoleIds);
		setValidMenuChildrenList(sysResourceList, sysRoleIds); // 递归查询及设置子菜单
		return sysResourceList;
	}
	
	/**
	 * 设置菜单子节点（可用）
	 * @param sysResourceList 需要设置节点的资源
	 */
	private void setValidMenuChildrenList(List<SysResource> sysResourceList) {
		//获取子节点
		for (SysResource sysResource : sysResourceList) {
			List<SysResource> sysResourceChildrenList = sysResourceDao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, sysResource.getId(), null);
			if (!sysResourceChildrenList.isEmpty()) {
				sysResource.setChildrenList(sysResourceChildrenList);
				setValidMenuChildrenList(sysResourceChildrenList);
			}
		}
	}
	
	/**
	 * 设置菜单子节点（可用）
	 * @param sysResourceList 需要设置节点的资源
	 * @param sysRoleIds 角色主键列表
	 */
	private void setValidMenuChildrenList(List<SysResource> sysResourceList, Integer[] sysRoleIds) {
		//获取子节点
		for (SysResource sysResource : sysResourceList) {
			List<SysResource> sysResourceChildrenList = sysResourceDao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, sysResource.getId(), sysRoleIds);
			if (!sysResourceChildrenList.isEmpty()) {
				sysResource.setChildrenList(sysResourceChildrenList);
				setValidMenuChildrenList(sysResourceChildrenList);
			}
		}
	}

	@Override
	public List<SysResource> findValidBySysRoleIds(Integer[] sysRoleIds) {
		List<SysResource> sysResourceList = sysResourceDao.select(Global.NORMAL, null, null, sysRoleIds);
		// 资源去重
		Set<SysResource> sysResourceSet = new HashSet<SysResource>(sysResourceList);
		return new ArrayList<SysResource>(sysResourceSet);
	}

	@Override
	public List<SysResource> find() {
		return sysResourceDao.select(null, null, null, null);
	}
	
	@Override
	public List<SysResource> findValid() {
		return sysResourceDao.select(Global.NORMAL, null, null, null);
	}

}
