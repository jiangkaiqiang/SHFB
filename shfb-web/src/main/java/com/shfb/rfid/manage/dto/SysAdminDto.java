package com.shfb.rfid.manage.dto;

import java.util.Date;

public class SysAdminDto {
    private Integer user_id;
	
	private String user_name;
	
	private Integer user_role_id;
	
	private Date login_time;//上次登陆时间
	
	private String password;
	
	private String user_tel;
	
	private String company;
	
	private Integer pro_id;//关联project主键
	
	private Integer valid_status; //是否有效，1有效，2无效
	
	private Date create_time; //创建时间
	
    private boolean overView;
	
	private boolean compManage; 
	
    private boolean processManage;
	
	private boolean projectManage; 
	
    private boolean compFactoryManage;
	
	private boolean userManage; 
	
    private boolean roleManage;
	
	private boolean logManage; 
	
    private boolean productManage;
	
	private boolean personalManage;

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

	public Integer getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(Integer user_role_id) {
		this.user_role_id = user_role_id;
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

	public Integer getPro_id() {
		return pro_id;
	}

	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
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

	public boolean isOverView() {
		return overView;
	}

	public void setOverView(boolean overView) {
		this.overView = overView;
	}

	public boolean isCompManage() {
		return compManage;
	}

	public void setCompManage(boolean compManage) {
		this.compManage = compManage;
	}

	public boolean isProcessManage() {
		return processManage;
	}

	public void setProcessManage(boolean processManage) {
		this.processManage = processManage;
	}

	public boolean isProjectManage() {
		return projectManage;
	}

	public void setProjectManage(boolean projectManage) {
		this.projectManage = projectManage;
	}

	public boolean isCompFactoryManage() {
		return compFactoryManage;
	}

	public void setCompFactoryManage(boolean compFactoryManage) {
		this.compFactoryManage = compFactoryManage;
	}

	public boolean isUserManage() {
		return userManage;
	}

	public void setUserManage(boolean userManage) {
		this.userManage = userManage;
	}

	public boolean isRoleManage() {
		return roleManage;
	}

	public void setRoleManage(boolean roleManage) {
		this.roleManage = roleManage;
	}

	public boolean isLogManage() {
		return logManage;
	}

	public void setLogManage(boolean logManage) {
		this.logManage = logManage;
	}

	public boolean isProductManage() {
		return productManage;
	}

	public void setProductManage(boolean productManage) {
		this.productManage = productManage;
	}

	public boolean isPersonalManage() {
		return personalManage;
	}

	public void setPersonalManage(boolean personalManage) {
		this.personalManage = personalManage;
	} 
	
	
}
