package com.wise.core.web.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录 传输对象
 * @author lingyuwang
 *
 */
public class Login implements Serializable {

	private static final long serialVersionUID = -444838980689999171L;

	@NotEmpty(message="用户名不能为空")
	private String userName;
	
	@NotEmpty(message="密码不能为空")
	private String pwd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
