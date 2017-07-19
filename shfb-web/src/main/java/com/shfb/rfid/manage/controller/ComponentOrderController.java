package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.ComponentOrderMapper;
import com.shfb.rfid.manage.dto.ComponentOrderDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.entity.ComponentOrder;
import com.shfb.rfid.manage.util.ExcelImportUtil;
@Controller
@RequestMapping(value = "/componentOrder")
public class ComponentOrderController extends BaseController {
	@Autowired
	private ComponentOrderMapper componentOrderDao;

	
	@RequestMapping(value = "/findComponentOrderPage", method = RequestMethod.POST)
	@ResponseBody
	public Object findComponentOrderPage(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="userProjectID", required=false) Integer userProjectID,
			@RequestParam(value="pro_id", required=false) Integer pro_id,
			@RequestParam(value="userCompFactoryID", required=false) Integer userCompFactoryID,
			@RequestParam(value="single_name", required=false) String single_name,
			@RequestParam(value="floor", required=false) String floor
			) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 10:pageSize;
		if (null==userProjectID||userProjectID==0) {
			userProjectID = null;
		}
		if (null==userCompFactoryID||userCompFactoryID==0) {
			userCompFactoryID = null;
		}
		PageHelper.startPage(pageNum, pageSize);
		single_name = "".equals(single_name)? null:single_name;
		floor = "".equals(floor)? null:floor;
		Page<ComponentOrderDto> componentOrders = componentOrderDao.findComponentOrderPage(pro_id, single_name, floor,userProjectID,userCompFactoryID);

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
	
	/**
	 * 查询所有订单编号
	 * @return
	 */
	@RequestMapping(value = "/findComponentOrders", method = RequestMethod.GET)
	@ResponseBody
	public List<ComponentOrder> findComponentOrders(@RequestParam(value="userCompFactoryID", required=false) Integer userCompFactoryID) {
		if(userCompFactoryID==0) {
			userCompFactoryID=null;
		}
		return componentOrderDao.findComponentOrders(userCompFactoryID);
	}
	
	
	/**
	 * 导出构件
	 * @param componentIdStrs
	 * @return
	 */
	@RequestMapping(value = "/exportCompOrder", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportCompOrder(String order_idStrs)  {
		String[] order_ids = order_idStrs.split(",");
		List<ComponentOrderDto> componentOrders = componentOrderDao.exportComponentOrders(order_ids);	
		
		Map<String, Object> dataExel = new HashMap<String, Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("项目名称");
		titles.add("单体");
		titles.add("楼层");
		titles.add("计划开始时间");
		titles.add("计划完成时间");
		titles.add("实际开始时间");
		titles.add("实际完成时间");
		List<Map<String, Object>> varList = new ArrayList<Map<String,Object>>();
		for (ComponentOrderDto componentOrder : componentOrders) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("var1", componentOrder.getPro_name());
			map.put("var2", componentOrder.getSingle_name());
			map.put("var3", componentOrder.getFloor());
			map.put("var4", componentOrder.getPlan_begin_date());
			map.put("var5", componentOrder.getPlan_end_date());
			map.put("var6", componentOrder.getReal_begin_date());
			map.put("var7", componentOrder.getReal_end_date());
			varList.add(map);
		}
		dataExel.put("titles", titles);
		dataExel.put("varList", varList);
		return ExcelImportUtil.exportExcel(dataExel);
	}
	
}
