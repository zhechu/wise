package com.wise.core.service.manage;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.BaseTest;
import com.wise.common.exception.service.ServiceException;
import com.wise.core.bean.manage.SysResource;

public class TestSysResourceService extends BaseTest {

	@Autowired
	private SysResourceService sysResourceService;
	
	@Test
	public void create() {
		SysResource sysResource = new SysResource();
		//sysResource.setParentId(0);
		sysResource.setName("运维管理");
		sysResource.setType(0);
		sysResource.setPermission("");
		//sysResource.setDescription("");
		sysResource.setUrl("");
		sysResource.setIcon("");
		sysResource.setStatus(1);
		sysResource.setSort(0);
		try {
			sysResourceService.create(sysResource);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			sysResourceService.deleteById(22);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			sysResourceService.delete(new Integer[]{23, 24});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		SysResource sysResource = new SysResource();
		sysResource.setId(25);
		sysResource.setName("运营管理");
		sysResource.setType(0);
		sysResource.setPermission("");
		//sysResource.setDescription("");
		sysResource.setUrl("");
		sysResource.setIcon("");
		sysResource.setStatus(0);
		sysResource.setSort(10);
		try {
			sysResourceService.update(sysResource);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		SysResource sysResource = sysResourceService.findById(25);
		System.out.println(sysResource);
	}
	
	@Test
	public void findByParentId() {
		//List<SysResource> sysResourceList = sysResourceService.findByParentId(Global.DEFAULT_PARENT_ID);
		List<SysResource> sysResourceList = sysResourceService.findByParentId(1);
		for (SysResource sysResource : sysResourceList) {
			System.out.println(sysResource);
		}
		System.out.println("size: "+sysResourceList.size());
	}
	
	@Test
	public void findValidMenuTree() {
		//List<SysResource> sysResourceList = sysResourceService.findValidMenuTree();
		Integer[] sysRoleIds = new Integer[]{1};
		List<SysResource> sysResourceList = sysResourceService.findValidMenuTree(sysRoleIds);
		for (SysResource sysResource : sysResourceList) {
			System.out.println(sysResource);
		}
		System.out.println("size: "+sysResourceList.size());
	}
	
}
