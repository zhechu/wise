package com.wise.core.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
     * 父级对象（附加属性）
     */
	protected T parent;

    /**
     * 所有父级编号
     */
	protected String parentIds;

    /**
     * 名称
     */
    @NotEmpty(message="{name.notempty}")
    @Length(min=2, message="{name.length}")
    protected String name;

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds == null ? null : parentIds.trim();
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}
