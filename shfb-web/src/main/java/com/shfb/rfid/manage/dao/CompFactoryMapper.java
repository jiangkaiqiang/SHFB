package com.shfb.rfid.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.entity.CompFactory;

public interface CompFactoryMapper {
    int deleteByPrimaryKey(Integer comp_factory_id);

    int insert(CompFactory record);

    int insertSelective(CompFactory record);

    CompFactory selectByPrimaryKey(Integer comp_factory_id);

    int updateByPrimaryKeySelective(CompFactory record);

    int updateByPrimaryKey(CompFactory record);
    
    Page<CompFactory> findAllCompFactory(@Param("pr_id")Integer pr_id, @Param("keyword")String keyword,@Param("userCompFactoryID")Integer userCompFactoryID);
    
    List<CompFactory> findCompFactorys();
    
}