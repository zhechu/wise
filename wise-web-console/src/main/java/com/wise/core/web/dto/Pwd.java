package com.wise.core.web.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修改密码 传输对象
 * @author lingyuwang
 *
 */
public class Pwd implements Serializable {
	
	private static final long serialVersionUID = -3761536260127959497L;

	@NotEmpty(message="旧密码不能为空")
	private String oldPwd;
	
	@NotEmpty(message="新密码不能为空")
	private String newPwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
