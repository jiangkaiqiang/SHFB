package com.wls.manage.crawler.tengxun.tech;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by haolidong on 2016/11/12.
 */
public class TXListCrawler {
    private static Logger logger = Logger.getLogger(TXListCrawler.class.getName());
    public static void main(String[] args) throws IOException {
        logger.info("Start Crawl Pages on www.toutiao.com");      //今日头条科技类
        try{
            Thread.sleep(1000);
            ListCrawlJob crawler =new ListCrawlJob();
            for (int i = 1; i < 10; i++) {
            	if(i<10){
            		crawler.getUrlList("http://tech.qq.com/l/201610/scroll_0"+i+".htm");
            	}else{
            		crawler.getUrlList("http://tech.qq.com/l/201610/scroll_"+i+".htm");
            	}
            	
			}
            
            //crawler.getUrlList("http://tech.qq.com/a/20161114/031873.htm");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
