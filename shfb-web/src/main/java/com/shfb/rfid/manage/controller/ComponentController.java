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
import com.shfb.rfid.manage.dao.ComponentStatusMapper;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.ComponentOrder;
import com.shfb.rfid.manage.entity.ComponentStatus;
@Controller
@RequestMapping(value = "/component")
public class ComponentController extends BaseController {
	@Autowired
	private ComponentMapper componentDao;
	
	@Autowired
	private ComponentStatusMapper componentStatusDao;
	
	@RequestMapping(value = "/findComponentPage", method = RequestMethod.POST)
	@ResponseBody
	public Object findComponentPage(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="pro_id", required=false) Integer pro_id,
			@RequestParam(value="single_name", required=false) String single_name,
			@RequestParam(value="floor", required=false) String floor,
			@RequestParam(value="component_type", required=false) String component_type,
			@RequestParam(value="component_status_id", required=false) Integer component_status_id
			) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 10:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		single_name = "".equals(single_name)? null:single_name;
		floor = "".equals(floor)? null:floor;
		component_type = "".equals(component_type)? null:component_type;
		Page<ComponentDto> components = componentDao.findComponentPage(pro_id, single_name, floor, component_type, component_status_id);

		return new PageInfo<ComponentDto>(components);
		
	}
	
	@RequestMapping(value = "/findComponentInfoById", method = RequestMethod.GET)
	@ResponseBody
	public ComponentDto findComponentInfoById(@RequestParam(value="component_id",required=true) Integer component_id)  {
		ComponentDto component = componentDao.findComponentInfoById(component_id);

		return component;
		
	}
	
	@RequestMapping(value = "/findComponentStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<ComponentStatus> findComponentStatus() {
		List<ComponentStatus> statuslist = componentStatusDao.findStatus();

		return statuslist;
		
	}
	
	
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto updateByPrimaryKeySelective(Component component)  {
		int res = componentDao.updateByPrimaryKeySelective(component);

		if( res==1 ) {
			return new ResultDto(1, "保存成功");
		} else {
			return new ResultDto(2, "保存失败");
		}
		
	}
	
	@RequestMapping(value = "/findSingleByProject", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> findSingleByProject(Integer pro_id)  {
	 	return componentDao.findSingleBysel(pro_id);
	}
	
	@RequestMapping(value = "/findFloorBySel", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> findFloorBySel(Integer pro_id, String single_name)  {
		List<Map<String, Object>> res = componentDao.findFloor(pro_id, single_name);
		return res;
	}
	
	@RequestMapping(value = "/findComponentTypes", method = RequestMethod.GET)
	@ResponseBody
	public List<Component> findComponentTypes()  {
		List<Component> types = componentDao.findComponentTypes();
		List<Component> res = new ArrayList<Component>();
		for (Component type : types) {
			if(type != null)
				res.add(type);
		}
		return res;
	}
	
	
	@RequestMapping(value = "/deleteComp", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto deleteComp(Integer component_id)  {
		int res = componentDao.deleteByPrimaryKey(component_id);

		if( res==1 ) {
			return new ResultDto(1, "删除成功");
		} else {
			return new ResultDto(2, "删除失败");
		}
		
	}
	
}
