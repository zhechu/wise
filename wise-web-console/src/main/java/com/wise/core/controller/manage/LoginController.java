package com.wise.core.controller.manage;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.zookeeper.Login;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wise.common.response.ResponseModel;
import com.wise.core.controller.BaseController;

/**
 * 登录
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {

	/**
	 * 进入登录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = {RequestMethod.GET})
	public String login(){
		return "/login";
	}
	
	/**
	 * 登录
	 * @param login 登录传输对象
	 * @param br 后台验证结果对象
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel login(@Valid Login login, BindingResult br){
		ResponseModel rm = new ResponseModel();
		return rm;
	}
	
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = {RequestMethod.GET})
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if (subject != null)
			subject.logout();
		return "redirect:/login.do";
	}

    /**
     * 未授权
     * @return
     */
	@RequestMapping(value = "/unauth.do", method = {RequestMethod.GET})
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login.do";
        }
        return "/shiro/unauth";
    }

}