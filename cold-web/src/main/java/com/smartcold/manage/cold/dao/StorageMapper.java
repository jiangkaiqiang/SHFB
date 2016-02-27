package com.smartcold.manage.cold.dao;

import org.apache.ibatis.annotations.Param;

import com.smartcold.manage.cold.entity.StorageEntity;

public interface StorageMapper {

	public StorageEntity findStorageById(@Param("id") int id);
	
	public StorageEntity findStorageByName(@Param("name") String name);
}
