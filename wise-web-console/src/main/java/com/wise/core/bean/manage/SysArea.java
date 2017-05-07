package com.wise.core.bean.manage;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.wise.core.bean.TreeBean;
import com.wise.core.config.DictMeta;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysArea extends TreeBean<SysArea> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5974637158127065591L;

    /**
     * 区域编码
     */
    @Length(max=50, message="{sys.area.code.length}")
    private String code;

    /**
     * 区域类型（1-国家，2-省份、直辖市，3-地市，4-区县）
     */
    @Min(value=0, message="{sys.area.type.min}")
    private Integer type;

    /**
     * 备注
     */
    @Length(max=200, message="{sys.area.remarks.length}")
    private String remarks;

    /**
     * 排序
     */
    @Min(value=0, message="{sys.area.sort.min}")
    private Integer sort;

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
    	return DictUtils.getDictLabel(String.valueOf(type), DictMeta.SYS_AREA_TYPE, String.valueOf(Global.NORMAL));
    }

	@Override
	public String toString() {
		return "SysArea [name=" + name + ", code=" + code + ", type=" + type + ", remarks=" + remarks + ", sort=" + sort
				+ ", parent=" + parent + ", parentIds=" + parentIds + ", id=" + id + "]";
	}

}