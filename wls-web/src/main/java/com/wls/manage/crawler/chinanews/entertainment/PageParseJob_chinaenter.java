package com.wls.manage.crawler.chinanews.entertainment;

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
public class PageParseJob_chinaenter {

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
    	Element source  = doc.select("div[class=e-op] span[class=orgin]").first();
//    	Element editorElement = doc.select("div[class=e-op] span[class=editor]").first();
        Element desc = doc.select("div[class=bd-con]").first();
        Element dateElement = doc.select("div[class=e-op] span[class=time]").first();
        if(desc!=null){
        	ni.setContent(desc.toString());
        }
        if(source != null){
        	ni.setSource(source.text());
        }
        if(dateElement != null){
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        	try {
				Date date=sdf.parse(dateElement.text());
				ni.setTime(date);
//					System.out.println(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        }
    }


}
