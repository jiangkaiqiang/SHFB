package com.wls.manage.crawler.chuangyebang.chuangye;

import com.wls.manage.crawler.general.BasicCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJob {

    public  String parse(String linkUrl){
        String content = "";
        try {
            content= BasicCrawler.crawlPage(linkUrl,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(content);
        return parseNews(doc);
    }

    private  String parseNews(Document doc) {
        Element desc = doc.select("div[class=article-content]").first();
        if(desc==null)
            return "";
        return desc.toString();
    }


}
