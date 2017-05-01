package com.wise.core.bean;

/**
 * 实体树基类
 * @author lingyuwang
 *
 * @param <T>
 */
public abstract class TreeBean<T> extends BaseBean<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6306703568883832713L;

    /**
     * 父级编号
     */
	protected Integer parentId;

    /**
     * 所有父级编号
     */
	protected String parentIds;

    /**
     * 父资源字符串（附加属性）
     */
    private String parentName;
    
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
