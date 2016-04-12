package com.wns2.tran;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.base.bean.TransmissionUtil;

public class WnsAdminTrans extends WnsBaseTrans
{
    public static final Logger log = Logger.getLogger(WnsAdminTrans.class);

    /**
     *验证客戶端请求的数据信息，并对请求的数据进行封装
     * 
     * @throws IOException
     * */
    public RequestBean getReqBean(final HttpServletRequest request)
            throws Exception
    {
        JSONObject json = null;
        String jstr = getString(request);
        log.info("BEFORE:" + jstr);
        // jstr = new String(
        // base64.decode(jstr.getBytes("utf-8"), base64.DEFAULT), "utf-8");
        jstr = URLDecoder.decode(jstr, "utf-8");
        // jstr = NgsteamRsa.decryptByPrivateKey(jstr,
        // NgsteamBase.ngsteamGetPrivateKey());
        log.info("AFTER:" + jstr);

        json = JSON.parseObject(jstr);
        RequestBean bean = TransmissionUtil.jsonToBean(json);
        
        return bean;
    }

    /**
     *将操作结果返回给
     * 
     * @throws Exception
     * */
    public String outResBean(HttpServletResponse response,
            final ResponseBean bean) throws Exception
    {
        OutputStream out = null;
        String json = null;
        try
        {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            out = response.getOutputStream();
            json = TransmissionUtil.responseToJson(bean);

            // json = URLEncoder.encode(json, "utf-8");
            // json = base64.encodeToString(json.getBytes(), base64.DEFAULT);
            // json = NgsteamRsa.encryptByPrivateKey(json,
            // NgsteamBase.ngsteamGetPrivateKey());
            out.write(json.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
        return json;
    }

    public static String getString(final HttpServletRequest request)
            throws IOException
    {
        if (request == null)
        {
            return null;
        }
        InputStream in = null;
        try
        {
            in = request.getInputStream();
            byte[] data = new byte[request.getContentLength()];
            byte[] buf = new byte[2048];
            int len = 0;
            int index = 0;
            while ((len = in.read(buf)) != -1)
            {
                System.arraycopy(buf, 0, data, index, len);
                index += len;
            }
            return new String(data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            if (in != null)
                in.close(); // 关闭输入流
        }
    }

    public void validator(RequestBean bean) throws UnsupportedEncodingException
    {
        bean.setCheck(true);
    }
}
