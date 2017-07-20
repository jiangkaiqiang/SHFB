package com.shfb.rfid.manage.controllerApp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.protocol.ResponseDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.github.pagehelper.Page;
import com.shfb.rfid.manage.controller.BaseController;
import com.shfb.rfid.manage.dao.CompProgressMapper;
import com.shfb.rfid.manage.dao.ComponentMapper;
import com.shfb.rfid.manage.dao.ProductCuringMapper;
import com.shfb.rfid.manage.dao.ProductEmbeddedPartsMapper;
import com.shfb.rfid.manage.dao.ProductModelSizeMapper;
import com.shfb.rfid.manage.dao.ProductSteelbarSizeMapper;
import com.shfb.rfid.manage.dao.ProjectMapper;
import com.shfb.rfid.manage.dao.SysUserMapper;
import com.shfb.rfid.manage.dao.UserRoleMapper;
import com.shfb.rfid.manage.dto.AppResultDto;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.CompProgress;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.ProductComponentSize;
import com.shfb.rfid.manage.entity.ProductCuring;
import com.shfb.rfid.manage.entity.ProductEmbeddedParts;
import com.shfb.rfid.manage.entity.ProductModelSize;
import com.shfb.rfid.manage.entity.ProductSteelbarSize;
import com.shfb.rfid.manage.entity.Project;
import com.shfb.rfid.manage.entity.SysUser;
import com.shfb.rfid.manage.entity.UserRole;
import com.shfb.rfid.manage.service.FtpService;
import com.shfb.rfid.manage.util.ResponseData;
import com.shfb.rfid.manage.util.StringUtil;
import com.shfb.rfid.manage.util.TimeUtil;

import net.sf.json.JSONArray;
import net.sf.jsqlparser.schema.Server;


/**
 * 构件制作模块-app接口
 * @author 远扬
 *
 */
@Controller
@RequestMapping(value = "/componentMake")
public class ComponentMakeController extends BaseController{
	
	@Autowired
	private ComponentMapper componentDao;
	@Autowired
	private ProductModelSizeMapper productModelSizeDao;	
	@Autowired
	private ProductSteelbarSizeMapper productSteelbarSizeDao;
	@Autowired
	private ProductEmbeddedPartsMapper productEmbeddedPartsDao;
	@Autowired
	private ProductCuringMapper productCuringDao;
	@Autowired
	private FtpService ftpservice;
	@Autowired
	private SysUserMapper userDao;
	@Autowired
	private ProjectMapper projectDao;
	@Autowired
	private CompProgressMapper comProgressDao;
	@Autowired
	private UserRoleMapper userRoleDao;
	
