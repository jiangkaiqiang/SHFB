package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.ProductModelSize;

public interface ProductModelSizeMapper {
    int deleteByPrimaryKey(Integer product_model_size_id);

    int insert(ProductModelSize record);

    int insertSelective(ProductModelSize record);

    ProductModelSize selectByPrimaryKey(Integer product_model_size_id);

    int updateByPrimaryKeySelective(ProductModelSize record);

    int updateByPrimaryKey(ProductModelSize record);
    
    ProductModelSize findByComponentId(Integer component_id);
}