package com.wls.manage.crawler.chuangyebang.chuangye;

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
public class ListCrawler_cyb {
	@Autowired
	private InformationMapper informationMapper;
    private static Logger logger = Logger.getLogger(ListCrawler_cyb.class.getName());
    public ListCrawlJob_cyb lj;
    public List<NewInfomationDto> parse() throws ParseException {
    	 logger.info("Start Crawl Pages on www.toutiao.com");   
         ListCrawlJob_cyb crawler =new ListCrawlJob_cyb();
         crawler.getUrlList("http://api.cyzone.cn/index.php?m=content&c=index&a=init&tpl=index_page&page=1");
         for(NewInfomationDto ni:crawler.an){
             new PageCrawJob_cyb().pageCraw(ni);
         }
         return crawler.an;
	}
    
    public static void main(String[] args) throws ParseException {
    	List<NewInfomationDto> an =new ListCrawler_cyb().parse();
    	for(NewInfomationDto ni:an){
    		System.out.println(ni.getSource());
    	}
	}
}
