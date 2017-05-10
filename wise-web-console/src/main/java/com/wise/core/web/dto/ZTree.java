package com.wise.core.web.dto;

import java.io.Serializable;

/**
 * 树结构 传输对象
 * @author lingyuwang
 *
 */
public class ZTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3013603528949047982L;

	protected Integer parentId;
	
	protected Integer id;
	
	protected String name;

	public ZTree(Integer parentId, Integer id, String name) {
		super();
		this.parentId = parentId;
		this.id = id;
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
