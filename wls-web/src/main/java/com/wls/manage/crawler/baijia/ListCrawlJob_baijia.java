package com.wls.manage.crawler.baijia;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawlJob_baijia {

    public List<NewInfomationDto> an = new ArrayList<NewInfomationDto>();
    private static Logger logger = Logger.getLogger(ListCrawler_baijia.class.getName());
    public  void getUrlList(String linkUrl) {
        //爬取列表页面
        logger.info("Start crawl list page:" + linkUrl);
        String content = "";
        try {
            content= BasicCrawler.crawlPage(linkUrl,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO  解析文章列表页面，获取需要爬取页面URL
        Document doc = Jsoup.parse(content);
        System.out.println(content);
        processListType(content);
    }

    private  void processListType(String content){
    	 JSONObject jsonObject = JSONObject.fromObject(content);
    	 JSONObject data=(JSONObject)jsonObject.get("data");
    	 JSONArray lists = data.getJSONArray("list");
    	 for (int i = 0; i < lists.size(); i++) {
			JSONObject info=(JSONObject)lists.get(i);
			System.out.println(info.get("m_display_url"));
			System.out.println(info.get("m_image_url"));
			System.out.println(info.get("m_title"));
			an.add(new NewInfomationDto("", info.get("m_image_url").toString(), info.get("m_title").toString(), info.get("m_display_url").toString(), "百度百家"));
		}
 	 
    	
    	 
    }
}
