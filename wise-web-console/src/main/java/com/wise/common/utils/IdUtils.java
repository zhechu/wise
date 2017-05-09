package com.wise.common.utils;

import java.util.UUID;

/**
 * 唯一标识符生成工具
 * @author lingyuwang
 *
 */
public class IdUtils {
	
	private IdUtils() {}
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(IdUtils.uuid());
	}
	
}
