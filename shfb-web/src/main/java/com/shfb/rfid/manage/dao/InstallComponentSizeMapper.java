package com.shfb.rfid.manage.dao;

import com.shfb.rfid.manage.entity.InstallComponentSize;

public interface InstallComponentSizeMapper {
    int deleteByPrimaryKey(Integer install_component_size_id);

    int insert(InstallComponentSize record);

    int insertSelective(InstallComponentSize record);

    InstallComponentSize selectByPrimaryKey(Integer install_component_size_id);

    int updateByPrimaryKeySelective(InstallComponentSize record);

    int updateByPrimaryKey(InstallComponentSize record);
}