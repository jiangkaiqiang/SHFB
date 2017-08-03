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
import com.shfb.rfid.manage.dao.SysUserMapper;
import com.shfb.rfid.manage.dto.BaseDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.entity.Cookies;
import com.shfb.rfid.manage.entity.SysUser;
import com.shfb.rfid.manage.service.CookieService;
import com.shfb.rfid.manage.service.FtpService;
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
				user.setLogin_time(new Date());
				userDao.updateUser(user);
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
			
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	@ResponseBody
	public Object updateUser(SysUser user) throws UnsupportedEncodingException {
		if (user.getUser_name() == null) {
			return new ResultDto(-1, "用户名不能为空");
		}
		userDao.updateUser(user);
		return new ResultDto(0,"更新成功");
	}
	
	@RequestMapping(value = "/changePwd")
	@ResponseBody
	public Object changePwd(HttpServletRequest request,@RequestParam(value="password") String password,
			@RequestParam(value="userID", required=false) Integer userID) {
		SysUser sysUser = new SysUser();
		sysUser.setUser_id(userID);
		sysUser.setPassword(password);
		userDao.updateUser(sysUser);
		logout(request);
		return new BaseDto(0);
	}
}
