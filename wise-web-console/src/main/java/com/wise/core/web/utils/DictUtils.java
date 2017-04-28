package com.wise.core.web.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.wise.core.bean.manage.SysDict;
import com.wise.core.service.manage.SysDictService;
import com.wise.web.utils.SpringContextHolder;

/**
 * 数据字典 工具类
 * @author lingyuwang
 *
 */
public class DictUtils {

	private static SysDictService sysDictService = SpringContextHolder.getBean(SysDictService.class);

	/**
	 * 获取字典标签
	 * @param value 字典值
	 * @param type 字典类型
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (SysDict sysDict : getDictList(type)){
				if (type.equals(sysDict.getType()) && value.equals(sysDict.getValue())){
					return sysDict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	/**
	 * 获取字典标签列表（以 , 拼接的标签列表）
	 * @param values 以 , 拼接的字典值列表
	 * @param type 字典类型
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = new ArrayList<String>();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	/**
	 * 获取字典值
	 * @param label 字典标签
	 * @param type 字典类型
	 * @param defaultLabel 默认值
	 * @return
	 */
	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (SysDict sysDict : getDictList(type)){
				if (type.equals(sysDict.getType()) && label.equals(sysDict.getLabel())){
					return sysDict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	/**
	 * 获取字典对象列表
	 * @param type 类型
	 * @return
	 */
	public static List<SysDict> getDictList(String type){
		List<SysDict> sysDictList = sysDictService.findByType(type);
		return sysDictList;
	}
	
}
