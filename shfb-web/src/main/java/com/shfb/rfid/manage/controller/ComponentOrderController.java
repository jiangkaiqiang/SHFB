package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
import com.shfb.rfid.manage.dao.ComponentOrderMapper;
import com.shfb.rfid.manage.dao.ComponentStatusMapper;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.dto.ComponentOrderDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.ComponentOrder;
import com.shfb.rfid.manage.entity.ComponentStatus;
@Controller
@RequestMapping(value = "/componentOrder")
public class ComponentOrderController extends BaseController {
	@Autowired
	private ComponentOrderMapper componentOrderDao;

	
	@RequestMapping(value = "/findComponentOrderPage", method = RequestMethod.POST)
	@ResponseBody
	public Object findComponentOrderPage(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="pro_id", required=false) Integer pro_id,
			@RequestParam(value="single_name", required=false) String single_name,
			@RequestParam(value="floor", required=false) String floor
			) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 10:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		single_name = "".equals(single_name)? null:single_name;
		floor = "".equals(floor)? null:floor;
		Page<ComponentOrderDto> componentOrders = componentOrderDao.findComponentOrderPage(pro_id, single_name, floor);

		return new PageInfo<ComponentOrderDto>(componentOrders);
		
	}
	
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto updateByPrimaryKeySelective(ComponentOrder componentOrder)  {
		int res = componentOrderDao.updateByPrimaryKeySelective(componentOrder);

		if( res==1 ) {
			return new ResultDto(1, "保存成功");
		} else {
			return new ResultDto(2, "保存失败");
		}
		
	}
	
	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
	@ResponseBody
	public ComponentOrderDto selectByPrimaryKey(@RequestParam(value="order_id", required=true) Integer order_id)  {
	 	return componentOrderDao.selectByPrimaryKey(order_id);
	}
	
}
