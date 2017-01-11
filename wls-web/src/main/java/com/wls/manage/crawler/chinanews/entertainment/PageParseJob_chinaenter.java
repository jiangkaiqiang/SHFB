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
            content= BasicCrawler.crawlPage(ni.getHref(),"gb2312");
//            System.out.println(content);
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(content);
        parseNews(doc,ni);
    }

    private  void parseNews(Document doc,NewInfomationDto ni) {
    	Element content  = doc.select("div[class=left_zw]").first();
    	Element source  = doc.select("div[class=left-t]").first();
    	ni.setContent(content.toString());
    	String compound = source.text().replaceAll("参与互动", "");
    	int index = compound.indexOf("来源：");
    	String sourceString="";
    	if (index>=0) {
    		sourceString = compound.substring(index+"来源：".length());
		}
//    	System.out.println(sourceString);
//    	System.out.println(sourceString);
    	ni.setSource(sourceString);
    }


}
