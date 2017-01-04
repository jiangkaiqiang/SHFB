package com.wls.manage.crawler.tengxun.tech;
import com.wls.manage.dao.InformationMapper;
import com.wls.manage.entity.InformationEntity;
import com.wls.manage.service.base.HttpService;
import com.wls.manage.service.base.impl.HttpServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by haolidong on 2016/11/21.
 */
public class genHTML {
	@Autowired
	private InformationMapper informationMapper;
    public void Gen(TXSciBean txsci,String time,String title){
    	String HTMLcontent="";
//        String HTMLcontent="<html><body>";
//        HTMLcontent+="<h1><font color=\"red\"><p align=\"center\">"+txsci.getTitle()+"</p></font></h1>\n";
//        HTMLcontent+="<p align=\"right\"><font color=\"gray\">"+txsci.getData()+"</font></p>\n";
        ArrayList<String> as = new ArrayList<String>();

        String pic="";
        ArrayList<String> ss = new ArrayList<String>();
        for(Content con :txsci.ac){
            if(con.isImg()){
                HTMLcontent+="<img src=\""+con.getCon()+"\" border=\"0\"/>\n";
                if(ss.size()<=3){
                	ss.add(con.getCon());
                }
                
//                if(picCount<=3){
//                    if(picCount==1){
//                        pic+=con.getCon();
//                    }else {
//                        pic=pic+";"+con.getCon();
//                    }
//
//                }
                
            }else{
                if(con.getCon().equals(""))continue;
                HTMLcontent+="<p>"+con.getCon()+"</p>\n";
            }
        }
        if(ss.size()==2||ss.size()==1){
        	pic=ss.get(0);
        }else if(ss.size()==3){
        	pic=ss.get(0)+";"+ss.get(1)+";"+ss.get(2);
        }
        System.out.println(pic);
        try{
            //"main\TxData\Test.html"
//            FileOutputStream fo = new FileOutputStream(new File(filename));
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo));
//            bw.write(HTMLcontent);
//            System.out.println(pic);
        	if(!HTMLcontent.equals("")&&!pic.equals("")){
        		HttpService httpService = new HttpServiceImpl();
            /**
             * 参数注意！！！！！！
             *  String title,//标题
			   String content,//内容
			 String infocategory,//分类：“1”：科技类，“2”：互联网类，3：校园类；4：财经类；5：创业类  注意：，这个地方用数字不要用字符串
			 String source,//来源：腾讯新闻 等等
			 String coverpiclist,//封面图片：解析几张放在这，用特殊字符隔开，注意：就只有一张，零张和三张这三种情况，如果有大于三张那就只取三张，大于一张少于三张就去一张，没有就0张
			 String time//添加时间
             * 时间注意替换一下吧：2016-01-23 12:10:12格式
             */
				InformationEntity informationEntity = new InformationEntity();
				informationEntity.setContent(HTMLcontent);
				informationEntity.setCoverpiclist(pic);
				informationEntity.setInfocategory("5");
				informationEntity.setSource("腾讯新闻");
				informationEntity.setTitle(title);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(time);
				informationEntity.setTime(date);
				informationMapper.insertInformation(informationEntity);
           /* String msg="content="+HTMLcontent+"&infocategory=5&source=腾讯新闻&coverpiclist="+pic+"&title="+title+"&time="+time;
//            System.out.println(msg);
//            InformationController infoctl = new InformationController();
            httpService.sendPost("http://localhost:8080/i/information/addInformation", msg,10);*/
//            infoctl.addInformation(txsci.getTitle(), HTMLcontent, "科学类", "", pic);
//            infoctl.findInformationByID(1);
//            bw.close();
//            fo.close();
        	}
            
        } catch (Exception e){
            e.printStackTrace();
        }



    }
}
