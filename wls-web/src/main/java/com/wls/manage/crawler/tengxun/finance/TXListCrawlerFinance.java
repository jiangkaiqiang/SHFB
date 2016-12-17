package com.wls.manage.crawler.tengxun.finance;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by haolidong on 2016/11/12.
 */
public class TXListCrawlerFinance {
    private static Logger logger = Logger.getLogger(TXListCrawlerFinance.class.getName());
    public static void main(String[] args) throws IOException {
        logger.info("Start Crawl Pages on www.toutiao.com");      //今日头条科技类
        try{
            Thread.sleep(1000);
            ListCrawlJobFinance crawler =new ListCrawlJobFinance();
//            for (int i = 43; i < 50; i++) {
            	crawler.getUrlList("http://finance.qq.com/c/jrscllist_1.htm?");
//			}
            
            	
			
            
            //crawler.getUrlList("http://tech.qq.com/a/20161114/031873.htm");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
