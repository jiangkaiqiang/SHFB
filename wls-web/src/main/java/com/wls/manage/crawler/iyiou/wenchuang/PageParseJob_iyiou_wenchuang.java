package com.wls.manage.crawler.iyiou.wenchuang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

import com.wls.manage.util.TimeUtil;
/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJob_iyiou_wenchuang {

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
    	Element introduction  = doc.select("div[id=post_content] div[id=post_brief]").first();
    	Element post_thumbnail  = doc.select("div[id=post_content] div[id=post_thumbnail]").first();
    	Elements post_description  = doc.select("div[id=post_content] div[id=post_description] p");
    	Element dateElement = doc.select("div[id=post_info] div[id=post_date]").first();
    	Element sourceElement = doc.select("div[id=post_info] div[id=post_source]").first();
    	Element authorElement = doc.select("div[id=post_info] div[id=post_author]").first();
    	ni.setSource(sourceElement.text()+"\t"+authorElement.text());
    	String dateString = dateElement.text();
//    	System.out.println(dateString);
    	String contentString=introduction.toString()+"\n"+post_thumbnail.toString()+"\n"+
    	"<div id=\"post_description\"> ";
    	
    	for (Element element : post_description) {
    		if(element.toString().startsWith("<p><strong>相关阅读：")||
    				element.toString().startsWith("<p><strong><a href")){
    			break;
    		}
    		contentString=contentString+element.toString()+"\n";
			
		}
    		ni.setContent(contentString+"</div>");
    		
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        	try {
        		
        		if(TimeUtil.isContainsChinese(dateString)){
        			Date newdate = Calendar.getInstance().getTime();
        			ni.setTime(newdate);
        		}else {
					Date date=sdf.parse(dateString);
					ni.setTime(date);
				}
        		
				
//				System.out.println(date);
//					System.out.println(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
////        
////        }
    }


}
