package com.wls.manage.crawler.chuangyebang.chuangye;

import java.io.IOException;
import java.util.logging.Logger;

import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawler {
    private static Logger logger = Logger.getLogger(ListCrawler.class.getName());
    public ListCrawlJob lj;
    public static void main(String[] args) throws IOException {
        logger.info("Start Crawl Pages on www.toutiao.com");      //今日头条科技类
        ListCrawlJob crawler =new ListCrawlJob();
        crawler.getUrlList("http://api.cyzone.cn/index.php?m=content&c=index&a=init&tpl=index_page&page=2");
        int count=0;
        for(NewInfomationDto ni:crawler.an){
            if(count++==2)break;
            ni.setContent(new PageCrawJob().pageCraw(ni.getHref()));
        }

        for(NewInfomationDto ni:crawler.an){
            System.out.println(ni.getContent());
        }

    }
}
