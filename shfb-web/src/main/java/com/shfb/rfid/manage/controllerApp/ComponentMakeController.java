package com.shfb.rfid.manage.controllerApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.shfb.rfid.manage.controller.BaseController;
import com.shfb.rfid.manage.dao.ComponentMapper;
import com.shfb.rfid.manage.dao.ProductCuringMapper;
import com.shfb.rfid.manage.dao.ProductEmbeddedPartsMapper;
import com.shfb.rfid.manage.dao.ProductModelSizeMapper;
import com.shfb.rfid.manage.dao.ProductSteelbarSizeMapper;
import com.shfb.rfid.manage.dao.ProjectMapper;
import com.shfb.rfid.manage.dao.SysUserMapper;
import com.shfb.rfid.manage.dto.ComponentDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.ProductCuring;
import com.shfb.rfid.manage.entity.ProductEmbeddedParts;
import com.shfb.rfid.manage.entity.ProductModelSize;
import com.shfb.rfid.manage.entity.ProductSteelbarSize;
import com.shfb.rfid.manage.entity.SysUser;
import com.shfb.rfid.manage.service.FtpService;
import com.shfb.rfid.manage.util.ResponseData;
import com.shfb.rfid.manage.util.StringUtil;


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
	
	/**
	 * app登录
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String userName, String password) {
		if(StringUtil.isnotNull(userName)&&StringUtil.isnotNull(password)){
			SysUser user = userDao.findUser(userName, password);
			if (user != null) {
				//以下返回信息为暂时，1代表业主总包 2代表构建厂
				if(user.getUser_role_id() == 2) {
					return  ResponseData.newSuccess("登录成功",user.getUser_id()+"","2");
				} else {
					return  ResponseData.newSuccess("登录成功",user.getUser_id()+"","1");
				}
	            
			}
			return ResponseData.newFailure("用户名或者密码不正确~");
		}else{
			return ResponseData.newFailure("用户名和密码不能为空~");
		}
		
	}
	
	/**
	 * 平板端下拉框接口(项目-单体-楼层-构件)
	 */
	@RequestMapping(value = "/getSelectContent", method = RequestMethod.GET)
	@ResponseBody
	public Object getSelectContent() {
		List<Map<String, Object>> projects = projectDao.findProjectNames();
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
					List<Component> components = componentDao.findComponentBysel(pro_id, single_name, floor.get("floor").toString());
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
	 * 获取构件列表
	 * @param component_status_id
	 * @return
	 */
	@RequestMapping(value = "/findComponentList")
	@ResponseBody	
	public Object findComponentList(Component parm) {
		Page<ComponentDto> components = componentDao.findAllComponent(parm);
		return  ResponseData.newSuccess(components, "查询成功");
		//return components;		
	}
	
	/**
	 * 获取构件的详细信息
	 * @param component_id
	 * @return
	 */
	@RequestMapping(value = "/findComponentByKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto findComponentByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		Component component = componentDao.selectByPrimaryKey(component_id);
		
		return new ResultDto(component);
		//return component;
		
	}
	
	/**
	 * 获取构件列表
	 * @param component_status_id
	 * @return
	 */
	/*@RequestMapping(value = "/findComponentListForClient")
	@ResponseBody	
	public Object findComponentListForClient(Integer pro_id) {
		if (pro_id==0) {
			pro_id=null;
		}
		List<Component> components = componentDao.findComponentByProID(pro_id);
		return  components;
	}*/
	
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
	@RequestMapping(value = "/findProductModelSizeByKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto findProductModelSizeByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductModelSize productComponentSize = productModelSizeDao.findByComponentId(component_id);
		return new ResultDto(productComponentSize);
		//return productComponentSize;
		
	}
	
	/**
	 * 上传钢模制作验收数据(模具尺寸的允许偏差和检验方法)
	 */
	@RequestMapping(value = "/insertProductModelSize", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto insertProductModelSize(ProductModelSize productModelSize) {

		if(productModelSize.getComponent_id() == null) new ResultDto(2,"param err");
		
		int res = productModelSizeDao.insertSelective(productModelSize);		
		if(res != 0) {
			return new ResultDto(1,"success");
		} else {
			return new ResultDto(2,"server err");
		}
		
	}
		
	/**
	 * 获取钢筋困扎数据(钢筋制品尺寸允许偏差和检验方法)
	 */
	@RequestMapping(value = "/findProductSteelbarSizeByKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto findProductSteelbarSizeByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductSteelbarSize productSteelbarSize = productSteelbarSizeDao.findByComponentId(component_id);
		return new ResultDto(productSteelbarSize);
		//return productSteelbarSize;
		
	}
	/**
	 * 上传钢筋困扎数据(钢筋制品尺寸允许偏差和检验方法)
	 */
	@RequestMapping(value = "/insertProductSteelbarSize", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto insertProductSteelbarSize(ProductSteelbarSize productSteelbarSize) {
		
		if(productSteelbarSize.getComponent_id() == null) new ResultDto(2,"param err");
		int res = productSteelbarSizeDao.insertSelective(productSteelbarSize);		
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
	@RequestMapping(value = "/findProductEmbeddedPartsByKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto findProductEmbeddedPartsByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductEmbeddedParts productEmbeddedParts = productEmbeddedPartsDao.findByComponentId(component_id);
		//return productEmbeddedParts;
		return new ResultDto(productEmbeddedParts);
	}
	/**
	 * 获取预埋件设置数据(上传预埋件和预留孔洞的允许偏差和检验方法)
	 */
	@RequestMapping(value = "/insertProductEmbeddedParts", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto insertProductEmbeddedParts(ProductEmbeddedParts productEmbeddedParts) {
		if(productEmbeddedParts.getComponent_id() == null) new ResultDto(2,"param err");
		int res = productEmbeddedPartsDao.insertSelective(productEmbeddedParts);		
		if(res != 0) {
			return new ResultDto(0,"success");
		} else {
			return new ResultDto(1,"server err");
		}
		
	}
	
	/**
	 * 获取混凝土浇筑及养护数据(预制构件养护质量)  
	 */
	@RequestMapping(value = "/findProductCuringByKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto findProductCuringByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductCuring productCuring = productCuringDao.findByComponentId(component_id);
		//return productCuring;
		return new ResultDto(productCuring);
	}	
	/**
	 * 上传混凝土浇筑及养护数据(预制构件养护质量)
	 */
	@RequestMapping(value = "/insertProductCuring", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto insertProductCuring(ProductCuring productCuring) {
		if(productCuring.getComponent_id() == null) new ResultDto(2,"param err");
		int res = productCuringDao.insertSelective(productCuring);		
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
				
				picture += (FtpService.FILE_Url + saveName + ";");
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
	
}
