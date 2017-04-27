package com.wise.core.controller.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wise.core.bean.manage.SysResource;
import com.wise.core.controller.BaseController;
import com.wise.core.service.manage.SysResourceService;
import com.wise.core.web.utils.UserUtils;

/**
 * 后台管理
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/console")
public class ConsoleController extends BaseController {

	@Autowired
	private SysResourceService sysResourceService;

	/**
	 * 进入首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main", method = {RequestMethod.GET})
	public String main(Model model){
		// 获取用户菜单树
		Integer[] sysRoleIds = UserUtils.getLoginUserRoleIds();
		List<SysResource> sysResourceList = sysResourceService.findValidMenuTree(sysRoleIds);
		model.addAttribute("sysResourceList", sysResourceList);
		return "/main";
	}
	
	/**
	 * 进入主页
	 * @return
	 */
	@RequestMapping(value = "/index", method = {RequestMethod.GET})
	public String index(){
		return "/index";
	}
	
}