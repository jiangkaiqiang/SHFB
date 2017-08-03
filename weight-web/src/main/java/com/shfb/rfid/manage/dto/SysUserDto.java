package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.SysUser;

public class SysUserDto {
	
	private SysUser sysUser;
	
	private String projectName;
	
	private String userRoleName;
	
	private String compFactoryName;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getCompFactoryName() {
		return compFactoryName;
	}

	public void setCompFactoryName(String compFactoryName) {
		this.compFactoryName = compFactoryName;
	}
	
	
}
