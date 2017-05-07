package com.wise.core.bean.manage;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wise.core.bean.BaseBean;
import com.wise.core.config.DictMeta;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysDict extends BaseBean<SysDict> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4803935162539000847L;

    /**
     * 类型
     */
    @NotEmpty(message="{sys.dict.type.notempty}")
    private String type;

    /**
     * 字典项值
     */
    @NotEmpty(message="{sys.dict.value.notempty}")
    private String value;

    /**
     * 字典项名称
     */
    @Length(min=2, max=50, message="{sys.dict.label.length}")
    private String label;

    /**
     * 状态值（0-禁用，1-启用）
     */
    @Min(value=0, message="{sys.dict.status.min}")
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 排序
     */
    @Min(value=0, message="{sys.dict.sort.min}")
    private Integer sort;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 状态字符串（附加属性）
     * @return
     */
    public String getStatusName() {
        return DictUtils.getDictLabel(String.valueOf(status), DictMeta.SYS_DICT_STATUS, String.valueOf(Global.NORMAL));
    }

	@Override
	public String toString() {
		return "SysDict [type=" + type + ", value=" + value + ", label=" + label + ", status=" + status + ", remarks="
				+ remarks + ", sort=" + sort + ", id=" + id + "]";
	}

}