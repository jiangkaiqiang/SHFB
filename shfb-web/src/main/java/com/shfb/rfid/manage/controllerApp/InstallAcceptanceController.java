package com.shfb.rfid.manage.controllerApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shfb.rfid.manage.dao.CompProgressMapper;
import com.shfb.rfid.manage.dao.ComponentMapper;
import com.shfb.rfid.manage.dao.InstallComponentSizeMapper;
import com.shfb.rfid.manage.dao.SysUserMapper;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.CompProgress;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.InstallComponentSize;
import com.shfb.rfid.manage.entity.SysUser;
import com.shfb.rfid.manage.service.FtpService;
import com.shfb.rfid.manage.util.TimeUtil;

/**
 * 安装验收模块-app接口
 * @author 远扬
 *
 */
@Controller
@RequestMapping(value = "/installAcceptance")
public class InstallAcceptanceController {
	@Autowired
	private InstallComponentSizeMapper installComponentSizeDao;
	@Autowired
	private FtpService ftpservice;
	@Autowired
	private ComponentMapper componentDao;
	@Autowired
	private CompProgressMapper comProgressDao;
	@Autowired
	private SysUserMapper userDao;
	/**
	 * 获取安装验收时的预制构件尺寸允许偏差及检查方法-如果没有数据表示第一次上传
	 */
	@RequestMapping(value = "/findInstallComponentSizeByKey")
	@ResponseBody
	public ResultDto findInstallComponentSizeByKey(
			@RequestParam(value="component_id", required=true) Integer component_id) {
		InstallComponentSize installComponentSize = installComponentSizeDao.findByComponentId(component_id);
		if(installComponentSize == null ) {
			installComponentSize = new InstallComponentSize();
		}
		return new ResultDto(installComponentSize);
	}
	/**
	 * 上传安装验收时的预制构件尺寸允许偏差及检查方法
	 */
	@RequestMapping(value = "/insertInstallComponentSize")
	@ResponseBody
	public ResultDto insertInstallComponentSize(InstallComponentSize installComponentSize, Integer token) {
		
		if(installComponentSize.getComponent_id() == null) return new ResultDto(2, "无法找到构件id", false);
		
		SysUser sysUser = userDao.findUserById(token);
		int resUpdate = updateComStatus(installComponentSize.getComponent_id(), 8, null);
		
		//if(resUpdate == 1) {
			updateComProgress(installComponentSize.getComponent_id(), sysUser.getUser_name(), "验收完成");
		//}
		
		
		//int res = installComponentSizeDao.insertSelective(installComponentSize);
		int res;
		 if(null == installComponentSizeDao.findByComponentId(installComponentSize.getComponent_id())) {
			 res = installComponentSizeDao.insertSelective(installComponentSize);
		 } else {
			 res = installComponentSizeDao.updateByPrimaryKeySelective(installComponentSize);
		 }
	
		if(res == 1) {
			return new ResultDto(1, "上传成功");
		} else {
			return new ResultDto(2, "上传失败", false);
		}
				
	}
	
	/**
	 * 安装验收-上传构件实物图
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadFileInstall", method = RequestMethod.POST)
	@ResponseBody
	public ResultDto uploadFileInstall(
			@RequestParam(value = "file", required = true) MultipartFile[] files, 
			@RequestParam(value="component_id", required=true) Integer component_id
			) throws Exception{
		return appUploadFile(files, component_id, 3);
		
	}
	
	/**
	 * 获取app信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAppVersion")
	@ResponseBody
	public ResultDto getAppVersion(){
		try {
			return new ResultDto(installComponentSizeDao.getAppVersion());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultDto(2, "server err", false);
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
	
}
