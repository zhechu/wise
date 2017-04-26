package com.wise.core.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
    @NotEmpty(message="邮箱不能为空")
    @Email(message="邮箱格式有误")
    private String email;

    /**
     * 头像
     */
    private String portraitPic;

    /**
     * 姓名
     */
    @NotEmpty(message="姓名不能为空")
    @Length(min=2, message="姓名至少2个字符")
    private String name;

    /**
     * 性别（0-女，1-男）
     */
    private Integer sex;

    /**
     * 电话
     */
    @Pattern(regexp="^((\\+\\d+)?1[3458]\\d{9})|((\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8})$", message="电话格式有误")
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