package com.wls.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Kaiqiang Jiang
 * @date:2016-6-4 下午3:45:34
 * @Description: (1)Test Git Update
 */
public class CommonTest {
	private String ChenRTest;
	private static String regEx = "[\\u4e00-\\u9fa5]";
	private static Pattern pat = Pattern.compile(regEx);
	
	
	public static boolean isContainsChinese(String str){
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find())  {
		flg = true;
		}
		return flg;
	}

	public static void main(String[] args) throws ParseException{
		
		System.out.println(isContainsChinese("3小时"));
		System.out.println(isContainsChinese("2017-11-11 01:00:00"));
		
		

//		ListCrawler_ly listCrawler_ly = new ListCrawler_ly();
//		listCrawler_ly.parse();
		
		
//		NewInfomationDto ni = new NewInfomationDto();
//		ni.setHref("http://www.lieyunwang.com/archives/157020");
//		new PageCrawJob_ly().pageCraw(ni);
//		
		
		
		
//		ListCrawler_baijia baijia = new ListCrawler_baijia();
//		baijia.parse();
		
		
//		NewInfomationDto ni = new NewInfomationDto();
		
//		ni.setHref("http://architech.baijia.baidu.com/article/779403");
//		new PageCrawJob_baijia().pageCraw(ni);
		
//		String time="02月22日 10:22";

		
		
//		System.out.println(time.substring(0,monthindex));
//		System.out.println(time.substring(monthindex+1,dayindex));
//		System.out.println(time.substring(minuteindex-2,minuteindex));
//		System.out.println(time.substring(minuteindex+1,minuteindex+3));
//		Calendar calendar = Calendar.getInstance();
//		int year = calendar.get(Calendar.YEAR);
//		
//		int monthindex=time.indexOf("月");
//		int dayindex=time.indexOf("日");
//		int minuteindex = time.indexOf(":");
//		
//		
//		int month=(Integer.parseInt(time.substring(0,monthindex)));
//		int day=(Integer.parseInt(time.substring(monthindex+1,dayindex)));
//		int hour=(Integer.parseInt(time.substring(minuteindex-2,minuteindex)));
//		int minute=(Integer.parseInt(time.substring(minuteindex+1,minuteindex+3)));		
//		String dateString = year+"-"+month+"-"+day+" "+hour+":"+minute+":00";		
//    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	try {
//			Date date=sdf.parse(dateString);
//			System.out.println(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		
	}
}
