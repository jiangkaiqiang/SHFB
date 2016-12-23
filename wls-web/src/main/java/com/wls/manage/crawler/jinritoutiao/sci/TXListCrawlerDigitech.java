package com.wls.manage.crawler.jinritoutiao.sci;
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
//            for (int i = 2; i < 8; i++) {
//				crawler.getUrlList("https://tns.simba.taobao.com/?name=itemdsp&o=j&spm=a2e01.7920152.0.0.cnNa7l&templateId=23032&url=http%3A%2F%2Fwww.toutiao.com%2Fnews_society%2F&iswt=1&pid=tt_32479643_3494618_63256340&refpid=tt_32479643_3494618_63256340&refpos=%2Cn%2Ci&adx_type=0&pvid=0a672a11000058554628368800ad4293_0&ps_id=a53061d4d036a1db52b35aa8e515cc96&fl=8&tanxdspv=http%3A%2F%2Frdstat.tanx.com%2Ftrd%3Ff%3D%26k%3Da09e279ad7f7a12a%26p%3Dmm_32479643_3494618_63256340%26pvid%3D0a672a11000058554628368800ad4293%26s%3D300x250%26d%3D17534123%26t%3D1481983528&u=http%3A%2F%2Fwww.toutiao.com%2Fnews_society%2F&r=http%3A%2F%2Fwww.toutiao.com%2Finternet%2F&tp=2&tsid=0a672a11000058554628368800ad4293&count=28&p4p=jsonp0iwtaqcqi&qq-pf-to=pcqq.c2c");
//			}
              
            	
			crawler.getUrlList("https://www.huxiu.com/tags/29.html");
            
          
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
