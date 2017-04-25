package com.wise.common.page;

/**
 * 分页接口
 * @author lingyuwang
 *
 */
public abstract interface Paginable {
	
	/**
	 * 获取总条数
	 * @return
	 */
	public abstract int getTotalCount();

	/**
	 * 获取总页数
	 * @return
	 */
	public abstract int getTotalPage();

	/**
	 * 获取每页至多显示条数
	 * @return
	 */
	public abstract int getPageSize();

	/**
	 * 获取页码
	 * @return
	 */
	public abstract int getPageNo();

	/**
	 * 判断是否为第一页
	 * @return
	 */
	public abstract boolean isFirstPage();

	/**
	 * 判断是否为最后一页
	 * @return
	 */
	public abstract boolean isLastPage();

	/**
	 * 获取下一页
	 * @return
	 */
	public abstract int getNextPage();

	/**
	 * 获取上一页
	 * @return
	 */
	public abstract int getPrePage();
}
