package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.ProductComponentSize;

public interface ProductComponentSizeMapper {
    int deleteByPrimaryKey(Integer product_component_size_id);

    int insert(ProductComponentSize record);

    int insertSelective(ProductComponentSize record);

    ProductComponentSize selectByPrimaryKey(Integer product_component_size_id);

    int updateByPrimaryKeySelective(ProductComponentSize record);

    int updateByPrimaryKey(ProductComponentSize record);
    
    ProductComponentSize findByComponentId(Integer component_id);
}