package com.shfb.rfid.manage.entity;

public class CompProgress {
	private Integer comp_progress_id;
	
	private String operation_date;
	
	private String operation_user;
	
	private String component_status_name;
	
	private Integer component_id;
	
	public Integer getComponent_id() {
		return component_id;
	}
	public void setComponent_id(Integer component_id) {
		this.component_id = component_id;
	}
	public Integer getComp_progress_id() {
		return comp_progress_id;
	}
	public void setComp_progress_id(Integer comp_progress_id) {
		this.comp_progress_id = comp_progress_id;
	}
	public String getOperation_date() {
		return operation_date;
	}
	public void setOperation_date(String operation_date) {
		this.operation_date = operation_date;
	}
	public String getOperation_user() {
		return operation_user;
	}
	public void setOperation_user(String operation_user) {
		this.operation_user = operation_user;
	}
	public String getComponent_status_name() {
		return component_status_name;
	}
	public void setComponent_status_name(String component_status_name) {
		this.component_status_name = component_status_name;
	}
	
}
