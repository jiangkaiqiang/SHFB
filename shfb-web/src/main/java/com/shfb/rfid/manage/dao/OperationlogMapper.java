package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.Operationlog;

public interface OperationlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Operationlog record);

    int insertSelective(Operationlog record);

    Operationlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Operationlog record);

    int updateByPrimaryKey(Operationlog record);
}