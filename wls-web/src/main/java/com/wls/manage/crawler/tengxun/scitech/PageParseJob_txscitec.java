package com.wls.manage.crawler.tengxun.scitech;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJob_txscitec {

    public  void parse(NewInfomationDto ni){
        String content = "";
        try {
            content= BasicCrawler.crawlPage(ni.getHref(),"gb2312");
//            System.out.println(content);
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(content);
        parseNews(doc,ni);
    }

    private  void parseNews(Document doc,NewInfomationDto ni) {
    	Element source  = doc.select("div[class=a_Info] span[class=a_source]").first();
    	Element time  = doc.select("div[class=a_Info] span[class=a_time]").first();
    	Element content  = doc.select("div[class=Cnt-Main-Article-QQ]").first();
    	if(content!=null){
//	    	System.out.println(ni.getTitle());
	    	ni.setContent(content.toString());
	    	ni.setSource(source.text());
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	try {
				Date date=sdf.parse(time.text());
				ni.setTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	
    }


}
