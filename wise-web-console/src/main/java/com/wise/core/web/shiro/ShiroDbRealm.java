package com.wise.core.web.shiro;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wise.common.config.Global;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.bean.manage.SysResource;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.service.manage.SysManagerService;
import com.wise.core.service.manage.SysResourceService;

/**
 * shiro 权限认证
 * @author lingyuwang
 *
 */
public class ShiroDbRealm extends AuthorizingRealm {

	//private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*@Autowired*/
	private SysManagerService sysManagerService;
	
	/*@Autowired*/
	private SysResourceService sysResourceService;

    public void setSysManagerService(SysManagerService sysManagerService) {
		this.sysManagerService = sysManagerService;
	}

	public void setSysResourceService(SysResourceService sysResourceService) {
		this.sysResourceService = sysResourceService;
	}

	/**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws DisabledAccountException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        
        // 校验用户名密码
        SysManager sysManager = sysManagerService.findByUserName(token.getUsername());
        if (sysManager == null)
        	return null;
        
		if (Global.FORBIDDEN.equals(sysManager.getStatus()))
			throw new DisabledAccountException("禁止登录");
		
		List<SysRole> sysRoleList = sysManager.getSysRoleList();
		// doGetAuthorizationInfo 用到 shiroUser
		LoginUser shiroUser = new LoginUser(sysManager.getId(), sysManager.getUserName(), sysManager.getName(), sysManager.getPortraitPic(), sysRoleList);
		
		return new SimpleAuthenticationInfo(shiroUser, sysManager.getPwd().toCharArray(), getName());
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	LoginUser shiroUser = (LoginUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		// 登录后拥有的权限
		info.addStringPermission("user");
		
		// 添加权限标识
		List<SysRole> sysRoleList = shiroUser.getRoleList();
		int size = sysRoleList.size();
		Integer[] sysRoleIds = new Integer[size];
		for (int i = 0; i < size; i++) {
			sysRoleIds[i] = sysRoleList.get(i).getId();
		}
        List<SysResource> sysResourceList = sysResourceService.findValidBySysRoleIds(sysRoleIds);
        for (SysResource sysResource : sysResourceList) {
        	String permission = sysResource.getPermission();
        	if (StringUtils.isNotEmpty(permission)) 
        		info.addStringPermission(permission);
		}
        return info;
    }
    
}
