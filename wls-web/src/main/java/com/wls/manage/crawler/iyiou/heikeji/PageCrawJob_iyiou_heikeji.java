package com.wls.manage.crawler.iyiou.heikeji;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wls.manage.dto.NewInfomationDto;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageCrawJob_iyiou_heikeji {
    public  void  pageCraw(NewInfomationDto ni){
        String content="";
        try{
            Thread.sleep(300);
            PageParseJob_iyiou_heikeji crawler =new PageParseJob_iyiou_heikeji();
            crawler.parse(ni);
        } catch (Exception e){
            e.printStackTrace();
        }
  
    }

    public static void main(String args[]){
    	NewInfomationDto ni = new NewInfomationDto();
    	ni.setHref("http://www.iyiou.com/p/37202");
    	new PageCrawJob_iyiou_heikeji().pageCraw(ni);
//        System.out.println(new PageCrawJob_cyb().pageCraw("http://www.cyzone.cn/a/20161230/307006.html"));
    }
}
