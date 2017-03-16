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
import com.wls.manage.dao.ResponseMapper;
import com.wls.manage.dto.BaseDto;
import com.wls.manage.entity.ResponseEntity;
import com.wls.manage.util.ResponseData;

@Controller
@RequestMapping(value = "/response")
public class ResponseController {

    @Autowired
    private ResponseMapper responseMapper;
    
    @RequestMapping(value = "/insertResponse")
    @ResponseBody
    public Object insertResponse(Integer userID,String responseDetail,Integer responseid,Integer flag) {
    	ResponseEntity responseEntity = new ResponseEntity();
    	responseEntity.setContent(responseDetail);
    	responseEntity.setFlag(flag);
    	responseEntity.setResponseid(BigInteger.valueOf(responseid));
    	responseEntity.setResponserid(BigInteger.valueOf(userID));
    	responseMapper.insertResponse(responseEntity);
    	return ResponseData.newSuccess("回复成功");
    }
    @RequestMapping(value = "/findResponseList", method = RequestMethod.POST)
   	@ResponseBody
   	public Object findResponseList(@RequestParam(value="pageNum",required=false) Integer pageNum,
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
   		return new PageInfo<ResponseEntity>(responseMapper.findAllResponse(keyword));
   		
   	}
       @RequestMapping(value = "/deleteResponse", method = RequestMethod.GET)
   	@ResponseBody
   	public Object deleteResponse(int responseID) {
   		 responseMapper.deleteByID(responseID);
   		 return new BaseDto(0);
   	}
   	
   	@RequestMapping(value = "/deleteByResponseIDs")
   	@ResponseBody
   	public Object deleteByResponseIDs(Integer[] responseIDs) {
   		for(Integer responseID:responseIDs){
   			responseMapper.deleteByID(responseID);
   		}
   		return new BaseDto(0);
   	}
}
