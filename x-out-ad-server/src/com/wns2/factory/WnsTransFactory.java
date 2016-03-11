package com.wns2.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.tran.WnsAdminTrans;
import com.wns2.tran.WnsBaseTrans;

public class WnsTransFactory
{

    public static RequestBean getReqBean(final HttpServletRequest request)
            throws Exception
    {
        int src = WnsSrcFactory.getSrc(request);
        WnsBaseTrans baseTrans = getTrans(src);
//        NgsteamBaseTrans baseTrans = new NgsteamBaseTrans();
        RequestBean bean = null;
        try
        {
            if (baseTrans != null)
            {
                bean = baseTrans.getReqBean(request);
            }
            else
            {
                bean = new RequestBean(false);
            }
            if (bean != null)
            {
                bean.setReqType(src);
                bean.setSrc(src);
                bean.setIp(getIpAddr(request));
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return bean;
    }

    /**
     * 将处理结果返回给请求方
     * 
     * @author 
     * @throws Exception
     * */
    public static String outResBean(final HttpServletResponse response,
            ResponseBean bean) throws Exception
    {
        WnsBaseTrans baseTrans = getTrans(bean.getSrc());
        if (baseTrans != null)
        {
            return baseTrans.outResBean(response, bean);
        }
        else
        {
            return "";
        }
    }

    private static WnsBaseTrans getTrans(int src)
    {
        if (src < WnsSrcFactory.SRC_API_END)
        {
        return new WnsBaseTrans();
        }
        else
        {
            return new WnsAdminTrans();
        }
//        switch (src)
//        {
//        case NgsteamSrcFactory.SRC_CLIENT:
//            return new ClientTrans();
//        case NgsteamSrcFactory.SRC_CPCLIENT:
//            return new ManagementTrans();
//        case NgsteamSrcFactory.SRC_ALIPAY:
//            return new AlipayTrans();
//        case NgsteamSrcFactory.SRC_YEEPAY:
//            return new YeepayTrans();
//        case NgsteamSrcFactory.SRC_SZF:
//            return new SzfTrans();
//        case NgsteamSrcFactory.SRC_UPPAY:
//            return new UpTrans();
//        case NgsteamSrcFactory.SRC_PEAK:
//            return new PeakTrans();
//        case NgsteamSrcFactory.SRC_SZF_ALL:
//            return new SzfAllTrans();
//        case NgsteamSrcFactory.SRC_SP_SYNCH:
//            return new SpSynchTrans();
//        case NgsteamSrcFactory.SRC_SAFE_CLIENT:
//            return new SafeClientTrans();
//        default:
//            return null;
//        }
    }
    
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
