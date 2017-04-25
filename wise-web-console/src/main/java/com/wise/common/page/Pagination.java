package com.wise.common.page;

import java.util.List;

/**
 * 分页器
 * @author lingyuwang
 *
 */
public class Pagination extends SimplePage {
	
	private static final long serialVersionUID = -8507725089892617272L;
	/**
	 * 结果集
	 */
	private List<?> list;
	/**
	 * 查询条件拼接，如：userName=admin&status=1
	 */
	private String criteria;

	public Pagination() {}

	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public int getFirstResult() {
		return (this.pageNo - 1) * this.pageSize;
	}

	public List<?> getList() {
		return this.list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

}
