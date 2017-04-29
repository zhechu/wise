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

	@NotEmpty(message="{sys.pwd.oldPwd.notempty}")
	private String oldPwd;
	
	@NotEmpty(message="{sys.pwd.newPwd.notempty}")
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
