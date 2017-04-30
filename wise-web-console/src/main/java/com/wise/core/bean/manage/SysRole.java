package com.wise.core.bean.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.wise.core.bean.BaseBean;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysRole extends BaseBean<SysRole> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4497175268922480227L;

	/**
     * 主键（自增）
     */
    private Integer id;

    /**
     * 角色名称
     */
    @NotEmpty(message="{sys.role.name.notempty}")
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态（0-禁用，1-启用）
     */
    private Integer status;

    /**
     * 创建者
     */
    private Integer creator;
    
    /**
     * 创建者名称（附加属性）
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 修改者
     */
    private Integer modifier;

    /**
     * 修改时间
     */
    private Date modifiedAt;

    /**
     * 资源主键列表（附加属性）
     */
    private List<Integer> sysResourceIdList = new ArrayList<Integer>(0);
    
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

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public List<Integer> getSysResourceIdList() {
		return sysResourceIdList;
	}

	public void setSysResourceIdList(List<Integer> sysResourceIdList) {
		this.sysResourceIdList = sysResourceIdList;
	}

    /**
     * 状态字符串（附加属性）
     * @return
     */
    public String getStatusName() {
        return DictUtils.getDictLabel(String.valueOf(status), "sys_role_status", String.valueOf(Global.NORMAL));
    }

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", createAt=").append(createAt);
        sb.append(", modifier=").append(modifier);
        sb.append(", modifiedAt=").append(modifiedAt);
        sb.append("]");
        return sb.toString();
    }
}