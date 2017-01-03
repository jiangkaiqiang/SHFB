package com.wls.manage.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wls.manage.crawler.tengxun.tech.PageParseJob;
import com.wls.manage.dao.CommentMapper;
import com.wls.manage.dao.PraiseMapper;
import com.wls.manage.dao.PublishMapper;
import com.wls.manage.dao.ResponseMapper;
import com.wls.manage.dao.UserMapper;
import com.wls.manage.dto.CommentDto;
import com.wls.manage.dto.PublishDto;
import com.wls.manage.dto.ResponseDto;
import com.wls.manage.entity.CommentEntity;
import com.wls.manage.entity.PraiseEntity;
import com.wls.manage.entity.PublishEntity;
import com.wls.manage.entity.ResponseEntity;
import com.wls.manage.entity.UserEntity;
import com.wls.manage.util.PageParseUtil;
import com.wls.manage.util.ResponseData;
/**
 * 
 * @author kaiqiang jiang
 * @version 创建时间：2016-9-13 下午2:24:54
 * 消息通知controller
 */
@Controller
@RequestMapping(value = "/publish")
public class PublishController extends BaseController {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PublishMapper publishMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private PraiseMapper praiseMapper;
	@Autowired
	private ResponseMapper responseMapper;
	
	/**
	 * 为前台user查询通知提供服务
	 * @param pageNum
	 * @param pageSize
	 * @param userID
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findPublishByUserId")
	@ResponseBody
	public Object findPublishByUserId(
			@RequestParam(value="userID", required=false) Integer userID) throws UnsupportedEncodingException {
		List<PublishEntity> publishEntities = publishMapper.findPublishByUserId(userID);
		List<PublishDto> pDtos = getPublishDtos(publishEntities);
		return pDtos;
	}
	
	private Page<PublishDto> getPublishDtos(List<PublishEntity> publishEntities,Integer userID) {
		Page<PublishDto> pDtos = new Page<PublishDto>();
		for (PublishEntity publishEntity : publishEntities) {
			PublishDto publishDto = new PublishDto();
			publishDto.setId(publishEntity.getId());
			publishDto.setContent(publishEntity.getContent());
			publishDto.setPubcategory(publishEntity.getPubcategory());
			switch (publishDto.getPubcategory()) {
			case 1:
				publishDto.setPubcategoryname("科技类");
				break;
			case 2:
				publishDto.setPubcategoryname("互联网类");
				break;
			case 3:
				publishDto.setPubcategoryname("校园类");
				break;
			case 4:
				publishDto.setPubcategoryname("财经类");
				break;
			case 5:
				publishDto.setPubcategoryname("创业类");
				break;
			default:
				break;
			}
			publishDto.setPublisherid(publishEntity.getPublisher());
			publishDto.setDescribe(publishEntity.getDescribe());
			publishDto.setPubtime(publishEntity.getPubtime());
			publishDto.setSchoolid(publishEntity.getSchoolid());
			publishDto.setTitle(publishEntity.getTitle());
			publishDto.setPubcover(publishEntity.getPubcover());
			UserEntity userEntity = userMapper.findUserById(publishEntity.getPublisher().intValue());
			if (userEntity!=null) {
				publishDto.setPublishername(userEntity.getNickname());
				publishDto.setPublisheravatar(userEntity.getAvatar());
			}
		
			List<CommentEntity> commentEntities = commentMapper.findCommentsByCommentId(publishEntity.getId().intValue(), 1);
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
		    
			publishDto.setCommentDtos(commentDtos);
			publishDto.setCommentnum(commentDtos.size());
			List<PraiseEntity> praiseEntities = praiseMapper.findPraisesByPublishId(publishEntity.getId().intValue());
			publishDto.setPraisenum(praiseEntities.size());
			for (PraiseEntity praiseEntity : praiseEntities) {
				if (praiseEntity.getPraiserid().intValue()==userID.intValue()) {
					publishDto.setPraiseflag(1);
					break;
				}
			}
			pDtos.add(publishDto);
		}
		return pDtos;
	}
	
	
	private Page<PublishDto> getPublishDtos(List<PublishEntity> publishEntities) {
		Page<PublishDto> pDtos = new Page<PublishDto>();
		for (PublishEntity publishEntity : publishEntities) {
			PublishDto publishDto = new PublishDto();
			publishDto.setId(publishEntity.getId());
			publishDto.setContent(publishEntity.getContent());
			publishDto.setPubcategory(publishEntity.getPubcategory());
			switch (publishDto.getPubcategory()) {
			case 1:
				publishDto.setPubcategoryname("科技类");
				break;
			case 2:
				publishDto.setPubcategoryname("互联网类");
				break;
			case 3:
				publishDto.setPubcategoryname("校园类");
				break;
			case 4:
				publishDto.setPubcategoryname("财经类");
				break;
			case 5:
				publishDto.setPubcategoryname("创业类");
				break;
			default:
				break;
			}
			publishDto.setPublisherid(publishEntity.getPublisher());
			publishDto.setDescribe(publishEntity.getDescribe());
			publishDto.setPubtime(publishEntity.getPubtime());
			publishDto.setSchoolid(publishEntity.getSchoolid());
			publishDto.setTitle(publishEntity.getTitle());
			publishDto.setPubcover(publishEntity.getPubcover());
			UserEntity userEntity = userMapper.findUserById(publishEntity.getPublisher().intValue());
			if (userEntity!=null) {
				publishDto.setPublishername(userEntity.getNickname());
				publishDto.setPublisheravatar(userEntity.getAvatar());
			}
		
			List<CommentEntity> commentEntities = commentMapper.findCommentsByCommentId(publishEntity.getId().intValue(), 1);
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
		    
			publishDto.setCommentDtos(commentDtos);
			publishDto.setCommentnum(commentDtos.size());
			List<PraiseEntity> praiseEntities = praiseMapper.findPraisesByPublishId(publishEntity.getId().intValue());
			publishDto.setPraisenum(praiseEntities.size());
			pDtos.add(publishDto);
		}
		return pDtos;
	}


	/**
	 * 根据分类查询发布
	 * @param pageNum
	 * @param pageSize
	 * @param categoryID
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findPublishList")
	@ResponseBody
	public Object findPublishList(
			@RequestParam(value="userID",required=false) Integer userID,
			@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="audit", required=false) Integer audit,
			@RequestParam(value="schoolid", required=false) Integer schoolid,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		if( !(audit == 1 || audit == 2 || audit == 3||audit == 4 || audit == 5 || audit == 6) ){
			audit = null;
		}
		if(schoolid==null||schoolid==-1){
			schoolid = null;
		}
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined")||keyword.equals(""))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		PageHelper.startPage(pageNum, pageSize);
		Page<PublishEntity> publishEntities = publishMapper.findPublishList(audit, keyword, schoolid);
		Page<PublishDto> publishDtos = getPublishDtos(publishEntities,userID);
		publishDtos.setPageSize(publishEntities.getPageSize());
		publishDtos.setPages(publishEntities.getPages());
		publishDtos.setTotal(publishEntities.getTotal());
	    return new PageInfo<PublishDto>(publishDtos);
	}
	
	/**
	 * 删除发布
	 * @param msgID
	 * @return
	 */
	@RequestMapping(value = "/deletePublish")
	@ResponseBody
	public Object deletePublish(Integer publishID,Integer userID) {
		 publishMapper.deletePublish(publishID);
		 return getPublishDtos(publishMapper.findPublishByUserId(userID));
	}
	
