package com.wise.common.response;

import java.util.List;

/**
 * bootstrap table 表格插件的响应对象
 * @author lingyuwang
 *
 */
public class BootstrapTableResponse {

	/**
	 * 数据列表
	 */
	private List<?> rows;
	
	/**
	 * 数据总条数
	 */
	private Integer total;
	
	public BootstrapTableResponse() {
		super();
	}

	public BootstrapTableResponse(List<?> rows, Integer total) {
		super();
		this.rows = rows;
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
