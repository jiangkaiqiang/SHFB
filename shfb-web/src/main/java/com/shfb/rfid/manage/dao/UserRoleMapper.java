package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer user_role_id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer user_role_id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}