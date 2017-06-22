package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.ComponentOrder;

public class ComponentOrderDto extends ComponentOrder{
	private String pro_name;

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	
}
