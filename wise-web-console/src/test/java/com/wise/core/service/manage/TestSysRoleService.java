package com.wise.core.service.manage;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.BaseTest;
import com.wise.common.exception.service.ServiceException;
import com.wise.core.bean.manage.SysRole;

public class TestSysRoleService extends BaseTest {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Test
	public void create() {
		SysRole sysRole = new SysRole();
		sysRole.setName("操作员");
		sysRole.setCreator(1);
		sysRole.setCreateAt(new Date());
		try {
			sysRoleService.create(sysRole);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			sysRoleService.deleteById(4);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			sysRoleService.delete(new Integer[]{4, 5});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		SysRole sysRole = new SysRole();
		sysRole.setId(6);
		sysRole.setName("普通人员");
		sysRole.setCreator(0);
		sysRole.setCreateAt(new Date());
		try {
			sysRoleService.update(sysRole);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		SysRole sysRole = sysRoleService.findById(1);
		System.out.println(sysRole);
	}
	
}
