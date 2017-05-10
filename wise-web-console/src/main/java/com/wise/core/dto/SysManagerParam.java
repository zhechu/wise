package com.wise.core.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户传输对象
 * @author lingyuwang
 *
 */
public class SysManagerParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2390296203912822434L;

	/**
	 * 组织机构主键
	 */
	private Integer orgId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 角色主键
	 */
	private Integer sysRoleId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 创建开始时间
	 */
	private Date createdAtStart;
	
	/**
	 * 创建结束时间
	 */
	private Date createdAtEnd;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAtStart() {
		return createdAtStart;
	}

	public void setCreatedAtStart(Date createdAtStart) {
		this.createdAtStart = createdAtStart;
	}

	public Date getCreatedAtEnd() {
		return createdAtEnd;
	}

	public void setCreatedAtEnd(Date createdAtEnd) {
		this.createdAtEnd = createdAtEnd;
	}
	
}
