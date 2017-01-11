package com.wls.manage.crawler.tengxun.scitech;

import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageCrawJob_chinaenter {
    public  void  pageCraw(NewInfomationDto ni){
        String content="";
        try{
            Thread.sleep(300);
            PageParseJob_chinaenter crawler =new PageParseJob_chinaenter();
            crawler.parse(ni);
        } catch (Exception e){
            e.printStackTrace();
        }
  
    }

    public static void main(String args[]){
    	NewInfomationDto ni = new NewInfomationDto();
    	ni.setHref("http://www.chinanews.com/yl/2017/01-11/8120924.shtml");
    	new PageCrawJob_chinaenter().pageCraw(ni);
//        System.out.println(new PageCrawJob_cyb().pageCraw("http://www.cyzone.cn/a/20161230/307006.html"));
    }
}
