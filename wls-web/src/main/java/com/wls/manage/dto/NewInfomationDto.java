package com.wls.manage.dto;

import java.util.Date;

public class NewInfomationDto {
	private String content;
	private String pic;
	private Date time;
	private String title;
	private String href;
	public NewInfomationDto(String content, String pic, String title,
			String href, String source) {
		super();
		this.content = content;
		this.pic = pic;
		this.title = title;
		this.href = href;
		this.source = source;
	}
	private String source;
	public NewInfomationDto(String content, String pic, Date time,
			String title, String href, String source) {
		super();
		this.content = content;
		this.pic = pic;
		this.time = time;
		this.title = title;
		this.href = href;
		this.source = source;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public NewInfomationDto() {
		super();
	}
	public NewInfomationDto(String content, String pic, Date time,
			String title, String href) {
		super();
		this.content = content;
		this.pic = pic;
		this.time = time;
		this.title = title;
		this.href = href;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
}