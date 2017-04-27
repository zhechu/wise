package com.wise.core.web.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.wise.common.exception.service.DataNotExistedException;
import com.wise.common.exception.service.ValueConflictException;
import com.wise.core.service.manage.SysManagerService;

/**
 * 自定义密码验证，通过SimpleCredentialsMatcher或HashedCredentialsMatcher
 * @author lingyuwang
 *
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	/*@Autowired*/
	private SysManagerService sysManagerService;
	
	public void setSysManagerService(SysManagerService sysManagerService) {
		this.sysManagerService = sysManagerService;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo authcInfo) {
	    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	    // 用户名
	    String username = token.getUsername();
	    // 页面传入的密码（明文）
	    String pwd = String.valueOf(token.getPassword());
	    
	    // 登录验证
	    try {
			sysManagerService.login(username, pwd);
		} catch (DataNotExistedException e) {
			// 用户不存在
			throw new UnknownAccountException(e);
		} catch (ValueConflictException e) {
			// 密码有误
			throw new IncorrectCredentialsException(e);
		}
	    
	    return true;
	}

}
