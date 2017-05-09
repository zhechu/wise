package com.wise.core.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * 个人信息 传输对象
 * @author lingyuwang
 *
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 2431723702449778148L;

	/**
     * 主键（自增）
     */
    private Integer id;

    /**
     * 账号邮箱
     */
    @Email(message="{sys.manager.info.email.email}")
    private String email;

    /**
     * 头像
     */
    private String portraitPic;

    /**
     * 姓名
     */
    @Length(min=2, max=50, message="{sys.manager.info.name.length}")
    private String name;

    /**
     * 性别（0-女，1-男）
     */
    private Integer sex;

    /**
     * 电话
     */
    @Pattern(regexp="^((\\+\\d+)?1[3458]\\d{9})|((\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8})$", message="{sys.manager.info.phone.pattern}")
    private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPortraitPic() {
		return portraitPic;
	}

	public void setPortraitPic(String portraitPic) {
		this.portraitPic = portraitPic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		this.phone = phone;
	}

}