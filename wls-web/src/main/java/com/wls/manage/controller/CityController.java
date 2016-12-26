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
    
    
}
