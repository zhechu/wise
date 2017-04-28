package com.wise.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;

/**
 * 控制器基础类
 * @author lingyuwang
 *
 */
public abstract class BaseController {

	/**
	 * 日志
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 转换验证错误信息
	 * @param fieldErrorList
	 * @return
	 */
	protected String convertToMessage(List<FieldError> fieldErrorList) {
		StringBuilder msg = new StringBuilder();
		for (FieldError fieldError : fieldErrorList) {  
			msg.append(fieldError.getDefaultMessage() + ";<br/><br/>");
        }
		// 去掉最后一个 <br/>
		int len = msg.length();
		if (msg.length() > 0) {
			return msg.substring(0, len-11);
		}
		return msg.toString();
	}

	/**
	 * 数组类型转换
	 * @param ids 字符串数组
	 * @return
	 */
	protected Integer[] convertToIntegerArray(String[] ids) {
		int len = ids.length;
		Integer[] idsTarget = new Integer[len];
		for (int i = 0; i < len; i++) {
			idsTarget[i] = Integer.parseInt(ids[i]);
		}
		return idsTarget;
	}

	/**
	 * 转换数组
	 * @param ids 字符串数组
	 * @return 整型列表
	 */
	protected List<Integer> convertToIntegerList(String[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (String id : ids) {
			idList.add(Integer.parseInt(id));
		}
		return idList;
	}
	
	/**
	 * 获取用户 IP
	 * @param request
	 * @return
	 */
	protected String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;
	}
	
}