package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.SysUser;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
	int deleteByPrimaryKey(@Param("user_id") int user_id);
	
	SysUser findUser(@Param("user_name") String username, @Param("password") String password);

	SysUser findUserByName(@Param("user_name") String user_name);
	
	SysUser findUserById(@Param("user_id") int user_id);
	
	int insert(SysUser record);
	
	void updateUser(SysUser userEntity);
}
