package com.wise.core.service.manage;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.BaseTest;
import com.wise.common.exception.service.ServiceException;
import com.wise.core.bean.manage.SysArea;

public class TestSysAreaService extends BaseTest {

	@Autowired
	private SysAreaService sysAreaService;
	
	@Test
	public void create() {
		SysArea sysArea = new SysArea();
		/*sysArea.setParentId(0);
		sysArea.setName("美国");
		sysArea.setCode("100001");
		sysArea.setType(1);
		sysArea.setSort(20);*/
		/*sysArea.setParentId(0);
		sysArea.setName("英国");
		sysArea.setCode("100002");
		sysArea.setType(1);
		sysArea.setSort(30);*/
		/*sysArea.setParentId(17);
		sysArea.setName("加州");
		sysArea.setCode("100101");
		sysArea.setType(2);
		sysArea.setSort(10);*/
		//sysArea.setParentId(18);
		sysArea.setName("xxx");
		sysArea.setCode("100201");
		sysArea.setType(2);
		sysArea.setSort(10);
		try {
			sysAreaService.create(sysArea);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		try {
			sysAreaService.deleteById(15);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try {
			sysAreaService.delete(new Integer[]{43});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		SysArea sysArea = new SysArea();
		sysArea.setId(10);
		//sysArea.setParentId(Global.DEFAULT_PARENT_ID);
		sysArea.setParentIds("0,");
		sysArea.setName("天河区");
		sysArea.setCode("100002");
		sysArea.setType(1);
		sysArea.setSort(20);
		try {
			sysAreaService.update(sysArea);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		SysArea sysArea = sysAreaService.findById(10);
		System.out.println(sysArea);
	}
	
	@Test
	public void findByParentId() {
		List<SysArea> sysAreaList = sysAreaService.findByParentId(3);
		for (SysArea sysArea : sysAreaList) {
			System.out.println(sysArea);
		}
		System.out.println("size: "+sysAreaList.size());
	}
	
}
