package com.shfb.rfid.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.dto.ModelDataDto;
import com.shfb.rfid.manage.entity.Component;

public interface ComponentMapper {
    int deleteByPrimaryKey(Integer component_id);
    
    int deleteByProId(Integer pro_id);

    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer component_id);
    
    Component selectByCom(Component record);

    int updateByPrimaryKeySelective(Component record);
    
    int updateByComponentNum(Component record);

    int updateByPrimaryKey(Component record);
    
    int updateComStatus(@Param("component_id") Integer component_id, 
    		@Param("component_status_id")Integer component_status_id, 
    		@Param("oldcomponent_status_id")Integer oldcomponent_status_id);
    
    Page<ComponentDto> findAllComponent(Component record);
    
    List<Map<String, Object>> findSingle(@Param("pro_id")Integer pro_id);
    
    List<Map<String, Object>> findSingleBysel(@Param("pro_id")Integer pro_id);
        
    List<Map<String, Object>> findFloor(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name);
    
    List<Component> findComponentByProID(@Param("pro_id")Integer pro_id);
    
    List<Component> findComponentBysel(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name, @Param("floor")String floor, @Param("comp_factory_id")Integer comp_factory_id);
    
    List<Component>  findComponentByselForClient(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name, 
    		@Param("floor")String floor,@Param("type")String type,@Param("compName")String compName);
    
    List<Component>  findComponentByselForClientUnBind(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name, 
    		@Param("floor")String floor,@Param("type")String type,@Param("compName")String compName);
   
    Page<ComponentDto> findComponentPage(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name, 
    		@Param("floor")String floor, @Param("component_type")String component_type, 
    		@Param("component_status_id")Integer component_status_id,
    		@Param("userProjectID")Integer userProjectID,@Param("userCompFactoryID")Integer userCompFactoryID);
    
    Page<ComponentDto> findComponentProductPage(@Param("pro_id")Integer pro_id, @Param("single_name")String single_name, 
    		@Param("floor")String floor, @Param("component_type")String component_type, 
    		@Param("component_status_id")Integer component_status_id,
    		@Param("userProjectID")Integer userProjectID,@Param("userCompFactoryID")Integer userCompFactoryID,@Param("order_num")String order_num);
    
    ComponentDto findComponentInfoById(Integer component_id);
    
    List<Component> findComponentTypes();
    
    List<ComponentDto> exportComponents(String[] componentIds);
    
    List<ComponentDto> exportComponentProduct(@Param("order_num")String order_num);
    
    int placeOrder(@Param("pro_id") Integer pro_id,
			@Param("single_name") String single_name,
			@Param("floor") String floor,
			@Param("plan_end_date") String plan_end_date,
			@Param("comp_factory_id") Integer comp_factory_id,
			@Param("order_user_id") String order_user_id,
			@Param("order_num") String order_num
    		);  
    int expeditGood(@Param("component_id") Integer component_id, @Param("expedit_date") String expedit_date);
    
    int addProductPlan(@Param("component_id") Integer component_id,
			@Param("product_plan_begin_date") String product_plan_begin_date,
			@Param("product_plan_end_date") String product_plan_end_date,
			@Param("product_explain") String product_explain);
    
    List<Integer> findComponentByOrderNum(@Param("order_num") String order_num);
    
    ComponentDto getComponentInfo(String component_num);
    
    List<Component> findComponentByCardNum(@Param("cardNum")String cardNum);

    List<Component> getDrawings(@Param("pro_id")Integer pro_id);
    
    List<Component> getComponentBycompNum(@Param("component_num")String component_num);
    
    int unbundling(@Param("pro_id") Integer pro_id, 
    		@Param("component_type")String component_type);
    
    Integer[] getComponentIds(@Param("pro_id") Integer pro_id,
			@Param("single_name") String single_name,
			@Param("floor") String floor,
			@Param("comp_factory_id") Integer comp_factory_id);
    
    int updateByExcelComponentNum(@Param("component_num")String component_num,@Param("component_size") String component_size,
			@Param("concrete_strength") String concrete_strength,@Param("weight") String weight,@Param("drawing") String drawing);
    
    List<ModelDataDto> getModelData(
			@Param("single") String single,
			@Param("floor") String floor,
			@Param("state") String state,
			@Param("project") String project,
			@Param("component_num") String component_num
			);
    
    List<Component> queryCompIds(@Param("single_name") String single_name,
			@Param("floor") String floor,
			@Param("pro_id") Integer pro_id);
    
}