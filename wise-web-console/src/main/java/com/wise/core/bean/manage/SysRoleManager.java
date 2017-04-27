package com.wise.core.bean.manage;

public class SysRoleManager {
    /**
     * 主键（自增）
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 管理员id
     */
    private Integer managerId;

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

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", managerId=").append(managerId);
        sb.append("]");
        return sb.toString();
    }
}