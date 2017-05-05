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
import com.wise.core.bean.manage.SysArea;
import com.wise.core.config.Global;
import com.wise.core.controller.BaseController;
import com.wise.core.service.manage.SysAreaService;
import com.wise.core.web.dto.ZTree;

/**
 * 区域管理
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/sysArea")
public class SysAreaController extends BaseController {

	@Autowired
	private SysAreaService sysAreaService;

	/**
	 * 进入区域页
	 * @return
	 */
	@RequiresPermissions({"sys:area:view"})
	@RequestMapping(value = "/list")
	public String list(){
		return "/sysArea/sysAreaList";
	}

	/**
	 * 进入编辑页
	 * @return
	 */
	@RequiresPermissions({"sys:area:add", "sys:area:update"})
	@RequestMapping(value = "/edit", method = {RequestMethod.GET})
	public String edit(Integer id, Model model){
		if (id != null) { // 编辑，反之添加
			SysArea sysArea = sysAreaService.findById(id);
			model.addAttribute("sysArea", sysArea);
		}
		return "/sysArea/sysAreaEdit";
	}

	/**
	 * 保存区域（添加和编辑，用 id 是否存在区分）
	 * @return
	 */
	@RequiresPermissions({"sys:area:add", "sys:area:update"})
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel save(@Valid SysArea sysArea, BindingResult br){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			if (sysArea.getId() != null) { // 编辑
				sysAreaService.update(sysArea);
				rm.msgSuccess("编辑区域成功");
			} else { // 添加
				sysAreaService.create(sysArea);
				rm.msgSuccess("添加区域成功");
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
	 * 删除区域
	 * @param ids 区域主键列表，多个则以逗号分割
	 * @return
	 */
	@RequiresPermissions({"sys:area:delete"})
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel delete(@RequestParam String ids){
		ResponseModel rm = new ResponseModel();
		String[] idss = null;
		try {
			idss = ids.split(",");
			sysAreaService.delete(convertToIntegerArray(idss));
			rm.msgSuccess("删除区域成功");
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
	 * 返回区域所有列表
	 * @return
	 */
	@RequiresPermissions({"sys:area:view"})
	@RequestMapping(value = "/data", method = {RequestMethod.POST})
	public @ResponseBody List<ZTree> data(){
		List<SysArea> sysAreaList = sysAreaService.find();
		return convertToZTree(sysAreaList);
	}
	
	/**
	 * 返回区域列表数据
	 * @return
	 */
	@RequiresPermissions({"sys:area:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public @ResponseBody BootstrapTableResponse list(Integer parentId){
		// 若父主键为空，则默认查询顶级区域
		if (parentId == null) {
			parentId = Global.DEFAULT_PARENT_ID;
		}
		return new BootstrapTableResponse(sysAreaService.findByParentId(parentId), 0);
	}
	
}