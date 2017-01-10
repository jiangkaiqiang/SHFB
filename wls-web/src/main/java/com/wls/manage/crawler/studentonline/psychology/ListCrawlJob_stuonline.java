package com.wls.manage.crawler.studentonline.psychology;


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
public class ListCrawlJob_stuonline {

    public List<NewInfomationDto> an = new ArrayList<NewInfomationDto>();
    private static Logger logger = Logger.getLogger(ListCrawler_stuonline.class.getName());
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
        Elements eles=doc.select("div[class=employ-item-wrap pd50] div[class=employ-item]");
        for (Element ele:eles){
        	Element href = ele.select("h3 a[href]").first();
//        	System.out.println(href.attr("href"));
//        	System.out.println(href.text());
        	Element img = ele.select("dl[class=item-info] img[src]").first();
        	an.add(new NewInfomationDto("", img.attr("src"), href.text(), href.attr("href"), ""));
        }
    }
}
