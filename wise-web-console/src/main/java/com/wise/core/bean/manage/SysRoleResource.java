package com.wise.core.bean.manage;

public class SysRoleResource {
    /**
     * 主键（自增）
     */
    private Integer id;

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 系统资源Id
     */
    private Integer sysResourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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