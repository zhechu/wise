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
import com.wise.core.service.TreeServiceImpl;

/**
 * 系统资源
 * @author lingyuwang
 *
 */
@Service("sysResourceService")
public class SysResourceServiceImpl extends TreeServiceImpl<SysResourceDao, SysResource> implements SysResourceService{

	@Autowired
	private SysRoleResourceDao sysRoleResourceDao;
	
	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysResource sysResource = dao.selectByPrimaryKey(id);
		if (sysResource == null)
			throw new DataNotExistedException("资源不存在");
		
		// 级联删除子孙资源
		String parentIds = sysResource.getParentIds() + sysResource.getId() + ",";
		List<SysResource> childrenList = dao.selectByLikeParentIds(parentIds);
		for (SysResource children : childrenList) {
			// 删除角色资源关系
			sysRoleResourceDao.deleteBySysResourceId(children.getId());
			
			// 删除资源
			dao.deleteByPrimaryKey(children.getId());
		}
		
		// 删除角色资源关系
		sysRoleResourceDao.deleteBySysResourceId(id);
		
		// 删除资源
		dao.deleteByPrimaryKey(id);
	}
	
	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			SysResource sysResource = dao.selectByPrimaryKey(id);
			if (sysResource == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("资源不存在");
			}

			// 级联删除子孙资源
			String parentIds = sysResource.getParentIds() + sysResource.getId() + ",";
			List<SysResource> childrenList = dao.selectByLikeParentIds(parentIds);
			for (SysResource children : childrenList) {
				// 删除角色资源关系
				sysRoleResourceDao.deleteBySysResourceId(children.getId());
				
				// 删除资源
				dao.deleteByPrimaryKey(children.getId());
			}
			
			// 删除角色资源关系
			sysRoleResourceDao.deleteBySysResourceId(id);
			
			// 删除资源
			dao.deleteByPrimaryKey(id);
		}
	}

	@Transactional
	@Override
	public void update(SysResource sysResource) throws DataNotExistedException {
		SysResource sysResourceSource = dao.selectByPrimaryKey(sysResource.getId());
		if (sysResourceSource == null)
			throw new DataNotExistedException("资源不存在");
		
		// 若资源的状态有更新，则级联更改子资源的状态
		if (!sysResourceSource.getStatus().equals(sysResource.getStatus())) {
			Integer status = sysResource.getStatus();
			String parentIds = sysResourceSource.getParentIds() + sysResourceSource.getId() + ",";
			List<SysResource> sysResourceList = dao.selectByLikeParentIds(parentIds);
			for (SysResource t : sysResourceList) {
				t.setStatus(status);
				dao.updateByPrimaryKeySelective(t);
			}
		}
		
		dao.updateByPrimaryKeySelective(sysResource);
	}
	
	@Override
	public List<SysResource> findValidMenuTree() {
		List<SysResource> sysResourceList = dao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, Global.DEFAULT_PARENT_ID, null);
		setValidMenuChildrenList(sysResourceList); // 递归查询及设置子菜单
		return sysResourceList;
	}

	@Override
	public List<SysResource> findValidMenuTree(Integer[] sysRoleIds) {
		List<SysResource> sysResourceList = dao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, Global.DEFAULT_PARENT_ID, sysRoleIds);
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
			List<SysResource> sysResourceChildrenList = dao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, sysResource.getId(), null);
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
			List<SysResource> sysResourceChildrenList = dao.select(Global.NORMAL, Global.SYS_RESOURCE_TYPE_MENU, sysResource.getId(), sysRoleIds);
			if (!sysResourceChildrenList.isEmpty()) {
				sysResource.setChildrenList(sysResourceChildrenList);
				setValidMenuChildrenList(sysResourceChildrenList);
			}
		}
	}

	@Override
	public List<SysResource> findValidBySysRoleIds(Integer[] sysRoleIds) {
		List<SysResource> sysResourceList = dao.select(Global.NORMAL, null, null, sysRoleIds);
		// 资源去重
		Set<SysResource> sysResourceSet = new HashSet<SysResource>(sysResourceList);
		return new ArrayList<SysResource>(sysResourceSet);
	}

	@Override
	public List<SysResource> find() {
		return dao.select(null, null, null, null);
	}
	
	@Override
	public List<SysResource> findValid() {
		return dao.select(Global.NORMAL, null, null, null);
	}

}
