package com.wise.core.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wise.core.bean.manage.SysDictMeta;
import com.wise.core.dao.manage.SysDictMetaDao;
import com.wise.core.service.BaseServiceImpl;

/**
 * 字典元数据
 * @author lingyuwang
 *
 */
@Service("sysDictMetaService")
public class SysDictMetaServiceImpl extends BaseServiceImpl<SysDictMetaDao, SysDictMeta>  implements SysDictMetaService{

	@Override
	public List<SysDictMeta> find() {
		return dao.select();
	}

	@Override
	public SysDictMeta findByCode(String code) {
		return dao.selectByCode(code);
	}
	
}
