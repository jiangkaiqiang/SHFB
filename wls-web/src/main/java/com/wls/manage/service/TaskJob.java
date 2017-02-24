package com.wls.manage.service;
import org.springframework.scheduling.annotation.Scheduled;    
import org.springframework.stereotype.Component;  
import com.wls.manage.service.base.HttpService;
import com.wls.manage.service.base.impl.HttpServiceImpl;  
@Component("taskJob")  
public class TaskJob {  
	String url = "http://www.weilanshu.com";
    @Scheduled(cron = "0 30 23 * * ?")  
    public void job1() {  
        HttpService httpService = new HttpServiceImpl();
        httpService.sendGet(url+"/i/information/addInformationWithChuangyebang",20000);
    }  
    @Scheduled(cron = "0 33 23 * * ?")  
    public void job2() {  
        HttpService httpService = new HttpServiceImpl();
        httpService.sendGet(url+"/i/information/addInformationWithIresearch",20000);
    }  
    @Scheduled(cron = "0 36 23 * * ?")  
    public void job3() {  
        HttpService httpService = new HttpServiceImpl();
        httpService.sendGet(url+"/i/information/addInformationWithYiouKeJi",20000);
    }  
    @Scheduled(cron = "0 39 23 * * ?")  
    public void job4() {  
        HttpService httpService = new HttpServiceImpl();
        httpService.sendGet(url+"/i/information/addInformationWithYiouWenChuang",20000);
    }  
    @Scheduled(cron = "0 42 23 * * ?")  
    public void job5() {  
        HttpService httpService = new HttpServiceImpl();
        httpService.sendGet(url+"/i/information/addInformationWithCXiaoYuanPsy",20000);
    }  
    @Scheduled(cron = "0 45 23 * * ?")  
    public void job6() {  
        HttpService httpService = new HttpServiceImpl();
        httpService.sendGet(url+"/i/information/addInformationWithChinanews",20000);
    } 
    /*
    @Scheduled(cron = "0 48 23 * * ?")  
    public void job7() {  
        HttpService httpService = new HttpServiceImpl();
        httpService.sendGet(url+"/i/information/addInformationWithTengxunTech",20000);
    }  */
}