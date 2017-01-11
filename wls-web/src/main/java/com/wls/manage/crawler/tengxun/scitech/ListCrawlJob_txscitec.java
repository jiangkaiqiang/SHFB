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
public class ListCrawlJob_txscitec {

    public List<NewInfomationDto> an = new ArrayList<NewInfomationDto>();
    private static Logger logger = Logger.getLogger(ListCrawler_txscitec.class.getName());
    public  void getUrlList(String linkUrl) {
        //爬取列表页面
        logger.info("Start crawl list page:" + linkUrl);
        String content = "";
        try {
            content= BasicCrawler.crawlPage(linkUrl,"gb2312");
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO  解析文章列表页面，获取需要爬取页面URL
        Document doc = Jsoup.parse(content);
        processListType(doc,linkUrl);
    }

    private  void processListType(Document doc, String linkUrl){
        Elements eles=doc.select("div[class=mod newslist] li");
        for (Element ele:eles){
           Element href = ele.select("a[href]").first();
           String title =href.text();
           String hrefString = href.attr("href");
           an.add(new NewInfomationDto("", "", title, hrefString, ""));  
        }
    }
}
