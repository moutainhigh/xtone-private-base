package com.swiftpass.util;

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
public class SwiftpassConfig {
    
    /**
     * 威富通交易密钥
     */
    public static String key ;
    
    /**
     * 威富通商户号
     */
    public static String mch_id;
    
    /**
     * 威富通请求url
     */
    public static String req_url;
    
    /**
     * 通知url
     */
    public static String notify_url;
    
    /**
     * 官包转发无线平台url
     */
    public static String wj_notify_url;
    /**
     * 官包支付宝PID
     */
    public static String Alipay_PARTNER;
    /**
     * 官包支付宝商户收款账号
     */
    public static String Alipay_SELLER;
    /**
     * 官包支付宝商户私钥，pkcs8格式
     */
    public static String Alipay_RSA_PRIVATE;
    /**
     * 官包支付宝支付通知地址
     */
    public static String Alipay_notify_url;
    
    
    static{
        Properties prop = new Properties();   
        InputStream in = SwiftpassConfig.class.getResourceAsStream("/swift_config.properties");   
        try {   
            prop.load(in);   
            key = prop.getProperty("key").trim();   
            mch_id = prop.getProperty("mch_id").trim();   
            req_url = prop.getProperty("req_url").trim();   
            notify_url = prop.getProperty("notify_url").trim();   
            wj_notify_url = prop.getProperty("wj_notify_url").trim();   
            Alipay_PARTNER = prop.getProperty("Alipay_PARTNER").trim();   
            Alipay_SELLER = prop.getProperty("Alipay_SELLER").trim();   
            Alipay_RSA_PRIVATE = prop.getProperty("Alipay_RSA_PRIVATE").trim();   
            Alipay_notify_url = prop.getProperty("Alipay_notify_url").trim();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
    }
}
