package com.shfb.rfid.manage.dao;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.entity.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer record_id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer record_id);

    int updateByPrimaryKeySelective(Record record);
    
    int addCarNumByRecordIdEntry(Record record);

    int updateByPrimaryKey(Record record);

	Page<Record> findAllRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	Page<Record> findAllErrorRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime);
}