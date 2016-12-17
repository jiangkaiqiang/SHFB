package com.wls.manage.crawler.tengxun.digitech;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by haolidong on 2016/11/12.
 */
public class TXListCrawlerDigitech {
    private static Logger logger = Logger.getLogger(TXListCrawlerDigitech.class.getName());
    public static void main(String[] args) throws IOException {
        logger.info("Start Crawl Pages on www.toutiao.com");      //今日头条科技类
        try{
            Thread.sleep(1000);
            ListCrawlJobDigitech crawler =new ListCrawlJobDigitech();
            for (int i = 2; i < 8; i++) {
				crawler.getUrlList("http://digi.tech.qq.com/c/digilist_"+i+".htm?");
			}
              
            	
			
            
          
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
