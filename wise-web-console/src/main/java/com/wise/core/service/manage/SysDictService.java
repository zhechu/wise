package com.wise.core.service.manage;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wise.core.bean.manage.SysDict;
import com.wise.core.dto.PageParam;
import com.wise.core.service.BaseService;

public interface SysDictService extends BaseService<SysDict> {

	/**
	 * 获取字典（可用）
	 * @return
	 */
	List<SysDict> findValid();
	
	/**
	 * 获取最大值字典
	 * @param type 类型
	 * @return
	 */
	SysDict findMaxValueByType(String type);
	
	/**
	 * 分页查询（模糊查询）
	 * @param pageParam 分页参数
	 * @param remarks 备注
	 * @param status 状态
	 * @return
	 */
	PageInfo<SysDict> findPage(PageParam pageParam, String remarks, Integer status);
	
	/**
	 * 分页查询（模糊查询，数据权限，行级与列级）
	 * @param pageParam 分页参数
	 * @param type 类型
	 * @param status 状态
	 * @return
	 */
	/*PageInfo<SysDict> findPageByAuth(PageParam pageParam, String type, Integer status);*/
	
}
