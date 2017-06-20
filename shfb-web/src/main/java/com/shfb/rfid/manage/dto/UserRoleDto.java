package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.UserRole;

public class UserRoleDto {
	private UserRole userRole;
	
	private Integer userNum; //该角色下用户的数量

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

	
}
