package com.shfb.rfid.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.entity.Component;

public interface ComponentMapper {
    int deleteByPrimaryKey(Integer component_id);

    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer component_id);

    int updateByPrimaryKeySelective(Component record);

    int updateByPrimaryKey(Component record);
    
    Page<ComponentDto> findAllComponent(Component record);
    
    List<Map<String, Object>> findSingle(@Param("pro_id")Integer pro_id);
    
    List<Map<String, Object>> findSingleBysel(@Param("pro_id")Integer pro_id);
        
    List<Map<String, Object>> findFloor(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name);
    
    List<Component> findComponentBysel(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name, @Param("floor")String floor);
    
    Page<ComponentDto> findComponentPage(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name, @Param("floor")String floor, @Param("component_type")String component_type, @Param("component_status_id")Integer component_status_id);
    
    ComponentDto findComponentInfoById(Integer component_id);
    
    List<Component> findComponentTypes();
    
   
    
}