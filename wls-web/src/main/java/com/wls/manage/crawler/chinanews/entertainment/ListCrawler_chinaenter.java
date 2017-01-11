package com.wls.manage.crawler.chinanews.entertainment;

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
public class ListCrawler_chinaenter {
	@Autowired
	private InformationMapper informationMapper;
    private static Logger logger = Logger.getLogger(ListCrawler_chinaenter.class.getName());
    public ListCrawlJob_chinaenter lj;
    public List<NewInfomationDto> parse() throws ParseException {
    	 logger.info("Start Crawl Pages on 中国大学生在线");   
         ListCrawlJob_chinaenter crawler =new ListCrawlJob_chinaenter();
         crawler.getUrlList("http://channel.chinanews.com/cns/cl/yl-mxnd.shtml?pager=0");
//         for(NewInfomationDto ni:crawler.an){
//             new PageCrawJob_chinaenter().pageCraw(ni);
//         }
         
         return crawler.an;
	}
    
    public static void main(String[] args) throws ParseException {
    	List<NewInfomationDto> an =new ListCrawler_chinaenter().parse();
//    	for(NewInfomationDto ni:an){
//    		System.out.println(ni.getSource());
//    	}
	}
}
