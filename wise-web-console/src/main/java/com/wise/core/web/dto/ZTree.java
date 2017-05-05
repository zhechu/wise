package com.wise.core.web.dto;

/**
 * 树结构 传输对象
 * @author lingyuwang
 *
 */
public class ZTree {

	private Integer parentId;
	
	private Integer id;
	
	private String name;

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
