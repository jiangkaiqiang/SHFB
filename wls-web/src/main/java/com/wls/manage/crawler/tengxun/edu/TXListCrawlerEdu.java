package com.wls.manage.crawler.tengxun.edu;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by haolidong on 2016/11/12.
 */
public class TXListCrawlerEdu {
    private static Logger logger = Logger.getLogger(TXListCrawlerEdu.class.getName());
    public static void main(String[] args) throws IOException {
        logger.info("Start Crawl Pages on www.toutiao.com");      //今日头条科技类
        try{
            Thread.sleep(1000);
            ListCrawlJobEdu crawler =new ListCrawlJobEdu();
            for (int i = 2; i < 40; i++) {
            	crawler.getUrlList("http://edu.qq.com/c/newsedu_"+i+".htm?");
			}
            
            	
			
            
            //crawler.getUrlList("http://tech.qq.com/a/20161114/031873.htm");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