	/**
	 * 点赞发布
	 * @param publishID
	 * @param userID
	 * @return
	 */
	@RequestMapping(value = "/praisePublish")
	@ResponseBody
	public Object praisePublish(Integer publishID,Integer userID) {
		PraiseEntity praiseEntity = new PraiseEntity();
		praiseEntity.setPraiseid(BigInteger.valueOf(publishID));
		praiseEntity.setPraiserid(BigInteger.valueOf(userID));
		praiseMapper.insertPraise(praiseEntity);
		return ResponseData.newSuccess();
	}
	
	/**
	 * 根据id查询发布
	 * @param msgID
	 * @return
	 */

	@RequestMapping(value = "/findPublishByID")
	@ResponseBody
	public Object findPublishByID(@RequestParam int publishID) {
		ArrayList<PublishEntity> publishEntities = new ArrayList<PublishEntity>();
		publishEntities.add(publishMapper.findPulishByID(publishID));
		return getPublishDtos(publishEntities).get(0);
	}
	
	/**
	 * 增加发布
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPublish")
	@ResponseBody
	public Object findPublishByID(@RequestParam(required = false) String title,//标题
			@RequestParam(required = false) String describe,
			@RequestParam(required = false) Integer pubcategory,
			@RequestParam(required = false) Integer publisher,
			@RequestParam(required = false) Integer schoolid,
			@RequestParam(required = false) String content
			){
		PublishEntity publishEntity = new PublishEntity();
		PageParseUtil pageParseUtil = new PageParseUtil();
		List<String> publishCovers = pageParseUtil.parse(content);
		publishEntity.setContent(content);
		publishEntity.setPubcategory(pubcategory);
		publishEntity.setDescribe(describe);
		publishEntity.setPublisher(BigInteger.valueOf(publisher));
		publishEntity.setSchoolid(schoolid);
		publishEntity.setTitle(title);
		if (publishCovers!=null&&!publishCovers.isEmpty()) {
			publishEntity.setPubcover(publishCovers.get(0));
		}
		publishMapper.insertPublish(publishEntity);
		return ResponseData.newSuccess();
	}
	
}