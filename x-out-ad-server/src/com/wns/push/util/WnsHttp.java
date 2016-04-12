package com.wns.push.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wns2.base.bean.WnsSysProperty;
import com.wns2.factory.WnsSrcFactory;
import com.wns2.util.WnsSpringHelper;

public class WnsHttp
{

    @SuppressWarnings("deprecation")
    public static Map<String, Object> getAppList(String apiurl, Long orgId,
            int startIndex, int endIndex, int priv)
    {
        Map<String, Object> map = new HashMap<String, Object>();

        PostMethod post = null;
        try
        {
            HttpClient httpClient = new HttpClient(new HttpClientParams(),
                    new SimpleHttpConnectionManager(true));
            String content = getFiles(orgId, startIndex, endIndex, priv);
            post = new PostMethod(apiurl);
            post.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
            post.getParams().setContentCharset("UTF-8");
            post.setRequestBody(content);
            {
                httpClient.getHttpConnectionManager().getParams()
                        .setConnectionTimeout(30 * 1000);
                httpClient.executeMethod(post);
            }

            String resp = post.getResponseBodyAsString();
            resp = URLDecoder.decode(resp, "utf-8");

            map = parseFiles(resp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            post.releaseConnection();
        }
        return map;
    }

    /**
     * 
     * 
     * @param userId
     * @param limitNumer
     * @return
     */
    public final static String getFiles(Long orgId, int startNumber,
            int endNumber, int priv)
    {
        JSONObject obj = new JSONObject();
        obj.put("app_type_id", "0");
        obj.put("app_type_name", "0");
        obj.put("topic_type", "1");
        if (orgId != null)
        {
            // obj.put("channel_id", user.getIdh().getIdhCode());
            // obj.put("channel_name", user.getIdh().getIdhName());
        }
        else
        {
            obj.put("channel_id", "");
            obj.put("channel_name", "");
        }
        obj.put("orgid", orgId);
        obj.put("private", priv);
        obj.put("size", endNumber);
        obj.put("start", startNumber);
        obj.put("next_num", endNumber);

        return obj.toString();
    }

    @SuppressWarnings("unchecked")
    private final static Map<String, Object> parseFiles(String jsonStr)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        String status = "";
        if (jsonObject.containsKey("error_code"))
        {
            String error = jsonObject.getString("error");
            status = jsonObject.getString("error_code");
            map.put("resultCode", status);
            map.put("resultInfo", error);
            return map;
        }
        else if (jsonObject.containsKey("appinfo"))
        {
            try
            {
                JSONArray packeges = jsonObject.getJSONArray("appinfo");
                Map<String, Object>[] arrayMaps = new Map[packeges.size()];
                // (Map<String, Object>[]) JSONArray
                // .toArray(packeges, Map.class);
                for (int i = 0; i < packeges.size(); i++)
                {
                    JSONObject obj = packeges.getJSONObject(i);
                    arrayMaps[i] = JSONObject.toJavaObject(obj, Map.class);
                }
                map.put("appinfo", arrayMaps);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            map.put("resultCode", "9999");
            map.put("resultInfo", "paserFiles" + jsonStr);
            return map;
        }
        return map;
    }

    public static String sendPost(String url, String content)
            throws ClientProtocolException, IOException
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url.trim());

        httpost.setEntity(new StringEntity(content, Consts.UTF_8));
        HttpResponse hresponse;
        String result = null;
        hresponse = httpclient.execute(httpost);
        HttpEntity entity = hresponse.getEntity();
        result = EntityUtils.toString(entity);
        EntityUtils.consume(entity);
        httpclient.getConnectionManager().shutdown();
        return result;
    }

    public static void sendCleanTask()
    {
        WnsSysProperty sysProperty = (WnsSysProperty) WnsSpringHelper
                .getBean("bSysProperty");
        String serverList = sysProperty.getServerList();
        try
        {
            String[] servers = serverList.split("\\|");
            for (String s : servers)
            {
                s += "/adminmanager/" + WnsSrcFactory.SRC_ADMIN_CLEAN_TASK;
                String content = "{\"date\":\"2014-4-27\"}";
                sendPost(s, content);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
