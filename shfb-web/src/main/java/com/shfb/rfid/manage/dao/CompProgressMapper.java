package com.shfb.rfid.manage.dao;

import java.util.List;

import com.shfb.rfid.manage.entity.CompProgress;

public interface CompProgressMapper {
  
	List<CompProgress> selectByPrimaryKey(Integer component_id);

}
