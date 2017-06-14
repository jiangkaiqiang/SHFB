package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.CityMapper;
import com.shfb.rfid.manage.dao.ProjectMapper;
import com.shfb.rfid.manage.dto.BaseDto;
import com.shfb.rfid.manage.dto.ProjectDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.entity.CityInfo;
import com.shfb.rfid.manage.entity.Project;
import com.shfb.rfid.manage.entity.ProvinceInfo;
import com.shfb.rfid.manage.util.EncodeUtil;
import com.github.pagehelper.Page;
@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {
	@Autowired
	private ProjectMapper projectDao;
	@Autowired
	private CityMapper cityDao;
	
	
	
	@RequestMapping(value = "/findProjectList", method = RequestMethod.POST)
	@ResponseBody
	public Object findProjectList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="provinceid", required=false) Integer provinceid,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		Page<Project> projects = projectDao.findAllProject(provinceid,keyword);
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

	
}
