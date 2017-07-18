package com.shfb.rfid.manage.dao;

import java.util.List;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.entity.SysUser;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
	int deleteByPrimaryKey(@Param("user_id") int user_id);
	
	int deleteByProId(@Param("pro_id") Integer pro_id);
	
	SysUser findUser(@Param("user_name") String username, @Param("password") String password);

	SysUser findUserByName(@Param("user_name") String user_name);
	
	SysUser findUserById(@Param("user_id") int user_id);
	
	int insert(SysUser record);
	
	void updateUser(SysUser userEntity);
		
	Page<SysUser> findAllUser(@Param("status")Integer status, @Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("userProjectID")Integer userProjectID);
	
	List<SysUser> findUsersByRoleID(@Param("userRoleID")Integer userRoleID);
}
