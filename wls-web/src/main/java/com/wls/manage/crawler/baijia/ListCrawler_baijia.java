package com.wls.manage.crawler.baijia;

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
public class ListCrawler_baijia {
	@Autowired
	private InformationMapper informationMapper;
    private static Logger logger = Logger.getLogger(ListCrawler_baijia.class.getName());
    public ListCrawlJob_baijia lj;
    public List<NewInfomationDto> parse() throws ParseException {
    	 logger.info("Start Crawl Pages on 百度百家");   
         ListCrawlJob_baijia crawler =new ListCrawlJob_baijia();
         crawler.getUrlList("http://baijia.baidu.com/ajax/labellatestarticle?page=0&pagesize=20&labelid=107&prevarticalid=784651");
         for(NewInfomationDto ni:crawler.an){
             new PageCrawJob_baijia().pageCraw(ni);
         }
//         return crawler.an;
         return crawler.an;
	}
    
}
