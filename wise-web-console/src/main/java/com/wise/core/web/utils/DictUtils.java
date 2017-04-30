package com.wise.core.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wise.core.bean.manage.SysDict;
import com.wise.core.service.manage.SysDictService;
import com.wise.core.utils.CacheUtils;
import com.wise.web.utils.SpringContextHolder;

/**
 * 数据字典 工具类
 * @author lingyuwang
 *
 */
public class DictUtils {

	private DictUtils() {}
	
	private static SysDictService sysDictService = SpringContextHolder.getBean(SysDictService.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
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
	@SuppressWarnings("unchecked")
	public static List<SysDict> getDictList(String type){
		Map<String, List<SysDict>> dictMap = (Map<String, List<SysDict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = new HashMap<String, List<SysDict>>();
			for (SysDict dict : sysDictService.findValid()){
				List<SysDict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictList = new ArrayList<SysDict>();
					dictList.add(dict);
					dictMap.put(dict.getType(), dictList);
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<SysDict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = new ArrayList<SysDict>();
		}
		return dictList;
	}
	
}
