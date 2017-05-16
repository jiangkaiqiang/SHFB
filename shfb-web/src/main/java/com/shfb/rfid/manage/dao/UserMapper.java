package com.shfb.rfid.manage.dao;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.entity.UserEntity;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

	UserEntity findUser(@Param("username") String username, @Param("password") String password);

	UserEntity findUserByName(@Param("username") String username);
	
	UserEntity findUserById(@Param("id") int id);
	
	void updateUser(UserEntity userEntity);
		
	Page<UserEntity> findAllUserForBg(@Param("audit")Integer audit, @Param("keyword")String keyword);
}
