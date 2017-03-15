package com.wls.manage.dao;

import com.github.pagehelper.Page;
import com.wls.manage.entity.UserEntity;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

	UserEntity findUser(@Param("username") String username, @Param("password") String password);

	UserEntity findUserByName(@Param("username") String username);

	UserEntity findUserByTelephone(@Param("telephone") String telephone);
	
	UserEntity findUserById(@Param("id") int id);

	void insertUser(UserEntity userEntity);
	
	void updateUser(UserEntity userEntity);
	
	void updateUserPwd( @Param("password")String password, @Param("telephone")String telephone);
	
	Page<UserEntity> findAllUser(@Param("audit")Integer audit, @Param("keyword")String keyword,@Param("suproleid")int suproleid,
			@Param("provinceid")Integer provinceid,@Param("cityid")Integer cityid,@Param("schoolid")Integer schoolid
			,@Param("userid")Integer userid);
	
	public int existenceUserName(@Param("username")String username);
    
	void deleteUser(@Param("id") int id);
	
	Page<UserEntity> findAllUserForBg(@Param("audit")Integer audit, @Param("keyword")String keyword);
}
