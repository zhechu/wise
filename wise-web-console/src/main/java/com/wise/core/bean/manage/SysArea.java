package com.wise.core.bean.manage;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wise.core.bean.TreeBean;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysArea extends TreeBean<SysArea> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5974637158127065591L;

    /**
     * 名称
     */
    @NotEmpty(message="{sys.area.name.notempty}")
    @Length(min=2, message="{sys.area.name.length}")
    private String name;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 区域类型（1-国家，2-省份、直辖市，3-地市，4-区县）
     */
    private Integer type;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 排序
     */
    private Integer sort;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 类型字符串（附加属性）
     * @return
     */
    public String getTypeName() {
    	return DictUtils.getDictLabel(String.valueOf(type), "sys_area_type", String.valueOf(Global.NORMAL));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentIds=").append(parentIds);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", remarks=").append(remarks);
        sb.append(", sort=").append(sort);
        sb.append("]");
        return sb.toString();
    }
}