package com.shfb.rfid.manage.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageParseUtil {
	public List<String> parse(String content){
		List<String> ls = new ArrayList<String>();
		Document doc = Jsoup.parse(content);
        Elements eles=doc.select("img");
        for(Element e:eles){
        	ls.add(e.attr("src"));
        }
        return ls;
	}
}
