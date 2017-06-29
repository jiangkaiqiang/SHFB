package com.shfb.rfid.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
					fileEntities.add(new UploadFileEntity(fileName, files[i], "uploadPic"));					
				}
				//保存文件
				res = ftpservice.uploadFileList(fileEntities);			
				}
				if( res == true ) 
					return new ResultDto(2,"sucess");
				else {
					return new ResultDto(2,"server err");
				}
			
		}
	 
	 /**
		 *app上传文件()
	 * @throws IOException 
		 */
		@RequestMapping(value="/fileUpload")
		@ResponseBody
		public ResultDto fileUpload(@RequestParam(value = "file", required = false) MultipartFile[] files,
				Integer pro_id) throws IOException{
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
					String saveName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
					fileEntities.add(new UploadFileEntity(saveName, files[i], "uploadComp"));					
				}
				//保存文件
				res = ftpservice.uploadFileList(fileEntities);			
				}
			/**
			 * 保存成功后解析excel，并将数据存到数据库
			 */
				if( res == true ) {
					List<Map<String, String>> result = ExcelImportUtil.readExcel(files[0].getInputStream(), 1, 0, 0);
					for (Map<String, String> map : result) {
						Component component = new Component();
						component.setComponent_name(map.get("var0"));
						if(map.get("var1") == null){
							continue;
						}
						component.setComponent_num(map.get("var1"));
						component.setSingle_name(map.get("var2"));
						component.setFloor(map.get("var3"));
						component.setComponent_type(map.get("var4"));
						component.setComponent_size(map.get("var5"));
						component.setPro_id(pro_id);
						componentDao.insertSelective(component);
					}
					return new ResultDto(2,"sucess");
				} else {
					return new ResultDto(2,"server err");
				}
			
		}
			
		
}
