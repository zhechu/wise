package com.wise.core.bean.manage;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.wise.core.bean.TreeBean;
import com.wise.core.config.DictMeta;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysResource extends TreeBean<SysResource> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8390851664374317272L;

    /**
     * 链接地址
     */
    @Length(max=200, message="{sys.resource.url.length}")
    private String url;

    /**
     * 图标
     */
    @Length(max=50, message="{sys.resource.icon.length}")
    private String icon;

    /**
     * 描述
     */
    @Length(max=200, message="{sys.resource.remarks.length}")
    private String remarks;

    /**
     * 显示状态（0-隐藏，1-显示）
     */
    @Min(value=0, message="{sys.resource.status.min}")
    private Integer status;

    /**
     * 资源类型（0-菜单，1-页面，2-操作）
     */
    @Min(value=0, message="{sys.resource.type.min}")
    private Integer type;

    /**
     * 权限字符串
     */
    @Length(max=50, message="{sys.resource.permission.length}")
    private String permission;

    /**
     * 资源排列顺序
     */
    @Min(value=0, message="{sys.resource.sort.min}")
    private Integer sort;

    /**
     * 资源子节点（附加属性）
     */
    private List<SysResource> childrenList = new ArrayList<SysResource>(0);
    
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

    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	/**
     * 状态字符串（附加属性）
     * @return
     */
    public String getStatusName() {
    	return DictUtils.getDictLabel(String.valueOf(status), DictMeta.SYS_RESOURCE_STATUS, String.valueOf(Global.NORMAL));
    }
    
    /**
     * 类型字符串（附加属性）
     * @return
     */
    public String getTypeName() {
    	return DictUtils.getDictLabel(String.valueOf(type), DictMeta.SYS_RESOURCE_TYPE, String.valueOf(Global.NORMAL));
    }

}