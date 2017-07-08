package com.shfb.rfid.manage.intercepter;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shfb.rfid.manage.dao.OperationLogMapper;
import com.shfb.rfid.manage.entity.SysUser;
import com.shfb.rfid.manage.entity.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户切取对于用户的操作日志
 * @author jiangkaiqiang
 * @version 创建时间：2017-6-2 下午2:57:30 
 *
 */
public class UserLogInterceptor implements HandlerInterceptor{

	@Autowired
	private OperationLogMapper operationLogDao;
	
	private SysUser sysUser;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		sysUser = (SysUser) request.getSession().getAttribute("user");
		return sysUser != null;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		String methodName = handlerMethod.getMethod().getName();
		OperationLog operationLog = new OperationLog();
		operationLog.setAdminname(sysUser.getUser_name());
		operationLog.setAddtime(new Date());
		if (methodName.equals("addUser")) {
			operationLog.setRequestname("添加用户");
			operationLog.setContent(String.format("用户名称：%s", request.getParameter("user_name")));
			operationLog.setRequesturl(request.getRequestURL().toString());
		}else if (methodName.equals("updateUser")) {
			operationLog.setRequestname("更新用户");
			operationLog.setContent(String.format("用户名称：%s", request.getParameter("user_name")));
			operationLog.setRequesturl(request.getRequestURL().toString());
		}
		else if (methodName.equals("deleteUserByID")) {
			operationLog.setRequestname("删除用户");
			operationLog.setContent(String.format("用户id：%s", request.getParameter("userID")));
			operationLog.setRequesturl(request.getRequestURL().toString());
		}
		else if (methodName.equals("deleteUserByIDs")) {
			String[] userIDs = request.getParameterValues("userIDs");
			operationLog.setRequestname("批量删除用户");
			operationLog.setContent(String.format("删除的用户id：%s",Arrays.toString(userIDs)));
			operationLog.setRequesturl(request.getRequestURL().toString());
		}
		else{
			
		}
		if (operationLog.getRequestname() != null) {
			operationLogDao.insertOperationLog(operationLog);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
