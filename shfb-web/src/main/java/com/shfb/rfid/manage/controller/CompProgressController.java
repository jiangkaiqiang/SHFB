package com.shfb.rfid.manage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shfb.rfid.manage.dao.CompProgressMapper;

@Controller
@RequestMapping(value = "/compProgress")
public class CompProgressController {

    @Autowired
    private CompProgressMapper compProgressDao;
    
    
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    @ResponseBody
    public Object selectByPrimaryKey(Integer component_id) {
        return compProgressDao.selectByPrimaryKey(component_id);
    }

    
}
