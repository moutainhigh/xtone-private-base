package com.wns2.log;



import org.apache.log4j.Logger;


public class WnsLog
{
    protected static final Logger CLIENT_REQ_LOG = Logger.getLogger("client");        // 客户端请求
    protected static final Logger ADMIN_REQ_LOG  = Logger.getLogger("admin");         // 管理后台
    protected static final Logger USER_ERROR_LOG = Logger.getLogger("runerror");      // 运行错误
    protected static final Logger MOBILE_LOG     = Logger.getLogger("mobile");        // 无法识别的手机短信中心号

    protected static final Logger FEEDBACK_LOG   = Logger.getLogger("feedback");
    protected static final Logger EXCEPTION_LOG  = Logger
                                                         .getLogger("exception");

    protected static final Logger log            = Logger
                                                         .getLogger(WnsLog.class);



    public final static int       ERROR_LOG      = 1;
    public final static int       ADMIN_LOG      = 2;
    public final static int       CLIENT_LOG     = 3;

 
    public static void log(Exception e)
    {
        USER_ERROR_LOG.error("error", e);
        log.info(e.toString());
    }

}
