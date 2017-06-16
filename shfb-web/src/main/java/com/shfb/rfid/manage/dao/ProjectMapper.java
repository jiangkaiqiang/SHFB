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

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    Page<Project> findAllProject(@Param("pr_id")Integer pr_id, @Param("keyword")String keyword);
    List<Map<String, Object>> findProjectNames();
}