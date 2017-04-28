package com.wise.core.controller.manage;

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

import com.github.pagehelper.PageInfo;
import com.wise.common.dto.PageParam;
import com.wise.common.exception.service.ServiceException;
import com.wise.common.response.BootstrapTableResponse;
import com.wise.common.response.ResponseModel;
import com.wise.core.bean.manage.SysDict;
import com.wise.core.controller.BaseController;
import com.wise.core.service.manage.SysDictService;

/**
 * 字典管理
 * @author lingyuwang
 *
 */
@Controller
@RequestMapping(value = "/sysDict")
public class SysDictController extends BaseController {

	@Autowired
	private SysDictService sysDictService;

	/**
	 * 进入字典列表页
	 * @return
	 */
	@RequiresPermissions({"sys:dict:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public String list(){
		return "/sysDict/sysDictList";
	}
	
	/**
	 * 进入编辑页
	 * @return
	 */
	@RequiresPermissions({"sys:dict:add", "sys:dict:update"})
	@RequestMapping(value = "/edit", method = {RequestMethod.GET})
	public String edit(Integer id, Model model){
		if (id != null) { // 编辑，反之添加
			SysDict sysDict = sysDictService.findById(id);
			model.addAttribute("sysDict", sysDict);
		}
		return "/sysDict/sysDictEdit";
	}
	
	/**
	 * 保存字典（添加和编辑，用 id 是否存在区分）
	 * @return
	 */
	@RequiresPermissions({"sys:dict:add", "sys:dict:update"})
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel save(@Valid SysDict sysDict, BindingResult br){
		ResponseModel rm = new ResponseModel();
		if(br.hasErrors()){ // 后台验证
			rm.msgFailed(convertToMessage(br.getFieldErrors()));
            return rm;
		}
		try {
			if (sysDict.getId() != null) { // 编辑
				sysDictService.update(sysDict);
				rm.msgSuccess("编辑字典成功");
			} else { // 添加
				sysDictService.create(sysDict);
				rm.msgSuccess("添加字典成功");
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
	 * 删除字典
	 * @param ids 字典主键列表，多个则以逗号分割
	 * @return
	 */
	@RequiresPermissions({"sys:dict:delete"})
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public @ResponseBody ResponseModel delete(@RequestParam String ids){
		ResponseModel rm = new ResponseModel();
		try {
			String[] idss = ids.split(",");
			sysDictService.delete(convertToIntegerArray(idss));
			rm.msgSuccess("删除字典成功");
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
	 * 返回字典列表数据
	 * @param pageParam 分页参数
	 * @param type 字典类型
	 * @param status 状态
	 * @return
	 */
	@RequiresPermissions({"sys:dict:view"})
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public @ResponseBody BootstrapTableResponse list(PageParam pageParam, String type, Integer status){
		PageInfo<SysDict> pageInfo = sysDictService.findPage(pageParam, type, status);
		return new BootstrapTableResponse(pageInfo.getList(), pageInfo.getTotal());
	}
	
}