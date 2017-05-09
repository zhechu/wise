package com.wise.core.bean.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.wise.core.bean.BaseBean;
import com.wise.core.config.DictMeta;
import com.wise.core.config.Global;
import com.wise.core.web.utils.DictUtils;

public class SysManager extends BaseBean<SysManager> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4003817900697556840L;

    /**
     * 用户名
     */
    @Length(min=2, max=50, message="{sys.manager.userName.length}")
    private String userName;

    /**
     * 姓名
     */
    @Length(min=2, max=50, message="{sys.manager.name.length}")
    private String name;

    /**
     * 性别（0-女，1-男）
     */
    @Min(value=0, message="{sys.manager.sex.min}")
    private Integer sex;

    /**
     * 电话
     */
    @Pattern(regexp="^((\\+\\d+)?1[3458]\\d{9})|((\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8})$", message="{sys.manager.phone.pattern}")
    private String phone;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 账号状态（0-禁用，1-启用）
     */
    @Min(value=0, message="{sys.manager.status.min}")
    private Integer status;

    /**
     * 账号邮箱
     */
    @Email(message="{sys.manager.email.email}")
    private String email;

    /**
     * 注册ip
     */
    private String registIp;

    /**
     * 头像
     */
    private String portraitPic;

    /**
     * 创建者
     */
    private SysManager creator;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改者
     */
    private SysManager modifier;

    /**
     * 修改时间
     */
    private Date modifiedAt;

    /**
     * 公司
     */
    @NotNull(message="{sys.manager.company.notnull}")
    private SysOrg company;

    /**
     * 部门
     */
    @NotNull(message="{sys.manager.dept.notnull}")
    private SysOrg dept;

    /**
     * 角色列表（附加属性）
     */
    private List<SysRole> sysRoleList = new ArrayList<SysRole>(0);
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRegistIp() {
        return registIp;
    }

    public void setRegistIp(String registIp) {
        this.registIp = registIp == null ? null : registIp.trim();
    }

    public String getPortraitPic() {
        return portraitPic;
    }

    public void setPortraitPic(String portraitPic) {
        this.portraitPic = portraitPic == null ? null : portraitPic.trim();
    }

	public SysManager getCreator() {
		return creator;
	}

	public void setCreator(SysManager creator) {
		this.creator = creator;
	}

	public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

	public SysOrg getCompany() {
		return company;
	}

	public void setCompany(SysOrg company) {
		this.company = company;
	}

	public SysOrg getDept() {
		return dept;
	}

	public void setDept(SysOrg dept) {
		this.dept = dept;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

    /**
     * 性别字符串（附加属性）
     * @return
     */
    public String getSexName() {
        return DictUtils.getDictLabel(String.valueOf(sex), DictMeta.SEX, String.valueOf(Global.NORMAL));
    }
    
    /**
     * 状态字符串（附加属性）
     * @return
     */
    public String getStatusName() {
    	return DictUtils.getDictLabel(String.valueOf(status), DictMeta.SYS_MANAGER_STATUS, String.valueOf(Global.NORMAL));
    }

}