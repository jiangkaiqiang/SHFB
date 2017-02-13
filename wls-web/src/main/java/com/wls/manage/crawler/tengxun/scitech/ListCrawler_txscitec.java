package com.wls.manage.crawler.tengxun.scitech;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.wls.manage.dao.InformationMapper;
import com.wls.manage.dto.NewInfomationDto;
/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawler_txscitec {
	@Autowired
	private InformationMapper informationMapper;
    private static Logger logger = Logger.getLogger(ListCrawler_txscitec.class.getName());
    public ListCrawlJob_txscitec lj;
    public List<NewInfomationDto> parse() throws ParseException {
    	 logger.info("Start Crawl Pages on 腾讯新闻科技类");   
         ListCrawlJob_txscitec crawler =new ListCrawlJob_txscitec();
     	Calendar cal=Calendar.getInstance();
     	int year=cal.get(Calendar.YEAR);
     	int month=cal.get(Calendar.MONTH)+1;
     	int day=cal.get(Calendar.DAY_OF_MONTH);
     	String monthString=month>10?""+month:"0"+month;
     	String dayString = day>10?""+day:"0"+day;
     	String url="http://tech.qq.com/l/"+year+monthString+"/scroll_"+dayString+".htm";
//     	System.out.println(url);
         crawler.getUrlList(url);
         for(NewInfomationDto ni:crawler.an){
             new PageCrawJob_txscitec().pageCraw(ni);
         }
         List<NewInfomationDto> infoarr = new ArrayList<NewInfomationDto>();
         for (NewInfomationDto newInfomationDto : crawler.an) {
			if(!newInfomationDto.getContent().equals("")){
				infoarr.add(newInfomationDto);
			}
		}
         return infoarr;
	}
    
    public static void main(String[] args) throws ParseException {
//    	List<NewInfomationDto> an =new ListCrawler_txscitec().parse();
//    	for(NewInfomationDto ni:an){
//    		System.out.println(ni.getContent());
//    	}
    	Calendar cal=Calendar.getInstance();
    	int year=cal.get(Calendar.YEAR);
    	int month=cal.get(Calendar.MONTH)+1;
    	int day=cal.get(Calendar.DAY_OF_MONTH);
    	String monthString=month>10?""+month:"0"+month;
    	String dayString = day>10?""+day:"0"+day;
    	String url="http://tech.qq.com/l/"+year+monthString+"/scroll_"+dayString+".htm";
    	System.out.println(url);
    	
    	
	}
}
