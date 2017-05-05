package com.wise.core.bean.manage;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wise.core.bean.TreeBean;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysOrg extends TreeBean<SysOrg> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1824731540172898011L;

    /**
     * 名称
     */
    @NotEmpty(message="{sys.org.name.notempty}")
    @Length(min=2, message="{sys.org.name.length}")
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 归属区域（附加属性）
     */
    @NotNull(message="{sys.org.areaId.notnull}")
    private SysArea sysArea;
    
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

	public SysArea getSysArea() {
		return sysArea;
	}

	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
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

	/**
     * 状态字符串（附加属性）
     * @return
     */
    public String getStatusName() {
    	return DictUtils.getDictLabel(String.valueOf(status), "sys_org_status", String.valueOf(Global.NORMAL));
    }
    
    /**
     * 类型字符串（附加属性）
     * @return
     */
    public String getTypeName() {
    	return DictUtils.getDictLabel(String.valueOf(type), "sys_org_type", String.valueOf(Global.NORMAL));
    }
    
    /**
     * 等级字符串（附加属性）
     * @return
     */
    public String getGradeName() {
    	return DictUtils.getDictLabel(String.valueOf(type), "sys_org_grade", String.valueOf(Global.NORMAL));
    }

}