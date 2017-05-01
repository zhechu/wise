package com.wise.core.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wise.core.bean.manage.SysOrg;
import com.wise.core.dao.manage.SysOrgDao;
import com.wise.core.service.TreeServiceImpl;

/**
 * 机构
 * @author lingyuwang
 *
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends TreeServiceImpl<SysOrgDao, SysOrg> implements SysOrgService{

	@Override
	public List<SysOrg> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
