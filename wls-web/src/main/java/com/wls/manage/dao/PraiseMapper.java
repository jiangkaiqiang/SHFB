package com.wls.manage.dao;

import java.util.List;

import com.wls.manage.entity.PraiseEntity;
import org.apache.ibatis.annotations.Param;
/**
 * 点赞mapper
 * @author jiangkaiqiang
 * @version 创建时间：2016-11-7 上午11:46:33 
 *
 */
public interface PraiseMapper {

    public void insertPraise(PraiseEntity praiseEntity);

	List<PraiseEntity> findPraisesByPublishId(@Param("publishId") int publishId);
	
    
}