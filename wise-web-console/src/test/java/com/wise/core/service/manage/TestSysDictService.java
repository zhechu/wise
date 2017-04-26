package com.wise.core.service.manage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.wise.BaseTest;
import com.wise.common.config.Global;
import com.wise.common.dto.PageParam;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysDict;

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
		sysDict.setDescription("管理员状态");
		sysDict.setSort(10);
		sysDict.setParentId(Global.PARENT_ID);
		try {
			sysDictService.create(sysDict);
		} catch (ValueConflictException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			sysDictService.deleteById(17);
		} catch (DataNotExistedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			sysDictService.delete(new Integer[]{18, 19});
		} catch (DataNotExistedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		SysDict sysDict = new SysDict();
		sysDict.setId(17);
		sysDict.setType("b_status");
		sysDict.setLabel("启用");
		sysDict.setValue("1");
		sysDict.setStatus(Global.NORMAL);
		sysDict.setDescription("管理员状态");
		sysDict.setSort(10);
		sysDict.setParentId(Global.PARENT_ID);
		try {
			sysDictService.update(sysDict);
		} catch (DataNotExistedException e) {
			e.printStackTrace();
		} catch (ValueConflictException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		SysDict sysDict = sysDictService.findById(1);
		System.out.println(sysDict);
	}
	
	@Test
	public void findPage() {
		PageParam pageParam = new PageParam();
		PageInfo<SysDict> pageInfo = sysDictService.findPage(pageParam, null, null);
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
