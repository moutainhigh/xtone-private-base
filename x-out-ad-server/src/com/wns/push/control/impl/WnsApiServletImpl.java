package com.wns.push.control.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wns.push.bean.ApkItem;
import com.wns.push.bean.AppSwitch;
import com.wns.push.bean.ChannelArea;
import com.wns.push.bean.Client;
import com.wns.push.bean.DelCmd;
import com.wns.push.bean.LibItem;
import com.wns.push.bean.WnsCountry;
import com.wns.push.bean.PushTestPhone;
import com.wns.push.bean.WhiteChannel;
import com.wns.push.bean.push_history;
import com.wns.push.bean.push_policy_ext;
import com.wns.push.control.WnsApiServlet;
import com.wns.push.dao.ApkDao;
import com.wns.push.dao.AppSwitchDao;
import com.wns.push.dao.ChannelAreaDao;
import com.wns.push.dao.ClientDao;
import com.wns.push.dao.LibDao;
import com.wns.push.dao.UninstallationLogDao;
import com.wns.push.dao.WhiteChannelDao;
import com.wns.push.dao.ldgPushTestDao;
import com.wns.push.dao.pushHistoryDao;
import com.wns.push.util.WnsDateUtil;
import com.wns.push.util.WnsLBSManager;
import com.wns.push.util.WnsPushTaskManager;
import com.wns.push.util.WnsStringUtil;
import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.factory.WnsEnum;
import com.wns2.factory.WnsRedisFactory;
import com.wns2.factory.WnsResultCodeFactory;
import com.wns2.factory.WnsSrcFactory;
import com.wns2.factory.WnsTag;
import com.wns2.log.WnsLog;
import com.wns2.util.WnsSpringHelper;
import com.wns2.util.WnsUtil;

public class WnsApiServletImpl extends WnsApiServlet
{

    /**
     * 
     */
    private static final long serialVersionUID = 7591994902635538204L;

    @Override
    public ResponseBean process(RequestBean request)
    {
        int src = request.getReqType();
        ResponseBean ret = null;

        switch (src)
        {

            case WnsSrcFactory.SRC_COMMAND_SILENCE:
                ret = commandSilence(request);
                break;

            case WnsSrcFactory.SRC_FEEDBACK:
                ret = feedBack(request);
                break;

            case WnsSrcFactory.SRC_PUSH_INFORMATION:
                ret = pushInformation(request);
                break;

            case WnsSrcFactory.SRC_PUSH_LOG:
                ret = pushLog(request);
                break;

            case WnsSrcFactory.SRC_DOWNLOAD_START:
                ret = downloadStartLog(request);
                break;

            case WnsSrcFactory.SRC_DOWNLOAD_FINISH:
                ret = downloadFinishLog(request);
                break;

            case WnsSrcFactory.SRC_INSTALL:
                ret = installLog(request);
                break;

            case WnsSrcFactory.SRC_UPLOAD_ERROR:
                ret = uploadError(request);
                break;

            case WnsSrcFactory.SRC_CHECK_LIB_UPDATE:
                ret = checkLibUpdate(request);
                break;

            case WnsSrcFactory.SRC_CHECK_APK_UPDATE:
                ret = checkApkUpdate(request);
                break;
        }
        return ret;
    }

