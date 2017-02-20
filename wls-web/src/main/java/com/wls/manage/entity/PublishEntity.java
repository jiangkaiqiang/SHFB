package com.wls.manage.entity;

import java.math.BigInteger;
import java.util.Date;
/**
 * 发帖实体类
 * @author jiangkaiqiang
 * @version 创建时间：2016-10-24 下午8:04:07 
 *
 */
public class PublishEntity {
	private BigInteger id;
	
	private BigInteger publisher;//接收者id
	
	private String title;
	
	private String describe;
	
	private String pubcover;
	
	private Integer pubcategory;
	
    private Date pubtime;//发布时间
	
	private String content;
	
	private Integer schoolid;
	
	private String appendixs;
	
	private String pubvideo;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getPublisher() {
		return publisher;
	}

	public void setPublisher(BigInteger publisher) {
		this.publisher = publisher;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPubcategory() {
		return pubcategory;
	}

	public void setPubcategory(Integer pubcategory) {
		this.pubcategory = pubcategory;
	}

	public Integer getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Integer schoolid) {
		this.schoolid = schoolid;
	}

	public String getPubcover() {
		return pubcover;
	}

	public void setPubcover(String pubcover) {
		this.pubcover = pubcover;
	}

	public String getAppendixs() {
		return appendixs;
	}

	public void setAppendixs(String appendixs) {
		this.appendixs = appendixs;
	}

	public String getPubvideo() {
		return pubvideo;
	}

	public void setPubvideo(String pubvideo) {
		this.pubvideo = pubvideo;
	}

	
}
