package com.wls.manage.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wls.manage.dao.ResponseMapper;
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
}
