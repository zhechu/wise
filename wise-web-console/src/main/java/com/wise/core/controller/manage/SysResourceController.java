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

import com.wise.common.config.Global;
import com.wise.common.exception.service.ServiceException;
import com.wise.common.response.BootstrapTableResponse;
import com.wise.common.response.ResponseModel;
import com.wise.core.bean.manage.SysResource;
import com.wise.core.controller.BaseController;
import com.wise.core.service.manage.SysResourceService;

/**
 * 资源管理
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/sysResource")
public class SysResourceController extends BaseController {

	@Autowired
	private SysResourceService sysResourceService;

	/**
	 * 进入资源页
	 * @return
	 */
	@RequiresPermissions({"sys:resource:view"})
	@RequestMapping(value = "/list")
	public String list(){
		return "/sysResource/sysResourceList";
	}

	/**
	 * 进入编辑页
	 * @return
	 */
	@RequiresPermissions({"sys:resource:add", "sys:resource:update"})
	@RequestMapping(value = "/edit", method = {RequestMethod.GET})
	public String edit(Integer id, Model model){
		if (id != null) { // 编辑，反之添加
			SysResource sysResource = sysResourceService.findById(id);
			model.addAttribute("sysResource", sysResource);
		}
		return "/sysResource/sysResourceEdit";
	}

	/**
	 * 保存资源（添加和编辑，用 id 是否存在区分）
	 * @return
	 */
	@RequiresPermissions({"sys:resource:add", "sys:resource:update"})
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel save(@Valid SysResource sysResource, BindingResult br){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			if (sysResource.getId() != null) { // 编辑
				sysResourceService.update(sysResource);
				rm.msgSuccess("编辑资源成功");
			} else { // 添加
				sysResourceService.create(sysResource);
				rm.msgSuccess("添加资源成功");
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
	 * 删除资源
	 * @param ids 资源主键列表，多个则以逗号分割
	 * @return
	 */
	@RequiresPermissions({"sys:resource:delete"})
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel delete(@RequestParam String ids){
		ResponseModel rm = new ResponseModel();
		String[] idss = null;
		try {
			idss = ids.split(",");
			sysResourceService.delete(convertToIntegerArray(idss));
			rm.msgSuccess("删除资源成功");
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
	 * 返回资源所有列表
	 * @return
	 */
	@RequiresPermissions({"sys:resource:view"})
	@RequestMapping(value = "/data", method = {RequestMethod.POST})
	public @ResponseBody List<SysResource> data(){
		return sysResourceService.find();
	}

	/**
	 * 返回资源列表数据
	 * @return
	 */
	@RequiresPermissions({"sys:resource:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public @ResponseBody BootstrapTableResponse list(Integer parentId){
		// 若父主键为空，则默认查询顶级资源
		if (parentId == null) {
			parentId = Global.DEFAULT_PARENT_ID;
		}
		return new BootstrapTableResponse(sysResourceService.findByParentId(parentId), 0);
	}
	
}