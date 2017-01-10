package com.wls.manage.crawler.iyiou.wenchuang;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.xpath.operations.Div;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawlJob_iyiou_wenchuang {

    public List<NewInfomationDto> an = new ArrayList<NewInfomationDto>();
    private static Logger logger = Logger.getLogger(ListCrawler_iyiou_wenchuang.class.getName());
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
        Elements eles=doc.select("div[class=tags-item tags_1 active] ul[class=specificpost-list] li[class=clearFix]");
        for (Element ele:eles){
//        	System.out.println(ele);
//        	Element href = ele.select("h3 a[href]").first();
////        	System.out.println(href.attr("href"));
////        	System.out.println(href.text());
        	Element img = ele.select("div[class=img fl] img[src]").first();
//        	System.out.println(img.attr("src"));
        	Element href = ele.select("div[class=text fl] a[href]").first();
//        	System.out.println(title.text());
//        	System.out.println(title.attr("href"));
        	an.add(new NewInfomationDto("", img.attr("src"), href.text(), href.attr("href"), ""));
        }
    }
}
