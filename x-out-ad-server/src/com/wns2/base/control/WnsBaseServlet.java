package com.wns2.base.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wns.push.util.WnsStringUtil;
import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.factory.WnsResultCodeFactory;
import com.wns2.factory.WnsSrcFactory;
import com.wns2.factory.WnsTransFactory;
import com.wns2.log.WnsLog;

public abstract class WnsBaseServlet extends HttpServlet
{

    public static final Logger log = Logger.getLogger(WnsBaseServlet.class);
    /**
     * 
     */
    private static final long serialVersionUID = 5828401831894498712L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
    {
        RequestBean bean = null;
        ResponseBean responsebean = null;
        long start = System.currentTimeMillis();
        try
        {
            request.setCharacterEncoding("utf-8");
            bean = WnsTransFactory.getReqBean(request);
        }
        catch (Exception e)
        {
            WnsLog.log(e);
            log.info(e.toString());
            responsebean = new ResponseBean(
                    WnsResultCodeFactory.UNKOWN_ERROR);
        }
        String errorcode = bean.getValue("response_error_code");
        if (!WnsStringUtil.isBlank(errorcode))
        {
            responsebean = new ResponseBean(
                    WnsResultCodeFactory.UNKOWN_ERROR);
        }
        else
        {
            try
            {
                responsebean = process(bean);
            }
            catch (Exception e)
            {
                WnsLog.log(e);
                log.info(e.toString());
                responsebean = new ResponseBean(
                        WnsResultCodeFactory.REQUEST_INTERFACE_ERROR);
            }
        }
        responsebean.setSrc(WnsSrcFactory.getSrc(request));
        String result = "";
        try
        {
            result = WnsTransFactory.outResBean(response, responsebean);// 输出处理结果
        }
        catch (Exception e)
        {
            WnsLog.log(e);
            log.info(e.toString());
        }
        long totaltime = System.currentTimeMillis() - start;
//        NgsteamLog.log(bean, responsebean, result, totaltime);// 记录访问日志
        // watch.stop();
    }

    public abstract ResponseBean process(RequestBean request);
}
