package com.wise.core.bean.manage;

import org.hibernate.validator.constraints.NotEmpty;

import com.wise.common.config.Global;
import com.wise.core.bean.BaseBean;
import com.wise.core.web.utils.DictUtils;

public class SysDict extends BaseBean<SysDict> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4803935162539000847L;

	/**
     * 主键（自增）
     */
    private Integer id;

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
    @NotEmpty(message="{sys.dict.label.notempty}")
    private String label;

    /**
     * 状态值（0-禁用，1-启用）
     */
    private Integer status;

    /**
     * 字典项描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父级字典项
     */
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 状态字符串（附加属性）
     * @return
     */
    public String getStatusName() {
        return DictUtils.getDictLabel(String.valueOf(status), "sys_dict_status", String.valueOf(Global.NORMAL));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", value=").append(value);
        sb.append(", label=").append(label);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append(", sort=").append(sort);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}