package com.wise.core.dao.manage;

import com.wise.core.bean.manage.SysManager;

public interface SysManagerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysManager record);

    int insertSelective(SysManager record);

    SysManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysManager record);

    int updateByPrimaryKey(SysManager record);
}