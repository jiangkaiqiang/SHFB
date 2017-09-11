package com.shfb.rfid.manage.controller;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.shfb.rfid.manage.dao.SysUserMapper;
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
	@Autowired
	private SysUserMapper sysUserDao;
	
	
	/**
	 * 构件管理
	 * @param pageNum
	 * @param pageSize
	 * @param userProjectID
	 * @param userCompFactoryID
	 * @param pro_id
	 * @param single_name
	 * @param floor
	 * @param component_type
	 * @param component_status_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
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
	
	/**
	 * 生产管理
	 * @param pageNum
	 * @param pageSize
	 * @param userProjectID
	 * @param userCompFactoryID
	 * @param pro_id
	 * @param single_name
	 * @param floor
	 * @param component_type
	 * @param component_status_id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findComponentProductPage", method = RequestMethod.POST)
	@ResponseBody
	public Object findComponentProductPage(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="userProjectID", required=false) Integer userProjectID,
			@RequestParam(value="userCompFactoryID", required=false) Integer userCompFactoryID,
			@RequestParam(value="pro_id", required=false) Integer pro_id,
			@RequestParam(value="single_name", required=false) String single_name,
			@RequestParam(value="floor", required=false) String floor,
			@RequestParam(value="component_type", required=false) String component_type,
			@RequestParam(value="component_status_id", required=false) Integer component_status_id,
			@RequestParam(value="order_num", required=false) String order_num
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
		order_num = "".equals(order_num)? null:order_num;
		Page<ComponentDto> components = componentDao.findComponentProductPage(pro_id, single_name, floor, 
				component_type, component_status_id,userProjectID,userCompFactoryID,order_num);

		return new PageInfo<ComponentDto>(components);
		
	}
	
	@RequestMapping(value = "/unbundling")
	@ResponseBody
	public Object unbundling(@RequestParam(value="pro_id", required=true) Integer pro_id,@RequestParam(value="component_type", required=true) String component_type){
		int res = 0;
		try {
			res = componentDao.unbundling(pro_id, component_type);
			return new ResultDto(1, "解绑成功,解绑的构件数:"+res);
		} catch (Exception e) {
			return new ResultDto(2, "解绑失败");
		}
		
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
	@RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto updateByPrimaryKey(Component componentParm,HttpServletRequest request)  {
			
			if(request.getParameter("jurisdictionEdit").equals("false")) {
				int res = componentDao.updateByPrimaryKeySelective(componentParm);
				if(res == 1) {
					return new ResultDto(1, "更新成功");
				} else {
					return new ResultDto(2, "更新失败");
				}
			}
			
			String userName = sysUserDao.findUserById(componentParm.getOrder_user_id()).getUser_name();
			
			componentParm.setOrder_user_id(null);
						
			//查询所有状态
			List<String> statusList = componentStatusDao.findStatusName();
			
			String nowDate = TimeUtil.dateToString(new Date(), "yyyy-MM-dd");
			
			//查询需要更新的构件id和目前状态
			Component component = componentDao.selectByPrimaryKey(componentParm.getComponent_id());
			//更新构件状态添加进度			
			Integer oldComponetStatusId = component.getComponent_status_id();
			
			if (oldComponetStatusId < componentParm.getComponent_status_id()) {
				//更新页面传过来的数据
				int res = componentDao.updateByPrimaryKeySelective(componentParm);
				
				//添加构件的状态进度
				for(int i=0; i<componentParm.getComponent_status_id()-oldComponetStatusId; i++) {
										
					String statusName = statusList.get(oldComponetStatusId+i);
					CompProgress progress = new CompProgress();
					progress.setOperation_date(nowDate);
					progress.setComponent_id(componentParm.getComponent_id());
					progress.setComponent_status_name(statusName);
					progress.setOperation_user(userName);
					comProgressDao.insertSelective(progress);
				}
				return new ResultDto(1, "更新成功");
			} else {
				return new ResultDto(2, "更新失败，构件状态有误");
				
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
	public ResultDto expeditGood(String component_ids,String expedit_date)  {
		String[] component_idArray = component_ids.split(",");
		int reses = 0;
		for (String comId : component_idArray) {
			int res = componentDao.expeditGood(Integer.valueOf(comId),expedit_date);
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
	 * 导出生产管理的构件
	 */	
	@RequestMapping(value = "/exportCompProduct", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportCompProduct(String order_num)  {
		
		List<ComponentDto> components = componentDao.exportComponentProduct(order_num);	
		
		Map<String, Object> dataExel = new HashMap<String, Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("构件编号");
		titles.add("订单号");
		titles.add("生产计划开始时间");
		titles.add("生产计划结束时间");
		titles.add("计划供应时间");
		titles.add("催货时间");
		List<Map<String, Object>> varList = new ArrayList<Map<String,Object>>();
		for (ComponentDto component : components) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("var1", component.getComponent_num());
			map.put("var2", component.getOrder_num());
			map.put("var3", component.getProduct_plan_begin_date());
			map.put("var4", component.getProduct_plan_end_date());
			map.put("var5", component.getPlan_end_date());
			map.put("var6", component.getExpedit_date());
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
			@RequestParam(value="plan_end_date", required=true) String plan_end_date,
			@RequestParam(value="comp_factory_id", required=true) Integer comp_factory_id,
			@RequestParam(value="order_user_id", required=true) String order_user_id,
			@RequestParam(value="order_username",required=true) String order_username
			) throws UnsupportedEncodingException {
			Date dateNow = new Date();
			String order_num=TimeUtil.dateToString(dateNow, "yyyyMMddHHmmss"); 
			//更新构件表
			int resUpdate = componentDao.placeOrder(pro_id, single_name, floor, plan_end_date, comp_factory_id, order_user_id,order_num);		
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
	 * 构件状态批量编辑
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
	@RequestMapping(value = "/stateEdit", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto stateEdit( 
			@RequestParam(value="pro_id", required=true) Integer pro_id,
			@RequestParam(value="single_name", required=true) String single_name,
			@RequestParam(value="floor", required=false) String floor,
			@RequestParam(value="component_status_id", required=true) Integer component_status_id,
			@RequestParam(value="order_user_id", required=true) String order_user_id,
			@RequestParam(value="order_username", required=true)String order_username
			) throws UnsupportedEncodingException {
		
			List<String> statusList = componentStatusDao.findStatusName();
			String nowDate = TimeUtil.dateToString(new Date(), "yyyy-MM-dd");
			String compIdsFail = "";
			//查询需要更新的构件id和目前状态
			List<Component> components = componentDao.queryCompIds(single_name, floor, pro_id);
			//更新构件状态添加进度
			for (Component component : components) {
				
				Integer oldComponetStatusId = component.getComponent_status_id();
				
				if (oldComponetStatusId < component_status_id) {
					//更改构件的当前状态
					componentDao.updateComStatus(component.getComponent_id(), component_status_id, null);
					//添加构件的状态进度
					for(int i=0; i<component_status_id-oldComponetStatusId; i++) {
						
						String statusName = statusList.get(oldComponetStatusId+i);
						CompProgress progress = new CompProgress();
						progress.setOperation_date(nowDate);
						progress.setComponent_id(component.getComponent_id());
						progress.setComponent_status_name(statusName);
						progress.setOperation_user(order_username);
						comProgressDao.insertSelective(progress);
					}
				} else {
					compIdsFail += component.getComponent_num() + ",";
					System.out.println("部分构件无法更新进度：" + compIdsFail);
					
				}
				
			}
			
			if("".equals(compIdsFail)) {
				return new ResultDto(1, "更新成功");
			} else {
				return new ResultDto(2, "部分构件无法更新进度：" + compIdsFail);
			}
	
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
	@RequestMapping(value = "/addProductPlan")
	@ResponseBody
	public ResultDto addProductPlan(
			@RequestParam(value="product_plan_begin_date", required=true) String product_plan_begin_date,
			@RequestParam(value="product_plan_end_date", required=false) String product_plan_end_date,
			@RequestParam(value="product_explain", required=true) String product_explain,
			@RequestParam(value="order_username",required=true) String order_username,
			@RequestParam(value="pro_id", required=true) Integer pro_id,
			@RequestParam(value="single_name", required=true) String single_name,
			@RequestParam(value="floor", required=false) String floor,
			@RequestParam(value="comp_factory_id", required=true) Integer comp_factory_id
			) throws UnsupportedEncodingException {
				
				Integer[] component_idArray = componentDao.getComponentIds(pro_id, single_name, floor, comp_factory_id);
				
				//String[] component_idArray = component_ids.split(",");
				int reses = 0;
				
				for (Integer comId : component_idArray) {
					int res = componentDao.addProductPlan(comId, product_plan_begin_date, product_plan_end_date, product_explain);
					
					if(res!=0) {
						//更新构件状态成功
						reses += res;
						//更新构件状态进度
						updateComProgress(comId, order_username, "已接单");
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
		compProgress.setComponent_status_name(component_status_name);
		
		List<CompProgress> list = comProgressDao.queryProgress(componentId, component_status_name);
		int resProgress=2;
		if(list != null && list.size() > 0) {
			
		} else {
			//更新构件状态进度表
			resProgress = comProgressDao.insertSelective(compProgress);
		}
		
		return resProgress;
	}
	
}