	/**
	 * app登录
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String userName, String password) {
		if(StringUtil.isnotNull(userName)&&StringUtil.isnotNull(password)){
			SysUser user = userDao.findUser(userName, password);
			if (user != null) {
				UserRole role = userRoleDao.selectByPrimaryKey(user.getUser_role_id());
				//以下返回信息为暂时，1代表业主总包 2代表构建厂
				if(role.getMenu_ids().contains("11")) {
					return new AppResultDto(true, "登录成功", user.getUser_id()+"", "2");
				} else {
					return new AppResultDto(true, "登录成功", user.getUser_id()+"", "1");
				}
	            
			}
			return new AppResultDto(false, "用户名或密码错误", "", "1");
		}else{
			return new AppResultDto(false, "用户名或密码为空", "", "1");
		}
		
	}
	
	/**
	 * 平板端下拉框接口(项目-单体-楼层-构件)
	 */
	@RequestMapping(value = "/getSelectContent")
	@ResponseBody
	public Object getSelectContent(Integer token) {
		SysUser sysUser = userDao.findUserById(token);
		Integer userProjectID = sysUser.getPro_id();
		Integer comp_factory_id = sysUser.getComp_factory_id();
		if (userProjectID==0) {
			userProjectID = null;
		}
		if (comp_factory_id==0) {
			comp_factory_id = null;
		}
		
		List<Map<String, Object>> projects = projectDao.findProjectNames(userProjectID);
		for (Map<String, Object> project : projects) {
			Integer pro_id = Integer.valueOf(project.get("pro_id").toString());
			List<Map<String, Object>> singles = componentDao.findSingle(pro_id);
			for (Map<String, Object> single : singles) {
				if(single == null) {
					continue;
				}
				String single_name = single.get("single_name").toString();
				List<Map<String, Object>> floors = componentDao.findFloor(pro_id, single_name);
				for (Map<String, Object> floor : floors) {
					if(floor == null ) {
						continue;
					}
					List<Component> components = componentDao.findComponentBysel(pro_id, single_name, floor.get("floor").toString(), comp_factory_id);
					floor.put("components", components);
				}
				single.put("floors", floors);
			}
			project.put("singles", singles);
		}
		return  ResponseData.newSuccess(projects, "查询成功");
		//return projects;
	}
	
	
	/**
	 * 客户端下拉框接口(项目)
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/getSelectProjectForClient")
	@ResponseBody
	public Object getSelectProjectForClient(String projectID) throws UnsupportedEncodingException {
		Integer userProjectID = Integer.valueOf(projectID);
		if (userProjectID==0) {
			userProjectID = null;
		}
		List<Map<String, Object>> projects = projectDao.findProjectNames(userProjectID);
		String prString = URLEncoder.encode(JSONArray.fromObject(projects).toString(), "UTF-8");
		return prString;
	}
	
	/**
	 * 客户端下拉框接口(单体)
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/getSelectSingleForClient")
	@ResponseBody
	public Object getSelectSingleForClient(String projectName) throws UnsupportedEncodingException {
		Project project = projectDao.findProjectByName(URLDecoder.decode(projectName, "UTF-8"));
		List<Map<String, Object>> singles = componentDao.findSingle(project.getPro_id());
		String prString = URLEncoder.encode(JSONArray.fromObject(singles).toString(), "UTF-8");
		return  prString;
	}
	/**
	 * 客户端下拉框接口(楼层)
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/getSelectFloorForClient")
	@ResponseBody
	public Object getSelectFloorForClient(String projectName, String singleName) throws UnsupportedEncodingException {
		Project project = projectDao.findProjectByName(URLDecoder.decode(projectName, "UTF-8"));
		List<Map<String, Object>> floors = componentDao.findFloor(project.getPro_id(), URLDecoder.decode(singleName, "UTF-8"));
		String prString = URLEncoder.encode(JSONArray.fromObject(floors).toString(), "UTF-8");
		return  prString;
	}
	
	/**
	 * 查询构件类型为客户端提供服务(为构建类型下拉框服务)
	 * @throws UnsupportedEncodingException 
	 */	
	@RequestMapping(value = "/findComonentTypeForClient")
	@ResponseBody	
	public Object findComonentTypeForClient() throws UnsupportedEncodingException {
		List<Component> components = componentDao.findComponentTypes();
		String prString = URLEncoder.encode(JSONArray.fromObject(components).toString(), "UTF-8");
		return prString;
		//return components;		
	}
	
