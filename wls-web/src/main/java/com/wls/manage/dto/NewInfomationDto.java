package com.wls.manage.dto;
public class NewInfomationDto {
	private String content;
	private String pic;
	private String time;
	private String title;
	private String href;
	@Override
	public String toString() {
		return "NewInfomationDto [content=" + content + ", pic=" + pic
				+ ", time=" + time + ", title=" + title + ", href=" + href
				+ "]";
	}
	public NewInfomationDto() {
		super();
	}
	public NewInfomationDto(String content, String pic, String time,
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
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