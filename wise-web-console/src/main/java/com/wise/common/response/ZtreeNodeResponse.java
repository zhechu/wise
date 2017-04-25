package com.wise.common.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * ztree 数据返回结构体
 * @author 何伟杰
 * @created 2017年4月14日
 */
public class ZtreeNodeResponse implements Serializable{

	private static final long serialVersionUID = -3367583907088480065L;
	
	private Integer id;
	private String name;
	private boolean checked = false;
	private String icon;
	private boolean open;
	private String url;
	
	private List<ZtreeNodeResponse> children = new ArrayList<ZtreeNodeResponse>();

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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ZtreeNodeResponse> getChildren() {
		return children;
	}

	public void setChildren(List<ZtreeNodeResponse> children) {
		this.children = children;
	}
	
	
	
	
}
