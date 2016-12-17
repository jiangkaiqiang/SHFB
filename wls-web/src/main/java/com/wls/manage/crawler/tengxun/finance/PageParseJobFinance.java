package com.wls.manage.crawler.tengxun.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wls.manage.crawler.general.BasicCrawler;
import com.wls.manage.crawler.tengxun.tech.Content;
import com.wls.manage.crawler.tengxun.tech.TXSciBean;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageParseJobFinance {
    
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
                if(con.contains("精彩视频推荐")){
                    break;
                }
                txsci.ac.add(new Content(false,ele.text()));
//                System.out.println(ele.text());
            }

        }
        genHTMLFinance genHTML = new genHTMLFinance();
        genHTML.Gen(txsci,time,contextTitle);
    }
    
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


}
