package com.wls.manage.crawler.iresearch.events;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJob_iresearch {

    public  void parse(NewInfomationDto ni){
        String content = "";
        try {
            content= BasicCrawler.crawlPage(ni.getHref(),"gb2312");
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(content);
        parseNews(doc,ni);
    }

    private  void parseNews(Document doc,NewInfomationDto ni) {
    	Element source  = doc.select("div[class=box] div[class=origin]").first();
    	String nameString=source.html();
    	String[] splitsStrings = nameString.split("&nbsp;");
    	List<String> ls = new ArrayList<String>();
    	for (String string : splitsStrings) {
			if(!string.equals("")){
				ls.add(string);
			}
		}
    	
    	if(ls.size()==3){
    		ni.setSource(ls.get(0).replaceAll("来源：", "")+"\t"+ls.get(1).replaceAll("作者：", ""));
    	}else{
    		ni.setSource(ls.get(0).replaceAll("来源：", ""));
    	}   	
    	Element contentElement = doc.select("div[class=m-article]").first();
    	ni.setContent(contentElement.toString());
//    	System.out.println(contentElement);
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        	try {
				Date date=sdf.parse(ls.get(ls.size()-1));
				ni.setTime(date);
//				System.out.println(date);
//					System.out.println(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//        
//        }
    }


}
