package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
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
import com.shfb.rfid.manage.dao.UserMapper;
import com.shfb.rfid.manage.dto.UploadFileEntity;
import com.shfb.rfid.manage.entity.CookieEntity;
import com.shfb.rfid.manage.entity.UserEntity;
import com.shfb.rfid.manage.service.CookieService;
import com.shfb.rfid.manage.service.FtpService;
import com.shfb.rfid.manage.util.EncodeUtil;
import com.shfb.rfid.manage.util.ResponseData;
import com.shfb.rfid.manage.util.StringUtil;
import com.taobao.api.ApiException;
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	private static String baseDir = "picture";
	@Autowired
	private UserMapper userDao;
	@Autowired
	private CookieService cookieService;
	@Autowired
	private FtpService ftpService;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request, String userName, String password) {
		if(StringUtil.isnotNull(userName)&&StringUtil.isnotNull(password)){
			UserEntity user = userDao.findUser(userName, EncodeUtil.encodeByMD5(password));
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
	     UserEntity userEntity = userDao.findUserById(spaceUserID);
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
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
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
			CookieEntity effectiveCookie = cookieService.findEffectiveCookie(token);
			if (effectiveCookie != null) {
				user = userDao.findUserByName(effectiveCookie.getUsername());
				if(user!=null){
					user.setPassword("********");
					request.getSession().setAttribute("user", user);
					return user;
				}
			}
		}
		user = new UserEntity();
		return user;
	}
		
	@RequestMapping(value = "/updatePhoto")
	@ResponseBody
	public Object updatePhoto(HttpServletRequest request, @RequestParam(required = false) MultipartFile userphoto) throws ApiException {
		UserEntity user = new UserEntity();
		UserEntity old_user = (UserEntity)request.getSession().getAttribute("user");
		user.setId(old_user.getId());
		if(userphoto!=null){
			String dir = String.format("%s/user/photo/%s", baseDir, user.getId());
			String fileName = String.format("user%s_%s.%s", user.getId(), new Date().getTime(), "jpg");
			UploadFileEntity uploadFileEntity = new UploadFileEntity(fileName, userphoto, dir);
			ftpService.uploadFile(uploadFileEntity);
			user.setPhoto(FtpService.READ_URL+"data/"+dir + "/" + fileName);//http://42.121.130.177:8089/picture/user/1124/3456789.png
			this.userDao.updateUser(user);
			UserEntity	ol_user = this.userDao.findUserById(user.getId().intValue());
			ol_user.setPassword("********");
			request.getSession().setAttribute("user",ol_user);
			return ResponseData.newSuccess(ol_user);
		}
		return ResponseData.newFailure();
	}
}
