package com.wls.manage.dao;

import com.wls.manage.entity.AdminEntity;

import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

	AdminEntity findAdmin(@Param("adminname") String adminname, @Param("adminpwd") String adminpwd);
	AdminEntity findAdminByName(@Param("adminname") String adminname);
}
