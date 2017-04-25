package com.wise.common.page;

import java.io.Serializable;

/**
 * 实现分页
 * @author lingyuwang
 *
 */
public class SimplePage implements Serializable, Paginable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 默认的每页至多显示条数
	 */
	public static final int DEF_COUNT = 20;

	/**
	 * 将页码拆箱，若页码为空或者小于1，则赋值1
	 * @param pageNo
	 * @return
	 */
	public static int cpn(Integer pageNo) {
		return (pageNo == null) || (pageNo.intValue() < 1) ? 1 : pageNo.intValue();
	}

	public SimplePage() {}

	public SimplePage(int pageNo, int pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}

	/**
	 * 调整页码
	 */
	public void adjustPageNo() {
		if (this.pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (this.pageNo > tp) {
			this.pageNo = tp;
		}
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getTotalPage() {
		int totalPage = this.totalCount / this.pageSize;
		if ((totalPage == 0) || (this.totalCount % this.pageSize != 0)) {
			totalPage++;
		}
		return totalPage;
	}

	public boolean isFirstPage() {
		return this.pageNo <= 1;
	}

	public boolean isLastPage() {
		return this.pageNo >= getTotalPage();
	}

	public int getNextPage() {
		if (isLastPage()) {
			return this.pageNo;
		}
		return this.pageNo + 1;
	}

	public int getPrePage() {
		if (isFirstPage()) {
			return this.pageNo;
		}
		return this.pageNo - 1;
	}

	/**
	 * 总条数
	 */
	protected int totalCount = 0;
	/**
	 * 每页至多显示条数
	 */
	protected int pageSize = DEF_COUNT;
	/**
	 * 当前页码
	 */
	protected int pageNo = 1;

	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}
}
