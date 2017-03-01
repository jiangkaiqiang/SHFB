package com.wls.manage.crawler.baijia;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJob_baijia {

    public  void parse(NewInfomationDto ni){
        String content = "";
        try {
            content= BasicCrawler.crawlPage(ni.getHref(),"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(content);
        parseNews(doc,ni);
    }

    private  void parseNews(Document doc,NewInfomationDto ni) {
    	Element timeElement  = doc.select("div[class=article-infos] div[class=article-info] span[class=time]").first();
    	Element content= doc.select("div[class=article-detail]").first();  
    	
    	String time=timeElement.text();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String combiedate = year+"年"+time; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
		Date date=null;
		try {
			date = sdf.parse(combiedate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ni.setContent(content.toString());
		ni.setTime(date);
		
    }


}
