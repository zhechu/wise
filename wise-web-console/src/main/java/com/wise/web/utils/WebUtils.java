package com.wise.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * web 工具类
 * @author lingyuwang
 *
 */
public class WebUtils {

	/**
	 * 是否是Ajax请求
	 * @param request 请求对象
	 * @return
	 */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) 
            return true;
        else
            return false;
    }
	
}
