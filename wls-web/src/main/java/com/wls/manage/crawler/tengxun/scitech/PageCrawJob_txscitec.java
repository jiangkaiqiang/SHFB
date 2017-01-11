package com.wls.manage.crawler.tengxun.scitech;

import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageCrawJob_txscitec {
    public  void  pageCraw(NewInfomationDto ni){
        String content="";
        try{
            Thread.sleep(300);
            PageParseJob_txscitec crawler =new PageParseJob_txscitec();
            crawler.parse(ni);
        } catch (Exception e){
            e.printStackTrace();
        }
  
    }

    public static void main(String args[]){
    	NewInfomationDto ni = new NewInfomationDto();
    	ni.setHref("http://tech.qq.com/a/20170111/037862.htm");
    	new PageCrawJob_txscitec().pageCraw(ni);
//        System.out.println(new PageCrawJob_cyb().pageCraw("http://www.cyzone.cn/a/20161230/307006.html"));
    }
}
