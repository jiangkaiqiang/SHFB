package com.wls.manage.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wls.manage.crawler.chuangyebang.chuangye.ListCrawler_cyb;
import com.wls.manage.crawler.iresearch.events.ListCrawler_iresearch;
import com.wls.manage.crawler.iyiou.heikeji.ListCrawler_iyiou_heikeji;
import com.wls.manage.crawler.iyiou.wenchuang.ListCrawler_iyiou_wenchuang;
import com.wls.manage.crawler.studentonline.psychology.ListCrawler_stuonline;
import com.wls.manage.dao.CommentMapper;
import com.wls.manage.dao.InforCategoryMapper;
import com.wls.manage.dao.InformationMapper;
import com.wls.manage.dao.ResponseMapper;
import com.wls.manage.dao.UserMapper;
import com.wls.manage.dto.BaseDto;
import com.wls.manage.dto.CommentDto;
import com.wls.manage.dto.InformationDto;
import com.wls.manage.dto.NewInfomationDto;
import com.wls.manage.dto.ResponseDto;
import com.wls.manage.dto.UploadFileEntity;
import com.wls.manage.entity.CommentEntity;
import com.wls.manage.entity.InformationEntity;
import com.wls.manage.entity.ResponseEntity;
import com.wls.manage.entity.UserEntity;
import com.wls.manage.service.FtpService;
import com.wls.manage.util.ResponseData;
/**
 * 资讯controller
 * @author jkq
 *
 */
@Controller
@RequestMapping(value = "/information")
public class InformationController extends BaseController {
	private static String baseDir = "picture";
	@Autowired
	private FtpService ftpService;

