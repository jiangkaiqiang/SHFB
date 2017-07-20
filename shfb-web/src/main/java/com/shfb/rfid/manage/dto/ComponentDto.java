package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.Component;

public class ComponentDto extends Component{
	
	private String component_status_name;
	private String pro_name;
	private String comp_factory_name;
	private String user_name;
	private String contacts_name;
	private String contacts_tel;
	
	
	
	public String getContacts_name() {
		return contacts_name;
	}

	public void setContacts_name(String contacts_name) {
		this.contacts_name = contacts_name;
	}

	public String getContacts_tel() {
		return contacts_tel;
	}

	public void setContacts_tel(String contacts_tel) {
		this.contacts_tel = contacts_tel;
	}

	public String getComponent_status_name() {
		return component_status_name;
	}

	public void setComponent_status_name(String component_status_name) {
		this.component_status_name = component_status_name;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getComp_factory_name() {
		return comp_factory_name;
	}

	public void setComp_factory_name(String comp_factory_name) {
		this.comp_factory_name = comp_factory_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}