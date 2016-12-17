package com.wls.manage.crawler.tengxun.finance;

import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.wls.manage.crawler.general.BasicCrawler;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageCrawJobFinance {
    private static Logger logger = Logger.getLogger(PageCrawJobFinance.class.getName());
    
    public static void craw(String url,String time,String title){
        try{
            Thread.sleep(1000);
            PageParseJobFinance crawler =new PageParseJobFinance();
            crawler.parse(url,time,title);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
