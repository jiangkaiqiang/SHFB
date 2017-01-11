package com.wls.manage.crawler.tengxun.scitech;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawlJob_chinaenter {

    public List<NewInfomationDto> an = new ArrayList<NewInfomationDto>();
    private static Logger logger = Logger.getLogger(ListCrawler_chinaenter.class.getName());
    public  void getUrlList(String linkUrl) {
        //爬取列表页面
        logger.info("Start crawl list page:" + linkUrl);
        String content = "";
        try {
            content= BasicCrawler.crawlPage(linkUrl,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO  解析文章列表页面，获取需要爬取页面URL
        Document doc = Jsoup.parse(content);
        processListType(doc,linkUrl);
    }

    private  void processListType(Document doc, String linkUrl){
        Elements tables=doc.select("div[class=con2] table[class=12v]");
        for (Element element : tables) {
        	Element hrefElement = element.select("tbody tr td[class] a[href]").first();
        	Element imgElement = element.select("tbody tr td[rowspan] img[src]").first();
        	Element dateElement = element.select("tbody tr td[align=right]").first();
//        	 System.out.println(timeElement.text());
        	String href=hrefElement.attr("href").replaceAll("\n", "").trim();
        	String title=hrefElement.text().replaceAll("\n", "").trim();
//        	System.out.println(dateElement.text());
        	String imgString="";
        	if(imgElement!=null){
        		imgString=imgElement.attr("src");
        	}
        	
        	
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	try {
				Date date=sdf.parse(dateElement.text());
				an.add(new NewInfomationDto("", imgString, date, title, href));
//				System.out.println(date);
//					System.out.println(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
