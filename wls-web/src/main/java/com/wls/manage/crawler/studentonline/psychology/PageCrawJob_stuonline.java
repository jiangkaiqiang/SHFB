package com.wls.manage.crawler.studentonline.psychology;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageCrawJob_stuonline {
    public  void  pageCraw(NewInfomationDto ni){
        String content="";
        try{
            Thread.sleep(300);
            PageParseJob_stuonline crawler =new PageParseJob_stuonline();
            crawler.parse(ni);
        } catch (Exception e){
            e.printStackTrace();
        }
  
    }

    public static void main(String args[]){
    	NewInfomationDto ni = new NewInfomationDto();
    	ni.setHref("http://campus.univs.cn/xl/2016/1214/1151626.shtml");
    	new PageCrawJob_stuonline().pageCraw(ni);
//        System.out.println(new PageCrawJob_cyb().pageCraw("http://www.cyzone.cn/a/20161230/307006.html"));
    }
}
