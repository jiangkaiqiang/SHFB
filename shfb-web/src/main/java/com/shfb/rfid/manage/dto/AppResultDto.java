package com.shfb.rfid.manage.dto;

public class AppResultDto {
	private boolean success;
	private String message;
	private String token;
	private String extra;
	
	public AppResultDto(boolean success, String message, String token, String extra) {
		super();
		this.success = success;
		this.message = message;
		this.token = token;
		this.extra = extra;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
}
