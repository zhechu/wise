package com.wise.core.bean.manage;

import com.wise.core.bean.BaseBean;

public class SysDictMeta extends BaseBean<SysDictMeta> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6576362091902454038L;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append("]");
        return sb.toString();
    }
}