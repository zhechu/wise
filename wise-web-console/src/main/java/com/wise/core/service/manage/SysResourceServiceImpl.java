package com.wise.core.service.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysResource;
import com.wise.core.dao.manage.SysResourceDao;

/**
 * 系统资源
 * @author lingyuwang
 *
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService{

	@Autowired
	private SysResourceDao sysResourceDao;

	@Transactional
	@Override
	public void create(SysResource sysResource) throws ValueConflictException {
		sysResourceDao.insertSelective(sysResource);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysResource sysResource = sysResourceDao.selectByPrimaryKey(id);
		if (sysResource == null)
			throw new DataNotExistedException("资源不存在");
		sysResourceDao.deleteByPrimaryKey(id);
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
			sysResourceDao.deleteByPrimaryKey(id);
		}
	}

	@Transactional
	@Override
	public void update(SysResource sysResource) throws DataNotExistedException {
		SysResource sysResourceSource = sysResourceDao.selectByPrimaryKey(sysResource.getId());
		if (sysResourceSource == null)
			throw new DataNotExistedException("资源不存在");
		sysResourceDao.updateByPrimaryKeySelective(sysResource);
	}

	@Override
	public SysResource findById(Integer id) {
		return sysResourceDao.selectByPrimaryKey(id);
	}

}
