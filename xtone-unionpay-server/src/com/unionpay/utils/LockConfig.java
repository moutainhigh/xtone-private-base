package com.unionpay.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LockConfig {
    
    /**
     * 银联回调通知地址
     */
    public static String unionpay_notify_url;
    /**
     * 银联商户号
     */
    public static String merId ;
    
    static{
        Properties prop = new Properties();   
        InputStream in = LockConfig.class.getResourceAsStream("/lock_config.properties");   
        try {   
            prop.load(in);   
            unionpay_notify_url = prop.getProperty("unionpay_notify_url").trim();   
            merId = prop.getProperty("merId").trim();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
    }
}
