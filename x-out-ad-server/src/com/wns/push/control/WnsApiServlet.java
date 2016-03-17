package com.wns.push.control;

import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.base.control.WnsBaseServlet;
import com.wns2.util.WnsSpringHelper;


public class WnsApiServlet extends WnsBaseServlet
{
    /**
     * 
     */
    private static final long serialVersionUID = 9136306206719498148L;

    @Override
    public ResponseBean process(RequestBean request)
    {
        // TODO Auto-generated method stub
        WnsBaseServlet servlet = (WnsBaseServlet) WnsSpringHelper.getBean("apiServlet");
        return servlet.process(request);
    }

}
