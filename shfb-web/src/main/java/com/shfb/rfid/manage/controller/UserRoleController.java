package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shfb.rfid.manage.dao.SysUserMapper;
import com.shfb.rfid.manage.dao.UserRoleMapper;
import com.shfb.rfid.manage.dto.BaseDto;
import com.shfb.rfid.manage.dto.ResultDto;
import com.shfb.rfid.manage.dto.UserRoleDto;
import com.shfb.rfid.manage.entity.SysUser;
import com.shfb.rfid.manage.entity.UserRole;
import com.shfb.rfid.manage.util.EncodeUtil;
import com.github.pagehelper.Page;
@Controller
@RequestMapping(value = "/userrole")
public class UserRoleController extends BaseController {
	@Autowired
	private UserRoleMapper userRoleDao;
	@Autowired
	private SysUserMapper userDao;
	
	@RequestMapping(value = "/findUserRoleList", method = RequestMethod.POST)
	@ResponseBody
	public Object findUserRoleList(@RequestParam(value="pageNum",required=false) Integer pageNum,
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
		Page<UserRole> userRoles = userRoleDao.findAllUserRole(startTime, endTime, keyword);
		Page<UserRoleDto> userRoleDtos = new Page<UserRoleDto>();
		for (UserRole userRole : userRoles) {
			UserRoleDto userRoleDto = new UserRoleDto();
			userRoleDto.setUserRole(userRole);
			List<SysUser> sysUsers = userDao.findUsersByRoleID(userRole.getUser_role_id());
		    userRoleDto.setUserNum(sysUsers.size());
		    userRoleDtos.add(userRoleDto);
		}
		userRoleDtos.setPageSize(userRoles.getPageSize());
		userRoleDtos.setPages(userRoles.getPages());
		userRoleDtos.setTotal(userRoles.getTotal());
		return new PageInfo<UserRoleDto>(userRoleDtos);
		
	}
	@RequestMapping(value = "/addUserRole", method = RequestMethod.GET)
	@ResponseBody
	public Object addUserRole(UserRole userRole) throws UnsupportedEncodingException {
		if (userRole.getUser_role_name() == null) {
			return new ResultDto(-1, "角色名不能为空");
		}
		userRoleDao.insert(userRole);
		return new BaseDto(0);
	}
	
	@RequestMapping(value = "/updateUserRole")
	@ResponseBody
	public Object updateUserRole(UserRole userRole){
		if (userRole.getUser_role_name() == null) {
			return new ResultDto(-1, "角色名不能为空");
		}
		userRoleDao.updateByPrimaryKeySelective(userRole);
		return new BaseDto(0);
	}
	
	@RequestMapping(value = "/findUserRoleByUserID")
	@ResponseBody
	public Object findUserRoleByUserID(Integer userID){
		SysUser sysUser  = userDao.findUserById(userID);
		return findUserRoleByID(sysUser.getUser_role_id());
	}
	
	
	@RequestMapping(value = "/findUserRoleByID")
	@ResponseBody
	public Object findUserRoleByID(Integer userRoleID) {
		UserRole userRole = userRoleDao.selectByPrimaryKey(userRoleID);
		UserRoleDto userRoleDto = new UserRoleDto();
		String menuids = userRole.getMenu_ids();
		String[] menus = menuids.split(";");
		List<Integer> menuList = new ArrayList<Integer>();
		for (int i = 0; i < menus.length; i++) {
			menuList.add(Integer.parseInt(menus[i]));
		}
		if (menuList.contains(1)) userRoleDto.setOverView(true);
		if (menuList.contains(2)) userRoleDto.setCompManage(true);
		if (menuList.contains(3)) userRoleDto.setProcessManage(true);
		if (menuList.contains(4)) userRoleDto.setProjectManage(true);
		if (menuList.contains(5)) userRoleDto.setCompFactoryManage(true);
		if (menuList.contains(6)) userRoleDto.setUserManage(true);
		if (menuList.contains(7)) userRoleDto.setRoleManage(true);
		if (menuList.contains(8)) userRoleDto.setLogManage(true);
		if (menuList.contains(9)) userRoleDto.setProductManage(true);
		if (menuList.contains(10)) userRoleDto.setPersonalManage(true);
		if (menuList.contains(11)) userRoleDto.setPcManage(true);
		if (menuList.contains(12)) userRoleDto.setBindManage(true);
		if (menuList.contains(13)) userRoleDto.setCompReadOnlyManage(true);
		userRoleDto.setUserRole(userRole);
		/*List<SysUser> sysUsers = userDao.findUsersByRoleID(userRole.getUser_role_id());
	    userRoleDto.setUserNum(sysUsers.size());*/
		return userRoleDto;
	}
	
	@RequestMapping(value = "/deleteUserRoleByID")
	@ResponseBody
	public Object deleteUserRoleByID(Integer userRoleID) {
		 userRoleDao.deleteByPrimaryKey(userRoleID);
		 return new BaseDto(0);
	}
	 @RequestMapping(value = "/findAllUserRole")
	    @ResponseBody
	    public Object findAllUserRole() {
	        return userRoleDao.findAllUserRoleList();
	    }
}
