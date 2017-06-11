package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.SysUserMapper;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.Cookies;
import com.shfb.rfid.manage.entity.SysUser;
import com.shfb.rfid.manage.service.CookieService;
import com.shfb.rfid.manage.service.FtpService;
import com.shfb.rfid.manage.util.EncodeUtil;
import com.shfb.rfid.manage.util.ResponseData;
import com.shfb.rfid.manage.util.StringUtil;
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	private static String baseDir = "picture";
	@Autowired
	private SysUserMapper userDao;
	@Autowired
	private CookieService cookieService;
	@Autowired
	private FtpService ftpService;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String userName, String password) {
		if(StringUtil.isnotNull(userName)&&StringUtil.isnotNull(password)){
			SysUser user = userDao.findUser(userName, password);
			if (user != null) {
				String cookie = cookieService.insertCookie(userName);
				user.setPassword("********");
				request.getSession().setAttribute("user", user);
	            return  ResponseData.newSuccess(String.format("token=%s", cookie));
			}
			return ResponseData.newFailure("用户名或者密码不正确~");
		}else{
			return ResponseData.newFailure("用户名和密码不能为空~");
		}
		
	}
	
	@RequestMapping(value = "/findUserByID")
	@ResponseBody
	public Object findUserByID(
			@RequestParam(value="spaceUserID", required=false) Integer spaceUserID
			) throws UnsupportedEncodingException {
	     SysUser userEntity = userDao.findUserById(spaceUserID);
	     return userEntity;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public Object logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					cookieService.deleteCookie(cookie.getValue());
				}
			}
		}
		return true;
	}

	@RequestMapping(value = "/findUser")
	@ResponseBody
	public Object findUser(HttpServletRequest request,String token) {
		SysUser user = (SysUser)request.getSession().getAttribute("user");
		if(user!=null){return user;}
		if(StringUtil.isNull(token)){
			Cookie[] cookies = request.getCookies();
			if(cookies!=null&&cookies.length>0){
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("token")) {token=	cookie.getValue();break;}
				}
			}
		}
		if(StringUtil.isnotNull(token)){
			Cookies effectiveCookie = cookieService.findEffectiveCookie(token);
			if (effectiveCookie != null) {
				user = userDao.findUserByName(effectiveCookie.getUsername());
				if(user!=null){
					user.setPassword("********");
					request.getSession().setAttribute("user", user);
					return user;
				}
			}
		}
		user = new SysUser();
		return user;
	}
		
	@RequestMapping(value = "/findUserList", method = RequestMethod.POST)
	@ResponseBody
	public Object findUserList(@RequestParam(value="pageNum",required=false) Integer pageNum,
			@RequestParam(value="pageSize") Integer pageSize, 
			@RequestParam(value="audit", required=false) Integer audit,
			@RequestParam(value="keyword", required=false) String keyword) throws UnsupportedEncodingException {
		if( !(audit == -1 || audit == 1 || audit == 0) ){
			audit = null;
		}
		pageNum = pageNum == null? 1:pageNum;
		pageSize = pageSize==null? 12:pageSize;
		PageHelper.startPage(pageNum, pageSize);
		if(keyword.equals("undefined"))
			keyword = null;
		else{
		keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		return new PageInfo<SysUser>(userDao.findAllUser(audit,keyword));
		
	}
	
	/*@RequestMapping(value = "/updatePhoto")
	@ResponseBody
	public Object updatePhoto(HttpServletRequest request, @RequestParam(required = false) MultipartFile userphoto) {
		SysUserEntity user = new SysUserEntity();
		SysUserEntity old_user = (SysUserEntity)request.getSession().getAttribute("user");
		user.setUser_id(old_user.getUser_id());
		if(userphoto!=null){
			String dir = String.format("%s/user/photo/%s", baseDir, user.getUser_id());
			String fileName = String.format("user%s_%s.%s", user.getUser_id(), new Date().getTime(), "jpg");
			UploadFileEntity uploadFileEntity = new UploadFileEntity(fileName, userphoto, dir);
			ftpService.uploadFile(uploadFileEntity);
			user.setPhoto(FtpService.READ_URL+"data/"+dir + "/" + fileName);//http://42.121.130.177:8089/picture/user/1124/3456789.png
			this.userDao.updateUser(user);
			SysUserEntity ol_user = this.userDao.findUserById(user.getUser_id());
			ol_user.setPassword("********");
			request.getSession().setAttribute("user",ol_user);
			return ResponseData.newSuccess(ol_user);
		}
		return ResponseData.newFailure();
	}*/
}
