package com.shfb.rfid.manage.dao;

import java.util.List;

import com.shfb.rfid.manage.entity.ComponentStatus;

public interface ComponentStatusMapper {
    int deleteByPrimaryKey(Integer component_status_id);

    int insert(ComponentStatus record);

    int insertSelective(ComponentStatus record);

    ComponentStatus selectByPrimaryKey(Integer component_status_id);

    int updateByPrimaryKeySelective(ComponentStatus record);

    int updateByPrimaryKey(ComponentStatus record);
    
    List<ComponentStatus> findStatus();
    
    List<String> findStatusName();
}