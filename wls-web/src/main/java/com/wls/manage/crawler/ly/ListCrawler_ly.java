package com.wls.manage.crawler.ly;

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
public class ListCrawler_ly {
	@Autowired
	private InformationMapper informationMapper;
    private static Logger logger = Logger.getLogger(ListCrawler_ly.class.getName());
    public ListCrawlJob_ly lj;
    public List<NewInfomationDto> parse() throws ParseException {
    	 logger.info("Start Crawl Pages on 猎云网");   
         ListCrawlJob_ly crawler =new ListCrawlJob_ly();
         crawler.getUrlList("http://www.lieyunwang.com/api/v1/posts?starttime=2016-03-01+14%3A50%3A27&cid=&keyword=&tag=&searchcontent=&scroll=true&scrollTo=100&title_remove_dujia=1");
         for(NewInfomationDto ni:crawler.an){
             new PageCrawJob_ly().pageCraw(ni);
         }
         return crawler.an;
	}
    
//    public static void main(String[] args) throws ParseException {
//    	List<NewInfomationDto> an =new ListCrawler_ly().parse();
//    	for(NewInfomationDto ni:an){
//    		System.out.println(ni.getSource());
//    	}
//	}
}
