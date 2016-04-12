package com.wns.push.statis;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.wns.push.util.WnsDateUtil;
import com.wns.push.util.WnsHttp;
import com.wns2.base.bean.WnsSysProperty;
import com.wns2.factory.WnsSrcFactory;
import com.wns2.util.WnsSpringHelper;

public class WnsStatisJob
{
    private static WnsSysProperty sysConfig = null;

    static
    {
//        sysConfig = (WnsSysProperty) WnsSpringHelper.getBean("bSysProperty");
    }

    public void work()
    {
        try
        {
        	System.out.println("------statisJob.work--------");
            String url = sysConfig.getStatis() + "/adminmanager/" + WnsSrcFactory.SRC_ADMIN_STATIS_CLIENT;
            String content = null;
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            date = calendar.getTime();
            
            content = "{\"date\":\"" + WnsDateUtil.NgsteamToString(date)
                    + "\"}"; 
        	System.out.println("------url--------"+url);
        	System.out.println("------content--------"+content);
            WnsHttp.sendPost(url, content);
            
            url = sysConfig.getStatis() + "/adminmanager/" + WnsSrcFactory.SRC_ADMIN_STATIS_MSG;
            WnsHttp.sendPost(url, content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        String content = null;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        date = calendar.getTime();
        
//        content = "{\"date\":\"" + WnsDateUtil.NgsteamToString(date)
//                + "\"}"; 

    	System.out.println("------content--------"+date);
	}
    
    
   
}
