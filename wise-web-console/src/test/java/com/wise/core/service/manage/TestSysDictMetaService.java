package com.wise.core.service.manage;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wise.BaseTest;
import com.wise.core.bean.manage.SysDictMeta;

public class TestSysDictMetaService extends BaseTest {

	@Autowired
	private SysDictMetaService sysDictMetaService;
	
	@Test
	public void find() {
		List<SysDictMeta> sysDictMetaList = sysDictMetaService.find();
		for (SysDictMeta sysDictMeta : sysDictMetaList) {
			System.out.println(sysDictMeta);
		}
	}
	
}
