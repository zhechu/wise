package com.wise.core.bean.manage;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wise.core.bean.BaseBean;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysResource extends BaseBean<SysResource> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8390851664374317272L;

	/**
     * 主键（自增）
     */
    private Integer id;

    /**
     * 资源名称
     */
    @NotEmpty(message="{sys.resource.name.notempty}")
    @Length(min=2, message="{sys.resource.name.length}")
    private String name;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示状态（0-隐藏，1-显示）
     */
    private Integer status;

    /**
     * 资源类型（0-菜单，1-页面，2-操作）
     */
    private Integer type;

    /**
     * 权限字符串
     */
    private String permission;

    /**
     * 父资源id（不同类型的资源，请勿设置上下级关系）
     */
    private Integer parentId;

    /**
     * 资源排列顺序
     */
    private Integer sort;

    /**
     * 资源子节点（附加属性）
     */
    private List<SysResource> childrenList = new ArrayList<SysResource>(0);

    /**
     * 父资源字符串（附加属性）
     */
    private String parentName;
    
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
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SysResource> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<SysResource> childrenList) {
		this.childrenList = childrenList;
	}

    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
     * 状态字符串（附加属性）
     * @return
     */
    public String getStatusName() {
    	return DictUtils.getDictLabel(String.valueOf(status), "sys_resource_status", String.valueOf(Global.NORMAL));
    }
    
    /**
     * 类型字符串（附加属性）
     * @return
     */
    public String getTypeName() {
    	return DictUtils.getDictLabel(String.valueOf(type), "sys_resource_type", String.valueOf(Global.NORMAL));
    }

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", permission=").append(permission);
        sb.append(", parentId=").append(parentId);
        sb.append(", sort=").append(sort);
        sb.append("]");
        return sb.toString();
    }
}