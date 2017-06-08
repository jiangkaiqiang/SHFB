package com.shfb.rfid.manage.dao;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.entity.SysUser;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {

	SysUser findUser(@Param("user_name") String username, @Param("password") String password);

	SysUser findUserByName(@Param("username") String username);
	
	SysUser findUserById(@Param("id") int id);
	
	int insert(SysUser record);
	
	void updateUser(SysUser userEntity);
		
	Page<SysUser> findAllUser(@Param("audit")Integer audit, @Param("keyword")String keyword);
}
