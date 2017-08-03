package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.ProductSteelbarSize;
import com.shfb.rfid.manage.entity.ReceiptComponentSize;

public interface ReceiptComponentSizeMapper {
    int deleteByPrimaryKey(Integer receipt_component_size_id);

    int insert(ReceiptComponentSize record);

    int insertSelective(ReceiptComponentSize record);

    ReceiptComponentSize selectByPrimaryKey(Integer receipt_component_size_id);

    int updateByPrimaryKeySelective(ReceiptComponentSize record);

    int updateByPrimaryKey(ReceiptComponentSize record);
    
    ReceiptComponentSize findByComponentId(Integer component_id);
}