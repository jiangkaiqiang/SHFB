package com.shfb.rfid.manage.dao;

import org.apache.ibatis.annotations.Param;
import com.shfb.rfid.manage.entity.OperationLog;

import com.github.pagehelper.Page;
/**
 * 操作日志表
 * @author jiangkaiqiang
 * @version 创建时间：2017-6-2 下午2:50:17 
 *
 */
public interface OperationLogMapper {
    
	void insertOperationLog(OperationLog operationLog);
	
	Page<OperationLog> findOperationLogList(@Param("adminName")String adminName);
}