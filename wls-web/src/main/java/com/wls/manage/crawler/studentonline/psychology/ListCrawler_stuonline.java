package com.wls.manage.crawler.studentonline.psychology;

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
public class ListCrawler_stuonline {
	@Autowired
	private InformationMapper informationMapper;
    private static Logger logger = Logger.getLogger(ListCrawler_stuonline.class.getName());
    public ListCrawlJob_stuonline lj;
    public List<NewInfomationDto> parse() throws ParseException {
    	 logger.info("Start Crawl Pages on 中国大学生在线");   
         ListCrawlJob_stuonline crawler =new ListCrawlJob_stuonline();
         crawler.getUrlList("http://campus.univs.cn/xl/2.shtml");
         for(NewInfomationDto ni:crawler.an){
             new PageCrawJob_stuonline().pageCraw(ni);
         }
         return crawler.an;
	}
    
    public static void main(String[] args) throws ParseException {
    	List<NewInfomationDto> an =new ListCrawler_stuonline().parse();
    	for(NewInfomationDto ni:an){
    		System.out.println(ni.getSource());
    	}
	}
}
