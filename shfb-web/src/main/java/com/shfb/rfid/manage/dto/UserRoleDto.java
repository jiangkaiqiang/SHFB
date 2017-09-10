package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.UserRole;

public class UserRoleDto {
	private UserRole userRole;
	
	private Integer userNum; //该角色下用户的数量
	
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
	
    private boolean pcManage;//是否构件厂
	
	private boolean bindManage;//解绑
	
	private boolean compReadOnlyManage;//构件只读权限
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
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

	public boolean isPcManage() {
		return pcManage;
	}

	public void setPcManage(boolean pcManage) {
		this.pcManage = pcManage;
	}

	public boolean isBindManage() {
		return bindManage;
	}

	public void setBindManage(boolean bindManage) {
		this.bindManage = bindManage;
	}

	public boolean isCompReadOnlyManage() {
		return compReadOnlyManage;
	}

	public void setCompReadOnlyManage(boolean compReadOnlyManage) {
		this.compReadOnlyManage = compReadOnlyManage;
	}
}
