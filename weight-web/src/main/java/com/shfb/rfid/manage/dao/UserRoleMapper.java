package com.shfb.rfid.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.entity.Project;
import com.shfb.rfid.manage.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer user_role_id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer user_role_id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    Page<UserRole> findAllUserRole(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("keyword")String keyword);
    
    List<UserRole> findAllUserRoleList();
}
