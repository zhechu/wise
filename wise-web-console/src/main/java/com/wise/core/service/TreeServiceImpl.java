package com.wise.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.TreeBean;
import com.wise.core.config.Global;
import com.wise.core.dao.TreeDao;

/**
 * 业务实现树基类
 * @author lingyuwang
 *
 */
public abstract class TreeServiceImpl<D extends TreeDao<T>, T extends TreeBean<T>> extends BaseServiceImpl<D, T> implements TreeService<T> {

	@Transactional
	@Override
	public void create(T t) throws ValueConflictException, DataNotExistedException {
		// 如果没有设置父节点，则代表为根节点，有则获取父节点主键
		String parentIds = Global.DEFAULT_PARENT_ID + ",";
		if (t.getParent() != null && t.getParent().getId() != Global.DEFAULT_PARENT_ID) {
			T parent = super.findById(t.getParent().getId());
			if (parent == null) 
				throw new DataNotExistedException("父节点不存在");
			parentIds = parent.getParentIds() + parent.getId() + ",";
		}
		// 设置父节点串
		t.setParentIds(parentIds);
		super.create(t);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		T parent = dao.selectByPrimaryKey(id);
		if (parent == null)
			throw new DataNotExistedException("数据不存在");
		// 级联删除子孙节点
		String parentIds = parent.getParentIds() + parent.getId() + ",";
		List<T> tList = dao.selectByLikeParentIds(parentIds);
		for (T t : tList) 
			dao.deleteByPrimaryKey(t.getId());
		// 删除节点
		dao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			T parent = dao.selectByPrimaryKey(id);
			if (parent == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("数据不存在");
			}
			// 级联删除子孙节点
			String parentIds = parent.getParentIds() + parent.getId() + ",";
			List<T> tList = dao.selectByLikeParentIds(parentIds);
			for (T t : tList) 
				dao.deleteByPrimaryKey(t.getId());
			// 删除节点
			dao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<T> findByParentId(Integer parentId) {
		return dao.selectByParentId(parentId);
	}

}
