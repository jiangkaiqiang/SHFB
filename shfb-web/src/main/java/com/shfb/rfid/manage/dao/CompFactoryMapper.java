package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.CompFactory;

public interface CompFactoryMapper {
    int deleteByPrimaryKey(Integer comp_factory_id);

    int insert(CompFactory record);

    int insertSelective(CompFactory record);

    CompFactory selectByPrimaryKey(Integer comp_factory_id);

    int updateByPrimaryKeySelective(CompFactory record);

    int updateByPrimaryKey(CompFactory record);
}