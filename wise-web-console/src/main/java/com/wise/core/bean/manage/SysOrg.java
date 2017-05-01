package com.wise.core.bean.manage;

import com.wise.core.bean.TreeBean;

public class SysOrg extends TreeBean<SysOrg> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1824731540172898011L;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 归属区域
     */
    private Integer areaId;

    /**
     * 机构编码
     */
    private String code;

    /**
     * 机构类型（1-公司，2-部门，3-小组，4-其它）
     */
    private Integer type;

    /**
     * 机构等级（1-一级，2-二级，3-三级，4-四级）
     */
    private Byte grade;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 状态（0-禁用，1-启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

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

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
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
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentIds=").append(parentIds);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", areaId=").append(areaId);
        sb.append(", code=").append(code);
        sb.append(", type=").append(type);
        sb.append(", grade=").append(grade);
        sb.append(", address=").append(address);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", status=").append(status);
        sb.append(", remarks=").append(remarks);
        sb.append("]");
        return sb.toString();
    }
}