package com.wise.common.page;

/**
 * 分页器
 * @author lingyuwang
 *
 */
public class Page extends SimplePage {
	
	private static final long serialVersionUID = -8507725089892617272L;
	
	/**
	 * 排序字段
	 */
	private String sortName;
	
	/**
	 * 排序方式（asc,desc）
	 */
	private String sortOrder;

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
