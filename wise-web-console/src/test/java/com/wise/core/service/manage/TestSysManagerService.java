package com.wise.core.service.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.wise.BaseTest;
import com.wise.common.exception.service.ServiceException;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.config.Global;
import com.wise.core.dto.PageParam;

public class TestSysManagerService extends BaseTest {

	@Autowired
	private SysManagerService sysManagerService;
	
	@Test
	public void create() {
		SysManager sysManager = new SysManager();
		sysManager.setUserName("testor2");
		sysManager.setName("测试高手");
		sysManager.setSex(0);
		sysManager.setPhone("15219890524");
		sysManager.setStatus(Global.NORMAL);
		sysManager.setEmail("testor@qq.com");
		sysManager.setPwd("123456");
		sysManager.setCreator(1);
		sysManager.setCreatedAt(new Date());
		sysManager.setRegistIp("127.0.0.1");
		List<SysRole> sysRoleList = new ArrayList<SysRole>();
		SysRole sysRole = new SysRole();
		sysRole.setId(1);
		sysRoleList.add(sysRole);
		sysManager.setSysRoleList(sysRoleList);
		try {
			sysManagerService.create(sysManager);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			sysManagerService.deleteById(5);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			sysManagerService.delete(new Integer[]{6, 7});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		SysManager sysManager = new SysManager();
		sysManager.setId(8);
		//sysManager.setUserName("testor2");
		sysManager.setName("测试高手2");
		sysManager.setSex(1);
		sysManager.setPhone("15219890524");
		sysManager.setStatus(Global.NORMAL);
		sysManager.setEmail("testor@qq.com");
		sysManager.setPwd("123456");
		sysManager.setCreator(1);
		sysManager.setCreatedAt(new Date());
		sysManager.setRegistIp("127.0.0.1");
		try {
			sysManagerService.update(sysManager);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		SysManager sysManager = sysManagerService.findById(1);
		System.out.println(sysManager);
	}
	
	@Test
	public void findPage() {
		String userName = null;
		Integer status = null;
		String email = null;
		Integer sysRoleId = null;
		String name = null;
		Date createdAtStart = null;
		Date createdAtEnd = null;
		PageParam pageParam = new PageParam();
		PageInfo<SysManager> pageInfo = sysManagerService.findPage(pageParam, userName, status, email, sysRoleId, name, createdAtStart, createdAtEnd);
		for (SysManager sysManager : pageInfo.getList()) {
			System.out.println(sysManager);
		}
		System.out.println("size: "+pageInfo.getList().size());
		System.out.println("total: "+pageInfo.getTotal());
	}
	
}
