package com.wise.core.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysDict;
import com.wise.core.config.Global;
import com.wise.core.dao.manage.SysDictDao;
import com.wise.core.dto.PageParam;
import com.wise.core.service.BaseServiceImpl;
import com.wise.core.utils.CacheUtils;
import com.wise.core.web.utils.DictUtils;

import tk.mybatis.orderbyhelper.OrderByHelper;

/**
 * 数据字典
 * @author lingyuwang
 *
 */
@Service("sysDictService")
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDao, SysDict>  implements SysDictService{

	@Autowired
	private SysDictDao sysDictDao;

	@Transactional
	@Override
	public void create(SysDict sysDict) throws ValueConflictException {
		List<SysDict> sysDictList = sysDictDao.selectByTypeAndLabel(sysDict.getType(), sysDict.getLabel());
		if (!sysDictList.isEmpty())
			throw new ValueConflictException("字典已经存在，类型、标签不能重复");
		sysDictDao.insertSelective(sysDict);

		// 清除缓存
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws DataNotExistedException {
		SysDict sysDict = sysDictDao.selectByPrimaryKey(id);
		if (sysDict == null)
			throw new DataNotExistedException("字典不存在");
		sysDictDao.deleteByPrimaryKey(id);
		
		// 清除缓存
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
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

		// 清除缓存
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
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

		// 清除缓存
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	public PageInfo<SysDict> findPage(PageParam pageParam, String type, Integer status) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
		OrderByHelper.orderBy(pageParam.getOrderBy("type", "asc"));
        List<SysDict> list = sysDictDao.selectByLike(type, status);
		return new PageInfo<SysDict>(list);
	}

	@Override
	public List<SysDict> findValid() {
		return sysDictDao.selectByStatus(Global.NORMAL);
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
