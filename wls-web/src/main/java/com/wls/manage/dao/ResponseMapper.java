package com.wls.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wls.manage.entity.ResponseEntity;
/**
 * 回复mapper
 * @author jiangkaiqiang
 * @version 创建时间：2016-11-7 上午11:46:33 
 *
 */
public interface ResponseMapper {

    public void insertResponse(ResponseEntity responseEntity);

	List<ResponseEntity> findresponsesByResponserId(@Param("responserID") int responserID);
	
	List<ResponseEntity> findresponsesByResponseId(@Param("responseID") int responseID);
	
	int deleteByID(int ID);
    
}