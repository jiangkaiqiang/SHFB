package com.wls.manage.crawler.chuangyebang.chuangye;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJob_cyb {

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
    	Element source  = doc.select("div[class=author-time pull-left] span[class=name]").first();
        Element desc = doc.select("div[class=article-content]").first();
        if(desc!=null){
        	ni.setContent(desc.toString());
        }
        if(source != null){
        	ni.setSource(source.text());
        }
    }


}
