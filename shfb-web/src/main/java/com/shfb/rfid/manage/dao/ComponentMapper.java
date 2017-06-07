package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.Component;

public interface ComponentMapper {
    int deleteByPrimaryKey(Integer component_id);

    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer component_id);

    int updateByPrimaryKeySelective(Component record);

    int updateByPrimaryKey(Component record);
}