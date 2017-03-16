package com.wls.manage.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wls.manage.dao.CommentMapper;
import com.wls.manage.dto.BaseDto;
import com.wls.manage.entity.CommentEntity;
import com.wls.manage.util.ResponseData;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    
    @RequestMapping(value = "/insertComment")
    @ResponseBody
    public Object insertComment(Integer userID,String commentDetail,Integer commentid,Integer flag) {
    	CommentEntity comment = new CommentEntity();
    	comment.setCommenterid(BigInteger.valueOf(userID));
    	comment.setContent(commentDetail);
    	comment.setFlag(flag);
    	comment.setCommentid(BigInteger.valueOf(commentid));
    	commentMapper.insertComment(comment);
    	return ResponseData.newSuccess("评论成功");
    }
    @RequestMapping(value = "/findCommentList", method = RequestMethod.POST)
	@ResponseBody
	public Object findCommentList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		return new PageInfo<CommentEntity>(commentMapper.findAllComment(keyword));
		
	}
    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteComment(int commentID) {
		 commentMapper.deleteByCommentID(commentID);
		 return new BaseDto(0);
	}
	
	@RequestMapping(value = "/deleteByCommentIDs")
	@ResponseBody
	public Object deleteByCommentIDs(Integer[] commentIDs) {
		for(Integer commentID:commentIDs){
			commentMapper.deleteByCommentID(commentID);
		}
		return new BaseDto(0);
	}
}
