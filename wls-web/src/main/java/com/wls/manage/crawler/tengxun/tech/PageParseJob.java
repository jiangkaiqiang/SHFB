package com.wls.manage.crawler.tengxun.tech;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wls.manage.crawler.general.BasicCrawler;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJob {
	// 获取img标签正则  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";  
    // 获取src路径的正则  
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)"; 
    public static void parse(String linkUrl,String time,String title){
        String content = "";
        try {
            content= BasicCrawler.crawlPage(linkUrl,"gb2312");
            //content=BasicCrawler.crawlPage(linkUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(content);
        parseNews(doc,linkUrl,time,title);
    }
    public static void main(String[] args) {
//    	getHtmlImgs("http://view.news.qq.com/original/legacyintouch/d577.html");
	}
    
    private static List<String> getImageUrl(String HTML) {  
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);  
        List<String> listImgUrl = new ArrayList<String>();  
        while (matcher.find()) {  
            listImgUrl.add(matcher.group());  
        }  
        return listImgUrl;  
    }  
    
    public static List<String> getHtmlImgs(String content){
//    	String content = "";
//    	content= BasicCrawler.crawlPage(linkUrl,"gb2312");
//    	Document doc = Jsoup.parse(content);
//    	System.out.println(doc);
    	List<String> listImgUrl=getImageUrl(content);
//    	for(String s:listImgUrl){
//    		System.out.println(s);
//    	}
//    	Elements eles = doc.select("img[src]");
//    	for(Element ele:eles){
//    		System.out.println(ele.text());
//    	}
    	List<String> ImageSrc = getImageSrc(listImgUrl);
//    	for(String s:ImageSrc){
//    		System.out.println(s);
//    	}
    	return ImageSrc;
    }
    
    private static List<String> getImageSrc(List<String> listImageUrl) {  
        List<String> listImgSrc = new ArrayList<String>();  
        for (String image : listImageUrl) {  
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);  
            while (matcher.find()) {  
                listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1));  
            }  
        }  
        return listImgSrc;  
    }  
    
    private static void parseNews(Document doc, String linkUrl,String time,String contextTitle) {
        Elements eles = doc.select("div[id=Cnt-Main-Article-QQ] p");
        TXSciBean txsci=new TXSciBean();
        for(Element ele : eles){
            Element img=ele.select("img[src]").first();
            if(img!=null){
                String title =img.text();
                txsci.ac.add(new Content(true,img.attr("src").toString()));
//                System.out.println(img.attr("src"));

            } else {
                String con=ele.text();
                if(con.contains("推荐：了解科技圈")||con.contains("精彩视频推荐")){
                    break;
                }
                txsci.ac.add(new Content(false,ele.text()));
                System.out.println(ele.text());
            }

        }
        genHTML genHTML = new genHTML();
        genHTML.Gen(txsci,time,contextTitle);
    }


}
