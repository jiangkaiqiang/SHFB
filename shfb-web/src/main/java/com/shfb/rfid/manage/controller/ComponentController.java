package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.CityMapper;
import com.shfb.rfid.manage.dao.ComponentMapper;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.entity.Component;
@Controller
@RequestMapping(value = "/component")
public class ComponentController extends BaseController {
	@Autowired
	private ComponentMapper comonentDao;
	@Autowired
	private CityMapper cityDao;
	
	
	
	@RequestMapping(value = "/findComponentPage", method = RequestMethod.POST)
	@ResponseBody
	public Object findComponentPage(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="provinceid", required=false) Integer provinceid,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		Page<ComponentDto> components = comonentDao.findComponentPage();

		return new PageInfo<ComponentDto>(components);
		
	}
	
}
