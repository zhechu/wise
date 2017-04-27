/**
 *
 */
package com.wise.core.web.shiro;

import java.io.Serializable;
import java.util.List;

import com.wise.core.bean.manage.SysRole;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author lingyuwang
 *
 */
public class LoginUser implements Serializable {

	private static final long serialVersionUID = -1373760761780840081L;

	private Integer id;
	private String loginName;
	private String name;
	private String portraitPic;
	private List<SysRole> roleList;

	public LoginUser(Integer id, String loginName, String name, String portraitPic, List<SysRole> roleList) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.portraitPic = portraitPic;
		this.roleList = roleList;
	}

	public Integer getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}
	
	public String getPortraitPic() {
		return portraitPic;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public String getRoleName() {
		if (roleList != null && !roleList.isEmpty())
			return roleList.get(0).getName();
		return null;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}
}