	@Autowired
	private InformationMapper informationDao;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResponseMapper responseMapper;
	/**
	 * 提供查询服务
	 * @param pageNum
	 * @param pageSize
	 * @param posterID
	 * @param keyword
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findAllInformation")
	@ResponseBody
	public Object findAllInformation(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="audit", required=false) Integer audit,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		if( !(audit == 1 || audit == 2 || audit == 3||audit == 4 || audit == 5) ){
			audit = null;
		}
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined")||keyword.equals(""))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		Page<InformationEntity> informationEntities = informationDao.findAllInformation(audit,keyword);
		Page<InformationDto> informationDtos = getInformationDtos(informationEntities);
		informationDtos.setPageSize(informationEntities.getPageSize());
		informationDtos.setPages(informationEntities.getPages());
		informationDtos.setTotal(informationEntities.getTotal());
		return new PageInfo<InformationDto>(informationDtos);
	}
	
	
	
	private Page<InformationDto> getInformationDtos(Page<InformationEntity> informationEntities) {
		Page<InformationDto> informationDtos = new Page<InformationDto>();
		for (InformationEntity informationEntity : informationEntities) {
			InformationDto informationDto = new InformationDto();
			informationDto.setId(informationEntity.getId());
			informationDto.setTitle(informationEntity.getTitle());
			informationDto.setTime(informationEntity.getTime());
			informationDto.setSource(informationEntity.getSource());
			informationDto.setContent(informationEntity.getContent());
			informationDto.setInfocategory(Integer.parseInt(informationEntity.getInfocategory()));
			switch (informationDto.getInfocategory()) {
			case 1:
				informationDto.setInfocategoryName("科技类");
				break;
			case 2:
				informationDto.setInfocategoryName("文娱类");
				break;
			case 3:
				informationDto.setInfocategoryName("创业类");
				break;
			case 4:
				informationDto.setInfocategoryName("时事类");
				break;
			case 5:
				informationDto.setInfocategoryName("校园类");
				break;
			default:
				break;
			}
			String[] inforCovers = informationEntity.getCoverpiclist().split(";");
			List<String> infList = new ArrayList<String>();
			for (String infor : inforCovers) {
				infList.add(infor);
			}
			informationDto.setCoverpiclist(infList);
			informationDto.setCoverpicnum(infList.size());
			List<CommentEntity> commentEntities = commentMapper.findCommentsByCommentId(informationEntity.getId().intValue(), 0);
			List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		    if (commentEntities!=null&&!commentEntities.isEmpty()) {
				for (CommentEntity commentEntity : commentEntities) {
					CommentDto commentDto = new CommentDto();
					commentDto.setCommenterid(commentEntity.getCommenterid());
					commentDto.setCommentid(commentEntity.getCommentid());
					commentDto.setCommenttime(commentEntity.getCommenttime());
					commentDto.setContent(commentEntity.getContent());
					commentDto.setFlag(commentEntity.getFlag());
					commentDto.setId(commentEntity.getId());
					UserEntity commentuserEntity = userMapper.findUserById(commentEntity.getCommenterid().intValue());
					commentDto.setUseravatar(commentuserEntity.getAvatar());
					commentDto.setUsernickname(commentuserEntity.getNickname());
					List<ResponseEntity> responseEntities = responseMapper.findresponsesByResponseId(commentEntity.getId().intValue());
					List<ResponseDto> responseDtos = new ArrayList<ResponseDto>();
					if (responseEntities!=null&&!responseEntities.isEmpty()) {
						for (ResponseEntity responseEntity : responseEntities) {
						   ResponseDto responseDto = new ResponseDto();
						   responseDto.setContent(responseEntity.getContent());
						   responseDto.setFlag(responseEntity.getFlag());
						   responseDto.setId(responseEntity.getId());
						   responseDto.setResponseid(responseEntity.getResponseid());
						   responseDto.setResponserid(responseEntity.getResponserid());
						   responseDto.setResponsetime(responseEntity.getResponsetime());
						   UserEntity responseuserEntity = userMapper.findUserById(responseEntity.getResponserid().intValue());
						   responseDto.setUseravatar(responseuserEntity.getAvatar());
						   responseDto.setUsernickname(responseuserEntity.getNickname());
						   responseDtos.add(responseDto);
						}	
					}
					commentDto.setResponsenum(responseDtos.size());
                    commentDto.setResponseDtos(responseDtos);
                    commentDtos.add(commentDto);
				}
			}
			informationDto.setCommentDtos(commentDtos);
			informationDto.setCommentnum(commentDtos.size());
			informationDtos.add(informationDto);
		}
		return informationDtos;
	}



	@RequestMapping(value = "/findInformationsByCate")
	@ResponseBody
	public Object findInformationsByCate(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize,@RequestParam(value="categoryID") String infocategory) throws UnsupportedEncodingException {
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 10:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		Page<InformationEntity> info = informationDao.findInformationByCategory(infocategory);
		PageInfo<InformationEntity> data = new PageInfo<InformationEntity>(info);
	    return ResponseData.newSuccess(data);
	}
	
	/**
	 * 删除资讯
	 * @param inforID
	 * @return
	 */
	@RequestMapping(value = "/deleteInformation")
	@ResponseBody
	public Object deleteInformation(int inforID) {
		 informationDao.deleteInformation(inforID);
		 return new BaseDto(0);
	}
	
	/**
	 * 根据资讯id查找资讯
	 * @param inforID
	 * @return
	 */
	@RequestMapping(value = "/findInformationByID")
	@ResponseBody
	public Object findInformationByID(@RequestParam Integer inforID) {
		InformationEntity informationEntity = informationDao.findInformationByID(inforID);
		Page<InformationEntity> informationEntities = new Page<InformationEntity>();
		informationEntities.add(informationEntity);
		InformationDto informationDto = getInformationDtos(informationEntities).get(0);
		return informationDto;
	}
	

