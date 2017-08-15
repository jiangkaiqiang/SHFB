package com.shfb.rfid.manage.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.dto.CarNumDto;
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
	
	Page<Record> findAllErrorEntryRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime);

	Page<Record> findAllErrorLeaveRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	Record findMergeRecord(@Param("carNum")String carNum,@Param("leaveTime")String leaveTime);
	
	Record findFirstRecord();

	List<CarNumDto> numEntryStatistics();
	
	List<CarNumDto> numLeaveStatistics();

}