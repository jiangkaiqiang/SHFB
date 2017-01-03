package com.wls.manage.crawler.chuangyebang.chuangye;


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
public class ListCrawlJob {

    public List<NewInfomationDto> an = new ArrayList<NewInfomationDto>();
    private static Logger logger = Logger.getLogger(ListCrawler.class.getName());
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
        Elements eles=doc.select("div[class=article-item clearfix]");
        for (Element ele:eles){
//            Elemen
            try{
                Element href=ele.select("div[class=item-pic pull-left] a[href]").first();
                Element img=ele.select("div[class=item-pic pull-left] img[src]").first();
                Element title=ele.select("div[class=item-intro] a[class=item-title]").first();
                Element time=ele.select("div[class=item-intro] div[class=item-push-info] span[data-time]").first();
//                System.out.println(href.attr("href"));
//                System.out.println(img.attr("src"));
//                System.out.println(title.text());
                Date date = new Date(Long.parseLong(time.attr("data-time"))*1000);
//                System.out.println(date);
                String transtime=date.getMonth()+"-"+date.getDay()+" "+date.getHours()+":"+date.getMinutes();
                an.add(new NewInfomationDto("", img.attr("src"), transtime, title.text(),href.attr("href")));
            } catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
