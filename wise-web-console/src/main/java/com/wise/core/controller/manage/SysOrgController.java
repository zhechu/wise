package com.wise.core.controller.manage;

import java.util.List;

import javax.validation.Valid;

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
import com.wise.core.bean.manage.SysOrg;
import com.wise.core.config.Global;
import com.wise.core.controller.BaseController;
import com.wise.core.service.manage.SysOrgService;
import com.wise.core.web.dto.ZTree;

/**
 * 机构管理
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/sysOrg")
public class SysOrgController extends BaseController {

	@Autowired
	private SysOrgService sysOrgService;

	/**
	 * 进入机构页
	 * @return
	 */
	@RequiresPermissions({"sys:org:view"})
	@RequestMapping(value = "/list")
	public String list(){
		return "/sysOrg/sysOrgList";
	}

	/**
	 * 进入编辑页
	 * @return
	 */
	@RequiresPermissions({"sys:org:add", "sys:org:update"})
	@RequestMapping(value = "/edit", method = {RequestMethod.GET})
	public String edit(Integer id, Model model){
		if (id != null) { // 编辑，反之添加
			SysOrg sysOrg = sysOrgService.findById(id);
			model.addAttribute("sysOrg", sysOrg);
		}
		return "/sysOrg/sysOrgEdit";
	}

	/**
	 * 保存机构（添加和编辑，用 id 是否存在区分）
	 * @return
	 */
	@RequiresPermissions({"sys:org:add", "sys:org:update"})
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel save(@Valid SysOrg sysOrg, BindingResult br){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			if (sysOrg.getId() != null) { // 编辑
				sysOrgService.update(sysOrg);
				rm.msgSuccess("编辑机构成功");
			} else { // 添加
				sysOrgService.create(sysOrg);
				rm.msgSuccess("添加机构成功");
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
	 * 删除机构
	 * @param ids 机构主键列表，多个则以逗号分割
	 * @return
	 */
	@RequiresPermissions({"sys:org:delete"})
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel delete(@RequestParam String ids){
		ResponseModel rm = new ResponseModel();
		String[] idss = null;
		try {
			idss = ids.split(",");
			sysOrgService.delete(convertToIntegerArray(idss));
			rm.msgSuccess("删除机构成功");
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
	 * 返回机构所有列表
	 * @return
	 */
	@RequiresPermissions({"sys:org:view"})
	@RequestMapping(value = "/data", method = {RequestMethod.POST})
	public @ResponseBody List<ZTree> data(){
		List<SysOrg> sysOrgList = sysOrgService.find();
		return convertToZTree(sysOrgList);
	}
	
	/**
	 * 返回机构所有列表（可用）
	 * @return
	 */
	@RequiresPermissions({"sys:org:view"})
	@RequestMapping(value = "/validData", method = {RequestMethod.POST})
	public @ResponseBody List<ZTree> validData(){
		List<SysOrg> sysOrgList = sysOrgService.findValid();
		return convertToZTree(sysOrgList);
	}
	
	/**
	 * 返回机构列表数据
	 * @return
	 */
	@RequiresPermissions({"sys:org:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public @ResponseBody BootstrapTableResponse list(Integer parentId){
		// 若父主键为空，则默认查询顶级机构
		if (parentId == null) {
			parentId = Global.DEFAULT_PARENT_ID;
		}
		return new BootstrapTableResponse(sysOrgService.findByParentId(parentId), 0);
	}
	
}