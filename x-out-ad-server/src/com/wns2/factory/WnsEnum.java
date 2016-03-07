package com.wns2.factory;

public class WnsEnum
{
    public final static int PUSH_TYPE_MSG     = 1; // 通知栏push消息
    public final static int PUSH_TYPE_INSTALL = 2; // 安装push消息
    public final static int PUSH_TYPE_LINK    = 3; // 桌面快捷方式push消息
    public final static int PUSH_TYPE_ADVICE  = 4; // 广告push消息
    public final static int PUSH_TYPE_NOTIFIY = 5; // 组合通知push消息
    public final static int PUSH_TYPE_APKWALL = 6; // 推荐墙
    
    public final static int PUSH_SUB_TYPE_APK = 0; // 推送apk连接
    public final static int PUSH_SUB_TYPE_URL = 1; // 推送url链接地址
    
    public final static int PUSH_ROLE_ADMIN   = 1; //管理员账号
    public final static int PUSH_ROLE_CHANNEL = 3; // 渠道账号
    public final static int PUSH_ROLE_DEV  = 4;  // 开发者账号
    
    public final static String FEEDBACK_TYPE_DWONLOAD_BEGIN = "10";
    public final static String FEEDBACK_TYPE_DWONLOAD_COMPLETED = "11";
    public final static String FEEDBACK_TYPE_DWONLOAD_ERROR = "12";
    public final static String FEEDBACK_TYPE_INSTALL_PRESENT = "13";    
    public final static String FEEDBACK_TYPE_INSTALL_COMPLETED = "14";
    public final static String FEEDBACK_TYPE_UNINSTALL_COMPLETED = "15";
    public final static String FEEDBACK_TYPE_EXCEPTION = "16";  
    public final static String FEEDBACK_TYPE_TIPRUN_POSITIVE = "17";
    public final static String FEEDBACK_TYPE_TIPRUN_NEGATIVE = "18";
    public final static String FEEDBACK_TYPE_INTERSTITIAL_PRESENT = "19";
    public final static String FEEDBACK_TYPE_INTERSTITIAL_CLICK = "20";
    
    
    public final static int ON = 0;
    public final static int OFF = 1;
    
    public final static int NETWORK_WIFI = 0;
    public final static int NETWORK_2G_3G = 1;
    public final static int NETWORK_ALL = 2;
}
