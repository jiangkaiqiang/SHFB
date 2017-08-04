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
import com.shfb.rfid.manage.dto.ModelDataDto;
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
@RequestMapping(value = "/model")
public class ModelDataController extends BaseController{
	
	@Autowired
	private ComponentMapper componentDao;

	/**
	 * 给模型提供数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCompData")
	@ResponseBody	
	public Object findComponentList(String single, String floor, String state, String project, String num) {
		try {
			List<ModelDataDto> components = componentDao.getModelData(single, floor, state, project, num);
			
			return  new ResultDto(components);
		} catch (Exception e) {
			e.printStackTrace();
			return   new ResultDto(2, "查询失败", false);
		}
		
		
			
	}
	
	
	
}
