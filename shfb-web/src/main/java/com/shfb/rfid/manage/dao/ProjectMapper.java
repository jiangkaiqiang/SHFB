package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer pro_id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer pro_id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}