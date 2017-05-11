package com.wise.core.config;

/**
 * 全局配置类
 * @author lingyuwang
 *
 */
public class Global {

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
	 * 默认字典值
	 */
	public static final Integer DEFAULT_DICT_VALUE = 0;
	
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
	
	/**
	 * 默认表的别名
	 */
	public static final String DEFAULT_TABLE_ALIAS = "a" + ".";
	
	/**
	 * Excel 单元格水平对齐方式
	 */
	public final static short ALIGN_GENERAL = 0;
    public final static short ALIGN_LEFT = 1;
    public final static short ALIGN_CENTER = 2;
    public final static short ALIGN_RIGHT = 3;
    
    /**
     * Excel 扩展名
     */
    public final static String EXCEL_EXT = ".xlsx";
    
    /**
     * Excel 操作类型（0：导出导入；1：仅导出；2：仅导入）
     */
    public final static short EXCEL_EXPORT_IMPORT = 0;
    public final static short EXCEL_EXPORT = 1;
    public final static short EXCEL_IMPORT = 2;
}
