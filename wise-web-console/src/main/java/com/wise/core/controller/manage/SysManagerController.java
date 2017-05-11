package com.wise.core.controller.manage;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.wise.common.exception.controller.ControllerException;
import com.wise.common.exception.controller.FileOutOfSizeException;
import com.wise.common.exception.controller.UploadFileException;
import com.wise.common.exception.service.ServiceException;
import com.wise.common.response.BootstrapTableResponse;
import com.wise.common.response.ResponseModel;
import com.wise.common.utils.FileUtils;
import com.wise.common.utils.IdUtils;
import com.wise.common.utils.ImgUtils;
import com.wise.common.utils.SecureUtil;
import com.wise.common.utils.StringUtils;
import com.wise.common.utils.excel.ExportExcel;
import com.wise.common.utils.excel.ImportExcel;
import com.wise.core.bean.manage.SysManager;
import com.wise.core.bean.manage.SysRole;
import com.wise.core.config.Global;
import com.wise.core.controller.BaseController;
import com.wise.core.dto.PageParam;
import com.wise.core.dto.SysManagerParam;
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
	public String list(Model model, @ModelAttribute("rm") ResponseModel rm){
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
	public @ResponseBody ResponseModel save(@Valid SysManager sysManager, BindingResult br, Integer[] roleIds, String picImg, HttpServletRequest request){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			// 头像地址
			String portraitPic = null;
			if (StringUtils.isNotBlank(picImg)) {
				String[] imgPart = picImg.split(",");
				if (imgPart.length != 2) {
					throw new UploadFileException("上传头像失败");
				}
				// 临时文件路径
				String tempFileName = IdUtils.uuid() + picSuffix;
				String tempFilePath = request.getServletContext().getRealPath(tempFileDirectory) + File.separator + tempFileName; 
				File file = ImgUtils.base64DataToImg(imgPart[1], tempFilePath);
				if (file == null || !file.exists()) {
					throw new UploadFileException("上传头像失败");
				}
				// 验证文件大小
	    		if (file.length() > fileMaxSize) {
	    			FileUtils.deleteFile(file);
	    			throw new FileOutOfSizeException("文件大小不能超过 5M");
				}
				String picPath = uploadService.uploadPic(file, tempFileName);
				portraitPic = storageServer + picPath;
				FileUtils.deleteFile(file);
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
			SysManager operator = new SysManager();
			operator.setId(loginUser.getId());
			if (sysManager.getId() != null) { // 编辑
				// 密码不为空时， base64 解密
				String pwd = sysManager.getPwd();
				if (StringUtils.isNotEmpty(pwd))
					pwd = SecureUtil.decodeByBase64(pwd);
				else
					pwd = null;
				sysManager.setPwd(pwd);
				sysManager.setModifier(operator);
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
				sysManager.setCreator(operator);
				// 创建时间
				sysManager.setCreatedAt(new Date());
				sysManagerService.create(sysManager);
				rm.msgSuccess("添加用户成功");
			}
		} catch (ServiceException e) {
			rm.msgFailed(e.getMessage());
			logger.debug(e.getMessage(), e);
		} catch (ControllerException e) {
			rm.msgFailed(e.getMessage());
			logger.error(e.getMessage(), e);
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
	 * @param sysManagerParam 用户参数
	 * @return
	 */
	@RequiresPermissions({"sys:manager:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public @ResponseBody BootstrapTableResponse list(PageParam pageParam, SysManagerParam sysManagerParam){
		PageInfo<SysManager> pageInfo = sysManagerService.findPage(pageParam, sysManagerParam);
		return new BootstrapTableResponse(pageInfo.getList(), pageInfo.getTotal());
	}
	
	/**
	 * 导出用户列表数据
	 * @param sortName 排序字段
	 * @param sortOrder 排序方式
	 * @param sysManagerParam
	 * @return
	 */
	@RequestMapping(value = "/export", method = {RequestMethod.POST})
	public String export(String sortName, String sortOrder, SysManagerParam sysManagerParam, HttpServletResponse response){
		try {
			String fileName = IdUtils.uuid() + Global.EXCEL_EXT;
			List<SysManager> sysManagerList = sysManagerService.find(sortName, sortOrder, sysManagerParam);
			new ExportExcel("用户数据", SysManager.class).setDataList(sysManagerList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/sysManager/list";
	}

	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/import/template", method = {RequestMethod.GET})
    public String importTemplate(HttpServletResponse response) {
		try {
            String fileName = IdUtils.uuid() + Global.EXCEL_EXT;;
    		List<SysManager> list = Lists.newArrayList(); 
    		list.add(sysManagerService.findById(UserUtils.getLoginUser().getId()));
    		new ExportExcel("用户数据", SysManager.class, Global.EXCEL_IMPORT).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/sysManager/list";
    }

    /**
     * 导入数据
     * @param file
     * @return
     */
    @RequestMapping(value = "/import", method=RequestMethod.POST)
    public String importFile(@RequestParam(value = "importFile", required = true) MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	ResponseModel rm = new ResponseModel(); // 返回提示信息
    	try {
			// 验证文件大小
    		if (file.getSize() > fileMaxSize) {
				throw new FileOutOfSizeException("文件大小不能超过 5M");
			}
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<SysManager> list = ei.getDataList(SysManager.class);
			// 加上 创建人、创建时间、注册IP、默认密码 数据
			SysManager operator = new SysManager();
			operator.setId(UserUtils.getLoginUser().getId());
			for (SysManager sysManager : list) {
				sysManager.setPwd(Global.DEFAULT_PWD);
				sysManager.setRegistIp(getClientIP(request)); // ip
				sysManager.setCreator(operator);
				sysManager.setCreatedAt(new Date()); // 创建时间
			}
			sysManagerService.createBatch(list);
			rm.msgSuccess("导入数据成功");
		} catch (ServiceException e) {
			rm.msgFailed(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (ControllerException e) {
			rm.msgFailed(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			rm.msgFailed(e.getMessage());
			logger.error(e.getMessage(), e);
		}
    	redirectAttributes.addFlashAttribute("rm", rm);
		return "redirect:/sysManager/list";
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
	public @ResponseBody ResponseModel save(@Valid UserInfo userInfo, BindingResult br, String picImg, HttpServletRequest request){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			SysManager sysManager = new SysManager();
			
			// 头像地址
			String portraitPic = null;
			if (StringUtils.isNotBlank(picImg)) {
				String[] imgPart = picImg.split(",");
				if (imgPart.length != 2) {
					throw new UploadFileException("上传头像失败");
				}
				// 临时文件路径
				String tempFileName = IdUtils.uuid() + picSuffix;
				String tempFilePath = request.getServletContext().getRealPath(tempFileDirectory) + File.separator + tempFileName; 
				File file = ImgUtils.base64DataToImg(imgPart[1], tempFilePath);
				if (file == null || !file.exists()) {
					throw new UploadFileException("上传头像失败");
				}
				String picPath = uploadService.uploadPic(file, tempFileName);
				portraitPic = storageServer + picPath;
				FileUtils.deleteFile(file);
			}
			sysManager.setPortraitPic(portraitPic);
						
			LoginUser loginUser = UserUtils.getLoginUser();
			sysManager.setId(loginUser.getId());
			sysManager.setName(userInfo.getName());
			sysManager.setSex(userInfo.getSex());
			sysManager.setPhone(userInfo.getPhone());
			sysManager.setEmail(userInfo.getEmail());
			SysManager operator = new SysManager();
			operator.setId(loginUser.getId());
			sysManager.setModifier(operator);
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