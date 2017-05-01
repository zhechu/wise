package com.wise.core.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wise.core.bean.manage.SysArea;
import com.wise.core.dao.manage.SysAreaDao;
import com.wise.core.service.TreeServiceImpl;

/**
 * 区域
 * @author lingyuwang
 *
 */
@Service("sysAreaService")
public class SysAreaServiceImpl extends TreeServiceImpl<SysAreaDao, SysArea> implements SysAreaService{

	@Override
	public List<SysArea> find() {
		return dao.select();
	}

}
