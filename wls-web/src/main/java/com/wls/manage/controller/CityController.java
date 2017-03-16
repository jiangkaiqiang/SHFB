package com.wls.manage.controller;

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
import com.wls.manage.dao.CityMapper;
import com.wls.manage.dao.ProvinceMapper;
import com.wls.manage.dao.SchoolMapper;
import com.wls.manage.dto.BaseDto;
import com.wls.manage.dto.ResultDto;
import com.wls.manage.entity.School_infoEntity;
import com.wls.manage.entity.UserEntity;
import com.wls.manage.util.EncodeUtil;

@Controller
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private ProvinceMapper provinceListMapper;

    @Autowired
    private CityMapper cityListDao;
    
    @Autowired
    private SchoolMapper schoolMapper;

    
    @RequestMapping(value = "/findProvinceList", method = RequestMethod.GET)
    @ResponseBody
    public Object findProvinceList() {
        return provinceListMapper.findProvinceList();
    }
    
    @RequestMapping(value = "/findCityList", method = RequestMethod.GET)
    @ResponseBody
    public Object findCityList() {
        return cityListDao.findCityList();
    }
    
    @RequestMapping(value = "/findSchoolList", method = RequestMethod.GET)
    @ResponseBody
    public Object findSchoolList() {
        return schoolMapper.findSchoolList();
    }
    
    @RequestMapping(value = "/findCitysByProvinceId", method = RequestMethod.GET)
    @ResponseBody
    public Object findCitysByProvinceId(@RequestParam Integer provinceID) {
        return cityListDao.findCitysByProvinceId(provinceID);
    }
    
    @RequestMapping(value = "/findSchoolsByCityId", method = RequestMethod.GET)
    @ResponseBody
    public Object findSchoolsByCityId(@RequestParam Integer cityID) {
        return cityListDao.findSchoolsByCityId(cityID);
    }

    @RequestMapping(value = "/findCityById", method = RequestMethod.GET)
    @ResponseBody
    public Object findCityById(@RequestParam Integer CityID) {
        return cityListDao.findCityById(CityID);
    }
    
    @RequestMapping(value = "/findProvinceById", method = RequestMethod.GET)
    @ResponseBody
    public Object findProvinceById(@RequestParam Integer provinceID) {
        return cityListDao.findProvinceById(provinceID);
    }
    
    @RequestMapping(value = "/findSchoolById", method = RequestMethod.GET)
    @ResponseBody
    public Object findSchoolById(@RequestParam Integer schoolID) {
        return cityListDao.findSchoolById(schoolID);
    }
    
    @RequestMapping(value = "/findSchoolByName", method = RequestMethod.GET)
    @ResponseBody
    public Object findSchoolByName(@RequestParam String schoolName) {
    	if (schoolName!=null) {
    		 return cityListDao.findSchoolByName(schoolName);
		}
    	else{
    		return null;
    	}
    }
    
    @RequestMapping(value = "/findProvinceByName", method = RequestMethod.GET)
    @ResponseBody
    public Object findProvinceByName(@RequestParam String provinceName) {
    	if (provinceName!=null) {
    		 return cityListDao.findProvinceByName(provinceName);
		}
    	else{
    		return null;
    	}
    }
    
    @RequestMapping(value = "/findCityByNameAndProvinceId", method = RequestMethod.GET)
    @ResponseBody
    public Object findCityByNameAndProvinceId(@RequestParam Integer provinceID,@RequestParam String cityName) {
    	if (cityName!=null) {
    		 return cityListDao.findCityByNameAndProvinceId(cityName,provinceID);
		}
    	else{
    		return null;
    	}
    }
    
    @RequestMapping(value = "/findSchoolByNameAndCityId", method = RequestMethod.GET)
    @ResponseBody
    public Object findSchoolByNameAndCityId(@RequestParam Integer cityID,@RequestParam String schoolName) {
    	if (schoolName!=null) {
    		 return cityListDao.findSchoolByNameAndCityId(schoolName,cityID);
		}
    	else{
    		return null;
    	}
    }
    
    @RequestMapping(value = "/findSchoolListForBg", method = RequestMethod.POST)
	@ResponseBody
	public Object findSchoolListForBg(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		return new PageInfo<School_infoEntity>(schoolMapper.findAllSchoolForBg(keyword));
	}
    @RequestMapping(value = "/deleteSchool", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteSchool(int schoolID) {
		 schoolMapper.deleteSchool(schoolID);
		 return new BaseDto(0);
	}
	
	@RequestMapping(value = "/deleteBySchoolIDs")
	@ResponseBody
	public Object deleteBySchoolIDs(Integer[] schoolIDs) {
		for(Integer schoolID:schoolIDs){
			schoolMapper.deleteSchool(schoolID);
		}
		return new BaseDto(0);
	}
	@RequestMapping(value = "/addSchool", method = RequestMethod.GET)
	@ResponseBody
	public Object addUser(School_infoEntity schoolEntity) throws UnsupportedEncodingException {
		School_infoEntity school = new School_infoEntity();
		if (schoolEntity.getSh_shool() == null || schoolEntity.getSh_city()==null) {
			return new ResultDto(-1, "学校名和城市不能为空");
		}
		school.setSh_shool(URLDecoder.decode(schoolEntity.getSh_shool(), "UTF-8"));
		school.setSh_city(schoolEntity.getSh_city());
		schoolMapper.insertSchool(school);
		return new BaseDto(0);
	}
}
