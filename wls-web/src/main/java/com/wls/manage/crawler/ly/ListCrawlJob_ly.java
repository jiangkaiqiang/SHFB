package com.wls.manage.crawler.ly;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/12.
 */
public class ListCrawlJob_ly {

	public List<NewInfomationDto> an = new ArrayList<NewInfomationDto>();
	private static Logger logger = Logger.getLogger(ListCrawler_ly.class
			.getName());

	public void getUrlList(String linkUrl) {
		// 爬取列表页面
		logger.info("Start crawl list page:" + linkUrl);
		String content = "";
		try {
			content = BasicCrawler.crawlPage(linkUrl, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(content);
		processListType(content);
	}

	private void processListType(String content) {
		JSONObject jsonObject = JSONObject.fromObject(content);
		JSONArray lists = jsonObject.getJSONArray("content");
		for (int i = 0; i < lists.size(); i++) {
			JSONObject info = (JSONObject) lists.get(i);
			String date = info.getString("post_date");
			String source = info.getString("author");
			String title = info.getString("title");
			String poster = info.getString("poster");
			String postUrl = info.getString("postUrl");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = null;
			try {
				time = sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
//			System.out.println(date+"\n"+source+"\n"+poster+"\n"+postUrl);
			an.add(new NewInfomationDto("", poster, time, title, postUrl, source));
			
		}
	}
}
