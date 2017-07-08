package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import com.shfb.rfid.manage.dao.CompProgressMapper;
import com.shfb.rfid.manage.dao.ComponentMapper;
import com.shfb.rfid.manage.dao.ComponentOrderMapper;
import com.shfb.rfid.manage.dao.ComponentStatusMapper;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.entity.CompProgress;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.ComponentStatus;
import com.shfb.rfid.manage.util.ExcelImportUtil;
import com.shfb.rfid.manage.util.ResponseData;
import com.shfb.rfid.manage.util.TimeUtil;

@Controller
@RequestMapping(value = "/component")
public class ComponentController extends BaseController {
	@Autowired
	private ComponentMapper componentDao;
	
	@Autowired
	private ComponentStatusMapper componentStatusDao;
	@Autowired
	private ComponentOrderMapper componentOrderDao;
	@Autowired
	private CompProgressMapper comProgressDao;
	
	@RequestMapping(value = "/findComponentPage", method = RequestMethod.POST)
	@ResponseBody
	public Object findComponentPage(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="userProjectID", required=false) Integer userProjectID,
			@RequestParam(value="userCompFactoryID", required=false) Integer userCompFactoryID,
			@RequestParam(value="pro_id", required=false) Integer pro_id,
			@RequestParam(value="single_name", required=false) String single_name,
			@RequestParam(value="floor", required=false) String floor,
			@RequestParam(value="component_type", required=false) String component_type,
			@RequestParam(value="component_status_id", required=false) Integer component_status_id
			) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 10:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if (null==userProjectID||userProjectID==0) {
			userProjectID = null;
		}
		if (null==userCompFactoryID||userCompFactoryID==0) {
			userCompFactoryID = null;
		}
		single_name = "".equals(single_name)? null:single_name;
		floor = "".equals(floor)? null:floor;
		component_type = "".equals(component_type)? null:component_type;
		Page<ComponentDto> components = componentDao.findComponentPage(pro_id, single_name, floor, 
				component_type, component_status_id,userProjectID,userCompFactoryID);

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
		List<Map<String, Object>> res = componentDao.findSingleBysel(pro_id);
	 	return res;
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
	/**
	 * 批量删除
	 * @param component_id
	 * @return
	 */
	@RequestMapping(value = "/deleteComps", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto deleteComps(String component_ids)  {
		String[] component_idArray = component_ids.split(",");
		int reses = 0;
		for (String comId : component_idArray) {
			int res = componentDao.deleteByPrimaryKey(Integer.valueOf(comId));
			reses += res;
		}
		
		if( reses == component_idArray.length ) {
			return new ResultDto(1, "删除成功");
		} else {
			return new ResultDto(2, "删除失败");
		}
		
	}
	
	/**
	 * 催货
	 * @param component_id
	 * @return
	 */
	@RequestMapping(value = "/expeditGood", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto expeditGood(String component_ids)  {
		String[] component_idArray = component_ids.split(",");
		int reses = 0;
		for (String comId : component_idArray) {
			int res = componentDao.expeditGood(Integer.valueOf(comId));
			reses += res;
		}
		
		if( reses == component_idArray.length ) {
			return new ResultDto(1, "催货成功");
		} else {
			return new ResultDto(2, "催货失败");
		}
		
	}
	/**
	 * 导出构件
	 * @param componentIdStrs
	 * @return
	 */
	@RequestMapping(value = "/exportComp", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportComp(String componentIdStrs)  {
		String[] componentIds = componentIdStrs.split(",");
		List<ComponentDto> components = componentDao.exportComponents(componentIds);	
		
		Map<String, Object> dataExel = new HashMap<String, Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("构件名称");
		titles.add("构件编号");
		titles.add("构件类型");
		titles.add("构件规格");
		List<Map<String, Object>> varList = new ArrayList<Map<String,Object>>();
		for (ComponentDto component : components) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("var1", component.getComponent_name());
			map.put("var2", component.getComponent_num());
			map.put("var3", component.getComponent_type());
			map.put("var4", component.getComponent_size());
			varList.add(map);
		}
		dataExel.put("titles", titles);
		dataExel.put("varList", varList);
		return ExcelImportUtil.exportExcel(dataExel);
	}
	
	/**
	 * 下单
	 * @param pageNum
	 * @param pageSize
	 * @param pro_id
	 * @param single_name
	 * @param floor
	 * @param component_type
	 * @param component_status_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto placeOrder( 
			@RequestParam(value="pro_id", required=true) Integer pro_id,
			@RequestParam(value="single_name", required=true) String single_name,
			@RequestParam(value="floor", required=false) String floor,
			@RequestParam(value="expedit_date", required=true) String expedit_date,
			@RequestParam(value="comp_factory_id", required=true) Integer comp_factory_id,
			@RequestParam(value="order_user_id", required=true) String order_user_id,
			@RequestParam(value="order_username",required=true) String order_username
			) throws UnsupportedEncodingException {
			Date dateNow = new Date();
			String order_num=TimeUtil.dateToString(dateNow, "yyyyMMddHHmmss"); 
			//更新构件表
			int resUpdate = componentDao.placeOrder(pro_id, single_name, floor, expedit_date, comp_factory_id, order_user_id,order_num);		
			//新加订单表
			int resInsert = componentOrderDao.insertSelective(pro_id, single_name, floor, comp_factory_id, order_username, order_num);
			
			List<Integer> componentIds = componentDao.findComponentByOrderNum(order_num);
			
			
			for (Integer componentId : componentIds) {				
				//更新构件状态进度表				
				int resProgress = updateComProgress(componentId, order_username, "已下单");
				
				if(resProgress !=1 ) {
					return  new ResultDto(2, "更新构件进度失败");
				}
			}
					
			if((resUpdate + resInsert) > 0) 
				return  new ResultDto(1, "下单成功");
			else
				return  new ResultDto(2, "下单失败");
		
	}
	
	/**
	 * 添加生产计划
	 * @param component_ids
	 * @param product_plan_begin_date
	 * @param product_plan_end_date
	 * @param explain
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/addProductPlan", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto addProductPlan( 
			@RequestParam(value="component_ids", required=true) String component_ids,
			@RequestParam(value="product_plan_begin_date", required=true) String product_plan_begin_date,
			@RequestParam(value="product_plan_end_date", required=false) String product_plan_end_date,
			@RequestParam(value="product_explain", required=true) String product_explain,
			@RequestParam(value="order_username",required=true) String order_username
			) throws UnsupportedEncodingException {
				
				String[] component_idArray = component_ids.split(",");
				int reses = 0;
				
				for (String comId : component_idArray) {
					int res = componentDao.addProductPlan(Integer.valueOf(comId), product_plan_begin_date, product_plan_end_date, product_explain);
					
					if(res!=0) {
						//更新构件状态成功
						reses += res;
						//更新构件状态进度
						updateComProgress(Integer.valueOf(comId), order_username, "已接单");
					} else {
						System.err.println("更新构件状态失败"+comId);
					}
					
				}
			if(reses == component_idArray.length) 
				return  new ResultDto(1, "提交计划成功");
			else
				return  new ResultDto(2, (component_idArray.length-reses)+"个构件提交计划失败");
		
	}
	
	
	public int updateComProgress(Integer componentId, String order_username, String component_status_name) {
		CompProgress compProgress = new CompProgress();
		compProgress.setComponent_id(componentId);
		compProgress.setOperation_date(TimeUtil.dateToString(new Date(), ""));
		compProgress.setOperation_user(order_username);
		compProgress.setComponent_status_name("已下单");
		//更新构件状态进度表
		int resProgress = comProgressDao.insertSelective(compProgress);
		return resProgress;
	}
	
}