	/**
	 * 将数据插入数据库，需要传入NewInfomationDto和自己设置的分类：1：科技类，2：文娱类，3：创业类，4：时事类，5：校园类
	 * @param newInfomationDtos
	 * @param category
	 */
	public void insertInformationByType(List<NewInfomationDto> newInfomationDtos,String category) {
		for (NewInfomationDto newInfomationDto : newInfomationDtos) {
			 InformationEntity informationEntity = new InformationEntity();
			    informationEntity.setContent(newInfomationDto.getContent());
			    informationEntity.setCoverpiclist(newInfomationDto.getPic());
			    informationEntity.setInfocategory(category);
			    informationEntity.setSource(newInfomationDto.getSource());
			    informationEntity.setTitle(newInfomationDto.getTitle());
			    informationEntity.setTime(newInfomationDto.getTime());
			    //数据库去重
			    if (informationDao.findInformationByTitle(informationEntity.getTitle())==null) {
			    	 informationDao.insertInformation(informationEntity);
				}
		}
	}
	
	
	/**
	 * C校园网-心理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addInformationWithCXiaoYuanPsy")
	@ResponseBody
	public Object addInformationWithCXiaoYuanPsy() throws Exception {
		ListCrawler_stuonline listCrawler = new ListCrawler_stuonline();
		List<NewInfomationDto> newInfomationDtos = listCrawler.parse();
		insertInformationByType(newInfomationDtos,"5");
		return ResponseData.newSuccess("添加成功");
	}
	
	/**
	 * 亿欧网-文创
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addInformationWithYiouWenChuang")
	@ResponseBody
	public Object addInformationWithYiouWenChuang() throws Exception {
		ListCrawler_iyiou_wenchuang listCrawler = new ListCrawler_iyiou_wenchuang();
		List<NewInfomationDto> newInfomationDtos = listCrawler.parse();
		insertInformationByType(newInfomationDtos,"2");
		return ResponseData.newSuccess("添加成功");
	}
	
	/**
	 * 亿欧网-黑科技
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addInformationWithYiouKeJi")
	@ResponseBody
	public Object addInformationWithYiouKeJi() throws Exception {
		ListCrawler_iyiou_heikeji listCrawler = new ListCrawler_iyiou_heikeji();
		List<NewInfomationDto> newInfomationDtos = listCrawler.parse();
		insertInformationByType(newInfomationDtos,"1");
		return ResponseData.newSuccess("添加成功");
	}
	
	/**
	 * 艾瑞
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addInformationWithIresearch")
	@ResponseBody
	public Object addInformationWithIresearch() throws Exception {
		ListCrawler_iresearch listCrawler = new ListCrawler_iresearch();
		List<NewInfomationDto> newInfomationDtos = listCrawler.parse();
		insertInformationByType(newInfomationDtos,"4");
		return ResponseData.newSuccess("添加成功");
	}
	
	/**
	 * 创业邦
	 * @param information
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addInformationWithChuangyebang")
	@ResponseBody
	public Object addInformationWithChuangyebang( 
			/*@RequestParam(required = false) MultipartFile uploadcoverpic,*/
			/*@RequestParam(required = false) String title,//标题
			@RequestParam(required = false) String content,//内容
			@RequestParam(required = false) String infocategory,//分类：“1”：科技类，“2”：互联网类，3：校园类；4：财经类；5：创业类  注意：，这个地方用数字不要用字符串
			@RequestParam(required = false) String source,//来源：腾讯新闻 等等
			@RequestParam(required = false) String coverpiclist,//封面图片：解析几张放在这，用特殊字符隔开，注意：就只有一张，零张和三张这三种情况，如果有大于三张那就只取三张，大于一张少于三张就去一张，没有就0张
			@RequestParam(required = false) String time//添加时间
*/			) throws Exception {
		/**
		 * 此处注释掉图片上传至ftp服务器，下次开发可能用到
		 */
		/*if (title == null || content == null) {
			return ResponseData.newFailure("标题和内容不能为空");
		}*/
		/*String dir = String.format("%s/infor/%s", baseDir, information.getId());
		if (uploadcoverpic != null) {
			*//**
			 * 这个地方需要把information爬取得图片存到ftp服务器上
			 *//*
			String fileName = String.format("information%s_%s.%s", information.getId(), new Date().getTime(), "jpg");
			UploadFileEntity uploadFileEntity = new UploadFileEntity(fileName, uploadcoverpic, dir);
			ftpService.uploadFile(uploadFileEntity);
			information.setCoverpiclist(FtpService.READ_URL+dir + "/" + fileName);
		}
		else {
			  throw new Exception("资讯上传图片时，封面图片为空异常");
		}*/
		ListCrawler_cyb listCrawler = new ListCrawler_cyb();
		List<NewInfomationDto> newInfomationDtos = listCrawler.parse();
		insertInformationByType(newInfomationDtos,"3");
		return ResponseData.newSuccess("添加成功");
	}
	
}
