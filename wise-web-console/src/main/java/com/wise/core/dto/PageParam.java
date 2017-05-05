package com.wise.core.dto;

import com.wise.core.config.Global;

/**
 * 分页传输对象
 * @author lingyuwang
 *
 */
public class PageParam {

	//当前页
    private Integer pageNum;
    
    //每页的数量
    private Integer pageSize;
    
    //排序字段
    private String sortName;
    
    //排序方式
    private String sortOrder;

	public PageParam() {
		super();
	}

	public PageParam(Integer pageNum, Integer pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public PageParam(Integer pageNum, Integer pageSize, String sortName, String sortOrder) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.sortName = sortName;
		this.sortOrder = sortOrder;
	}

	public Integer getPageNum() {
		if (pageNum == null || pageNum<=0)
			return Global.DEFAULT_PAGE_NUM;
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize<=0)
			return Global.DEFAULT_PAGE_SIZE;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

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
	
	/**
	 * 获取排序语句
	 * @param defaultSortName 默认排序字段
	 * @param defaultSortOrder 默认排序方式
	 * @return
	 */
	public String getOrderBy(String defaultSortName, String defaultSortOrder) {
		if(sortName == null)
			sortName = Global.DEFAULT_TABLE_ALIAS + defaultSortName;
		else
			sortName = Global.DEFAULT_TABLE_ALIAS + sortName;
		if (sortOrder == null) 
			sortOrder = defaultSortOrder;
		return sortName + " " + sortOrder;
	}
	
}
