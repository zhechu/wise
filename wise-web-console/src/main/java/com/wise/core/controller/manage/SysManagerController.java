package com.wise.core.controller.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wise.common.exception.service.ServiceException;
import com.wise.common.response.BootstrapTableResponse;
import com.wise.common.response.ResponseModel;
import com.wise.common.utils.SecureUtil;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.config.Global;
import com.wise.core.controller.BaseController;
import com.wise.core.dto.PageParam;
import com.wise.core.service.UploadService;
import com.wise.core.service.manage.SysManagerService;
import com.wise.core.service.manage.SysRoleService;
import com.wise.core.web.dto.Pwd;
import com.wise.core.web.dto.UserInfo;
import com.wise.core.web.shiro.LoginUser;
import com.wise.core.web.utils.UserUtils;

/**
 * 后台用户管理
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/sysManager")
public class SysManagerController extends BaseController {

	@Autowired
	private SysManagerService sysManagerService;

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private UploadService uploadService;

	/**
	 * 进入用户列表页
	 * @return
	 */
	@RequiresPermissions({"sys:manager:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public String list(Model model){
		// 可用角色列表
		List<SysRole> sysRoleList = sysRoleService.findValid();
		model.addAttribute("sysRoleList", sysRoleList);
		return "/sysManager/sysManagerList";
	}

	/**
	 * 进入编辑页
	 * @return
	 */
	@RequiresPermissions({"sys:manager:add", "sys:manager:update"})
	@RequestMapping(value = "/edit", method = {RequestMethod.GET})
	public String edit(Integer id, Model model){
		if (id != null) { // 编辑，反之添加
			SysManager sysManager = sysManagerService.findById(id);
			model.addAttribute("sysManager", sysManager);
			// 将角色 id 转成用逗号分割的字符串
			List<Integer> roleIdList = new ArrayList<Integer>();
			List<SysRole> sysRoleList = sysManager.getSysRoleList();
			for (SysRole sysRole : sysRoleList) {
				roleIdList.add(sysRole.getId());
			}
			String roleIds = StringUtils.join(roleIdList.toArray(), ",");  
			model.addAttribute("roleIds", roleIds);
		}
		// 可用角色列表
		List<SysRole> sysRoleList = sysRoleService.findValid();
		model.addAttribute("sysRoleList", sysRoleList);
		return "/sysManager/sysManagerEdit";
	}

	/**
	 * 保存（添加和编辑，用 id 是否存在区分）
	 * @return
	 */
	@RequiresPermissions({"sys:manager:add", "sys:manager:update"})
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel save(@RequestParam(value="pic", required=false) MultipartFile pic, @Valid SysManager sysManager, BindingResult br, Integer[] roleIds){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			// 头像地址
			String portraitPic = "";
			if (pic != null) {
				String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
				portraitPic = storageServer + path;
			}
			sysManager.setPortraitPic(portraitPic);
			
			// 角色
			List<SysRole> sysRoleList = new ArrayList<SysRole>();
			if (roleIds != null) {
				for (Integer roleId : roleIds) {
					SysRole sysRole = new SysRole();
					sysRole.setId(roleId);
					sysRoleList.add(sysRole);
				}
			}
			sysManager.setSysRoleList(sysRoleList);
			LoginUser loginUser = UserUtils.getLoginUser();
			Integer loginUserId = loginUser.getId();
			if (sysManager.getId() != null) { // 编辑
				// 密码不为空时， base64 解密
				String pwd = sysManager.getPwd();
				if (StringUtils.isNotEmpty(pwd))
					pwd = SecureUtil.decodeByBase64(pwd);
				else
					pwd = null;
				sysManager.setPwd(pwd);
				// 修改者从会话中获取当前用户ID）
				sysManager.setModifier(loginUserId);
				// 修改时间
				sysManager.setModifiedAt(new Date());
				sysManagerService.update(sysManager);
				rm.msgSuccess("编辑用户成功");
			} else { // 添加
				// 密码为空时，初始化一个密码
				String pwd = sysManager.getPwd();
				if (StringUtils.isEmpty(pwd))
					pwd = Global.DEFAULT_PWD;
				else
					// 密码 base64 解密
					pwd = SecureUtil.decodeByBase64(pwd);
				sysManager.setPwd(pwd);
				// ip
				sysManager.setRegistIp(getClientIP(request)); 
				// 创建者（从会话中获取当前用户ID）
				sysManager.setCreator(loginUserId);
				// 创建时间
				sysManager.setCreatedAt(new Date());
				sysManagerService.create(sysManager);
				rm.msgSuccess("添加用户成功");
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
	 * 删除
	 * @param ids 字典主键列表，多个则以逗号分割
	 * @return
	 */
	@RequiresPermissions({"sys:manager:delete"})
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel delete(@RequestParam String ids){
		ResponseModel rm = new ResponseModel();
		String[] idss = null;
		try {
			idss = ids.split(",");
			sysManagerService.delete(convertToIntegerArray(idss));
			rm.msgSuccess("删除用户成功");
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
	 * 返回用户列表数据
	 * @param pageParam 分页参数
	 * @param type 字典类型
	 * @param status 状态
	 * @return
	 */
	@RequiresPermissions({"sys:manager:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public @ResponseBody BootstrapTableResponse list(PageParam pageParam, String userName, Integer status, String email, Integer sysRoleId, String name, Date createdAtStart, Date createdAtEnd){
		PageInfo<SysManager> pageInfo = sysManagerService.findPage(pageParam, userName, status, email, sysRoleId, name, createdAtStart, createdAtEnd);
		return new BootstrapTableResponse(pageInfo.getList(), pageInfo.getTotal());
	}
	
	/**
	 * 进入修改密码页
	 * @return
	 */
	@RequiresPermissions({"user"})
	@RequestMapping(value = "/pwd", method = {RequestMethod.GET})
	public String pwd(){
		return "/sysManager/pwd";
	}
	
	/**
	 * 修改密码
	 * @param pwd 密码传输对象
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"user"})
	@RequestMapping(value = "/pwd", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel pwd(@Valid Pwd pwd, BindingResult br){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			String oldPwd = pwd.getOldPwd();
			String newPwd = pwd.getNewPwd();
			// 密码 base64 解密
			oldPwd = SecureUtil.decodeByBase64(oldPwd);
			newPwd = SecureUtil.decodeByBase64(newPwd);
			LoginUser loginUser = UserUtils.getLoginUser();
			sysManagerService.updatePwd(loginUser.getLoginName(), oldPwd, newPwd);
			rm.msgSuccess("修改密码成功");
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
	 * 进入个人资料页
	 * @return
	 */
	@RequiresPermissions({"user"})
	@RequestMapping(value = "/info", method = {RequestMethod.GET})
	public String info(Model model){
		LoginUser loginUser = UserUtils.getLoginUser();
		SysManager sysManager = sysManagerService.findById(loginUser.getId());
		model.addAttribute("sysManager", sysManager);
		return "/sysManager/info";
	}
	
	/**
	 * 修改个人资料
	 * @param userInfo 个人资料传输对象
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"user"})
	@RequestMapping(value = "/info", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel save(@RequestParam(value="pic", required=false) MultipartFile pic, @Valid UserInfo userInfo, BindingResult br){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			SysManager sysManager = new SysManager();
			
			// 头像地址
			String portraitPic = "";
			if (pic != null) {
				String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
				portraitPic = storageServer + path;
			}
			sysManager.setPortraitPic(portraitPic);
						
			LoginUser loginUser = UserUtils.getLoginUser();
			sysManager.setId(loginUser.getId());
			sysManager.setName(userInfo.getName());
			sysManager.setSex(userInfo.getSex());
			sysManager.setPhone(userInfo.getPhone());
			sysManager.setEmail(userInfo.getEmail());
			// 修改者从会话中获取当前用户ID）
			sysManager.setModifier(loginUser.getId());
			// 修改时间
			sysManager.setModifiedAt(new Date());
			sysManagerService.updateInfo(sysManager);
			rm.msgSuccess("修改个人资料成功");
		} catch (ServiceException e) {
			rm.msgFailed(e.getMessage());
			logger.debug(e.getMessage(), e);
		} catch (Exception e) {
			rm.msgFailed("未知错误，请联系管理员");
			logger.error(e.getMessage(), e);
		}
		return rm;
	}
}