package com.wise.core.controller.manage;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wise.common.exception.service.ServiceException;
import com.wise.common.response.ResponseModel;
import com.wise.common.utils.SecureUtil;
import com.wise.core.controller.BaseController;
import com.wise.core.web.dto.Login;

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
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	public String login(){
		if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/console/main";
        }
		return "/login";
	}
	
	/**
	 * 登录
	 * @param login 登录传输对象
	 * @param br 后台验证结果对象
	 * @return
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel login(@Valid Login login, BindingResult br){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			// 密码 base64 解密
			Subject user = SecurityUtils.getSubject();
			String pwd = SecureUtil.decodeByBase64(login.getPwd());
			String userName = login.getUserName();
	        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd.toCharArray());
	        user.login(token);
			rm.msgSuccess("登录成功");
		} catch (ServiceException e) {
			rm.msgFailed(e.getMessage());
			logger.debug(e.getMessage(), e);
		} catch (UnknownAccountException e) {
            rm.msgFailed("用户不存在");
			logger.debug("用户不存在", e);
        } catch (DisabledAccountException e) {
        	rm.msgFailed("用户未启用");
			logger.debug("用户未启用", e);
        } catch (IncorrectCredentialsException e) {
        	rm.msgFailed("密码有误");
			logger.debug("密码有误", e);
        } catch (Exception e) {
			rm.msgFailed("未知错误，请联系管理员");
			logger.error(e.getMessage(), e);
		}
		return rm;
	}
	
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping(value = "/logout", method = {RequestMethod.GET})
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if (subject != null)
			subject.logout();
		return "redirect:/login";
	}

    /**
     * 未授权
     * @return
     */
	@RequestMapping(value = "/unauth", method = {RequestMethod.GET})
    public String unauth() {
		// 若未登录，则回到登录页
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "/shiro/unauth";
    }

}