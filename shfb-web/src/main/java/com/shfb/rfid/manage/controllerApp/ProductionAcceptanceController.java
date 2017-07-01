package com.shfb.rfid.manage.controllerApp;

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
import org.springframework.web.multipart.MultipartFile;

import com.shfb.rfid.manage.dao.ComponentMapper;
import com.shfb.rfid.manage.dao.ProductComponentSizeMapper;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.ProductComponentSize;
import com.shfb.rfid.manage.service.FtpService;

/**
 * 生产验收模块-app接口
 * @author 远扬
 *
 */
@Controller
@RequestMapping(value = "/productionAcceptance")
public class ProductionAcceptanceController {
	@Autowired
	private ProductComponentSizeMapper  productComponentSizeDao;
	@Autowired
	private FtpService ftpservice;
	@Autowired
	private ComponentMapper componentDao;
	/**
	 * 获取生产验收时的预制构件尺寸允许偏差及检查方法-如果没有数据表示第一次上传
	 */
	@RequestMapping(value = "/findProductComponentSizeByKey", method = RequestMethod.GET)
	@ResponseBody
	public ResultDto findProductComponentSizeByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		ProductComponentSize productComponentSize = productComponentSizeDao.findByComponentId(component_id);
		//return productComponentSize;
		return new ResultDto(productComponentSize);
	}
	/**
	 * 上传生产验收时的预制构件尺寸允许偏差及检查方法
	 */
	@RequestMapping(value = "/insertProductComponentSize", method = RequestMethod.POST)
	@ResponseBody
	public Map insertProductComponentSize(ProductComponentSize productComponentSize) {
		Map map = new HashMap();
		if(productComponentSize.getComponent_id() == null) return map;
		int res = productComponentSizeDao.insertSelective(productComponentSize);		
		map.put("res", res);
		return map;		
	}
	
	/**
	 * 生产验收-上传构件实物图
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadFileAccept", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto uploadFileAccept(
			@RequestParam(value = "file", required = true) MultipartFile[] files, 
			@RequestParam(value="component_id", required=true) Integer component_id
			) throws Exception{
		return appUploadFile(files, component_id, 0);
		
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
