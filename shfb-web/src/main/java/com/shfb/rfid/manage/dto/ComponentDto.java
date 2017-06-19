package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.Component;

public class ComponentDto extends Component{
	
	private String component_status_name;
	
	public String getComponent_status_name() {
		return component_status_name;
	}

	public void setComponent_status_name(String component_status_name) {
		this.component_status_name = component_status_name;
	}
	
}