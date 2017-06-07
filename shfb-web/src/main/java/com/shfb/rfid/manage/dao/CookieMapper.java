package com.shfb.rfid.manage.dao;

import org.apache.ibatis.annotations.Param;

import com.shfb.rfid.manage.entity.Cookies;

public interface CookieMapper {

	public void insertCookie(Cookies cookieEntity);
	
	public void deleteCookie(@Param("cookie") String cookie);
	
	public Cookies findEffectiveCookie(@Param("cookie") String cookie);
}
