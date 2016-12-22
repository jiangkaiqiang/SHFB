package com.wls.manage.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wls.manage.dao.CommentMapper;
import com.wls.manage.entity.CommentEntity;
import com.wls.manage.util.ResponseData;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    
    @RequestMapping(value = "/insertComment", method = RequestMethod.GET)
    @ResponseBody
    public Object findProvinceList(Integer userID,String commentDetail,Integer commentid,Integer flag) {
    	CommentEntity comment = new CommentEntity();
    	comment.setCommenterid(BigInteger.valueOf(userID));
    	comment.setContent(commentDetail);
    	comment.setFlag(flag);
    	comment.setCommentid(BigInteger.valueOf(commentid));
    	commentMapper.insertComment(comment);
    	return ResponseData.newSuccess("发布成功");
    }
}
