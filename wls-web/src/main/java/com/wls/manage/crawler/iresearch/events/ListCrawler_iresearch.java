package com.wls.manage.crawler.iresearch.events;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.wls.manage.dao.InformationMapper;
import com.wls.manage.dto.NewInfomationDto;
/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawler_iresearch {
	@Autowired
	private InformationMapper informationMapper;
    private static Logger logger = Logger.getLogger(ListCrawler_iresearch.class.getName());
    public ListCrawlJob_iresearch lj;
    public List<NewInfomationDto> parse() throws ParseException {
    	 logger.info("Start Crawl Pages on 艾瑞网");   
         ListCrawlJob_iresearch crawler =new ListCrawlJob_iresearch();
         crawler.getUrlList("http://www.iresearch.cn/include/pages/redis.aspx?specialId=399&lastId=news.2663800");
         for(NewInfomationDto ni:crawler.an){
             new PageCrawJob_iresearch().pageCraw(ni);
         }
         return crawler.an;
	}
    
    public static void main(String[] args) throws ParseException {
    	List<NewInfomationDto> an =new ListCrawler_iresearch().parse();
    	for(NewInfomationDto ni:an){
    		System.out.println(ni.getSource());
    	}
	}
}
