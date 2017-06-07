package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.ProductEmbeddedParts;

public interface ProductEmbeddedPartsMapper {
    int deleteByPrimaryKey(Integer product_embedded_parts_id);

    int insert(ProductEmbeddedParts record);

    int insertSelective(ProductEmbeddedParts record);

    ProductEmbeddedParts selectByPrimaryKey(Integer product_embedded_parts_id);

    int updateByPrimaryKeySelective(ProductEmbeddedParts record);

    int updateByPrimaryKey(ProductEmbeddedParts record);
}