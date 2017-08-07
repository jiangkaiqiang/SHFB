package com.shfb.rfid.manage.dao;

import java.util.List;

import com.shfb.rfid.manage.entity.CompProgress;

public interface CompProgressMapper {
  
	List<CompProgress> selectByPrimaryKey(Integer component_id);

	int insertSelective(CompProgress compProgress);
	
	List<CompProgress> queryProgress(Integer component_id, String component_status_name);
}
