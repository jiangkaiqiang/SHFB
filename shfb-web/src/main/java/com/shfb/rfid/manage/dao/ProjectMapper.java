package com.shfb.rfid.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.entity.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer pro_id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer pro_id);
    
    Project findProjectByName(String pro_name);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    Page<Project> findProjectsByUserID(@Param("userProjectID")Integer userProjectID);
    
    Page<Project> findAllProject(@Param("pr_id")Integer pr_id, @Param("keyword")String keyword,@Param("userProjectID")Integer userProjectID);
    
    List<Project> findAllProjectList();
    
    List<Map<String, Object>> findProjectNames(@Param("userProjectID")Integer userProjectID);
}