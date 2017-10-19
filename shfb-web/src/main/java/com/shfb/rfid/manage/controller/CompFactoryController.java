package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.CityMapper;
import com.shfb.rfid.manage.dao.CompFactoryMapper;
import com.shfb.rfid.manage.dto.BaseDto;
import com.shfb.rfid.manage.dto.CompFactoryDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.entity.CityInfo;
import com.shfb.rfid.manage.entity.CompFactory;
import com.shfb.rfid.manage.entity.ProvinceInfo;
import com.shfb.rfid.manage.util.EncodeUtil;
import com.github.pagehelper.Page;
@Controller
@RequestMapping(value = "/compfactory")
public class CompFactoryController extends BaseController {
	@Autowired
	private CompFactoryMapper compfactoryDao;
	@Autowired
	private CityMapper cityDao;
	
	
	
	@RequestMapping(value = "/findCompFactoryList", method = RequestMethod.POST)
	@ResponseBody
	public Object findCompFactoryList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="provinceid", required=false) Integer provinceid,
			@RequestParam(value="userCompFactoryID", required=false) Integer userCompFactoryID,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		if (userCompFactoryID==0) {
			userCompFactoryID = null;
		}
		Page<CompFactory> compFactories = compfactoryDao.findAllCompFactory(provinceid,keyword,userCompFactoryID);
		Page<CompFactoryDto> compFactoryDtos = new Page<CompFactoryDto>();
		for (CompFactory compFactory : compFactories) {
			CompFactoryDto compFactoryDto = new CompFactoryDto();
			compFactoryDto.setCompfactory(compFactory);
			if (compFactory.getPr_id()!=null&&compFactory.getCi_id()!=null) {
				ProvinceInfo provinceInfo = cityDao.findProvinceById(compFactory.getPr_id());
				compFactoryDto.setProvinceName(provinceInfo.getPr_province());
				CityInfo cityInfo = cityDao.findCityById(compFactory.getCi_id());
				compFactoryDto.setCityName(cityInfo.getCi_city());
			}
			compFactoryDtos.add(compFactoryDto);
		}
		compFactoryDtos.setPageSize(compFactories.getPageSize());
		compFactoryDtos.setPages(compFactories.getPages());
		compFactoryDtos.setTotal(compFactories.getTotal());
		return new PageInfo<CompFactoryDto>(compFactoryDtos);
		
	}
	@RequestMapping(value = "/addCompFactory", method = RequestMethod.GET)
	@ResponseBody
	public Object addCompFactory(CompFactory compFactory) throws UnsupportedEncodingException {
		if (compFactory.getComp_factory_name() == null || compFactory.getContacts_name() == null) {
			return new ResultDto(-1, "构件厂名和联系人不能为空");
		}
		compfactoryDao.insert(compFactory);
		return new BaseDto(0);
	}
	
	@RequestMapping(value = "/updateCompFactory")
	@ResponseBody
	public Object updateCompFactory(CompFactory compFactory){
		if (compFactory.getComp_factory_name() == null || compFactory.getContacts_name() == null) {
			return new ResultDto(-1, "构件厂名和联系人不能为空");
		}
		compfactoryDao.updateByPrimaryKey(compFactory);
		return new BaseDto(0);
	}
	
	
	@RequestMapping(value = "/findCompFactoryByID")
	@ResponseBody
	public Object findCompFactoryByID(Integer compFactoryID) {
		CompFactory compFactory = compfactoryDao.selectByPrimaryKey(compFactoryID);
		CompFactoryDto compFactoryDto = new CompFactoryDto();
		compFactoryDto.setCompfactory(compFactory);
		if (compFactory.getPr_id()!=null&&compFactory.getCi_id()!=null) {
			ProvinceInfo provinceInfo = cityDao.findProvinceById(compFactory.getPr_id());
			compFactoryDto.setProvinceName(provinceInfo.getPr_province());
			CityInfo cityInfo = cityDao.findCityById(compFactory.getCi_id());
			compFactoryDto.setCityName(cityInfo.getCi_city());
		}
		return compFactoryDto;
	}
	
	@RequestMapping(value = "/deleteCompFactoryByID")
	@ResponseBody
	public Object deleteCompFactoryByID(Integer compFactoryID) {
		 compfactoryDao.deleteByPrimaryKey(compFactoryID);
		 return new BaseDto(0);
	}
	
	@RequestMapping(value = "/deleteCompFactoryByIDs")
	@ResponseBody
	public Object deleteCompFactoryByIDs(Integer[] compFactoryIDs) {
		for(Integer compFactoryID:compFactoryIDs){
			compfactoryDao.deleteByPrimaryKey(compFactoryID);
		}
		return new BaseDto(0);
	}
	
	/**
	 * 查询所有构件厂（为构件厂下拉框提供服务）
	 * @param compFactoryID
	 * @return
	 */
	@RequestMapping(value = "/findCompFactorys")
	@ResponseBody
	public List<CompFactory> findCompFactorys() {
		List<CompFactory> compFactorys = compfactoryDao.findCompFactorys();
		CompFactory compFactory = new CompFactory();
		compFactory.setComp_factory_id(0);
		compFactory.setComp_factory_name("全部");
		compFactorys.add(0,compFactory);
		return compFactorys;
	}
	
	/**
	 * 查询所有构件厂（为构件下单下拉框提供服务，不需要添加全部）
	 * @param compFactoryID
	 * @return
	 */
	@RequestMapping(value = "/findCompFactorysForOrder")
	@ResponseBody
	public List<CompFactory> findCompFactorysForOrder() {
		List<CompFactory> compFactorys = compfactoryDao.findCompFactorys();
		return compFactorys;
	}
	
}
