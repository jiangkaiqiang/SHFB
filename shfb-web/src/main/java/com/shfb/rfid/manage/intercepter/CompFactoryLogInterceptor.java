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
 * 用户切取对于构件厂的操作日志
 * @author jiangkaiqiang
 * @version 创建时间：2017-6-2 下午2:57:30 
 *
 */
public class CompFactoryLogInterceptor implements HandlerInterceptor{

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
		if (methodName.equals("addCompFactory")) {
			operationLog.setRequestname("添加构件厂");
			operationLog.setContent(String.format("构件厂名称：%s", request.getParameter("comp_factory_name")));
			operationLog.setRequesturl(request.getRequestURL().toString());
		}else if (methodName.equals("updateCompFactory")) {
			operationLog.setRequestname("更新构件厂");
			operationLog.setContent(String.format("构件厂名称：%s", request.getParameter("comp_factory_name")));
			operationLog.setRequesturl(request.getRequestURL().toString());
		}
		else if (methodName.equals("deleteCompFactoryByID")) {
			operationLog.setRequestname("删除构件厂");
			operationLog.setContent(String.format("构件厂id：%s", request.getParameter("compFactoryID")));
			operationLog.setRequesturl(request.getRequestURL().toString());
		}
		else if (methodName.equals("deleteCompFactoryByIDs")) {
			String[] compFactoryIDs = request.getParameterValues("compFactoryIDs");
			operationLog.setRequestname("批量删除构件厂");
			operationLog.setContent(String.format("删除的构件厂id：%s",Arrays.toString(compFactoryIDs)));
			operationLog.setRequesturl(request.getRequestURL().toString());
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
