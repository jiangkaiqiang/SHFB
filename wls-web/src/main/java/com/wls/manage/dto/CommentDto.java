package com.wls.manage.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.wls.manage.entity.ResponseEntity;

public class CommentDto {
	private BigInteger id;

	private BigInteger commenterid;//评论者id
	
	private BigInteger commentid;//评论对象的id
	
	private Integer flag; //区分评论的对象 0:information 1:publish
	
	private String content;
	
	private Date commenttime;
	
	private String usernickname;
	
	private String useravatar;
	
	private Integer responsenum;
	
	private List<ResponseDto> responseDtos;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getCommenterid() {
		return commenterid;
	}

	public void setCommenterid(BigInteger commenterid) {
		this.commenterid = commenterid;
	}

	public BigInteger getCommentid() {
		return commentid;
	}

	public void setCommentid(BigInteger commentid) {
		this.commentid = commentid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}

	public List<ResponseDto> getResponseDtos() {
		return responseDtos;
	}

	public void setResponseDtos(List<ResponseDto> responseDtos) {
		this.responseDtos = responseDtos;
	}

	public String getUsernickname() {
		return usernickname;
	}

	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}

	public String getUseravatar() {
		return useravatar;
	}

	public void setUseravatar(String useravatar) {
		this.useravatar = useravatar;
	}

	public Integer getResponsenum() {
		return responsenum;
	}

	public void setResponsenum(Integer responsenum) {
		this.responsenum = responsenum;
	}
	
}
