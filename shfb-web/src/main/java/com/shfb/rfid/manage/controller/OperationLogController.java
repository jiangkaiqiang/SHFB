package com.shfb.rfid.manage.controller;

import java.io.UnsupportedEncodingException;

import com.shfb.rfid.manage.dao.OperationLogMapper;
import com.shfb.rfid.manage.entity.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 操作日志controller
 * @author jiangkaiqiang
 * @version 创建时间：2017-5-26 下午2:42:18 
 *
 */
@Controller
@RequestMapping(value = "/operationLog")
public class OperationLogController extends BaseController {
	@Autowired
	private OperationLogMapper operationLogDao;
	/**
	 * 查询操作日志
	 * @param pageNum
	 * @param pageSize
	 * @param companyName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/findOperationLogList")
	@ResponseBody
	public Object findOperationLogList(
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize") Integer pageSize,
			@RequestParam(value = "adminName", required = false) String adminName)
			throws UnsupportedEncodingException {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 12 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<OperationLog>(
				operationLogDao.findOperationLogList(adminName));

	}
}