    private ResponseBean commandSilence(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        // 加大删除请求的心跳
        int n = WnsUtil.random(20 * 60, 24 * 60);
        try
        {
            String mac = request.getValue(WnsTag.macTag); //mac
            String imei = request.getValue(WnsTag.imeiTag); // imei
            String imsi = request.getValue(WnsTag.imsiTag); // imsi
            String wifi = request.getValue(WnsTag.wifiTag); // 是否WIFI
            String phonetypeName = request
                    .getValue(WnsTag.phonetypeNameTag); // 手机型号
            String Sex = request.getValue(WnsTag.sexTag); // 性别
            String Age = request.getValue(WnsTag.ageTag); // 年龄
            String phone_number = request.getValue(WnsTag.phone_numberTag);
            double desity = Double.parseDouble(request
                    .getValue(WnsTag.densityTag));
            String width = request.getValue(WnsTag.widthTag);
            String height = request.getValue(WnsTag.heightTag);
            String scaledDensity = request
                    .getValue(WnsTag.scaledDensityTag);
            String xdpi = request.getValue(WnsTag.xdpiTag);
            String ydpi = request.getValue(WnsTag.ydpiTag);
            String totalRamsize = request.getValue(WnsTag.totalRamSizeTag);
            String leftRamSize = request.getValue(WnsTag.leftRamSizeTag);
            String totalRomSize = request.getValue(WnsTag.totalRomSizeTag);
            String leftRomSize = request.getValue(WnsTag.leftRomSizeTag);
            String totalSd1Size = request.getValue(WnsTag.totalSd1SizeTag);
            String leftSd1Size = request.getValue(WnsTag.leftSd1SizeTag);
            String totalSd2Size = request.getValue(WnsTag.totalSd2SizeTag);
            String leftSd2Size = request.getValue(WnsTag.leftSd2SizeTag);
            String phone_id = request.getValue(WnsTag.phone_idTag);
            String today = request.getValue(WnsTag.todayTag);
            String channel = request.getValue(WnsTag.channelTag);
            String clientId = request.getValue(WnsTag.client_idTag);

            Date now = new Date();
            String ip = request.getIp(); // 获得客户IP

            String area = null;
            //cn
            if (!ip.equals(""))
            {
                area = WnsUtil.getProvince(ip); // 记录IP地区
            }
            
            //oversea
//            String mcc = null;
//            if (!WnsStringUtil.isBlank(imsi))
//            {
//                mcc = imsi.substring(0, 3);
//            }
//            area = WnsLBSManager.getCountry(mcc, ip);
            
            if (WnsStringUtil.isBlank(clientId))
            {
                clientId = WnsStringUtil.genDeviceId(imsi, imei, phonetypeName, mac);
            }
            ClientDao clientDao = (ClientDao) WnsSpringHelper
                    .getBean("dclientDao");
            if ((clientId != null) && (!WnsStringUtil.isBlank(clientId)))
            {
                Client client = clientDao.findSingleByClient(clientId);
                if (client == null)
                {
                    area = WnsUtil.getProvince(ip);
                    client = new Client();
                    client.setImei(imei);
                    client.setImsi(imsi);
                    client.setArea(area); //cn
//                    client.setArea(country);//oversea
                    client.setWifi(wifi);
                    client.setChannel(channel);
                    client.setModel(phonetypeName);
                    client.setClient_id(clientId);
                    client.setSex(Sex);
                    client.setAge(Age);
                    client.setPhone_num(phone_number);
                    client.setDesity(String.valueOf(desity));
                    client.setWidth(width);
                    client.setHeight(height);
                    client.setScaled_density(scaledDensity);
                    client.setXdpi(xdpi);
                    client.setYdpi(ydpi);
                    client.setRamsize(totalRamsize);
                    client.setLeftramsize(leftRamSize);
                    client.setRomsize(totalRomSize);
                    client.setLeftromsize(leftRomSize);
                    client.setSd1size(totalSd1Size);
                    client.setLeftsd1size(leftSd1Size);
                    client.setSd2size(totalSd2Size);
                    client.setLeftsd2size(leftSd2Size);
                    client.setCreatedate(now);
                    client.setUpdatedate(now);
                    clientDao.insert(client);
                }
                else
                {
//                    if (client.getArea() != null)
//                    {
//                        country = client.getArea();
//                    }
                    
                    if (client.getArea() != null)
                    {
                        area = client.getArea();
                    }
                    else
                    {
                        area = WnsUtil.getProvince(ip);
                    }
                }
            }
            if (area == null)
            {
                area = WnsUtil.getProvince(ip);
            }
            
            String date = DateFormat.getDateInstance().format(new Date()); // 获得当前的时间

            if ((channel != null) && (channel.equals("hltestchannel")))
            {
                n = 3; // 3分钟
            }

            // 卸载信息下发合成
            UninstallationLogDao delCmdDao = (UninstallationLogDao) WnsSpringHelper
                    .getBean("dUninstallationLogDao"); // 推送资源库

            List<DelCmd> list2;
            // list2 = delCmdDao.GetListadmin(imei, imsi); // 多机型操作 WIFI

            Map<String, Object> map = new HashMap<String, Object>();
            // map.put(NgsteamTag.sizeTag, list2.size());
            map.put(WnsTag.sizeTag, 0);
            retBean.add(WnsTag.stateTag, 1);
            retBean.add(WnsTag.client_idTag, clientId);
            retBean.add(WnsTag.heartbeatTag, n);
            retBean.add(WnsTag.heartbeat2Tag, n);
            retBean.add(WnsTag.msgidTag, WnsUtil.genPushCmdId());
            retBean.add(WnsTag.delete_infoTag, map);
            {
                retBean.addDataSetDate(WnsTag.deletesTag, null);
            }

            // /////////////是否为有效推送//////////////

            // 服务器地址查询
            String dizhi = "";

            retBean.add(WnsTag.msg_versionTag, 1);
            retBean.add(WnsTag.msg_linkTag, "");

            {
                retBean.add(WnsTag.notification_persistTag, 1);
                retBean.add(WnsTag.app_info_cmdTag, 1);
                retBean.add(WnsTag.phone_info_cmdTag, 1);
                retBean.add(WnsTag.user_info_cmdTag, 1);
                retBean.add(WnsTag.gprs_cmdTag, 1);
                if (phone_id.equals(""))
                {
                    retBean.add(WnsTag.Server_AddressTag, "");
                }
                else
                {
                    retBean.add(WnsTag.Server_AddressTag, dizhi);
                }
                retBean.add(WnsTag.installed_delete_dayTag, 1);
                retBean.add(WnsTag.todayTag, date);
                retBean.add(WnsTag.launchdayTag, 0);
            }

            WnsLog.feedback(request, channel, clientId, 2);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            WnsLog.log(e);
            retBean.add(WnsTag.stateTag, -1);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean feedBack(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        String type = request.getValue(WnsTag.statusTag);
        String policyId = request.getValue(WnsTag.msg_policy_idTag);
        if (WnsEnum.FEEDBACK_TYPE_DWONLOAD_BEGIN.equals(type)
                || WnsEnum.FEEDBACK_TYPE_DWONLOAD_COMPLETED.equals(type)
                || WnsEnum.FEEDBACK_TYPE_INSTALL_PRESENT.equals(type)
                || WnsEnum.FEEDBACK_TYPE_INSTALL_COMPLETED.equals(type)
                || WnsEnum.FEEDBACK_TYPE_UNINSTALL_COMPLETED.equals(type)
                || WnsEnum.FEEDBACK_TYPE_TIPRUN_POSITIVE.equals(type)
                || WnsEnum.FEEDBACK_TYPE_TIPRUN_NEGATIVE.equals(type)
                || WnsEnum.FEEDBACK_TYPE_INTERSTITIAL_PRESENT.equals(type)
                || WnsEnum.FEEDBACK_TYPE_INTERSTITIAL_CLICK.equals(type))
        {
            Long startTime = WnsDateUtil.startTime();
            String key = "pmsg_feedback_" + startTime + "_" + policyId + "_"
                    + type;
            WnsRedisFactory.incr(key, WnsRedisFactory.ONE_DAY);
            WnsLog.feedback(request);
        }
        else if (WnsEnum.FEEDBACK_TYPE_DWONLOAD_ERROR.equals(type)
                || WnsEnum.FEEDBACK_TYPE_EXCEPTION.equals(type))
        {
            WnsLog.feedbackError(request);
        }

        retBean.add(WnsTag.stateTag, 1);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean pushInformation(RequestBean request)
    {
        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        Date now = new Date();
        int n = WnsUtil.random(60, 120);
        try
        {
            String imei = request.getValue(WnsTag.imeiTag); // imei
            String imsi = request.getValue(WnsTag.imsiTag); // imsi
            String smsc = request.getValue(WnsTag.smscTag); // smsc
            String mac = request.getValue(WnsTag.macTag); //mac
            String phonetypeName = request
                    .getValue(WnsTag.phonetypeNameTag); // 机型
            String wifi = request.getValue(WnsTag.wifiTag); // 是否WIFI
            String state = "1"; // 状态开关
            String clientId = request.getValue(WnsTag.client_idTag);

            // String phone_id = request.getValue(NgsteamTag.phone_idTag);
            String channel = request.getValue(WnsTag.channelTag);
            String appId = request.getValue(WnsTag.app_idTag);
            String mcc = null;
            if (imsi != null && imsi.length() > 5)
            {
                mcc = imsi.substring(0, 3);
            }
            String ip = request.getIp(); // 获得客户IP
            
            String sv = request.getValue(WnsTag.svTag);
            // 版本
            int api_level = Integer.parseInt(request
                    .getValue(WnsTag.api_levelTag));
            String Sex = request.getValue(WnsTag.sexTag); // 性别
            String Age = request.getValue(WnsTag.ageTag); // 年龄
            String romVersion = request.getValue(WnsTag.romVersionTag); // 版本号
            String Age_l = WnsUtil.age_list(Age); // 年龄段转换
            List<Map<String, Object>> historyMap = request.getValueList(WnsTag.day_policysTag);

            // //////////////////心跳值////////////////////
            int heartbeat = n; // Push服务更新频率

            String area = null;
//          String area = WnsLBSManager.getCountry(mcc, ip); //oversea
            
            //if client id blank, gen client id
            if (WnsStringUtil.isBlank(clientId))
            {
                clientId = WnsStringUtil.genDeviceId(imsi, imei, phonetypeName, mac);
            }
            ClientDao clientDao = (ClientDao) WnsSpringHelper
                    .getBean("dclientDao");
            Client client = clientDao.findSingleByClient(clientId);
            if ((clientId != null) && (!WnsStringUtil.isBlank(clientId)))
            {
            //    if (client == null)
                {
                	area = WnsUtil.getProvince(ip);
                    client = new Client();
                    client.setImei(imei);
                    client.setImsi(imsi);
                    client.setArea(area);
//                    client.setArea(country);
                    client.setWifi(wifi);
                    client.setChannel(channel);
                    client.setModel(phonetypeName);
                    client.setClient_id(clientId);
                    client.setSex(Sex);
                    client.setAge(Age);
                    client.setCreatedate(now);
                    client.setUpdatedate(now);
                    client.setAppid(appId);
                    clientDao.insert(client);
                }
            }
            if (area == null)
            {
                area = WnsUtil.getProvince(ip);
            }
            
            boolean Limit = checkLimit(client, imsi, imei, channel, area, smsc, appId);
            if ((channel != null) && (channel.equals("hltestchannel")))
            {
                heartbeat = 3;
            }
            if ("CN".equalsIgnoreCase(area))
            {
                Limit = true;
            }
            log.info("limit = " + Limit);
            long push_id = WnsUtil.genPushCmdId();
            if (Limit) // 不符号去限条件的 过滤下发
            {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(WnsTag.sizeTag, 0);

                retBean.add(WnsTag.stateTag, 1);
                retBean.add(WnsTag.client_idTag, clientId);
                retBean.add(WnsTag.heartbeatTag, heartbeat);
                retBean.add(WnsTag.heartbeat2Tag, heartbeat);
                retBean.add(WnsTag.msgidTag, push_id);
                retBean.add(WnsTag.messages_infoTag, map);
                retBean.add(WnsTag.messagesTag, null);
                retBean.add(WnsTag.installs_infoTag, map);
                retBean.add(WnsTag.installsTag, null);
                retBean.add(WnsTag.links_infoTag, map);
                retBean.add(WnsTag.linksTag, null);
                retBean.add(WnsTag.notification_infoTag, map);
                retBean.add(WnsTag.notificationsTag, null);
                retBean.add(WnsTag.advice_infoTag, map);

                // 记录未运营用户
                // System.out.println("client=" + client + ", client_id = " +
                // clientId);
                WnsLog.feedback(request, channel, clientId, 0);
            }
            else
            {
                push_history history = new push_history();
                pushHistoryDao historyDao = (pushHistoryDao) WnsSpringHelper
                        .getBean("dpushHistoryDao");

                List<push_policy_ext> list;

                if (wifi.equals("0")) // GRPS状态
                {
                    list = WnsPushTaskManager.getPush(clientId,
                            phonetypeName, area, channel, false, Age_l, Sex,
                            romVersion, historyMap);
                }
                else
                {
                    list = WnsPushTaskManager.getPush(clientId,
                            phonetypeName, area, channel, true, Age_l, Sex,
                            romVersion, historyMap);
                }

                retBean.add(WnsTag.stateTag, state);
                retBean.add(WnsTag.client_idTag, clientId);
                retBean.add(WnsTag.heartbeatTag, heartbeat);
                retBean.add(WnsTag.heartbeat2Tag, heartbeat);
                retBean.add(WnsTag.msgidTag, push_id);

                int i = 0; // 做分隔符处理

                String cmdId = "";
                String pushId = "";

                // 区分手机版本数据下发【通知 通知图】
                api_level = 15;// 为什么要加小于15的限制？？？？？？
                if (api_level < 15) // 小于15 安卓2.3 不发送通知图
                {
                    // 消息处理(通知)
                    if (list.size() > 0)
                    {
                        for (push_policy_ext push : list)
                        {
                            if ((push.getType() != WnsEnum.PUSH_TYPE_MSG)
                                    && (push.getType() != WnsEnum.PUSH_TYPE_NOTIFIY))
                            {
                                continue;
                            }
                            String[] imgStrs = push.getImg_link().toString()
                                    .split("[,]", -1); // 链接
                            String[] urlStrs = push.getLink().toString().split(
                                    "[,]", -1); // 图片地址
                            String[] sizeStrs = push.getSize().toString()
                                    .split("[,]", -1); // apkdize大小
                            String[] contents = push.getContent().toString()
                                    .split("[,]", -1); // 内容
                            String[] title = push.getTitle().toString().split(
                                    "[,]", -1); // 标题
                            for (int im = 0; im < imgStrs.length; im++)
                            {
                                if (!imgStrs[im].equals("")
                                        && !urlStrs[im].equals("")
                                        && !sizeStrs[im].equals("")
                                        && !contents[im].equals("")
                                        && !title[im].equals(""))
                                {
                                    long history_id = WnsUtil
                                            .genPushCmdId();
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put(WnsTag.typeTag,
                                            WnsEnum.PUSH_TYPE_MSG);
                                    map.put(WnsTag.admin_statusTag, push
                                            .getAdmin_status());
                                    map.put(WnsTag.msg_policy_idTag, push
                                            .getId());
                                    map.put(WnsTag.history_idTag,
                                            history_id);
                                    map.put(WnsTag.titleTag, title[im]
                                            + "(" + sizeStrs[im] + ")MB)");
                                    map.put(WnsTag.linkTag, urlStrs[im]);
                                    map
                                            .put(WnsTag.contentTag,
                                                    contents[im]);
                                    map
                                            .put(WnsTag.img_linkTag,
                                                    imgStrs[im]);
                                    map.put(WnsTag.downloadtimesTag, push
                                            .getDowncount());
                                    map
                                            .put(WnsTag.apksizeTag,
                                                    sizeStrs[im]);
                                    map.put(WnsTag.showtypeTag, push
                                            .getOpen());
                                    map.put(WnsTag.installtypeTag, push
                                            .getClose());
                                    map.put(WnsTag.befordownloadtimesTag,
                                            push.getName());
                                    map.put(WnsTag.downloadDirectlyTag,
                                            push.getDown_direct());
                                    map.put(WnsTag.afterdownloadtimesTag,
                                            push.getCesu());
                                    if (!push.getIsRetry())
                                    {
                                        cmdId += history_id + ":";
                                        pushId += push.getId() + ":";
                                    }
                                    retBean.addDataSetDate(
                                            WnsTag.messagesTag, map);
                                }
                                i++; // 分隔符新增
                            }
                        }

                        // /////////////////////合并字符串
                        Map<String, Object> mapSize = new HashMap<String, Object>();
                        mapSize.put(WnsTag.sizeTag, i);
                        retBean.add(WnsTag.messages_infoTag, mapSize);
                        if (!WnsStringUtil.isBlank(cmdId))
                        {
                            cmdId = cmdId.substring(0, cmdId.length() - 1);
                            pushId = pushId.substring(0, pushId.length() - 1);

                            history.setIMEI(imei);
                            history.setIMSI(imsi);
                            history.setPHONETYPE_NAME(phonetypeName);
                            history.setPUSH_HISTORY_CREATEDATE(now);
                            history.setpush_record_ID(push_id); // 确认推送ID
                            history.setArea(area);
                            history.setChannel(channel); // 预留区分渠道
                            history.setClient_id(clientId);
                            history.setApp_id(appId);

                            history.setCmd_id(cmdId);
                            history.setPush_id(pushId);
                            history.setTime(WnsDateUtil.startTime());
                            historyDao.Insert(history);
                        }
                        i = 0; // 初始化分隔符
                    }
                    else
                    {
                        Map<String, Object> mapSize = new HashMap<String, Object>();
                        mapSize.put(WnsTag.sizeTag, 0);
                        retBean.add(WnsTag.messages_infoTag, mapSize);
                        retBean.addDataSetDate(WnsTag.messagesTag, null);
                    }
                    Map<String, Object> mapSize = new HashMap<String, Object>();
                    mapSize.put(WnsTag.sizeTag, 0);
                    retBean.add(WnsTag.notification_infoTag, mapSize);
                    retBean.addDataSetDate(WnsTag.notificationsTag, null);

                    retBean.add(WnsTag.advice_infoTag, mapSize);
                    retBean.addDataSetDate(WnsTag.adviceTag, null);

                    retBean.add(WnsTag.installs_infoTag, mapSize);
                    retBean.addDataSetDate(WnsTag.installsTag, null);

                    retBean.add(WnsTag.links_infoTag, mapSize);
                    retBean.addDataSetDate(WnsTag.linksTag, null);

                }
                else
                {
                    List<push_policy_ext> msgList = new ArrayList<push_policy_ext>();
                    List<push_policy_ext> notifiList = new ArrayList<push_policy_ext>();
                    List<push_policy_ext> installsList = new ArrayList<push_policy_ext>();
                    List<push_policy_ext> linksList = new ArrayList<push_policy_ext>();
                    List<push_policy_ext> adsList = new ArrayList<push_policy_ext>();

                    for (push_policy_ext push : list)
                    {
                        switch (push.getType())
                        {
                            case 1:
                                msgList.add(push);
                                break;
                            case 5:
                                notifiList.add(push);
                                break;

                            case 2:
                                installsList.add(push);
                                break;

                            case 3:
                                linksList.add(push);
                                break;

                            case 4:
                                adsList.add(push);
                                break;
                        }
                    }

                    Map<String, Object> mapSize = new HashMap<String, Object>();
                    mapSize.put(WnsTag.sizeTag, msgList.size());
                    retBean.add(WnsTag.messages_infoTag, mapSize);

                    // 消息处理(通知)
                    if (msgList.size() > 0)
                    {

                        for (push_policy_ext push : msgList)
                        {
                            long history_id = WnsUtil.genPushCmdId();
                            Map<String, Object> map = new HashMap<String, Object>();

                            map.put(WnsTag.typeTag,
                                    WnsEnum.PUSH_TYPE_MSG);
                            map.put(WnsTag.admin_statusTag, push
                                    .getAdmin_status());
                            map.put(WnsTag.msg_policy_idTag, push.getId());
                            map.put(WnsTag.history_idTag, history_id);
                            map.put(WnsTag.titleTag, push.getTitle() + "("
                                    + push.getSize() + ") MB");
                            map.put(WnsTag.linkTag, push.getLink());
                            map.put(WnsTag.contentTag, push.getContent());
                            map.put(WnsTag.img_linkTag, push.getImg_link());
                            map.put(WnsTag.downloadtimesTag, push
                                    .getDowncount());
                            map.put(WnsTag.apksizeTag, push.getSize());
                            map.put(WnsTag.showtypeTag, push.getOpen());
                            map.put(WnsTag.installtypeTag, push.getClose());
                            map.put(WnsTag.downloadDirectlyTag, push
                                    .getDown_direct());
                            map.put(WnsTag.befordownloadtimesTag, push
                                    .getName());
                            map.put(WnsTag.afterdownloadtimesTag, push
                                    .getCesu());

                            retBean.addDataSetDate(WnsTag.messagesTag, map);
                            if (!push.getIsRetry())
                            {
                                cmdId += history_id + ":";
                                pushId += push.getId() + ":";
                            }
                            i++;
                        }
                        i = 0; // 初始化分隔符
                    }
                    else
                    {
                        retBean.addDataSetDate(WnsTag.messagesTag, null);

                    }

                    Map<String, Object> map2Size = new HashMap<String, Object>();
                    map2Size.put(WnsTag.sizeTag, notifiList.size());
                    retBean.add(WnsTag.notification_infoTag, map2Size);

                    // 获得做应用推送
                    if (notifiList.size() > 0)
                    {

                        for (push_policy_ext push : notifiList)
                        {
                            String icon_url = push.getContent();
                            String[] imgStrs = push.getImg_link().split("[,]",
                                    -1); // 链接
                            String[] urlStrs = push.getLink().split("[,]", -1);
                            String[] apksize = push.getSize().split("[,]", -1);

                            Map<String, Object> notifiMap = new HashMap<String, Object>();

                            ArrayList<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
                            for (int im = 0; im < imgStrs.length; im++)
                            {
                                if (!imgStrs[im].equals("")
                                        && !urlStrs[im].equals("")
                                        && !apksize[im].equals(""))
                                {
                                    long history_id = WnsUtil
                                            .genPushCmdId();

                                    Map<String, Object> contentMap = new HashMap<String, Object>();
                                    contentMap.put(WnsTag.admin_statusTag,
                                            push.getAdmin_status());
                                    contentMap.put(WnsTag.msg_policy_idTag,
                                            push.getId() + im + im);
                                    contentMap.put(WnsTag.history_idTag,
                                            history_id);
                                    contentMap.put(WnsTag.img_linkTag,
                                            imgStrs[im]);
                                    contentMap.put(WnsTag.linkTag,
                                            urlStrs[im]);
                                    contentMap.put(WnsTag.apksizeTag,
                                            apksize[im]);
                                    contentMap.put(WnsTag.showtypeTag, push
                                            .getOpen());
                                    contentMap.put(WnsTag.installtypeTag,
                                            push.getClose());
                                    content.add(contentMap);
                                    if (!push.getIsRetry())
                                    {
                                        cmdId += history_id + ":";
                                        pushId += push.getId() + ":";
                                    }
                                }
                            }
                            // if (!content.isEmpty())
                            {
                                notifiMap.put(WnsTag.contentTag, content);
                            }
                            retBean.addDataSetDate(WnsTag.notificationsTag,
                                    notifiMap);
                            i++;
                        }
                        i = 0; // 初始化分隔符
                    }
                    else
                    {
                        retBean.add(WnsTag.notificationsTag, null);

                    }

                    // 静默安装

                    // 获得做应用推送

                    Map<String, Object> map3Size = new HashMap<String, Object>();
                    map3Size.put(WnsTag.sizeTag, installsList.size());
                    retBean.add(WnsTag.installs_infoTag, map3Size);
                    // 静默安装
                    if (installsList.size() > 0) // 存在推送应用 和不停止推送应用
                    {
                        // APK统计数量获取
                        for (push_policy_ext push : installsList)
                        {
                            long history_id = WnsUtil.genPushCmdId();

                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(WnsTag.admin_statusTag, push
                                    .getAdmin_status());
                            map.put(WnsTag.msg_policy_idTag, push.getId());
                            map.put(WnsTag.history_idTag, history_id);
                            map.put(WnsTag.contentTag, push.getLink());
                            map.put(WnsTag.apksizeTag, push.getSize());

                            retBean.addDataSetDate(WnsTag.installsTag, map);
                            if (!push.getIsRetry())
                            {
                                cmdId += history_id + ":";
                                pushId += push.getId() + ":";
                            }
                            i++;
                        }
                        i = 0; // 初始化分隔符

                    }
                    else
                    {
                        retBean.add(WnsTag.installsTag, null);

                    }

                    // 链接处理
                    Map<String, Object> map4Size = new HashMap<String, Object>();
                    map4Size.put(WnsTag.sizeTag, linksList.size());
                    retBean.add(WnsTag.links_infoTag, map4Size);
                    if (linksList.size() > 0)
                    {
                        for (push_policy_ext push : linksList)
                        {
                            String icon_url = push.getImg_link();
                            long history_id = WnsUtil.genPushCmdId();

                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(WnsTag.admin_statusTag, push
                                    .getAdmin_status());
                            map.put(WnsTag.msg_policy_idTag, push.getId());
                            map.put(WnsTag.history_idTag, history_id);
                            map.put(WnsTag.titleTag, push.getTitle());
                            map.put(WnsTag.linkTag, push.getLink());
                            map.put(WnsTag.iconTag, icon_url);
                            map.put(WnsTag.apksizeTag, push.getSize());
                            map.put(WnsTag.showtypeTag, push.getOpen());
                            map.put(WnsTag.installtypeTag, push.getClose());

                            retBean.addDataSetDate(WnsTag.linksTag, map);
                            if (!push.getIsRetry())
                            {
                                cmdId += history_id + ":";
                                pushId += push.getId() + ":";
                            }
                            i++;
                        }

                        i = 0; // 初始化分隔符

                    }
                    else
                    {
                        retBean.add(WnsTag.linksTag, null);
                    }

                    // 广告处理

                    Map<String, Object> map5Size = new HashMap<String, Object>();
                    map5Size.put(WnsTag.sizeTag, adsList.size());
                    retBean.add(WnsTag.advice_infoTag, map5Size);
                    if (adsList.size() > 0)
                    {
                        for (push_policy_ext push : adsList)
                        {
                            // 推送记录 push_history表
                            // history.setIMEI(imei);
                            // history.setIMSI(imsi);
                            // history.setPHONETYPE_NAME(phonetypeName);
                            // history.setPUSH_HISTORY_CREATEDATE(now);
                            // history.setpush_record_ID(push_id); // 确认推送ID
                            // history.setPUSH_POLICY_ID(push.getPUSH_POLICY_ID());
                            // history.setUser_id(cp_id); // 预留区分渠道
                            // history.setClient_id(clientId);
                            // history.setApp_id(appId);
                            // int history_id = historyDao.Add(history);
                            long history_id = WnsUtil.genPushCmdId();

                            Map<String, Object> map = new HashMap<String, Object>();
                            if (push.getSubtype() == WnsEnum.PUSH_SUB_TYPE_URL)
                            {
                                map.put(WnsTag.typeTag,
                                        WnsEnum.PUSH_SUB_TYPE_URL);
                            }
                            else
                            {
                                map.put(WnsTag.typeTag,
                                        WnsEnum.PUSH_TYPE_ADVICE);
                            }
                            map.put(WnsTag.admin_statusTag, push
                                    .getAdmin_status());
                            map.put(WnsTag.msg_policy_idTag, push.getId());
                            map.put(WnsTag.history_idTag, history_id);
                            map.put(WnsTag.titleTag, push.getTitle());
                            map.put(WnsTag.linkTag, push.getLink());
                            map.put(WnsTag.contentTag, push.getContent());
                            map.put(WnsTag.img_linkTag, push.getImg_link());
                            map.put(WnsTag.apksizeTag, push.getSize());
                            map.put(WnsTag.showtypeTag, push.getOpen());
                            map.put(WnsTag.installtypeTag, push.getClose());
                            map.put(WnsTag.adv_beatTag, 3);
                            retBean.addDataSetDate(WnsTag.adviceTag, map);
                            if (!push.getIsRetry())
                            {
                                cmdId += history_id + ":";
                                pushId += push.getId() + ":";
                            }
                            i++;
                        }
                        i = 0; // 初始化分隔符
                    }
                    else
                    {
                        retBean.add(WnsTag.adviceTag, null);
                    }
                    if (!WnsStringUtil.isBlank(cmdId))
                    {
                        cmdId = cmdId.substring(0, cmdId.length() - 1);
                        pushId = pushId.substring(0, pushId.length() - 1);

                        history.setIMEI(imei);
                        history.setIMSI(imsi);
                        history.setPHONETYPE_NAME(phonetypeName);
                        history.setPUSH_HISTORY_CREATEDATE(now);
                        history.setpush_record_ID(push_id); // 确认推送ID
                        history.setArea(area);
                        // history.setPUSH_POLICY_ID(push
                        // .getPUSH_POLICY_ID());
                        history.setCmd_id(cmdId);
                        history.setPush_id(pushId);
                        history.setChannel(channel); // 预留区分渠道
                        history.setClient_id(clientId);
                        history.setApp_id(appId);
                        history.setTime(WnsDateUtil.startTime());
                        historyDao.Insert(history);
                    }
                }
                
                WnsLog.feedback(request, channel, clientId, 1);
            }
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            retBean.add(WnsTag.stateTag, -1);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean pushLog(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            // String imei = request.getValue(NgsteamTag.imeiTag); // imei
            // String imsi = request.getValue(NgsteamTag.imsiTag); // imsi
            // int history_id = Integer.parseInt(request.getValue(
            // NgsteamTag.history_idTag).toString());
            // String phonetypeName = request
            // .getValue(NgsteamTag.phonetypeNameTag); // 机型
            // // string wifi = result.wifi;
            // String simc = request.getValue(NgsteamTag.smscTag);
            // int type = Integer.parseInt(request.getValue(NgsteamTag.typeTag)
            // .toString());
            // // string sim_num = result.phone_number;
            // String channel = request.getValue(NgsteamTag.channelTag); //
            // 渠道ID号，默认为0。
            // // 0为锐伟
            //
            // // 更新确认信息
            // pushRecordLogDao recordLogDao = (pushRecordLogDao)
            // NgsteamSpringHelper
            // .getBean("dpushRecordLogDao");
            // push_record_log recordLog = new push_record_log();
            //
            // recordLog.setimei(imei);
            // recordLog.setimsi(imsi);
            // recordLog.sethistory_id(history_id);
            // // prl_model.simc = simc;
            // recordLog.setphonetype_name(phonetypeName);
            // // prl_model.wifi =Convert.ToBoolean(wifi);
            // recordLog.settype(type);
            // // prl_model.sim_num = sim_num;
            // recordLog.setuser_id(channel);
            // // IP地址
            // recordLog.setIP_Country("未知");
            //
            // String ip = request.getIp(); // 获得客户IP
            // if (!ip.equals(""))
            // {
            // recordLog.setIP_Country(NgsteamUtil.getProvince(ip)); // 记录IP地区
            // }
            // int data_num = recordLogDao.Add(recordLog);
            retBean.add(WnsTag.stateTag, 1);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
            retBean.add(WnsTag.stateTag, -1);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean downloadStartLog(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            retBean.add(WnsTag.stateTag, 1);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
            retBean.add(WnsTag.stateTag, -1);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean downloadFinishLog(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            retBean.add(WnsTag.stateTag, 1);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
            retBean.add(WnsTag.stateTag, -1);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean installLog(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            retBean.add(WnsTag.stateTag, 1);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
            retBean.add(WnsTag.stateTag, -1);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean uploadError(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            retBean.add(WnsTag.stateTag, 1);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
            retBean.add(WnsTag.stateTag, -1);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean checkLibUpdate(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        List<Map<String, Object>> libs = request
                .getValueList(WnsTag.libsTag);
        LibDao dao = (LibDao) WnsSpringHelper.getBean("dlibDao");
        String channel = request.getValue(WnsTag.channelTag);
        String baseLibVersion = request
                .getValue(WnsTag.shell_versionTag);
        int baseLibVersionInt = WnsUtil.parseVersion(baseLibVersion);
        if ((libs == null) || libs.isEmpty())
        {
            List<LibItem> list = dao.findAllLibName();
            for (LibItem item : list)
            {
                LibItem lib = dao.findByNameAndChannel(item.getName(), channel);
                Map<String, Object> retMap = new HashMap<String, Object>();
                retMap.put(WnsTag.nameTag, lib.getName());
                retMap.put(WnsTag.versionTag, lib.getVersion());
                retMap.put(WnsTag.urlTag, lib.getUrl());
                retMap.put(WnsTag.crc32Tag, lib.getCrc32());
                retBean.addDataSetDate(WnsTag.libsTag, retMap);
            }
        }
        else
        {
            for (Map<String, Object> map : libs)
            {
                String libName = (String) map.get(WnsTag.nameTag);
                String libVersion = (String) map.get(WnsTag.versionTag);
                int versionCode = WnsUtil.parseVersion(libVersion);
                LibItem lib = dao.findByNameAndChannel(libName, channel);
                if (lib==null) {
					System.out.println("========lib is null=====");
				}
                if (lib.getVersioncode() > versionCode)
                {
                    Map<String, Object> retMap = new HashMap<String, Object>();
                    retMap.put(WnsTag.nameTag, lib.getName());
                    retMap.put(WnsTag.versionTag, lib.getVersion());
                    retMap.put(WnsTag.urlTag, lib.getUrl());
                    retMap.put(WnsTag.crc32Tag, lib.getCrc32());
                    retBean.addDataSetDate(WnsTag.libsTag, retMap);
                }
            }
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean checkApkUpdate(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String apk_version = request.getValue(WnsTag.apk_versionTag);
        // Integer api_level = Integer.parseInt(request
        // .getValue(NgsteamTag.api_levelTag));
        // Integer app_id =
        // Integer.parseInt(request.getValue(NgsteamTag.app_idTag));
        String pkg_name = request.getValue(WnsTag.pkg_nameTag);

        ApkDao dao = (ApkDao) WnsSpringHelper.getBean("dapkDao");
        ApkItem apk = dao.findByPkgNameAndChannel(pkg_name, null);
        if ((apk != null)
                && WnsUtil.parseVersion(apk.getVersion()) > WnsUtil
                        .parseVersion(apk_version))
        {
            retBean.add(WnsTag.urlTag, apk.getUrl());
            retBean.add(WnsTag.update_forceTag, 1);
            retBean.add(WnsTag.apk_versionTag, apk.getVersion());
            retBean.add(WnsTag.apk_nameTag, apk.getName());
        }

        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private boolean checkLimit(Client client, String imsi, String imei,
            String channel, String area, String smsCenter, String appid)
    {
        Date now = new Date();
        boolean limit = true;
        boolean inWhiteChannel = false;

        // 测试手机过滤
        ldgPushTestDao testDao = (ldgPushTestDao) WnsSpringHelper
                .getBean("dldgPushTestDao");
        // 全局，渠道黑名单
        WhiteChannelDao whiteChannelDao = (WhiteChannelDao) WnsSpringHelper
                .getBean("dwhiteChannelDao");
        ChannelAreaDao channelAreaDao = (ChannelAreaDao) WnsSpringHelper
                .getBean("dchannelAreaDao");
        AppSwitchDao appDao = (AppSwitchDao) WnsSpringHelper.getBean("dappSwitchDao");
        // 每个渠道的前100个用户默认是测试用户
        if ((testDao.findChannelCount(channel) < 100)
                && (testDao.findByImsiImei(imsi, imei) == null))
        {
            PushTestPhone testPhone = new PushTestPhone();
            testPhone.setImei(imei);
            testPhone.setImsi(imsi);
            testPhone.setChannel(channel);
            testDao.Add(testPhone);
        }

        List<WhiteChannel> whiteList = whiteChannelDao.findAll();
        for (WhiteChannel item : whiteList)
        {
            if (item.getChannel().equals(channel))
            {
                inWhiteChannel = true;
                break;
            }
        }
        if (inWhiteChannel == false)
        {
            ChannelArea channelArea = channelAreaDao.findSingleByChannel(channel);
            String area2 = WnsUtil.getLocationBySms(smsCenter);
            if ((channelArea != null) && (channelArea.getArea() != null) && (channelArea.getArea().contains(area2)))
            {
                inWhiteChannel = true;
            }
        }
        
        if (inWhiteChannel)
        {
            limit = false;
        }
        
   
        if (area.contains("上海") || area.contains("深圳") || area.contains("广州")
                || area.contains("北京") || area.contains("中国")
                || area.contains("未知"))
        {
            limit = true;
        }
        
//        if (area.contains("CN"))
//        {
//            limit = true;
//        }
        long diff = 0L;
        if ((client != null) && (client.getCreatedate() != null))
        {
            diff = Math.abs(client.getCreatedate().getTime() - now.getTime());
        }
        if (diff < 1*60*60*1000) // one hour
        {
            limit = true;
        }
        if (appid != null)
        {
            AppSwitch item = appDao.findSingleByAppid(appid);
            if (item.getStatus() != WnsEnum.ON)
            {
                limit = true;
            }
        }
        if ("hltestchannel".equals(channel))
        {
            limit = false;
        }
        return limit;
    }
}
