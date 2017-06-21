package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.CompFactory;


public class CompFactoryDto {
	private CompFactory compfactory;
	
	private String provinceName;
	
	private String cityName;

	

	public CompFactory getCompfactory() {
		return compfactory;
	}

	public void setCompfactory(CompFactory compfactory) {
		this.compfactory = compfactory;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
}
