package com.wise.core.bean.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wise.core.bean.BaseBean;
import com.wise.core.config.DictMeta;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysRole extends BaseBean<SysRole> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4497175268922480227L;

    /**
     * 角色名称
     */
    @NotEmpty(message="{sys.role.name.notempty}")
    @Length(min=2, max=50, message="{sys.role.name.length}")
    private String name;

    /**
     * 描述
     */
    @Length(max=200, message="{sys.role.remarks.length}")
    private String remarks;

    /**
     * 状态（0-禁用，1-启用）
     */
    @NotNull
    @Min(value=0, message="{sys.role.status.min}")
    private Integer status;

    /**
     * 创建者
     */
    private SysManager creator;
    
    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 修改者
     */
    private SysManager modifier;

    /**
     * 修改时间
     */
    private Date modifiedAt;

    /**
     * 资源主键列表（附加属性）
     */
    private List<Integer> sysResourceIdList = new ArrayList<Integer>(0);
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

	public SysManager getCreator() {
		return creator;
	}

	public void setCreator(SysManager creator) {
		this.creator = creator;
	}

	public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public SysManager getModifier() {
		return modifier;
	}

	public void setModifier(SysManager modifier) {
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
        return DictUtils.getDictLabel(String.valueOf(status), DictMeta.SYS_ROLE_STATUS, String.valueOf(Global.NORMAL));
    }

}