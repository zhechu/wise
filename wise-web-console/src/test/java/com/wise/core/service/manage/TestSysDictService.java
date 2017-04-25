package com.wise.core.service.manage;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wise.BaseTest;
import com.wise.common.config.Global;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysDict;
import com.wise.core.dao.manage.SysDictDao;

public class TestSysDictService extends BaseTest {

	@Autowired
	private SysDictService sysDictService;
	
	@Autowired
	private SysDictDao sysDictDao;
	
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
	public void selectById() {
		SysDict sysDict = sysDictService.selectById(1);
		System.out.println(sysDict);
	}
	
	@Test
	public void test() {
		PageHelper.startPage(2, 10);
        List<SysDict> list = sysDictDao.select();
        for (SysDict sysDict : list) {
			System.out.println(sysDict);
		}
        System.out.println("size: "+list.size());
        System.out.println("count: "+((Page) list).getTotal());
	}
	
}
