package com.wise.core.config;

/**
 * 全局配置类
 * @author lingyuwang
 *
 */
public class Global {

	/**
	 * 图片服务地址
	 */
	public static final String IMG_URL = "http://192.168.163.44:8888/";
	
	/**
	 * 禁用/启用
	 */
	public static final Integer FORBIDDEN = 0;
	public static final Integer NORMAL = 1;

	/**
	 * 树的默认父节点
	 */
	public static final Integer DEFAULT_PARENT_ID = 0;
	
	/**
	 * 资源类型，菜单/页面/操作
	 */
	public static final Integer SYS_RESOURCE_TYPE_MENU = 0;
	public static final Integer SYS_RESOURCE_TYPE_PAGE = 1;
	public static final Integer SYS_RESOURCE_TYPE_OPERATE = 2;
	
	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PWD = "123456";
	
	/**
	 * 分页默认参数
	 */
	public static final int DEFAULT_PAGE_NUM = 1;
	public static final int DEFAULT_PAGE_SIZE = 20;
	
}