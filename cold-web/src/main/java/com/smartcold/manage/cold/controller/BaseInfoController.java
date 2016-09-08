package com.smartcold.manage.cold.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smartcold.manage.cold.dao.newdb.*;
import com.smartcold.manage.cold.entity.newdb.StorageKeyValue;
import com.smartcold.manage.cold.enums.StorageType;
import com.smartcold.manage.cold.service.GoodsService;
import com.smartcold.manage.cold.service.StorageService;
import com.smartcold.manage.cold.util.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/baseInfo")
public class BaseInfoController extends BaseController {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private PackMapper packDao;
	
	@Autowired
	private WallMaterialMapper wallMaterialDao;
	
	@Autowired
	private UsageMapper usageDao;
	
	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "/findAllGoods", method = RequestMethod.GET)
	@ResponseBody
	public Object findAllGoods(){
		return goodsService.getAllGoods();
	}
	
	@RequestMapping(value = "/findAllPack", method = RequestMethod.GET)
	@ResponseBody
	public Object findAllPack(){
		return packDao.findAll();
	}
	
	@RequestMapping(value = "/findAllWallMaterial", method = RequestMethod.GET)
	@ResponseBody
	public Object findAllWallMaterial(){
		return wallMaterialDao.findAll();
	}
	
	@RequestMapping(value = "/findAllUsage", method = RequestMethod.GET)
	@ResponseBody
	public Object findAllUsage(){
		return usageDao.findAll();
	}
	
	@RequestMapping("/getKeyValueData")
	@ResponseBody
	public Object getKeyValueData(@RequestParam("type")Integer type, @RequestParam("oid")int oid,
			@RequestParam("key")String key,
			@RequestParam(value="nums",defaultValue="480")Integer nums){
		return storageService.findByNums(type, oid, key, nums);
	}
	
	@RequestMapping("/getKeyValueDataByTime")
	@ResponseBody
	public Object getKeyValueDataByTime(@RequestParam("type")Integer type, @RequestParam("oid")int oid,
			@RequestParam("key")String key,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime){
		return storageService.findByTime(type, oid, key, startTime, endTime);
	}
	
	  /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public  int daysBetween(Date smdate,Date bdate) 
    {    
        try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			smdate=sdf.parse(sdf.format(smdate));  
			bdate=sdf.parse(sdf.format(bdate));  
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			long between_days=(time2-time1)/(1000*3600*24);  
            return Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			return -1;
		}           
    } 
    
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public  String getDateFormat(int day) 
    {    
    	System.err.println(day);
	     if(day<=1|day==-1) return "yyyy-MM-dd HH:mm:ss";
	     else if(day<=3) return "yyyy-MM-dd HH:mm";
	     else if(day<=5) return "yyyy-MM-dd HH";
	     else  return "yyyy-MM-dd";
    } 
	
	@RequestMapping("/getKeyValueDataByFilter")
	@ResponseBody
	public ResponseData<Object> getKeyValueDataByFilter(Integer type,String key,Integer[] oids,String[] onames, String startTime,String endTime){
		try {
			System.err.println(type+"/"+key+"/"+oids+"/"+onames+"/"+startTime+"/"+endTime);
			if(key!=null&&key!=""){
				 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			        Date sttime=sdf.parse(startTime);  
			        Date edTime=sdf.parse(endTime);  
			        String groupfm=getDateFormat(daysBetween(sttime, edTime));
			        System.err.println(groupfm);
			        HashMap<String,Object> restData=new HashMap<String, Object>();
			        List<Object> restList=new ArrayList<Object>();
			        int maxsize=0;int index=-1;
			        for (int i = 0; i < oids.length; i++) {
			        	HashMap<String, Object> linmap=new HashMap<String, Object>();
			             int oid=	oids[i];String oname=onames[i];		        
			        	 List<StorageKeyValue> datalist = storageService.findByTime(type, oid, key, sttime, edTime);
			        	 if(datalist.size()>maxsize){ index=i; };
			        	 linmap.put("type", "line");
			        	 linmap.put("data", datalist);
			        	 linmap.put("name", oname);
			        	 restList.add(linmap);
					}
			        
			        restData.put("ydata", restList);
//			        restData.put("xData", value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseData.newSuccess();
	}

}
