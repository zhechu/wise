package com.wise.core.service.manage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.wise.BaseTest;
import com.wise.common.exception.service.ServiceException;
import com.wise.core.bean.manage.SysDict;
import com.wise.core.config.Global;
import com.wise.core.dto.PageParam;

public class TestSysDictService extends BaseTest {

	@Autowired
	private SysDictService sysDictService;
	
	@Test
	public void create() {
		SysDict sysDict = new SysDict();
		sysDict.setType("b_status");
		sysDict.setLabel("启用");
		sysDict.setValue("1");
		sysDict.setStatus(Global.NORMAL);
		sysDict.setRemarks("管理员状态");
		sysDict.setSort(10);
		try {
			sysDictService.create(sysDict);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			sysDictService.deleteById(31);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			sysDictService.delete(new Integer[]{18, 19});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		SysDict sysDict = new SysDict();
		sysDict.setId(31);
		sysDict.setType("b_status");
		sysDict.setLabel("启用2");
		sysDict.setValue("2");
		sysDict.setStatus(Global.FORBIDDEN);
		sysDict.setRemarks("管理员状态2");
		sysDict.setSort(10);
		try {
			sysDictService.update(sysDict);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		SysDict sysDict = sysDictService.findById(31);
		System.out.println(sysDict);
	}
	
	@Test
	public void findPage() {
		PageParam pageParam = new PageParam();
		pageParam.setPageNum(2);
		PageInfo<SysDict> pageInfo = sysDictService.findPage(pageParam, null, null);
		for (SysDict sysDict : pageInfo.getList()) {
			System.out.println(sysDict);
		}
		System.out.println("size: "+pageInfo.getList().size());
		System.out.println("total: "+pageInfo.getTotal());
		
		pageParam = new PageParam();
		pageInfo = sysDictService.findPage(pageParam, null, null);
		for (SysDict sysDict : pageInfo.getList()) {
			System.out.println(sysDict);
		}
		System.out.println("size: "+pageInfo.getList().size());
		System.out.println("total: "+pageInfo.getTotal());
	}
	
	/*@Test
	public void findPageByAuth() {
		PageParam pageParam = new PageParam();
		PageInfo<SysDict> pageInfo = sysDictService.findPageByAuth(pageParam, null, null);
		for (SysDict sysDict : pageInfo.getList()) {
			System.out.println(sysDict);
		}
		System.out.println("size: "+pageInfo.getList().size());
		System.out.println("total: "+pageInfo.getTotal());
	}*/
	
}
