package com.wise.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wise.common.exception.service.DataNotAllowUpdateException;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.BaseBean;
import com.wise.core.dao.BaseDao;

/**
 * 业务实现基类
 * @author lingyuwang
 *
 */
public abstract class BaseServiceImpl<D extends BaseDao<T>, T extends BaseBean<T>> implements BaseService<T> {

	@Autowired
	protected D dao;

	@Transactional
	@Override
	public void create(T t) throws ValueConflictException, DataNotExistedException {
		dao.insertSelective(t);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		T t = dao.selectByPrimaryKey(id);
		if (t == null)
			throw new DataNotExistedException("数据不存在");
		dao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			T t = dao.selectByPrimaryKey(id);
			if (t == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("数据不存在");
			}
			dao.deleteByPrimaryKey(id);
		}
	}

	@Transactional
	@Override
	public void update(T t) throws DataNotExistedException, ValueConflictException, DataNotAllowUpdateException {
		T tSource = dao.selectByPrimaryKey(t.getId());
		if (tSource == null)
			throw new DataNotExistedException("数据不存在");
		dao.updateByPrimaryKeySelective(t);
	}

	@Override
	public T findById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

}
