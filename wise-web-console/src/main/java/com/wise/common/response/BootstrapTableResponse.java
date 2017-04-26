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
	private long total;
	
	public BootstrapTableResponse() {
		super();
	}

	public BootstrapTableResponse(List<?> rows, long total) {
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
