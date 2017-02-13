package com.wls.manage.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.wls.manage.entity.CommentEntity;

public class PublishDto {
    private BigInteger id;
	
	private BigInteger publisherid;//接收者id
	
	private String title;
	
	private String describe;
	
	private Integer pubcategory;
	
	private String pubcategoryname;
	
    private Date pubtime;//发布时间
	
	private String content;
	
	private Integer schoolid;
	
	private Integer commentnum;
	
	private String publishername;
	
	private String publisheravatar;
	
	private List<CommentDto> commentDtos;
	
	private Integer praisenum;
	
	private Integer praiseflag;
	
	private String pubcover;//上传的全部图片，字符串以；分隔
	
	private List<String> pubCovers;//显示在列表的封面图片
	
	private List<String> picFiles;//显示在作品内部的全部图片

	private List<AppendixDto> appendixDtos;
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getPublisherid() {
		return publisherid;
	}

	public void setPublisherid(BigInteger publisherid) {
		this.publisherid = publisherid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getPubcategory() {
		return pubcategory;
	}

	public void setPubcategory(Integer pubcategory) {
		this.pubcategory = pubcategory;
	}

	public String getPubcategoryname() {
		return pubcategoryname;
	}

	public void setPubcategoryname(String pubcategoryname) {
		this.pubcategoryname = pubcategoryname;
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

	public Integer getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Integer schoolid) {
		this.schoolid = schoolid;
	}

	public Integer getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

	public String getPublisheravatar() {
		return publisheravatar;
	}

	public void setPublisheravatar(String publisheravatar) {
		this.publisheravatar = publisheravatar;
	}

	

	public List<CommentDto> getCommentDtos() {
		return commentDtos;
	}

	public void setCommentDtos(List<CommentDto> commentDtos) {
		this.commentDtos = commentDtos;
	}

	public Integer getPraisenum() {
		return praisenum;
	}

	public void setPraisenum(Integer praisenum) {
		this.praisenum = praisenum;
	}

	public Integer getPraiseflag() {
		return praiseflag;
	}

	public void setPraiseflag(Integer praiseflag) {
		this.praiseflag = praiseflag;
	}

	public String getPubcover() {
		return pubcover;
	}

	public void setPubcover(String pubcover) {
		this.pubcover = pubcover;
	}

	public List<String> getPubCovers() {
		return pubCovers;
	}

	public void setPubCovers(List<String> pubCovers) {
		this.pubCovers = pubCovers;
	}

	public List<String> getPicFiles() {
		return picFiles;
	}

	public void setPicFiles(List<String> picFiles) {
		this.picFiles = picFiles;
	}

	public List<AppendixDto> getAppendixDtos() {
		return appendixDtos;
	}

	public void setAppendixDtos(List<AppendixDto> appendixDtos) {
		this.appendixDtos = appendixDtos;
	}
	
}
