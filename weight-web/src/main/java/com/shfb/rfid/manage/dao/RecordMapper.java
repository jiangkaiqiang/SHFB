package com.shfb.rfid.manage.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.dto.CarNumDto;
import com.shfb.rfid.manage.entity.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer record_id);

    int insert(Record record);

    Record selectByPrimaryKey(Integer record_id);

    int updateByPrimaryKeySelective(Record record);
    
    int addCarNumByRecordIdEntry(Record record);
    
	Page<Record> findAllRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime
			,@Param("startEntryTime")String startEntryTime, @Param("endEntryTime")String endEntryTime,@Param("material")String material,
			@Param("companyName")String companyName);
	
	Page<Record> findAllErrorEntryRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime);

	Page<Record> findAllErrorLeaveRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	Page<Record> findAllErrorTimeRecords(@Param("keyword")String keyword,@Param("startTime")String startTime, @Param("endTime")String endTime);
	
	Record findMergeRecord(@Param("carNum")String carNum,@Param("leaveTime")String leaveTime);
	
	Record findFirstRecord();
	
	Record findFirstLeaveRecord();

	List<CarNumDto> numEntryStatistics();
	
	List<CarNumDto> numLeaveStatistics();
	
	Record selectEntryRecord(String car_num);
	
	Record selectCarNum(String car_num);
	
	List<CarNumDto> weightEntryStatistics();
	
	List<CarNumDto> weightLeaveStatistics();
}