package com.shfb.rfid.manage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shfb.rfid.manage.dao.CityMapper;
import com.shfb.rfid.manage.dao.ProvinceMapper;

@Controller
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private ProvinceMapper provinceListMapper;

    @Autowired
    private CityMapper cityListDao;
    
    
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
    
    
    @RequestMapping(value = "/findCitysByProvinceId", method = RequestMethod.GET)
    @ResponseBody
    public Object findCitysByProvinceId(@RequestParam Integer provinceID) {
        return cityListDao.findCitysByProvinceId(provinceID);
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
    
}
