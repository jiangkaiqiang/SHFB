package com.shfb.rfid.manage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.css.ElementCSSInlineStyle;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.RecordMapper;
import com.shfb.rfid.manage.dto.BaseDto;
import com.shfb.rfid.manage.dto.CarNumDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.Record;
import com.shfb.rfid.manage.service.FtpService;
import com.shfb.rfid.manage.util.MathUtil;
import com.shfb.rfid.manage.util.ServerInterface;
import com.shfb.rfid.manage.util.TimeUtil;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value = "/record")
public class RecordController extends BaseController {
	public static String weightDiff = ""; //总的重量差值
	public static String timeDiff = ""; //总的时间差值
	@Autowired
	private RecordMapper recordDao;
	@Autowired
	private FtpService ftpservice;
	
	@RequestMapping(value = "/findRecordList", method = RequestMethod.POST)
	@ResponseBody
	public Object findRecordList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="startTime", required=false) String startTime,
			@RequestParam(value="endTime", required=false) String endTime,
			@RequestParam(value="startEntryTime", required=false) String startEntryTime,
			@RequestParam(value="endEntryTime", required=false) String endEntryTime,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="material", required=false) String material,
			@RequestParam(value="companyName", required=false) String companyName) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		if(null==material||material.equals("undefined"))
			material = null;
		else{
			material = URLDecoder.decode(material, "UTF-8");
		}
		if(null==companyName||companyName.equals("undefined"))
			companyName = null;
		else{
			companyName = URLDecoder.decode(companyName, "UTF-8");
		}
		Page<Record> records = recordDao.findAllRecords(keyword,startTime,endTime,startEntryTime,endEntryTime,material,companyName);
		return new PageInfo<Record>(records);
		
	}

	
	@RequestMapping(value = "/findErrorEntryRecordList", method = RequestMethod.POST)
	@ResponseBody
	public Object findErrorEntryRecordList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="startTime", required=false) String startTime,
			@RequestParam(value="endTime", required=false) String endTime,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		Page<Record> records = recordDao.findAllErrorEntryRecords(keyword,startTime,endTime);
		return new PageInfo<Record>(records);
		
	}
	
	@RequestMapping(value = "/findErrorLeaveRecordList", method = RequestMethod.POST)
	@ResponseBody
	public Object findErrorLeaveRecordList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="startTime", required=false) String startTime,
			@RequestParam(value="endTime", required=false) String endTime,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		Page<Record> records = recordDao.findAllErrorLeaveRecords(keyword,startTime,endTime);
		return new PageInfo<Record>(records);
		
	}
	
	@RequestMapping(value = "/findErrorTimeRecordList", method = RequestMethod.POST)
	@ResponseBody
	public Object findErrorTimeRecordList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="startTime", required=false) String startTime,
			@RequestParam(value="endTime", required=false) String endTime,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		Page<Record> records = recordDao.findAllErrorTimeRecords(keyword,startTime,endTime);
		return new PageInfo<Record>(records);
		
	}
	
	
	@RequestMapping(value = "/addRecordEntry")
	@ResponseBody
	public Object addRecordEntry(HttpServletRequest request) throws UnsupportedEncodingException {
		InputStream is= null; 
		String contentStr=""; 
				
		try {
			is = request.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		try {
			contentStr= IOUtils.toString(is, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(contentStr);
		Record record = new Record();
		
		String[] data;
		String qrcodeStr = null;
		if(contentStr.indexOf("{") == -1) {
			 data = contentStr.split(",");
		} else {
			int k = contentStr.indexOf("{");
			data = contentStr.substring(0, k).split(",");
			qrcodeStr = contentStr.substring(k);
		}
		
		//二维码信息
		if(qrcodeStr != null) {
			JSONObject jb = JSONObject.fromObject(qrcodeStr);
			String driver = jb.getString("id");
			String ticketId = jb.getString("ticketId");
			
			Map<String, String> res = ServerInterface.getUserInfo(driver);
			if(res != null) {
				record.setUserName(res.get("userName"));
				record.setTel(res.get("tel"));
				record.setDeliverCompanyName(res.get("deliverCompanyName"));
				record.setCompanyName(res.get("companyName"));
				record.setCarVarieties(res.get("carVarieties"));
				record.setDriverNo(res.get("driverNo"));
				record.setTicketId(ticketId);
			}
			
		} 
		
		//车牌号
		String carNumStr = data[0].trim();
		
		if("null".equals(carNumStr)) {
			carNumStr = null;
		}
		
		record.setCar_num(carNumStr);
		record.hashCode();
		Date date = TimeUtil.stringToDate(data[1], "yyyyMMddHHmmss");
		String dateStr = TimeUtil.dateToString(date, "yyyy-MM-dd HH:mm:ss");
		
		Record recordLateLy = recordDao.selectCarNum(carNumStr);
		if(recordLateLy != null && recordLateLy.getLeave_time() == null) {
			data[3] = "出场";
		} else {
			data[3] = "进场";
		}
		
		if("进场".equals(data[3])) {
			record.setEntry_time(dateStr);
			record.setEntry_weight(data[2]);
			record.setEntry_pic("http://139.196.139.164:65531/weight/uploadPic/" + data[4]);
			recordDao.insert(record);			
			return new ResultDto(2,"添加成功");
		}
		if("出场".equals(data[3])) {
			
			record.setLeave_time(dateStr);
			record.setLeave_weight(data[2]);
			record.setLeave_pic("http://139.196.139.164:65531/weight/uploadPic/" + data[4]);
			
			Record recordTem = recordDao.selectEntryRecord(record.getCar_num());
			
			if(recordTem != null && (!"".equals(record.getCar_num()))) {
				record.setRecord_id(recordTem.getRecord_id());
				recordDao.updateByPrimaryKeySelective(record);
				double qrQuantity = MathUtil.div(Double.parseDouble(recordTem.getEntry_weight()), 2350.0, 2);
				double hcQuantity = MathUtil.div(Double.parseDouble(data[2]), 2350.0, 2);

				//调用接口修改小票
				ServerInterface.updataSign(recordTem.getTicketId(), qrQuantity+"", hcQuantity+"");
				return new ResultDto(2,"添加成功");
			}
			
			
		}
		 return new ResultDto(3,"添加失败");
		
			
	}
	
	@RequestMapping(value = "/addRecordLeave")
	@ResponseBody
	public Object addRecordLeave(Record record,@RequestParam(value = "files", required = false) MultipartFile[] files) throws UnsupportedEncodingException {
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
				record.setLeave_pic(FtpService.FILE_Url+"uploadPic/"+fileName);
			}
			//保存文件
			res = ftpservice.uploadFileList(fileEntities);	
			recordDao.insert(record);
			}
			if(res == true ) 
				return new ResultDto(1,"添加成功");
			else {
				return new ResultDto(2,"添加失败");
			}
	}
	
	@RequestMapping(value = "/updateRecord")
	@ResponseBody
	public Object updateRecord(Record record) throws UnsupportedEncodingException{
		record.setCompanyName(URLDecoder.decode(record.getCompanyName(), "UTF-8"));
		record.setMaterial(URLDecoder.decode(record.getMaterial(), "UTF-8"));
		recordDao.updateByPrimaryKeySelective(record);
		return new BaseDto(0);
	}
	
	
	@RequestMapping(value = "/addCarNumByRecordIdEntry")
	@ResponseBody
	public Object addCarNumByRecordIdEntry(@RequestParam(value="recordId", required=false) Integer recordId,
			@RequestParam(value="carNum", required=false) String carNum) throws UnsupportedEncodingException{
		if(carNum.equals("undefined"))
			carNum = null;
		else{
			carNum = URLDecoder.decode(carNum, "UTF-8");
		}
		Record record = new Record();
		record.setRecord_id(recordId);
		record.setCar_num(carNum);
		recordDao.addCarNumByRecordIdEntry(record);
		return new BaseDto(0);
	}
	
	@RequestMapping(value = "/addCarNumByRecordIdLeave")
	@ResponseBody
	public Object addCarNumByRecordIdLeave(@RequestParam(value="recordId", required=false) Integer recordId,
			@RequestParam(value="carNum", required=false) String carNum) throws UnsupportedEncodingException{
		if(carNum.equals("undefined"))
			carNum = null;
		else{
			carNum = URLDecoder.decode(carNum, "UTF-8");
		}
		Record leaveRecord =  recordDao.selectByPrimaryKey(recordId);
		Record mergeRecord = recordDao.findMergeRecord(carNum, leaveRecord.getLeave_time());
		if (mergeRecord==null) {
			return new BaseDto(1);
		}
		Record updateRecord = new Record();
		updateRecord.setRecord_id(mergeRecord.getRecord_id());
		updateRecord.setLeave_pic(leaveRecord.getLeave_pic());
		updateRecord.setLeave_time(leaveRecord.getLeave_time());
		updateRecord.setLeave_weight(leaveRecord.getLeave_weight());
		recordDao.updateByPrimaryKeySelective(updateRecord);
		recordDao.deleteByPrimaryKey(leaveRecord.getRecord_id());
		return new BaseDto(0);
	}
	
	@RequestMapping(value = "/findFirstRecord")
	@ResponseBody
	public Object findFirstRecord(HttpServletRequest request) {
				
//		HttpSession session = request.getSession();
//		Record record = (Record) session.getServletContext().getAttribute("firstRecord");
		Record recordEntry = recordDao.findFirstRecord();
		Record recordLeave = recordDao.findFirstLeaveRecord();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (sdf.parse(recordEntry.getEntry_time()).getTime() >= sdf.parse(recordLeave.getLeave_time()).getTime()) {
				return recordEntry;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return recordLeave;
	}
	
	@RequestMapping(value = "/findRecordByID")
	@ResponseBody
	public Object findRecordByID(Integer recordID) {
		Record record = recordDao.selectByPrimaryKey(recordID);
		return record;
	}
	
	@RequestMapping(value = "/deleteRecordByID")
	@ResponseBody
	public Object deleteRecordByID(Integer recordID) {
		 recordDao.deleteByPrimaryKey(recordID);
		 return new BaseDto(0);
	}
	
	@RequestMapping(value = "/deleteRecordByIDs")
	@ResponseBody
	public Object deleteRecordByIDs(Integer[] recordIDs) {
		for(Integer recordID:recordIDs){
			recordDao.deleteByPrimaryKey(recordID);
		}
		return new BaseDto(0);
	}
	
	
	@RequestMapping(value = "/numStatistics")
	@ResponseBody
	public Object numStatistics() {
		List<CarNumDto> entrylist = recordDao.numEntryStatistics();
		Map<String, Integer> entryMap = new HashMap<String, Integer>();
		List<Integer> entryArray = new ArrayList<Integer>();
		for (CarNumDto carNumDto : entrylist) {
			entryMap.put(carNumDto.getSta_date(), carNumDto.getCar_num());
		}
		
		List<CarNumDto> leavelist = recordDao.numLeaveStatistics();
		Map<String, Integer> leaveMap = new HashMap<String, Integer>();
		List<Integer> leaveArray = new ArrayList<Integer>();
		for (CarNumDto carNumDto : leavelist) {
			leaveMap.put(carNumDto.getSta_date(), carNumDto.getCar_num());
		}
		
		Set<String> dateSet = new TreeSet<String>();
		
		for (CarNumDto carNumsDto : entrylist) {
			dateSet.add(carNumsDto.getSta_date());
		}
		for (CarNumDto carNumDto : leavelist) {
			dateSet.add(carNumDto.getSta_date());
		}
	
		for (String dateEle : dateSet) {
			entryArray.add(entryMap.get(dateEle)==null?0:entryMap.get(dateEle));
			leaveArray.add(leaveMap.get(dateEle)==null?0:leaveMap.get(dateEle));
		}
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("dateRes", dateSet);
		res.put("entryArray", entryArray);
		res.put("leaveArray", leaveArray);
		return new ResultDto(res);
	}
	
	@RequestMapping(value = "/weightStatistics")
	@ResponseBody
	public Object weightStatistics() {
		List<CarNumDto> entrylist = recordDao.weightEntryStatistics();
		Map<String, Integer> entryMap = new HashMap<String, Integer>();
		List<Integer> entryArray = new ArrayList<Integer>();
		for (CarNumDto carNumDto : entrylist) {
			entryMap.put(carNumDto.getSta_date(), carNumDto.getCar_num());
		}
		
		List<CarNumDto> leavelist = recordDao.weightLeaveStatistics();
		Map<String, Integer> leaveMap = new HashMap<String, Integer>();
		List<Integer> leaveArray = new ArrayList<Integer>();
		for (CarNumDto carNumDto : leavelist) {
			leaveMap.put(carNumDto.getSta_date(), carNumDto.getCar_num());
		}
		
		Set<String> dateSet = new TreeSet<String>();
		
		for (CarNumDto carNumsDto : entrylist) {
			dateSet.add(carNumsDto.getSta_date());
		}
		for (CarNumDto carNumDto : leavelist) {
			dateSet.add(carNumDto.getSta_date());
		}
	
		for (String dateEle : dateSet) {
			entryArray.add(entryMap.get(dateEle)==null?0:entryMap.get(dateEle));
			leaveArray.add(leaveMap.get(dateEle)==null?0:leaveMap.get(dateEle));
		}
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("dateRes", dateSet);
		res.put("entryArray", entryArray);
		res.put("leaveArray", leaveArray);
		return new ResultDto(res);
	}
	
	

}
