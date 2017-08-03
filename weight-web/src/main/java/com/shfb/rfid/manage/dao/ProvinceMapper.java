package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.ProvinceInfo;

import java.util.List;

/**
 * province list
 * @author jiangkaiqiang
 * @version 创建时间：2016-11-7 下午2:04:16 
 *
 */
public interface ProvinceMapper {

    List<ProvinceInfo> findProvinceList();

}
