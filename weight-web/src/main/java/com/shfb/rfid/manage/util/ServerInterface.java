package com.shfb.rfid.manage.util;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

public class ServerInterface {
	private static final String rootUrl = "http://180.168.218.122:8281/tongche/";
	private static final String tokenUrl = rootUrl + "weigh/getToken";
	private static final String userInfoUrl = rootUrl + "weigh/getUserInfo";
	private static final String orderDetil = rootUrl + "weigh/getItemOrderDetil";
	private static final String signUrl = rootUrl + "weigh/sign";
	private static final String tel = "13600000002";
	private static final String password = "e10adc3949ba59abbe56e057f20f883e";
	public static String token;
	//签收人
	public static String userId;
	
	/**
	 * 获取token和用户id
	 * @param tel
	 * @param password
	 * @return
	 */
	public static Map<String, String> getToken() {
		try {
			Response response = Jsoup.connect(tokenUrl).data("tel", tel).data("password", password).ignoreContentType(true).method(Method.POST).execute();
			Map<String, String> cookie = response.cookies();
			String result = response.body(); 				
			JSONObject jb = JSONObject.fromObject(result);
			if("200".equals(jb.getString("code"))) {
				token = jb.getString("result");
				userId = jb.getString("id");
				return cookie;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	/**
	 * 获取驾驶员相关信息
	 * @param id 驾驶员id
	 * @return
	 */
	public static Map getUserInfo(String id) {
		Map<String, String> cookie = getToken();
		Map<String, String> res = null;
		try {
			String result = Jsoup.connect(userInfoUrl).data("token", token).data("id", id).ignoreContentType(true).cookies(cookie).post().text();
			JSONObject jb = JSONObject.fromObject(result);
			if("200".equals(jb.getString("code"))) {
				res = new HashMap<String, String>();
				JSONObject resultJb = jb.getJSONObject("result");
				//驾驶员姓名
				res.put("userName", resultJb.getString("userName"));
				//驾驶员联系
				res.put("tel", resultJb.getString("tel"));
				//发货单位
				res.put("deliverCompanyName", resultJb.getString("deliverCompanyName"));
				//所属单位
				res.put("companyName", resultJb.getString("companyName"));
				//规格型号
				if(resultJb.get("vehicleInfo")!=null) {
					
					res.put("carVarieties", resultJb.getJSONObject("vehicleInfo").getString("carVarieties"));
				}
				//驾照号
				if(resultJb.get("driverInfo")!=null) {
					
					res.put("driverNo", resultJb.getJSONObject("driverInfo").getString("driverNo"));
				}
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;

	}
	
	/**
	 * 修改小票
	 * @param id 小票id
	 * @return
	 */
	public static boolean updataSign(String id, String qrQuantity, String hcQuantity) {
		Map<String, String> cookie = getToken();
		Map<String, String> parm = new HashMap<String, String>();
		parm.put("id", id);
		parm.put("updater", userId);
		parm.put("qrQuantity", qrQuantity);
		parm.put("hcQuantity", hcQuantity);
		parm.put("token", token);
		try {
			String result = Jsoup.connect(signUrl).data(parm).ignoreContentType(true).cookies(cookie).post().text();
			JSONObject jb = JSONObject.fromObject(result);
			if("200".equals(jb.getString("code"))) {
				return true;
			} else {
				System.out.println("--------------------"+jb.getString("message")+"----------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	public static void main(String[] args) {
		System.out.println(getToken());
		System.out.println(token);
		
	}
}
