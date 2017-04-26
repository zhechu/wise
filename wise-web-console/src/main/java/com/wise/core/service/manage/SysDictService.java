package com.wise.core.service.manage;

import com.github.pagehelper.PageInfo;
import com.wise.common.dto.PageParam;
import com.wise.core.bean.manage.SysDict;
import com.wise.core.service.BaseService;

public interface SysDictService extends BaseService<SysDict> {

	/**
	 * 分页查询（模糊查询）
	 * @param pageParam 分页参数
	 * @param type 类型
	 * @param status 状态
	 * @return
	 */
	PageInfo<SysDict> findPage(PageParam pageParam, String type, Integer status);
	
	/**
	 * 分页查询（模糊查询，数据权限，行级与列级）
	 * @param pageParam 分页参数
	 * @param type 类型
	 * @param status 状态
	 * @return
	 */
	/*PageInfo<SysDict> findPageByAuth(PageParam pageParam, String type, Integer status);*/
	
}
