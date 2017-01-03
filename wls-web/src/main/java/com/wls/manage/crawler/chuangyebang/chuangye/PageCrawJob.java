package com.wls.manage.crawler.chuangyebang.chuangye;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by haolidong on 2016/11/14.
 */
public class PageCrawJob {
    public  String  pageCraw(String url){
        String content="";
        try{
            Thread.sleep(300);
            PageParseJob crawler =new PageParseJob();
            content=crawler.parse(url);
        } catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }

    public static void main(String args[]){
        System.out.println(new PageCrawJob().pageCraw("http://www.cyzone.cn/a/20161230/307006.html"));
    }
}
