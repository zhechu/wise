package com.wise.core.controller.manage;

import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wise.common.exception.service.ServiceException;
import com.wise.common.response.BootstrapTableResponse;
import com.wise.common.response.ResponseModel;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.controller.BaseController;
import com.wise.core.service.manage.SysRoleService;
import com.wise.core.web.shiro.LoginUser;
import com.wise.core.web.utils.UserUtils;

/**
 * 角色管理
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController extends BaseController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 进入角色列表页
	 * @return
	 */
	@RequiresPermissions({"sys:role:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public String list(){
		return "/sysRole/sysRoleList";
	}

	/**
	 * 进入编辑页
	 * @return
	 */
	@RequiresPermissions({"sys:role:add", "sys:role:update"})
	@RequestMapping(value = "/edit", method = {RequestMethod.GET})
	public String edit(Integer id, Model model){
		if (id != null) { // 编辑，反之添加
			SysRole sysRole = sysRoleService.findById(id);
			model.addAttribute("sysRole", sysRole);
		}
		return "/sysRole/sysRoleEdit";
	}

	/**
	 * 保存角色（添加和编辑，用 id 是否存在区分）
	 * @return
	 */
	@RequiresPermissions({"sys:role:add", "sys:role:update"})
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel save(@Valid SysRole sysRole, BindingResult br, String resourceIds){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			// 角色拥有资源权限
			if (StringUtils.isNotEmpty(resourceIds)) {
				String[] resourceIdss = resourceIds.split(",");
				sysRole.setSysResourceIdList(convertToIntegerList(resourceIdss));
			}
			// 当前用户
			LoginUser loginUser = UserUtils.getLoginUser();
			if (sysRole.getId() != null) { // 编辑
				// 设置修改人和修改时间
				sysRole.setModifier(loginUser.getId());
				sysRole.setModifiedAt(new Date());
				sysRoleService.update(sysRole);
				rm.msgSuccess("编辑角色成功");
			} else { // 添加
				// 设置创建人和创建时间
				sysRole.setCreator(loginUser.getId());
				sysRole.setCreateAt(new Date());
				sysRoleService.create(sysRole);
				rm.msgSuccess("添加角色成功");
			}
		} catch (ServiceException e) {
			rm.msgFailed(e.getMessage());
			logger.debug(e.getMessage(), e);
		} catch (Exception e) {
			rm.msgFailed("未知错误，请联系管理员");
			logger.error(e.getMessage(), e);
		}
		return rm;
	}
	
	/**
	 * 删除角色
	 * @param ids 主键列表，多个则以逗号分割
	 * @return
	 */
	@RequiresPermissions({"sys:role:delete"})
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel delete(@RequestParam String ids){
		ResponseModel rm = new ResponseModel();
		String[] idss = null;
		try {
			idss = ids.split(",");
			sysRoleService.delete(convertToIntegerArray(idss));
			rm.msgSuccess("删除角色成功");
		} catch (ServiceException e) {
			rm.msgFailed(e.getMessage());
			logger.debug(e.getMessage(), e);
		} catch (Exception e) {
			rm.msgFailed("未知错误，请联系管理员");
			logger.error(e.getMessage(), e);
		}
		return rm;
	}
	
	/**
	 * 返回角色列表数据
	 * @return
	 */
	@RequiresPermissions({"sys:role:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public @ResponseBody BootstrapTableResponse listData(){
		return new BootstrapTableResponse(sysRoleService.find(), 0);
	}
	
}