package com.wise.core.bean;

import java.io.Serializable;

/**
 * 实体基类
 * @author lingyuwang
 *
 * @param <T>
 */
public abstract class BaseBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 419382243025145964L;

	/**
     * 主键（自增）
     */
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
