package com.wise.core.service.manage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wise.common.exception.service.DataNotAllowUpdateException;
import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.bean.manage.SysOrg;
import com.wise.core.config.Global;
import com.wise.core.dao.manage.SysOrgDao;
import com.wise.core.service.TreeServiceImpl;

/**
 * 机构
 * @author lingyuwang
 *
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends TreeServiceImpl<SysOrgDao, SysOrg> implements SysOrgService{

	@Transactional
	@Override
	public void update(SysOrg sysOrg) throws DataNotExistedException, ValueConflictException, DataNotAllowUpdateException {
		SysOrg sysOrgSource = dao.selectByPrimaryKey(sysOrg.getId());
		if (sysOrgSource == null)
			throw new DataNotExistedException("数据不存在");
		// 若机构的状态有更新，则级联更改子孙机构的状态
		if (!sysOrgSource.getStatus().equals(sysOrg.getStatus())) {
			Integer status = sysOrg.getStatus();
			String parentIds = sysOrgSource.getParentIds() + sysOrgSource.getId() + ",";
			List<SysOrg> sysOrgList = dao.selectByLikeParentIds(parentIds);
			for (SysOrg t : sysOrgList) {
				t.setStatus(status);
				dao.updateByPrimaryKeySelective(t);
			}
		}
		// 更新机构
		dao.updateByPrimaryKeySelective(sysOrg);
	}

	@Override
	public List<SysOrg> find() {
		return dao.select();
	}

	@Override
	public List<SysOrg> findValid() {
		return dao.selectByStatus(Global.NORMAL);
	}

}
