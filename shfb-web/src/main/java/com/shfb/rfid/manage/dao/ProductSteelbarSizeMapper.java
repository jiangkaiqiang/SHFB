package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.ProductSteelbarSize;

public interface ProductSteelbarSizeMapper {
    int deleteByPrimaryKey(Integer product_steelbar_size_id);

    int insert(ProductSteelbarSize record);

    int insertSelective(ProductSteelbarSize record);

    ProductSteelbarSize selectByPrimaryKey(Integer product_steelbar_size_id);

    int updateByPrimaryKeySelective(ProductSteelbarSize record);

    int updateByPrimaryKey(ProductSteelbarSize record);
    
    ProductSteelbarSize findByComponentId(Integer component_id);
}