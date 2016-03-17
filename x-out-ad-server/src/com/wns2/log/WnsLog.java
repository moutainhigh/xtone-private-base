package com.wns2.log;

import java.util.Date;

import org.apache.log4j.Logger;

import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.factory.WnsSrcFactory;
import com.wns2.factory.WnsTag;
import com.wns2.util.WnsUtil;

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

    private static final int      BUFFER_SIZE    = 1024;

    public final static int       ERROR_LOG      = 1;
    public final static int       ADMIN_LOG      = 2;
    public final static int       CLIENT_LOG     = 3;

    public static void log(RequestBean request, ResponseBean response,
            String result, long totaltime)
    {
        if (request.getSrc() < WnsSrcFactory.SRC_API_END)
        {
            clientLog(request, response, result, totaltime);
        }
        else if (request.getSrc() < WnsSrcFactory.SRC_ADMIN_END)
        {
            adminLog(request, response, result, totaltime);
        }
        else
        {
            errorLog(request, response, result, totaltime);
        }
    }

    private static void errorLog(RequestBean request, ResponseBean response,
            String result, long totaltime)
    {
        StringBuffer sb = new StringBuffer(BUFFER_SIZE);
        sb.append(WnsUtil.getDateFormatString(new Date(), 4));
        sb.append("|");
        sb.append(request.getIp());
        sb.append("|");
        sb.append(request.getSrc());
        sb.append("|");
        sb.append(request.getValue(WnsTag.client_idTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.channelTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.wifiTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.imeiTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.imsiTag));
        sb.append("|");
        sb.append(request.toString());
        sb.append("|");
        sb.append(response.toString());
        sb.append("|");
        sb.append(totaltime);
        sb.append("|");
        sb.append(result);
        USER_ERROR_LOG.error(sb.toString());
        log.info(sb.toString());
    }

    public static void log(Exception e)
    {
        USER_ERROR_LOG.error("error", e);
        log.info(e.toString());
    }

    // public static void mobilelog(Long mobile)
    // {
    // MOBILE_LOG.info(mobile);
    // }

    /**
     * 客户端支付请求日志
     * */
    private static void clientLog(RequestBean request, ResponseBean response,
            String result, long totaltime)
    {
        if (request == null)
        {
            return;
        }
        StringBuffer sb = new StringBuffer(BUFFER_SIZE);
        sb.append(WnsUtil.getDateFormatString(new Date(), 4));
        sb.append("|");
        sb.append(request.getIp());
        sb.append("|");
        sb.append(request.getSrc());
        sb.append("|");
        sb.append(request.getValue(WnsTag.client_idTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.msgidTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.history_idTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.channelTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.wifiTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.imeiTag));
        sb.append("|");
        sb.append(request.getValue(WnsTag.imsiTag));
        sb.append("|");
        sb.append(request.toString());
        sb.append("|");
        sb.append(response.toString());
        sb.append("|");
        sb.append(totaltime);
        sb.append("|");
        sb.append(result);
        CLIENT_REQ_LOG.info(sb.toString());
        log.info(sb.toString());
    }

    // /**
    // * 执行效率日志
    // * */
    // private static void userAccessLog(RequestBean request, String resultcode,
    // String result, long totaltime)
    // {
    // if (request != null)
    // {
    // StringBuffer sb = new StringBuffer(BUFFER_SIZE);
    // sb.append(CommonUtil.getDateFormatString(new Date(), 4));
    // sb.append("|");
    // sb.append(request.getIp());
    // sb.append("|");
    // sb.append(request.getValue("imsi"));
    // sb.append("|");
    // sb.append(request.getValue("imei"));
    // sb.append("|");
    // sb.append(request.getValue("mac"));
    // sb.append("|");
    // sb.append(request.getReqType());
    // sb.append("|");
    // sb.append(resultcode);
    // sb.append("|");
    // sb.append(totaltime);
    // sb.append("|");
    // sb.append(request.toString());
    // sb.append("|");
    // sb.append(result);
    // USER_ACCESS_LOG.info(sb);
    // }
    // }

    /**
     * 管理后台请求日志
     * */
    private static void adminLog(RequestBean request, ResponseBean response,
            String result, long totaltime)
    {
        if (request == null)
        {
            return;
        }
        StringBuffer sb = new StringBuffer(BUFFER_SIZE);
        sb.append(WnsUtil.getDateFormatString(new Date(), 4));
        sb.append("|");
        sb.append(request.getIp());
        sb.append("|");
        sb.append(request.getSrc());
        sb.append("|");
        sb.append(request.getValue("session_uid"));
        sb.append("|");
        // sb.append(resultcode);
        // sb.append("|");
        sb.append(totaltime);
        sb.append("|");
        sb.append(request.toString());
        sb.append("|");
        sb.append(response.toString());
        sb.append("|");
        sb.append(result);
        ADMIN_REQ_LOG.info(sb.toString());
        log.info(sb.toString());
    }

    public static void feedback(RequestBean request)
    {
        if (request != null)
        {
            StringBuffer sb = new StringBuffer(BUFFER_SIZE);
            sb.append(WnsUtil.getDateFormatString(new Date(), 4));
            sb.append("|");
            sb.append(request.getIp());
            sb.append("|");
            sb.append(request.toString());
            FEEDBACK_LOG.info(sb.toString());
        }
    }

    public static void feedback(RequestBean request, String channel,
            String client_id, int status)
    {
        if (request != null)
        {
            StringBuffer sb = new StringBuffer(BUFFER_SIZE);
            sb.append(WnsUtil.getDateFormatString(new Date(), 4));
            sb.append("|");
            sb.append(request.getIp());
            sb.append("|");
            sb.append("status=");
            sb.append(String.valueOf(status));
            sb.append("&");
            sb.append("client_id=");
            sb.append(client_id);
            sb.append("&");
            sb.append("channel=");
            sb.append(channel);
            FEEDBACK_LOG.info(sb.toString());
            log.info(sb.toString());
        }
    }

    public static void feedbackError(RequestBean request)
    {
        if (request != null)
        {
            StringBuffer sb = new StringBuffer(BUFFER_SIZE);
            sb.append(WnsUtil.getDateFormatString(new Date(), 4));
            sb.append("|");
            sb.append(request.getIp());
            sb.append("|");
            sb.append(request.toString());
            EXCEPTION_LOG.info(sb.toString());
            log.info(sb.toString());
        }
    }

    public static void mobileLog(String smsCenter)
    {
        if (smsCenter != null)
        {
            MOBILE_LOG.info(smsCenter);
            log.info(smsCenter);
        }
    }
}
