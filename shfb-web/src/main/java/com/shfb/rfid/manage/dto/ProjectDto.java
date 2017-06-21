package com.shfb.rfid.manage.dto;

import com.shfb.rfid.manage.entity.Project;

public class ProjectDto {
	private Project project;
	
	private String provinceName;
	
	private String cityName;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