	/**
	 * 客户端下拉框接口(构件)
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/getSelectCompForClient")
	@ResponseBody
	public Object getSelectCompForClient(String projectName, String singleName,String floorName,String typeName,String bindName,String compName)
			throws UnsupportedEncodingException {
		boolean bindFlag = true;
		List<Component> components = null;
		if (projectName.equals("null")) {
			projectName = null;
		}
		else {
			projectName = URLDecoder.decode(projectName, "UTF-8");
		}
		if (singleName.equals("null")) {
			singleName = null;
		}
		else {
			 singleName = URLDecoder.decode(singleName, "UTF-8");	
		}
		if (floorName.equals("null")) {
			floorName = null;
		}
		else {
			floorName = URLDecoder.decode(floorName, "UTF-8");	
		}
		if (typeName.equals("null")) {
			typeName = null;
		}
		else {
			typeName = URLDecoder.decode(typeName, "UTF-8");	
		}
		if (bindName==null) {
		}
		else {
			bindName = URLDecoder.decode(bindName, "UTF-8");
			if (bindName.equals("已绑定")) {
				bindFlag = false;
			}
		}
		if (compName==null||compName.equals("")) {
			compName = null;
		}
		Project project = projectDao.findProjectByName(projectName);
		if (project==null) {
			project = new Project();
		}
		if (bindFlag) {
			components = componentDao.findComponentByselForClient(project.getPro_id(), singleName, floorName,typeName,compName);	
		}
		else {
			components = componentDao.findComponentByselForClientUnBind(project.getPro_id(), singleName, floorName,typeName,compName);	
		}
		return  ResponseData.newSuccess(components, "查询成功");
	}
	
	
	/**
	 * 获取构件列表
	 * @param component_status_id
	 * @return
	 */
	@RequestMapping(value = "/findComponentList")
	@ResponseBody	
	public Object findComponentList(Component parm, Integer token) {
		SysUser sysUser = userDao.findUserById(token);
		Integer userProjectID = sysUser.getPro_id();
		Integer comp_factory_id = sysUser.getComp_factory_id();
		if (userProjectID==0) {
			userProjectID = null;
		}
		parm.setComp_factory_id(comp_factory_id);
		parm.setPro_id(userProjectID);
		Page<ComponentDto> components = componentDao.findAllComponent(parm);
		return  ResponseData.newSuccess(components, "查询成功");
		//return components;		
	}
	
	
	/**
	 * 
	 * @param component_status_id
	 * @return
	 */
	@RequestMapping(value = "/findComponentListForClient")
	@ResponseBody	
	public Object findComponentListForClient(Component parm) {
		Integer pro_id = parm.getPro_id();
		if (parm.getPro_id()==0) {
			pro_id = null;
		}
		List<Component> components = componentDao.findComponentByProID(pro_id);
		return  ResponseData.newSuccess(components, "查询成功");
		//return components;		
	}
	
	/**
	 * 获取构件的详细信息
	 * @param component_id 构件id
	 * @param component_num 构件编号
	 * @param card_num rfid卡号
	 * @return
	 */
	@RequestMapping(value = "/findComponentByKey")
	@ResponseBody
	public ResultDto findComponentByKey(Component record) {
		try {
			Component component = componentDao.selectByCom(record);
			if(component == null) {
				component = new Component();
			}
			return new ResultDto(component);
		} catch (Exception e) {
			return new ResultDto(2, "param err", false);
		}
		
	}
	
	
	/**
	 * 查询构件类型(为构建类型下拉框服务)
	 */	
	@RequestMapping(value = "/findComonentType")
	@ResponseBody	
	public Object findComonentType() {
		List<Component> components = componentDao.findComponentTypes();
		return  ResponseData.newSuccess(components, "查询成功");
		//return components;		
	}
	
