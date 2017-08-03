package com.shfb.rfid.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.dto.ComponentOrderDto;
import com.shfb.rfid.manage.entity.ComponentOrder;

public interface ComponentOrderMapper {
    
    Page<ComponentOrderDto> findComponentOrderPage(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name
    		, @Param("floor")String floor,@Param("userProjectID")Integer userProjectID,@Param("userCompFactoryID")Integer userCompFactoryID);
    
    int updateByPrimaryKeySelective(ComponentOrder componentOrder);
    
    ComponentOrderDto selectByPrimaryKey(Integer order_id);
    
    int insertSelective(@Param("pro_id") Integer pro_id,
			@Param("single_name") String single_name,
			@Param("floor") String floor,
			@Param("comp_factory_id") Integer comp_factory_id,
			@Param("order_username") String order_username,
			@Param("order_num") String order_num);
    
    List<ComponentOrderDto> exportComponentOrders(String[] order_ids);
    
    List<ComponentOrder> findComponentOrders(@Param("userCompFactoryID")Integer userCompFactoryID);
    
}