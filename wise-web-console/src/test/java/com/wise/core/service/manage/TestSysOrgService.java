package com.wise.core.service.manage;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.BaseTest;
import com.wise.common.exception.service.ServiceException;
import com.wise.core.bean.manage.SysOrg;
import com.wise.core.config.Global;

public class TestSysOrgService extends BaseTest {

	@Autowired
	private SysOrgService sysOrgService;
	
	@Test
	public void create() {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setParentId(Global.DEFAULT_PARENT_ID);
		sysOrg.setName("广东省总公司");
		sysOrg.setAreaId(1);
		try {
			sysOrgService.create(sysOrg);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			sysOrgService.deleteById(27);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			sysOrgService.delete(new Integer[]{28});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setId(28);
		sysOrg.setParentId(Global.DEFAULT_PARENT_ID);
		sysOrg.setName("广东省总公司");
		sysOrg.setAreaId(2);
		try {
			sysOrgService.update(sysOrg);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		SysOrg sysOrg = sysOrgService.findById(10);
		System.out.println(sysOrg);
	}
	
	@Test
	public void findByParentId() {
		List<SysOrg> sysOrgList = sysOrgService.findByParentId(3);
		for (SysOrg sysOrg : sysOrgList) {
			System.out.println(sysOrg);
		}
		System.out.println("size: "+sysOrgList.size());
	}
	
}
