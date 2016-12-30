package com.wls.manage.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.wls.manage.entity.CommentEntity;

public class InformationDto {
	
	private BigInteger id;
	
	private String title;
	
	private String source;
	
	private String content;
	
	private Date time;
	
	private Integer commentnum;
	
	private Integer infocategory;
	
	private String infocategoryName;
	
	private List<String> coverpiclist;
	
	private Integer coverpicnum;
	
	private List<CommentDto> commentDtos;
	

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}

	public List<String> getCoverpiclist() {
		return coverpiclist;
	}

	public void setCoverpiclist(List<String> coverpiclist) {
		this.coverpiclist = coverpiclist;
	}

	public Integer getInfocategory() {
		return infocategory;
	}

	public void setInfocategory(Integer infocategory) {
		this.infocategory = infocategory;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCoverpicnum() {
		return coverpicnum;
	}

	public void setCoverpicnum(Integer coverpicnum) {
		this.coverpicnum = coverpicnum;
	}


	public List<CommentDto> getCommentDtos() {
		return commentDtos;
	}

	public void setCommentDtos(List<CommentDto> commentDtos) {
		this.commentDtos = commentDtos;
	}

	public String getInfocategoryName() {
		return infocategoryName;
	}

	public void setInfocategoryName(String infocategoryName) {
		this.infocategoryName = infocategoryName;
	}

	
}
