package com.wls.manage.crawler.tengxun.digitech;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.crawler.tengxun.tech.PageCrawJob;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawlJobDigitech {
    private static Logger logger = Logger.getLogger(TXListCrawlerDigitech.class.getName());
    public void getUrlList(String linkUrl) {
        //爬取列表页面
        logger.info("Start crawl list page:" + linkUrl);
        String content = "";
        try {
            content=BasicCrawler.crawlPage(linkUrl,"gb2312");
            //content=BasicCrawler.crawlPage(linkUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO  解析文章列表页面，获取需要爬取页面URL
        Document doc = Jsoup.parse(content);
        processListType(doc,linkUrl);
    }

    private void processListType(Document doc, String linkUrl){
        Elements eles=doc.select("div[id=listZone] div[class=info]");
        for (Element ele:eles){
//        	System.out.println(ele);
        	Element href = ele.select("h3 a[href]").first();
        	System.out.println(href.attr("href"));
        	System.out.println(href.text());
        	Element time = ele.select("span[class=newTime]").first();
        	System.out.println(time.text());
        	String url="http://digi.tech.qq.com/"+href.attr("href");
        	PageCrawJobDigitech.craw(url, time.text(),href.text());
        }

        //System.out.println(doc);
    }
}
