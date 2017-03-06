package com.wls.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wls.manage.dao.CityMapper;
import com.wls.manage.dao.ProvinceMapper;
import com.wls.manage.dao.SchoolMapper;

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
}
