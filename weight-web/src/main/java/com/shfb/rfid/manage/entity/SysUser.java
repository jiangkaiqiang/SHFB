package com.shfb.rfid.manage.entity;

import java.util.Date;
/**
 * 用户实体类
 * @author jiangkaiqiang
 * @version 创建时间：2016-10-21 下午1:52:37 
 *
 */
public class SysUser {
	private Integer user_id;
	
	private String user_name;
	
	private Date login_time;//上次登陆时间
	
	private String password;
	
	private String user_tel;
	
	private String company;
	
	private Integer valid_status; //是否有效，1有效，2无效
	
	private Date create_time; //创建时间
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getValid_status() {
		return valid_status;
	}

	public void setValid_status(Integer valid_status) {
		this.valid_status = valid_status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
