package com.shfb.rfid.manage.controller;

import java.io.Console;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.CityMapper;
import com.shfb.rfid.manage.dao.ComponentMapper;
import com.shfb.rfid.manage.dao.ProjectMapper;
import com.shfb.rfid.manage.dao.SysUserMapper;
import com.shfb.rfid.manage.dto.BaseDto;
import com.shfb.rfid.manage.dto.ProjectDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.CityInfo;
import com.shfb.rfid.manage.entity.Component;
import com.shfb.rfid.manage.entity.Project;
import com.shfb.rfid.manage.entity.ProvinceInfo;
import com.shfb.rfid.manage.service.FtpService;
import com.shfb.rfid.manage.util.ExcelImportUtil;
import com.shfb.rfid.manage.util.TimeUtil;
@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {
	@Autowired
	private ProjectMapper projectDao;
	@Autowired
	private CityMapper cityDao;
	@Autowired
	private FtpService ftpservice;
	@Autowired
	private ComponentMapper componentDao;
	@Autowired
	private SysUserMapper SysUserDao;
	
	
	
	@RequestMapping(value = "/findProjectList", method = RequestMethod.POST)
	@ResponseBody
	public Object findProjectList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="provinceid", required=false) Integer provinceid,
			@RequestParam(value="userProjectID", required=false) Integer userProjectID,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		if (userProjectID==0) {
			userProjectID = null;
		}
		Page<Project> projects = projectDao.findAllProject(provinceid,keyword,userProjectID);
		Page<ProjectDto> projectDtos = new Page<ProjectDto>();
		for (Project project : projects) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setProject(project);
			if (project.getPr_id()!=null&&project.getCi_id()!=null) {
				ProvinceInfo provinceInfo = cityDao.findProvinceById(project.getPr_id());
				projectDto.setProvinceName(provinceInfo.getPr_province());
				CityInfo cityInfo = cityDao.findCityById(project.getCi_id());
				projectDto.setCityName(cityInfo.getCi_city());
			}
			projectDtos.add(projectDto);
		}
		projectDtos.setPageSize(projects.getPageSize());
		projectDtos.setPages(projects.getPages());
		projectDtos.setTotal(projects.getTotal());
		return new PageInfo<ProjectDto>(projectDtos);
		
	}
	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	@ResponseBody
	public Object addProject(Project project) throws UnsupportedEncodingException {
		if (project.getPro_name() == null || project.getContacts_name() == null) {
			return new ResultDto(-1, "项目名和联系人不能为空");
		}
		projectDao.insert(project);
		return new BaseDto(0);
	}
	
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public Object updateProject(Project project){
		if (project.getPro_name() == null || project.getContacts_name() == null) {
			return new ResultDto(-1, "项目名和联系人不能为空");
		}
		projectDao.updateByPrimaryKey(project);
		return new BaseDto(0);
	}
	
	
	@RequestMapping(value = "/findProjectByID")
	@ResponseBody
	public Object findProjectByID(Integer projectID) {
		Project project = projectDao.selectByPrimaryKey(projectID);
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProject(project);
		if (project.getPr_id()!=null&&project.getCi_id()!=null) {
			ProvinceInfo provinceInfo = cityDao.findProvinceById(project.getPr_id());
			projectDto.setProvinceName(provinceInfo.getPr_province());
			CityInfo cityInfo = cityDao.findCityById(project.getCi_id());
			projectDto.setCityName(cityInfo.getCi_city());
		}
		return projectDto;
	}
	
	@RequestMapping(value = "/deleteProjectByID")
	@ResponseBody
	public Object deleteProjectByID(Integer projectID) {
		 projectDao.deleteByPrimaryKey(projectID);
		 componentDao.deleteByProId(projectID);
		 SysUserDao.deleteByProId(projectID);
		 return new BaseDto(0);
	}
	
	@RequestMapping(value = "/deleteProjectByIDs")
	@ResponseBody
	public Object deleteProjectByIDs(Integer[] projectIDs) {
		for(Integer projectID:projectIDs){
			projectDao.deleteByPrimaryKey(projectID);
		}
		return new BaseDto(0);
	}

	 @RequestMapping(value = "/findAllProject")
	 @ResponseBody
	    public Object findAllProject() {
	        return projectDao.findAllProjectList();
	    }
	  
	 @RequestMapping(value = "/findAllProjectByUserID")
		@ResponseBody
		public Object findAllProjectByUserID(Integer userProjectID) {
		 if (userProjectID==0) {
			userProjectID = null;
		 }
		 return projectDao.findProjectsByUserID(userProjectID);
		}
	 
	 /**
		 *app上传文件()
	 * @throws IOException 
		 */
		@RequestMapping(value="/importPic")
		@ResponseBody
		public ResultDto importPic(@RequestParam(value = "files", required = false) MultipartFile[] files) throws IOException{
			/**
			 * 保存上传的图片
			 */
			boolean res=false;
			if (null != files && files.length>0) {		
				List<UploadFileEntity> fileEntities = new ArrayList<UploadFileEntity>();				
				for (int i = 0; i < files.length; i++) {
					//获取文件的原始名字
					String fileName = files[i].getOriginalFilename();
					String rFileName = fileName.replaceAll("#", "");
					fileEntities.add(new UploadFileEntity(rFileName, files[i], "uploadPic"));					
					Component component = new Component();
					component.setComponent_num(fileName.substring(0,fileName.lastIndexOf(".")));
					component.setDrawing(FtpService.FILE_Url+"uploadPic/"+rFileName);
					componentDao.updateByComponentNum(component);
				}
				//保存文件
				res = ftpservice.uploadFileList(fileEntities);			
				}
				if( res == true ) 
					return new ResultDto(1,"上传成功");
				else {
					return new ResultDto(2,"上传失败");
				}
			
		}
	 
	 /**
		 *导入构件
	 * @throws IOException 
		 */
		@RequestMapping(value="/fileUpload")
		@ResponseBody
		public ResultDto fileUpload(@RequestParam(value = "file", required = false) MultipartFile[] files,
				Integer pro_id) throws IOException{
			//上传失败的数据
			String errStr = "";
			//上传成功的数量
			int successCount = 0;
			/**
			 * 保存上传的excel
			 */
			boolean res=false;
			if (null != files && files.length>0) {		
				List<UploadFileEntity> fileEntities = new ArrayList<UploadFileEntity>();				
				for (int i = 0; i < files.length; i++) {
					//获取文件的原始名字
					String fileName = files[i].getOriginalFilename();
					//文件保存的名字
					String saveName = System.currentTimeMillis() + fileName;
					fileEntities.add(new UploadFileEntity(saveName, files[i], "uploadComp"));					
				}
				//保存文件
				res = ftpservice.uploadFileList(fileEntities);			
				}
			/**
			 * 保存成功后解析excel，并将数据存到数据库
			 */
				if( res == true ) {
					String nowDate = TimeUtil.dateFormat.format(new Date());
					List<Map<String, String>> result = ExcelImportUtil.readExcel(files[0].getInputStream(), 1, 0, 0);
					System.out.println(result.size());
					successCount = result.size();
					for (Map<String, String> map : result) {
						Component component = new Component();
						
						if(map.get("var0") == null){
							continue;
						}
						
						List<Component> comlist = componentDao.getComponentBycompNum(map.get("var0")); 
						//数据库中有该构件
						if( comlist != null && comlist.size() > 0) {
							
							errStr += map.get("var0") + ";";
							String drawImg;
							if(map.get("var4") == null) {
								drawImg = "http://139.196.139.164:65531/shfb/uploadPic/blank.png";
							}else{
								drawImg = "http://139.196.139.164:65531/shfb/uploadPic/"+map.get("var4").trim();
							}
							componentDao.updateByExcelComponentNum(comlist.get(0).getComponent_num(), map.get("var5"), map.get("var6"), map.get("var7"), drawImg);
							successCount--;
							continue;
						}
						
						component.setComponent_num(map.get("var0"));
						component.setSingle_name(map.get("var1"));
						component.setFloor(map.get("var2"));
						component.setComponent_type(map.get("var3"));
						if(map.get("var4") == null) {
							component.setDrawing("http://139.196.139.164:65531/shfb/uploadPic/blank.png");
						}else{
							component.setDrawing("http://139.196.139.164:65531/shfb/uploadPic/"+map.get("var4").trim());
						}
						component.setConcrete_strength(map.get("var5"));
						component.setWeight(map.get("var6"));
						component.setComponent_size(map.get("var7"));
						component.setPro_id(pro_id);
						component.setImportDate(nowDate);
						componentDao.insertSelective(component);
					
					}
					
					if(!errStr.equals("")) {
						System.out.println("重复构件"+errStr);
						return new ResultDto(1,"导入成功,数量："+successCount+";以下编号重复:"+errStr);
					}
					
					return new ResultDto(1,"导入成功,数量："+successCount);
				
				} else {
					return new ResultDto(2,"导入失败");
				}
			
		}
			
		
}
