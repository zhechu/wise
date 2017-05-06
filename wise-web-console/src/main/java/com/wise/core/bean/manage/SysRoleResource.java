package com.wise.core.bean.manage;

import com.wise.core.bean.BaseBean;

public class SysRoleResource extends BaseBean<SysRoleResource> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5506322395457721217L;

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 系统资源Id
     */
    private Integer sysResourceId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getSysResourceId() {
        return sysResourceId;
    }

    public void setSysResourceId(Integer sysResourceId) {
        this.sysResourceId = sysResourceId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", sysResourceId=").append(sysResourceId);
        sb.append("]");
        return sb.toString();
    }
}