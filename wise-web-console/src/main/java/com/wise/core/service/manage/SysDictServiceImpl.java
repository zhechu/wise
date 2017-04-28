package com.wise.core.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wise.common.dto.PageParam;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysDict;
import com.wise.core.dao.manage.SysDictDao;

import tk.mybatis.orderbyhelper.OrderByHelper;

/**
 * 数据字典
 * @author lingyuwang
 *
 */
@Service("sysDictService")
public class SysDictServiceImpl implements SysDictService{

	@Autowired
	private SysDictDao sysDictDao;

	@Transactional
	@Override
	public void create(SysDict sysDict) throws ValueConflictException {
		List<SysDict> sysDictList = sysDictDao.selectByTypeAndLabel(sysDict.getType(), sysDict.getLabel());
		if (!sysDictList.isEmpty())
			throw new ValueConflictException("字典已经存在，类型、标签不能重复");
		sysDictDao.insertSelective(sysDict);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysDict sysDict = sysDictDao.selectByPrimaryKey(id);
		if (sysDict == null)
			throw new DataNotExistedException("字典不存在");
		sysDictDao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void delete(Integer[] ids) throws DataNotExistedException {
		for (Integer id : ids) {
			SysDict sysDict = sysDictDao.selectByPrimaryKey(id);
			if (sysDict == null) {
				// 手动回滚
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new DataNotExistedException("字典不存在");
			}
			sysDictDao.deleteByPrimaryKey(id);
		}
	}

	@Transactional
	@Override
	public void update(SysDict sysDict) throws DataNotExistedException, ValueConflictException {
		SysDict sysDictSource = sysDictDao.selectByPrimaryKey(sysDict.getId());
		if (sysDictSource == null)
			throw new DataNotExistedException("字典不存在");
		// 判断字典的类型和标签是否有更新，有则检查是否已存在
		if (!sysDictSource.getType().equals(sysDict.getType()) || !sysDictSource.getLabel().equals(sysDict.getLabel())) {
			List<SysDict> sysDictList = sysDictDao.selectByTypeAndLabel(sysDict.getType(), sysDict.getLabel());
			if (!sysDictList.isEmpty())
				throw new ValueConflictException("字典已经存在，类型、标签不能重复");
		}
		sysDictDao.updateByPrimaryKeySelective(sysDict);
	}

	@Override
	public SysDict findById(Integer id) {
		return sysDictDao.selectByPrimaryKey(id);
	}
	
	@Override
	public List<SysDict> findByType(String type) {
		return sysDictDao.selectByType(type);
	}

	@Override
	public PageInfo<SysDict> findPage(PageParam pageParam, String type, Integer status) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
		OrderByHelper.orderBy(pageParam.getOrderBy("type", "asc"));
        List<SysDict> list = sysDictDao.select(type, status);
		return new PageInfo<SysDict>(list);
	}

	/*@Override
	public PageInfo<SysDict> findPageByAuth(PageParam pageParam, String type, Integer status) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize()).setOrderBy(pageParam.getOrderBy("type", "asc"));
		String fields = "id, type, value, label";
		String filtrate = "type = 'status'";
        List<SysDict> list = sysDictDao.selectByAuth(fields, filtrate, type, status);
		return new PageInfo<SysDict>(list);
	}*/
	
}
