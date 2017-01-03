package com.wls.manage.crawler.chuangyebang.chuangye;

import java.io.IOException;
import java.util.logging.Logger;

import com.wls.manage.dto.NewInfomationDto;
import com.wls.manage.service.base.HttpService;
import com.wls.manage.service.base.impl.HttpServiceImpl;

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
//        int count=0;
        for(NewInfomationDto ni:crawler.an){
//            if(count++==2)break;
            ni.setContent(new PageCrawJob().pageCraw(ni.getHref()));
        }
        
        
        for(NewInfomationDto ni:crawler.an){
        	HttpService httpService = new HttpServiceImpl();
        	String msg="content="+ni.getContent()+"&infocategory=5&source=创业邦&coverpiclist="+ni.getPic()+"&title="+ni.getTitle()+"&time="+ni.getTime();
            httpService.sendPost("http://localhost:8080/i/information/addInformation", msg,10);
//            System.out.println(ni.getContent());
//            System.out.println(ni.get);
        }

    }
}
