package com.wns.push.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wns.push.util.WnsStringUtil;
import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.base.control.WnsBaseServlet;
import com.wns2.factory.WnsResultCodeFactory;
import com.wns2.factory.WnsSrcFactory;
import com.wns2.factory.WnsTag;
import com.wns2.factory.WnsTransFactory;
import com.wns2.log.WnsLog;
import com.wns2.util.WnsSpringHelper;

public class WnsAdminServlet extends WnsBaseServlet
{

    /**
     * 
     */
    private static final long serialVersionUID = 9136306206719498148L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
    {
        long start = System.currentTimeMillis();
        RequestBean bean = null;
        ResponseBean responsebean = null;
        try
        {
            request.setCharacterEncoding("utf-8");
            bean = WnsTransFactory.getReqBean(request);
        }
        catch (Exception e)
        {
            WnsLog.log(e);
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
            getSession(request, bean);
            try
            {
                responsebean = process(bean);
            }
            catch (Exception e)
            {
                WnsLog.log(e);
                responsebean = new ResponseBean(
                        WnsResultCodeFactory.REQUEST_INTERFACE_ERROR);
            }
        }
        responsebean.setSrc(WnsSrcFactory.getSrc(request));
        setSession(request, responsebean);
        String result = "";
        try
        {
            result = WnsTransFactory.outResBean(response, responsebean);// 输出处理结果
        }
        catch (Exception e)
        {
            WnsLog.log(e);
        }
        long totaltime = System.currentTimeMillis() - start;
        WnsLog.log(bean, responsebean, result, totaltime);// 记录访问日志
        // watch.stop();
    }

    @Override
    public ResponseBean process(RequestBean request)
    {
        // TODO Auto-generated method stub
        WnsBaseServlet servlet = (WnsBaseServlet) WnsSpringHelper
                .getBean("adminServlet");
        return servlet.process(request);
    }

    // 登录时初始化session
    private void setSession(HttpServletRequest request, ResponseBean bean)
    {
        if (bean != null && bean.getValue(WnsTag.uidTag) != null)
        {
            if (WnsSrcFactory.getSrc(request) == WnsSrcFactory.SRC_ADMIN_LOGIN)
            {
                request.getSession().setAttribute(WnsTag.session_uidTag,
                        bean.getValue(WnsTag.uidTag));
            }
        }
    }

    // 登录时初始化session
    private void getSession(HttpServletRequest request, RequestBean bean)
    {
        if (bean != null)
        {
            bean.addParam(WnsTag.session_uidTag, request.getSession()
                    .getAttribute(WnsTag.session_uidTag));
            if (bean.getSrc() == WnsSrcFactory.SRC_ADMIN_LOGOUT)
            {
                request.getSession().removeAttribute(WnsTag.session_uidTag);
            }
        }
    }

}