	/**
	 * 构件制作-上传构件实物图
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadFileProduct", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto uploadFileProduct(
			@RequestParam(value = "file", required = true) MultipartFile[] files, 
			@RequestParam(value="component_id", required=true) Integer component_id
			) throws Exception{
		return appUploadFile(files, component_id, 0);
		
	}
	
	/**
	 * 获取钢模制作验收数据(模具尺寸的允许偏差和检验方法)
	 */
	@RequestMapping(value = "/findProductModelSizeByKey")
	@ResponseBody
	public ResultDto findProductModelSizeByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductModelSize productModelSize = productModelSizeDao.findByComponentId(component_id);
		if(productModelSize == null) {
			productModelSize = new ProductModelSize();
		}
		return new ResultDto(productModelSize);
		//return productComponentSize;
		
	}
	
	/**
	 * 上传钢模制作验收数据(模具尺寸的允许偏差和检验方法)
	 */
	@RequestMapping(value = "/insertProductModelSize")
	@ResponseBody
	public ResultDto insertProductModelSize(ProductModelSize productModelSize,Integer token) {

		if(productModelSize.getComponent_id() == null) new ResultDto(2,"param err");
		
		SysUser sysUser = userDao.findUserById(token);
		int resUpdate = updateComStatus(productModelSize.getComponent_id(), 4, null);
		//if(resUpdate == 1) {
			updateComProgress(productModelSize.getComponent_id(), sysUser.getUser_name(), "生产中");
		//}
		
		 int res;
		 if(null == productModelSizeDao.findByComponentId(productModelSize.getComponent_id())) {
			 res = productModelSizeDao.insertSelective(productModelSize);
		 } else {
			 res = productModelSizeDao.updateByPrimaryKeySelective(productModelSize);
		 }
		
				
		
		if(res != 0) {
			return new ResultDto(1,"success");
		} else {
			return new ResultDto(2,"server err");
		}
		
	}
		
	/**
	 * 获取钢筋困扎数据(钢筋制品尺寸允许偏差和检验方法)
	 */
	@RequestMapping(value = "/findProductSteelbarSizeByKey")
	@ResponseBody
	public ResultDto findProductSteelbarSizeByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductSteelbarSize productSteelbarSize = productSteelbarSizeDao.findByComponentId(component_id);
		if(productSteelbarSize == null ) {
			productSteelbarSize = new ProductSteelbarSize();
		}
		
		return new ResultDto(productSteelbarSize);
		//return productSteelbarSize;
		
	}
	/**
	 * 上传钢筋困扎数据(钢筋制品尺寸允许偏差和检验方法)
	 */
	@RequestMapping(value = "/insertProductSteelbarSize")
	@ResponseBody
	public ResultDto insertProductSteelbarSize(ProductSteelbarSize productSteelbarSize) {
		
		if(productSteelbarSize.getComponent_id() == null) new ResultDto(2,"param err");
		//int res = productSteelbarSizeDao.insertSelective(productSteelbarSize);
		
		int res;
		 if(null == productSteelbarSizeDao.findByComponentId(productSteelbarSize.getComponent_id())) {
			 res = productSteelbarSizeDao.insertSelective(productSteelbarSize);
		 } else {
			 res = productSteelbarSizeDao.updateByPrimaryKeySelective(productSteelbarSize);
		 }
		
		if(res != 0) {
			return new ResultDto(0,"success");
		} else {
			return new ResultDto(1,"server err");
		}
		
	}
	
	@RequestMapping(value = "/updateByComponentNum")
	@ResponseBody
	public ResultDto updateByComponentNum(Component component)  {
		int res = componentDao.updateByComponentNum(component);

		if( res==1 ) {
			return new ResultDto(1, "保存成功");
		} else {
			return new ResultDto(2, "保存失败");
		}
		
	}
	
	/**
	 * 获取预埋件设置数据( 预埋件和预留孔洞的允许偏差和检验方法)
	 */
	@RequestMapping(value = "/findProductEmbeddedPartsByKey")
	@ResponseBody
	public ResultDto findProductEmbeddedPartsByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductEmbeddedParts productEmbeddedParts = productEmbeddedPartsDao.findByComponentId(component_id);
		if(productEmbeddedParts == null ){
			productEmbeddedParts = new ProductEmbeddedParts();
		}
	
		return new ResultDto(productEmbeddedParts);
	}
	/**
	 * 预埋件设置数据(上传预埋件和预留孔洞的允许偏差和检验方法)
	 */
	@RequestMapping(value = "/insertProductEmbeddedParts")
	@ResponseBody
	public ResultDto insertProductEmbeddedParts(ProductEmbeddedParts productEmbeddedParts) {
		if(productEmbeddedParts.getComponent_id() == null) new ResultDto(2,"param err");
		//int res = productEmbeddedPartsDao.insertSelective(productEmbeddedParts);		
		
		int res;
		 if(null == productEmbeddedPartsDao.findByComponentId(productEmbeddedParts.getComponent_id())) {
			 res = productEmbeddedPartsDao.insertSelective(productEmbeddedParts);
		 } else {
			 res = productEmbeddedPartsDao.updateByPrimaryKeySelective(productEmbeddedParts);
		 }
		
		if(res != 0) {
			return new ResultDto(0,"success");
		} else {
			return new ResultDto(1,"server err");
		}
		
	}
	
	/**
	 * 获取混凝土浇筑及养护数据(预制构件养护质量)  
	 */
	@RequestMapping(value = "/findProductCuringByKey")
	@ResponseBody
	public ResultDto findProductCuringByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductCuring productCuring = productCuringDao.findByComponentId(component_id);
		if(productCuring == null ) {
			productCuring = new ProductCuring();
		}
		return new ResultDto(productCuring);
	}	
	/**
	 * 上传混凝土浇筑及养护数据(预制构件养护质量)
	 */
	@RequestMapping(value = "/insertProductCuring")
	@ResponseBody
	public ResultDto insertProductCuring(ProductCuring productCuring) {
		if(productCuring.getComponent_id() == null) new ResultDto(2,"param err");
		//int res = productCuringDao.insertSelective(productCuring);		
		
		int res;
		 if(null == productCuringDao.findByComponentId(productCuring.getComponent_id())) {
			 res = productCuringDao.insertSelective(productCuring);
		 } else {
			 res = productCuringDao.updateByPrimaryKeySelective(productCuring);
		 }
		
		if(res != 0) {
			return new ResultDto(0,"success");
		} else {
			return new ResultDto(1,"server err");
		}
		
	}
	
	/**
	 * app上传构件实物图
	 * @param files
	 * @param component_id
	 * @param process 上传图片的模块 0构件制作 1生产验收 2收货验收 3安装验收 
	 * @return
	 */
	public ResultDto appUploadFile(MultipartFile[] files, Integer component_id, int process) {
		boolean res=false;
		String picture="";
		if (null != files && files.length>0) {		
			List<UploadFileEntity> fileEntities = new ArrayList<UploadFileEntity>();
			
			for (int i = 0; i < files.length; i++) {
				//获取文件的原始名字
				String fileName = files[i].getOriginalFilename();
				//文件保存的名字
				String saveName = component_id+"_"+i+"_"+System.currentTimeMillis()
					+fileName.substring(fileName.lastIndexOf("."));
				fileEntities.add(new UploadFileEntity(saveName, files[i], ""));
				if(i!=files.length-1) {
					picture += (FtpService.FILE_Url + saveName + ";");
				}else{
					picture += (FtpService.FILE_Url + saveName );
				}
				
			}

			res = ftpservice.uploadFileList(fileEntities);			
		}
				//如果文件保存成功，更新数据库
				if( res == true ) {
					Component component = new Component();
					component.setComponent_id(component_id);
					switch (process) {
					case 0:
						component.setPicture_comp_make(picture);
						break;
					case 1:
						component.setPicture_product_accept(picture);
						break;
					case 2:
						component.setPicture_receipt(picture);
						break;
					case 3:
						component.setPicture_install(picture);
						break;
					default:
						break;
					}					
					
					componentDao.updateByPrimaryKeySelective(component);
					return new ResultDto(1,"success");
				} else {
					return new ResultDto(2,"server err");
				}
		
	}
	
	
	/**
	 * 改变构件状态
	 */
	public int updateComStatus(Integer componentId, Integer component_status_id,Integer oldcomponent_status_id) {
		int res = componentDao.updateComStatus(componentId, component_status_id, oldcomponent_status_id);
		return res;
	}
	
	
	/**
	 * 改变构件进度状态
	 * @param componentId
	 * @param order_username
	 * @param component_status_name
	 * @return
	 */
	public int updateComProgress(Integer componentId, String order_username, String component_status_name) {
		CompProgress compProgress = new CompProgress();
		compProgress.setComponent_id(componentId);
		compProgress.setOperation_date(TimeUtil.dateToString(new Date(), ""));
		compProgress.setOperation_user(order_username);
		compProgress.setComponent_status_name(component_status_name);
		//更新构件状态进度表
		int resProgress = comProgressDao.insertSelective(compProgress);
		return resProgress;
	}
	
	/**
	 * 获取构件基本信息
	 */
	@RequestMapping(value = "/getComInfo")
	@ResponseBody
	public ComponentDto insertProductCuring(String comonentNum) {		
		ComponentDto component = componentDao.getComponentInfo(comonentNum);
		System.out.println("component");
		return component;
	}
	
	/**
	 * 获取卡号是否已经绑定
	 */
	@RequestMapping(value = "/getCardBindInfo")
	@ResponseBody
	public String getCardBindInfo(String cardNum) {
		List<Component> components = componentDao.findComponentByCardNum(cardNum);
		if (components==null||components.size()==0) {
			return "";
		}
		else {
			return components.get(0).getComponent_num();
		}
	}
	
     /**
	 * 获取所有图纸
	 */
	@RequestMapping(value = "/getDrawings")
	@ResponseBody
	public Object getDrawings(Integer token) {
		SysUser sysUser = userDao.findUserById(token);
		Integer userProjectID = sysUser.getPro_id();
		if (userProjectID==0) {
			userProjectID = null;
		}
		List<Component> drawings = componentDao.getDrawings(userProjectID);
		
		return new ResultDto(drawings);
		
	}
}
