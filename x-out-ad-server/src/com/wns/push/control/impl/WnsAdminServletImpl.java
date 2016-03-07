package com.wns.push.control.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wns.push.admin.bean.AdminMenu;
import com.wns.push.admin.bean.WnsApp;
import com.wns.push.bean.AdminUser;
import com.wns.push.bean.ApkItem;
import com.wns.push.bean.AppSwitch;
import com.wns.push.bean.Area;
import com.wns.push.bean.ChannelArea;
import com.wns.push.bean.LibItem;
import com.wns.push.bean.WnsChannelOrg;
import com.wns.push.bean.StatisClient;
import com.wns.push.bean.StatisClientQuery;
import com.wns.push.bean.StatisMsg;
import com.wns.push.bean.StatisMsgQuery;
import com.wns.push.bean.WhiteChannel;
import com.wns.push.bean.push_policy;
import com.wns.push.control.WnsAdminServlet;
import com.wns.push.dao.AdminMenuDao;
import com.wns.push.dao.AdminUserDao;
import com.wns.push.dao.ApkDao;
import com.wns.push.dao.AppSwitchDao;
import com.wns.push.dao.AreaDao;
import com.wns.push.dao.ChannelAreaDao;
import com.wns.push.dao.ClientDao;
import com.wns.push.dao.LibDao;
import com.wns.push.dao.WnsChannelOrgDao;
import com.wns.push.dao.StatisClientDao;
import com.wns.push.dao.StatisMsgDao;
import com.wns.push.dao.WhiteChannelDao;
import com.wns.push.dao.pushHistoryDao;
import com.wns.push.dao.pushPolicyDao;
import com.wns.push.util.WnsDateUtil;
import com.wns.push.util.WnsHttp;
import com.wns.push.util.WnsPushTaskManager;
import com.wns.push.util.WnsStringUtil;
import com.wns2.base.bean.WnsSysProperty;
import com.wns2.base.bean.RequestBean;
import com.wns2.base.bean.ResponseBean;
import com.wns2.factory.WnsEnum;
import com.wns2.factory.WnsRedisFactory;
import com.wns2.factory.WnsResultCodeFactory;
import com.wns2.factory.WnsSrcFactory;
import com.wns2.factory.WnsTag;
import com.wns2.tran.WnsPager;
import com.wns2.util.WnsMd5;
import com.wns2.util.WnsSpringHelper;
import com.wns2.util.WnsUtil;

public class WnsAdminServletImpl extends WnsAdminServlet
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
        if ((src != WnsSrcFactory.SRC_ADMIN_LOGIN)
                && (src != WnsSrcFactory.SRC_ADMIN_STATIS_CLIENT)
                && (src != WnsSrcFactory.SRC_ADMIN_STATIS_MSG)
                && (src != WnsSrcFactory.SRC_ADMIN_CLEAN_TASK))
        {
            String uid = request.getValue(WnsTag.session_uidTag);

            if (uid == null)
            {
                ResponseBean retBean = new ResponseBean(
                        WnsResultCodeFactory.SUCCESS);
                retBean.add(WnsTag.resultTag,
                        WnsResultCodeFactory.REQUEST_ADMIN_NOT_LOGIN);
                return retBean;
            }
        }
        switch (src)
        {

            case WnsSrcFactory.SRC_ADMIN_LOGIN:
                ret = login(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_GET_USERNAME:
                ret = getUserName(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_MENU:
                ret = loadMenu(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_ADD_INSTALL_PUSH:
                ret = addInstallPush(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_ADD_MSG_PUSH:
                ret = addMsgPush(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_ADD_LINK_PUSH:
                ret = addLinkPush(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_ADD_ADVICE_PUSH:
                ret = addAdvicePush(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_ADD_NOTIFY_PUSH:
                ret = addNotifyPush(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_DELETE_RECORD:
                ret = deleteRecord(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_DELETE_PUSH:
                ret = deletePush(request);
                break;

            // case NgsteamSrcFactory.SRC_ADMIN_EDIT_PUSH:
            // ret = editPush(request);
            // break;

            case WnsSrcFactory.SRC_ADMIN_GET_PUSH:
                ret = getPush(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_PUSH:
                ret = loadPush(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_APK:
                ret = loadApk(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_LIB:
                ret = loadLib(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_CHANNEL:
                ret = loadChannel(request);
                break;

             case WnsSrcFactory.SRC_ADMIN_LOAD_APPLIST:
//               ret = loadAppList(request);
               break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_CLIENTSTATIS:
                ret = loadClientStatis(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_MSGSTATIS:
                ret = loadMsgStatis(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_MODEL:
                ret = loadModel(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_PUSHSTATIS:
                ret = loadPushStatis(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_MSGCOUNT:
                ret = loadMsgCount(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOGOUT:
                ret = logout(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_STATIS_CLIENT:
                ret = statisClient(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_STATIS_MSG:
                ret = statisMsg(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_CLEAN_TASK:
                ret = cleanTask(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_AREA:
                ret = loadArea(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_CHANNELAREA:
                ret = loadChannelArea(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_INSERT_CHANNELAREA:
                ret = insertChannelArea(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_EDIT_CHANNELAREA:
                ret = editChannelArea(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_WHITE_CHANNEL:
                ret = loadWhiteChannel(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_INSERT_WHITE_CHANNEL:
                ret = insertWhiteChannel(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_EDIT_WHITE_CHANNEL:
                ret = editWhiteChannel(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_APP:
                ret = loadApp(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_LOAD_APP_SWITCH:
                ret = loadAppSwitch(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_INSERT_APP_SWITCH:
                ret = insertAppSwitch(request);
                break;

            case WnsSrcFactory.SRC_ADMIN_EDIT_APP_SWITCH:
                ret = editAppSwitch(request);
                break;
        }
        return ret;
    }

    private ResponseBean login(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            String userName = request.getValue(WnsTag.usernameTag);
            String password = request.getValue(WnsTag.passwdTag);
            System.out.println("username====="+userName);
            System.out.println("password====="+password);
            AdminUserDao dao = (AdminUserDao) WnsSpringHelper
                    .getBean("dadminUserDao");
            AdminUser user2 = dao.findByName(userName);
            if ((user2 != null)
                    && user2.getPassword().equals(
                            WnsMd5.ngsteamMd5(password)))
            {
                retBean.add(WnsTag.resultTag,
                        WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
                retBean.add(WnsTag.uidTag, user2.getId());
            }
            else
            {
                retBean.add(WnsTag.resultTag,
                        WnsResultCodeFactory.REQUEST_ADMIN_ERROR);
            }
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean logout(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean getUserName(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            String uid = request.getValue(WnsTag.session_uidTag);
            if (uid != null)
            {
                Long id = Long.parseLong(uid);

                AdminUserDao dao = (AdminUserDao) WnsSpringHelper
                        .getBean("dadminUserDao");
                AdminUser user = dao.findSingle(id);
                retBean.add(WnsTag.usernameTag, user.getName());
                retBean.add(WnsTag.lastloginTag, WnsUtil
                        .getDateFormatString(user.getUpdatedate(), 3));

                retBean.add(WnsTag.resultTag,
                        WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
            }
            else
            {
                retBean.add(WnsTag.resultTag,
                        WnsResultCodeFactory.REQUEST_ADMIN_NOT_LOGIN);
            }
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_NOT_LOGIN);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadMenu(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            long uid = Long.parseLong(request
                    .getValue(WnsTag.session_uidTag));
            Long role;
            AdminUserDao userDao = (AdminUserDao) WnsSpringHelper
                    .getBean("dadminUserDao");
            AdminUser user = userDao.findSingle(uid);
            role = user.getRole();
            AdminMenuDao dao = (AdminMenuDao) WnsSpringHelper
                    .getBean("dadminMenuDao");
            List<AdminMenu> parentList = dao.findByPidAndRole(0, role
                    .intValue());
            for (AdminMenu menu : parentList)
            {
                List<AdminMenu> childrenList = dao.findByPidAndRole(menu
                        .getId(), role.intValue());
                menu.setChildren(childrenList);
                retBean.addDataSetDate("menus", WnsUtil.modelToMap(menu));
            }

            retBean.add("result",
                    WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean addInstallPush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        try
        {
            String title = request.getValue(WnsTag.titleTag);
            String apkurl = request.getValue(WnsTag.apkurlTag);
            String weight = request.getValue(WnsTag.weightTag);
            String count = request.getValue(WnsTag.countTag);
            String allpush = request.getValue(WnsTag.allpushTag);
            String network = request.getValue(WnsTag.networkTag);
            String startDate = request.getValue(WnsTag.startDateTag);
            String startHour = request.getValue(WnsTag.startHourTag);
            String endDate = request.getValue(WnsTag.endDateTag);
            String endHour = request.getValue(WnsTag.endHourTag);
            String channel = request.getValue(WnsTag.channelTag);
            String area = request.getValue(WnsTag.areaTag);
            String showconfrim = request.getValue(WnsTag.showconfrimTag);
            String sex = request.getValue(WnsTag.sexTag);

            pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                    .getBean("dpushPolicyDao");
            push_policy model;
            String id = request.getValue(WnsTag.idTag);
            if (id != null && !WnsStringUtil.isBlank(id))
            {
                model = dao.findSingle(Integer.parseInt(id));
            }
            else
            {
                model = new push_policy();
            }
            model.setType(WnsEnum.PUSH_TYPE_INSTALL);
            model.setStatus(0);
            model.setAll_push(true);
            model.setAge("ALL");
            model.setSex(sex);
            model.setRom("ALL");
            if (!WnsStringUtil.isBlank(showconfrim))
            {
                model.setClose(showconfrim);
            }
            else
            {
                model.setClose("1");
            }
            model.setOpen("1");
            model.setName("下载次数");
            model.setCesu("千次");
            model.setUpdatedate(new Date());
            model.setBegintime(WnsDateUtil.StringToDate(startDate,
                    startHour));
            model.setEndtime(WnsDateUtil.StringToDate(endDate, endHour));
            model.setDown_direct(1);
            model.setCanal_switch(1);
            model.setChannel(channel);
            model.setChannelname(channel);
            if (allpush.equalsIgnoreCase("1"))
            {
                model.setAll_push(true);
            }
            else
            {
                model.setAll_push(false);
            }
            model.setArea(area);
            model.setNetwork(Integer.parseInt(network));
            model.setTitle(title);
            model.setLink(apkurl);
            model.setWeight(Integer.parseInt(weight));
            model.setPush_count(Integer.parseInt(count));
            model.setSize(String.valueOf(WnsUtil
                    .ByteConversionMB(WnsUtil.getUrlFileSize(apkurl)))); // 实际apk大小
            model.setModel("ALL");

            if (id != null && !WnsStringUtil.isBlank(id))
            {
                dao.Update(model);
            }
            else
            {
                dao.Insert(model);
            }
            // loadPush(retBean, request, channel,
            // NgsteamEnum.PUSH_TYPE_INSTALL);
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
            retBean.setPager(request.getPager());
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean addMsgPush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        try
        {
            String title = request.getValue(WnsTag.titleTag);
            String content = request.getValue(WnsTag.contentTag);
            String imgurl = request.getValue(WnsTag.imgurlTag);
            String apkurl = request.getValue(WnsTag.apkurlTag);
            String weight = request.getValue(WnsTag.weightTag);
            String count = request.getValue(WnsTag.countTag);
            String allpush = request.getValue(WnsTag.allpushTag);
            String network = request.getValue(WnsTag.networkTag);
            String startDate = request.getValue(WnsTag.startDateTag);
            String startHour = request.getValue(WnsTag.startHourTag);
            String endDate = request.getValue(WnsTag.endDateTag);
            String endHour = request.getValue(WnsTag.endHourTag);
            String channel = request.getValue(WnsTag.channelTag);
            String area = request.getValue(WnsTag.areaTag);
            String download = request.getValue(WnsTag.downloadTag);
            String showconfrim = request.getValue(WnsTag.showconfrimTag);
            String sex = request.getValue(WnsTag.sexTag);

            pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                    .getBean("dpushPolicyDao");
            push_policy model;
            String id = request.getValue(WnsTag.idTag);
            if (id != null && !WnsStringUtil.isBlank(id))
            {
                model = dao.findSingle(Integer.parseInt(id));
            }
            else
            {
                model = new push_policy();
            }
            model.setType(WnsEnum.PUSH_TYPE_MSG);
            model.setStatus(0);
            model.setAll_push(true);
            model.setAge("ALL");
            model.setSex(sex);
            model.setRom("ALL");
            if (!WnsStringUtil.isBlank(showconfrim))
            {
                model.setClose(showconfrim);
            }
            else
            {
                model.setClose("1");
            }
            model.setOpen("1");
            model.setName("下载次数");
            model.setCesu("千次");
            model.setNetwork(Integer.parseInt(network));
            model.setUpdatedate(new Date());
            model.setBegintime(WnsDateUtil.StringToDate(startDate,
                    startHour));
            model.setEndtime(WnsDateUtil.StringToDate(endDate, endHour));
            model.setDown_direct(Integer.parseInt(download));
            model.setCanal_switch(1);
            model.setChannel(channel);
            model.setChannelname(channel);
            if (allpush.equalsIgnoreCase("1"))
            {
                model.setAll_push(true);
            }
            else
            {
                model.setAll_push(false);
            }
            model.setArea(area);

            model.setTitle(title);
            model.setContent(content);
            model.setImg_link(imgurl);
            model.setLink(apkurl);
            model.setWeight(Integer.parseInt(weight));
            model.setPush_count(Integer.parseInt(count));
            model.setSize(String.valueOf(WnsUtil
                    .ByteConversionMB(WnsUtil.getUrlFileSize(apkurl)))); // 实际apk大小
            model.setModel("ALL");
            model.setDowncount(WnsUtil.random(200, 2000));

            if (id != null && !WnsStringUtil.isBlank(id))
            {
                dao.Update(model);
            }
            else
            {
                dao.Insert(model);
            }
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
            retBean.setPager(request.getPager());
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean addLinkPush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        try
        {
            String title = request.getValue(WnsTag.titleTag);
            String imgurl = request.getValue(WnsTag.imgurlTag);
            String apkurl = request.getValue(WnsTag.apkurlTag);
            String weight = request.getValue(WnsTag.weightTag);
            String count = request.getValue(WnsTag.countTag);
            String allpush = request.getValue(WnsTag.allpushTag);
            String network = request.getValue(WnsTag.networkTag);
            String startDate = request.getValue(WnsTag.startDateTag);
            String startHour = request.getValue(WnsTag.startHourTag);
            String endDate = request.getValue(WnsTag.endDateTag);
            String endHour = request.getValue(WnsTag.endHourTag);
            String channel = request.getValue(WnsTag.channelTag);
            String area = request.getValue(WnsTag.areaTag);
            String showconfrim = request.getValue(WnsTag.showconfrimTag);
            String sex = request.getValue(WnsTag.sexTag);

            pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                    .getBean("dpushPolicyDao");
            push_policy model;
            String id = request.getValue(WnsTag.idTag);
            if (id != null && !WnsStringUtil.isBlank(id))
            {
                model = dao.findSingle(Integer.parseInt(id));
            }
            else
            {
                model = new push_policy();
            }
            model.setType(WnsEnum.PUSH_TYPE_LINK);
            model.setStatus(0);
            model.setAll_push(true);
            model.setAge("ALL");
            model.setSex(sex);
            model.setRom("ALL");
            if (!WnsStringUtil.isBlank(showconfrim))
            {
                model.setClose(showconfrim);
            }
            else
            {
                model.setClose("1");
            }
            model.setOpen("1");
            model.setName("下载次数");
            model.setCesu("千次");
            model.setUpdatedate(new Date());
            model.setBegintime(WnsDateUtil.StringToDate(startDate,
                    startHour));
            model.setEndtime(WnsDateUtil.StringToDate(endDate, endHour));
            model.setDown_direct(1);
            model.setCanal_switch(1);
            model.setChannel(channel);
            model.setChannelname(channel);
            if (allpush.equalsIgnoreCase("1"))
            {
                model.setAll_push(true);
            }
            else
            {
                model.setAll_push(false);
            }
            area = "ALL";
            model.setNetwork(Integer.parseInt(network));
            model.setArea(area);
            model.setImg_link(imgurl);

            model.setTitle(title);
            model.setLink(apkurl);
            model.setWeight(Integer.parseInt(weight));
            model.setPush_count(Integer.parseInt(count));
            model.setSize(String.valueOf(WnsUtil
                    .ByteConversionMB(WnsUtil.getUrlFileSize(apkurl)))); // 实际apk大小
            model.setModel("ALL");

            if (id != null && !WnsStringUtil.isBlank(id))
            {
                dao.Update(model);
            }
            else
            {
                dao.Insert(model);
            }
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
            retBean.setPager(request.getPager());
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean addAdvicePush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        try
        {
            String title = request.getValue(WnsTag.titleTag);
            String content = request.getValue(WnsTag.contentTag);
            String imgurl = request.getValue(WnsTag.imgurlTag);
            String apkurl = request.getValue(WnsTag.apkurlTag);
            String weight = request.getValue(WnsTag.weightTag);
            String count = request.getValue(WnsTag.countTag);
            String allpush = request.getValue(WnsTag.allpushTag);
            String network = request.getValue(WnsTag.networkTag);
            String startDate = request.getValue(WnsTag.startDateTag);
            String startHour = request.getValue(WnsTag.startHourTag);
            String endDate = request.getValue(WnsTag.endDateTag);
            String endHour = request.getValue(WnsTag.endHourTag);
            String channel = request.getValue(WnsTag.channelTag);
            String area = request.getValue(WnsTag.areaTag);
            String showconfrim = request.getValue(WnsTag.showconfrimTag);
            String sex = request.getValue(WnsTag.sexTag);
            String subtype = request.getValue(WnsTag.subtypeTag);

            pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                    .getBean("dpushPolicyDao");
            push_policy model;
            String id = request.getValue(WnsTag.idTag);
            if (id != null && !WnsStringUtil.isBlank(id))
            {
                model = dao.findSingle(Integer.parseInt(id));
            }
            else
            {
                model = new push_policy();
            }
            model.setType(WnsEnum.PUSH_TYPE_ADVICE);
            model.setStatus(0);
            model.setAll_push(true);
            model.setNetwork(Integer.parseInt(network));
            model.setAge("ALL");
            model.setSex(sex);
            model.setRom("ALL");
            if (!WnsStringUtil.isBlank(showconfrim))
            {
                model.setClose(showconfrim);
            }
            else
            {
                model.setClose("1");
            }
            model.setOpen("1");
            model.setName("下载次数");
            model.setCesu("千次");
            model.setUpdatedate(new Date());
            model.setBegintime(WnsDateUtil.StringToDate(startDate,
                    startHour));
            model.setEndtime(WnsDateUtil.StringToDate(endDate, endHour));
            model.setDown_direct(1);
            model.setCanal_switch(1);
            model.setChannel(channel);
            model.setChannelname(channel);
            if (allpush.equalsIgnoreCase("1"))
            {
                model.setAll_push(true);
            }
            else
            {
                model.setAll_push(false);
            }
            area = "ALL";
            model.setArea(area);
            model.setTitle(title);
            model.setImg_link(imgurl);
            model.setContent(content);
            model.setLink(apkurl);
            model.setWeight(Integer.parseInt(weight));
            model.setPush_count(Integer.parseInt(count));
            model.setSize(String.valueOf(WnsUtil
                    .ByteConversionMB(WnsUtil.getUrlFileSize(apkurl)))); // 实际apk大小
            model.setModel("ALL");
            model.setSubtype(Integer.parseInt(subtype));

            if (id != null && !WnsStringUtil.isBlank(id))
            {
                dao.Update(model);
            }
            else
            {
                dao.Insert(model);
            }
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
            retBean.setPager(request.getPager());
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean addNotifyPush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            String title = request.getValue(WnsTag.titleTag);
            String content = request.getValue(WnsTag.contentTag);
            String imgurl = request.getValue(WnsTag.imgurlTag);
            String apkurl = request.getValue(WnsTag.apkurlTag);
            String title2 = request.getValue(WnsTag.title2Tag);
            String content2 = request.getValue(WnsTag.content2Tag);
            String imgurl2 = request.getValue(WnsTag.imgurl2Tag);
            String apkurl2 = request.getValue(WnsTag.apkurl2Tag);
            String title3 = request.getValue(WnsTag.title3Tag);
            String content3 = request.getValue(WnsTag.content3Tag);
            String imgurl3 = request.getValue(WnsTag.imgurl3Tag);
            String apkurl3 = request.getValue(WnsTag.apkurl3Tag);
            String title4 = request.getValue(WnsTag.title4Tag);
            String content4 = request.getValue(WnsTag.content4Tag);
            String imgurl4 = request.getValue(WnsTag.imgurl4Tag);
            String apkurl4 = request.getValue(WnsTag.apkurl4Tag);
            String weight = request.getValue(WnsTag.weightTag);
            String count = request.getValue(WnsTag.countTag);
            String allpush = request.getValue(WnsTag.allpushTag);
            String network = request.getValue(WnsTag.networkTag);
            String startDate = request.getValue(WnsTag.startDateTag);
            String startHour = request.getValue(WnsTag.startHourTag);
            String endDate = request.getValue(WnsTag.endDateTag);
            String endHour = request.getValue(WnsTag.endHourTag);
            String channel = request.getValue(WnsTag.channelTag);
            String area = request.getValue(WnsTag.areaTag);
            String showconfrim = request.getValue(WnsTag.showconfrimTag);
            String sex = request.getValue(WnsTag.sexTag);

            pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                    .getBean("dpushPolicyDao");
            push_policy model;
            String id = request.getValue(WnsTag.idTag);
            if (id != null && !WnsStringUtil.isBlank(id))
            {
                model = dao.findSingle(Integer.parseInt(id));
            }
            else
            {
                model = new push_policy();
            }

            String imgUrl = imgurl;
            String Content = content;
            String apkSize = String.valueOf(WnsUtil
                    .ByteConversionMB(WnsUtil.getUrlFileSize(apkurl)));
            String Title = title;
            String ApkUrl = apkurl;

            if (!WnsStringUtil.isBlank(imgurl2))
            {
                imgUrl += "," + imgurl2;
                Content += "," + content2;
                apkSize += ","
                        + String.valueOf(WnsUtil
                                .ByteConversionMB(WnsUtil
                                        .getUrlFileSize(apkurl2)));
                Title += "," + title2;
                ApkUrl += "," + apkurl2;
            }
            if (!WnsStringUtil.isBlank(imgurl3))
            {
                imgUrl += "," + imgurl3;
                Content += "," + content3;
                apkSize += ","
                        + String.valueOf(WnsUtil
                                .ByteConversionMB(WnsUtil
                                        .getUrlFileSize(apkurl3)));
                Title += "," + title3;
                ApkUrl += "," + apkurl3;
            }
            if (!WnsStringUtil.isBlank(imgurl4))
            {
                imgUrl += "," + imgurl4;
                Content += "," + content4;
                apkSize += ","
                        + String.valueOf(WnsUtil
                                .ByteConversionMB(WnsUtil
                                        .getUrlFileSize(apkurl4)));
                Title += "," + title4;
                ApkUrl += "," + apkurl4;
            }

            model.setType(WnsEnum.PUSH_TYPE_NOTIFIY);
            model.setStatus(0);
            model.setAll_push(true);
            model.setNetwork(Integer.parseInt(network));
            model.setAge("ALL");
            model.setSex(sex);
            model.setRom("ALL");
            if (!WnsStringUtil.isBlank(showconfrim))
            {
                model.setClose(showconfrim);
            }
            else
            {
                model.setClose("1");
            }
            model.setOpen("1");
            model.setName("下载次数");
            model.setCesu("千次");
            model.setUpdatedate(new Date());
            model.setBegintime(WnsDateUtil.StringToDate(startDate,
                    startHour));
            model.setEndtime(WnsDateUtil.StringToDate(endDate, endHour));
            model.setDown_direct(1);
            model.setCanal_switch(1);
            model.setChannel(channel);
            model.setChannelname(channel);
            if (allpush.equalsIgnoreCase("1"))
            {
                model.setAll_push(true);
            }
            else
            {
                model.setAll_push(false);
            }
            area = "ALL";
            model.setArea(area);

            model.setTitle(Title);
            model.setContent(Content);
            model.setImg_link(imgUrl);
            model.setLink(ApkUrl);
            model.setWeight(Integer.parseInt(weight));
            model.setPush_count(Integer.parseInt(count));
            model.setSize(apkSize); // 实际apk大小
            model.setModel("ALL");
            model.setDowncount(WnsUtil.random(200, 2000));

            if (id != null && !WnsStringUtil.isBlank(id))
            {
                dao.Update(model);
            }
            else
            {
                dao.Insert(model);
            }
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
            retBean.setPager(request.getPager());
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean deleteRecord(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        try
        {
            pushHistoryDao historyDao = (pushHistoryDao) WnsSpringHelper
                    .getBean("dpushHistoryDao");
            historyDao.DeleteAll();
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        }
        catch (RuntimeException e) // 接口异常记录
        {
            e.printStackTrace();
            retBean.add(WnsTag.resultTag,
                    WnsResultCodeFactory.REQUEST_ADMIN_ERROR);
        }
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadPush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        long uid = Long.parseLong(request.getValue(WnsTag.session_uidTag));
        String channel = null;
        int role;
        long orgId = 0L;
        role = WnsEnum.PUSH_ROLE_ADMIN;
        WnsChannelOrgDao dao = (WnsChannelOrgDao) WnsSpringHelper
                .getBean("dchannelOrgDao");

        List<WnsChannelOrg> list = dao.findAll();
        if (role != WnsEnum.PUSH_ROLE_ADMIN)
        {
            for (WnsChannelOrg item : list)
            {
                if (!item.getChannelname().contains("计费")
                        && (item.getOrgid().longValue() == orgId))
                {
                    channel = item.getChannel();
                    break;
                }
            }
        }

        int type = Integer.parseInt(request.getValue(WnsTag.typeTag));
        String start = request.getValue(WnsTag.startTag);
        String end = request.getValue(WnsTag.endTag);
        loadPush(retBean, request, start, end, channel, type);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean deletePush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        // int type = Integer.parseInt(request.getValue(NgsteamTag.typeTag));
        int id = Integer.parseInt(request.getValue(WnsTag.idTag));
        pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                .getBean("dpushPolicyDao");
        push_policy item = dao.findSingle(id);
        if (item != null)
        {
            item.setStatus(-1);
            dao.Update(item);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean getPush(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        // int type = Integer.parseInt(request.getValue(NgsteamTag.typeTag));
        int id = Integer.parseInt(request.getValue(WnsTag.idTag));
        pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                .getBean("dpushPolicyDao");
        push_policy item = dao.findSingle(id);
        Map<String, Object> map;
        if (item != null)
        {
            if (item.getType() == WnsEnum.PUSH_TYPE_NOTIFIY)
            {
                String[] imgStrs = item.getImg_link().toString().split("[,]",
                        -1); // 链接
                String[] urlStrs = item.getLink().toString().split("[,]", -1); // 图片地址
                String[] contents = item.getContent().toString().split("[,]",
                        -1); // 内容
                String[] title = item.getTitle().toString().split("[,]", -1); // 标题
                item.setImg_link(imgStrs[0]);
                item.setLink(urlStrs[0]);
                item.setContent(contents[0]);
                item.setTitle(title[0]);
                map = WnsUtil.modelToMap(item);
                if (imgStrs.length >= 2)
                {
                    map.put(WnsTag.title2Tag, title[1]);
                    map.put(WnsTag.content2Tag, contents[1]);
                    map.put(WnsTag.imgurl2Tag, imgStrs[1]);
                    map.put(WnsTag.apkurl2Tag, urlStrs[1]);
                }
                if (imgStrs.length >= 3)
                {
                    map.put(WnsTag.title3Tag, title[2]);
                    map.put(WnsTag.content3Tag, contents[2]);
                    map.put(WnsTag.imgurl3Tag, imgStrs[2]);
                    map.put(WnsTag.apkurl3Tag, urlStrs[2]);
                }
                if (imgStrs.length >= 4)
                {
                    map.put(WnsTag.title4Tag, title[3]);
                    map.put(WnsTag.content4Tag, contents[3]);
                    map.put(WnsTag.imgurl4Tag, imgStrs[3]);
                    map.put(WnsTag.apkurl4Tag, urlStrs[3]);
                }
                retBean.add(WnsTag.eventTag, map);
            }
            else
            {
                map = WnsUtil.modelToMap(item);
                retBean.add(WnsTag.eventTag, map);
            }
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadPush(ResponseBean retBean, RequestBean bean,
            String start, String end, String channel, int type)
    {
        pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                .getBean("dpushPolicyDao");

        // NgsteamPager page = bean.getPager();
        start = WnsDateUtil.NgsteamToString(WnsDateUtil
                .StringToDate(start));
        end = WnsDateUtil
                .NgsteamToString(WnsDateUtil.StringToDate(end));
        List<push_policy> list = dao.findPushByType(start, end, channel, type);
        Map<String, Object> map;
        for (push_policy item : list)
        {
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }

        // page.setTotalRows(dao.findPushCountByType(channel, type));
        // retBean.setPager(page);
        return retBean;
    }

    private ResponseBean loadApk(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        ApkDao dao = (ApkDao) WnsSpringHelper.getBean("dapkDao");

        List<ApkItem> list = dao.findAll();
        Map<String, Object> map;
        for (ApkItem item : list)
        {
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        WnsPager page = request.getPager();
        page.setTotalRows(list.size());
        retBean.setPager(request.getPager());
        return retBean;
    }

    private ResponseBean loadLib(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        LibDao dao = (LibDao) WnsSpringHelper.getBean("dlibDao");

        List<LibItem> list = dao.findAll();
        Map<String, Object> map;
        for (LibItem item : list)
        {
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        WnsPager page = request.getPager();
        page.setTotalRows(list.size());
        retBean.setPager(request.getPager());
        return retBean;
    }

    private ResponseBean loadChannel(RequestBean request)
    {
     	System.out.println("loadChannel===>");
        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        long uid = Long.parseLong(request.getValue(WnsTag.session_uidTag));
        int role;
        long orgId = 0;
        role = WnsEnum.PUSH_ROLE_ADMIN;

        WnsChannelOrgDao dao = (WnsChannelOrgDao) WnsSpringHelper
                .getBean("dchannelOrgDao");

        List<WnsChannelOrg> retList = new ArrayList<WnsChannelOrg>();
        List<WnsChannelOrg> list = dao.findAll();
        if (role == WnsEnum.PUSH_ROLE_ADMIN)
        {
        

            for (WnsChannelOrg item : list)
            {
            	System.out.println("ChannleOrg.id===>"+item.getId());
            	System.out.println("ChannleOrg.channel===>"+item.getChannel());
            	System.out.println("ChannleOrg.name===>"+item.getChannelname());
                retList.add(item);
            }
        }
        else
        {
            for (WnsChannelOrg item : list)
            {
            	System.out.println("ChannleOrg.id===>"+item.getId());
            	System.out.println("ChannleOrg.channel===>"+item.getChannel());
            	System.out.println("ChannleOrg.name===>"+item.getChannelname());
                if (!item.getChannelname().contains("计费")
                        && (item.getOrgid().longValue() == orgId))
                {
                    retList.add(item);
                }
            }
        }

        Map<String, Object> map;
        for (WnsChannelOrg item : retList)
        {
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.channelsTag, map);
        }
        if (role == WnsEnum.PUSH_ROLE_ADMIN)
        {
            retBean.add(WnsTag.is_adminTag, 1);
        }
        else
        {
            retBean.add(WnsTag.is_adminTag, 0);
        }

        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadClientStatis(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        WnsPager page = request.getPager();
        String channel = request.getValue(WnsTag.channelTag);
        String model = request.getValue(WnsTag.modelTag);
        String area = request.getValue(WnsTag.areaTag);
        String start = request.getValue(WnsTag.startDateTag);
        String end = request.getValue(WnsTag.endDateTag);
        if ((channel != null) && (channel.equals("-1")))
        {
            channel = null;
        }

        if ((model != null) && model.equals("-1"))
        {
            model = null;
        }

        if ((area != null) && area.equals("-1"))
        {
            area = null;
        }

        Date startDate = WnsDateUtil.StringToDate(start);
        Date endDate = WnsDateUtil.StringToDate(end);
        WnsChannelOrgDao dao = (WnsChannelOrgDao) WnsSpringHelper
                .getBean("dchannelOrgDao");
        Map<String, String> channelMap = new HashMap<String, String>();
        if (channel != null)
        {
            List<WnsChannelOrg> channelList = dao.findAll();
            for (WnsChannelOrg item : channelList)
            {
                channelMap.put(item.getChannel(), item.getChannelname());
            }
        }
        Map<String, Object> map;
        StatisClientDao statisDao = (StatisClientDao) WnsSpringHelper
                .getBean("dclientStatisDao");
        List<StatisClientQuery> list = statisDao.findClientQuery(channel,
                model, area, startDate, endDate, page);
        for (StatisClientQuery item : list)
        {
            if (channel == null)
            {
                item.setChannel("全部");
            }
            else
            {
                if ("ALL".equals(channel))
                {
                    item.setChannel(channelMap.get(item.getChannel()));
                }
                else
                {
                    item.setChannel(channelMap.get(channel));
                }
            }
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }

        page.setTotalRows(statisDao.findClientQueryCount(channel, model, area,
                startDate, endDate));
        retBean.setPager(page);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadMsgStatis(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        WnsPager page = request.getPager();
        String channel = request.getValue(WnsTag.channelTag);
        String model = request.getValue(WnsTag.modelTag);
        String area = request.getValue(WnsTag.areaTag);
        String start = request.getValue(WnsTag.startDateTag);
        String end = request.getValue(WnsTag.endDateTag);
        if ((channel != null) && (channel.equals("-1")))
        {
            channel = null;
        }

        if ((model != null) && model.equals("-1"))
        {
            model = null;
        }

        if ((area != null) && area.equals("-1"))
        {
            area = null;
        }

        Date startDate = WnsDateUtil.StringToDate(start);
        Date endDate = WnsDateUtil.StringToDate(end);

        WnsChannelOrgDao dao = (WnsChannelOrgDao) WnsSpringHelper
                .getBean("dchannelOrgDao");
        Map<String, String> channelMap = new HashMap<String, String>();
        if (channel != null)
        {
            List<WnsChannelOrg> channelList = dao.findAll();
            for (WnsChannelOrg item : channelList)
            {
                channelMap.put(item.getChannel(), item.getChannelname());
            }
        }
        Map<String, Object> map;
        StatisMsgDao statisDao = (StatisMsgDao) WnsSpringHelper
                .getBean("dmsgStatisDao");
        List<StatisMsgQuery> list = statisDao.findMsgQuery(channel, model,
                area, startDate, endDate, page);
        for (StatisMsgQuery item : list)
        {
            if (channel == null)
            {
                item.setChannel("全部");
            }
            else
            {
                if ("ALL".equals(channel))
                {
                    item.setChannel(channelMap.get(item.getChannel()));
                }
                else
                {
                    item.setChannel(channelMap.get(channel));
                }
            }
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }

        page.setTotalRows(statisDao.findMsgQueryCount(channel, model, area,
                startDate, endDate));
        retBean.setPager(page);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    @SuppressWarnings("unchecked")
    private ResponseBean loadPushStatis(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        List<Integer> list = new ArrayList<Integer>();
        Long startTime = WnsDateUtil.startTime();
        // String key = "pmsg_feedback_" + startTime + "_" + policyId + "_" +
        // type;
        Set<String> keys = WnsRedisFactory.keys("pmsg_feedback_"
                + startTime + "_*");
        for (String key : keys)
        {
            String[] strs = key.split("_");
            Integer policyId = null;
            try
            {
                policyId = Integer.parseInt(strs[3]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if ((policyId != null) && !list.contains(policyId))
            {
                list.add(policyId);
            }
        }
        pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                .
                getBean("dpushPolicyDao");
        Map<String, Object> map;
        String key;
        String value;
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        String[] types = { WnsEnum.FEEDBACK_TYPE_DWONLOAD_BEGIN,
                WnsEnum.FEEDBACK_TYPE_DWONLOAD_COMPLETED,
                WnsEnum.FEEDBACK_TYPE_DWONLOAD_ERROR,
                WnsEnum.FEEDBACK_TYPE_INSTALL_PRESENT,
                WnsEnum.FEEDBACK_TYPE_INSTALL_COMPLETED,
                WnsEnum.FEEDBACK_TYPE_UNINSTALL_COMPLETED,
                WnsEnum.FEEDBACK_TYPE_TIPRUN_POSITIVE,
                WnsEnum.FEEDBACK_TYPE_TIPRUN_NEGATIVE,
                WnsEnum.FEEDBACK_TYPE_INTERSTITIAL_PRESENT };
        String[] tags = { WnsTag.down_beginTag,
                WnsTag.down_completeTag, WnsTag.down_errorTag,
                WnsTag.install_presetTag, WnsTag.install_completeTag,
                WnsTag.uninstall_completeTag,
                WnsTag.tiprun_positiveTag, WnsTag.tiprun_negativeTag,
                WnsTag.inter_presentTag };

        for (Integer policyId : list)
        {
            map = new HashMap<String, Object>();
            push_policy item = dao.findSingle(policyId);
            if (item == null)
            {
                continue;
            }
            map.put(WnsTag.msg_policy_idTag, item.getId());
            map.put(WnsTag.titleTag, item.getTitle());
            key = "com.wns.push.util.WnsPushTaskManager_id_"
                    + item.getId();
            value = WnsRedisFactory.get(key);
            if (value == null)
            {
                map.put(WnsTag.countTag, 0);
            }
            else
            {
                map.put(WnsTag.countTag, Integer.parseInt(value));
            }

            for (int i = 0; i < types.length; i++)
            {
                key = "pmsg_feedback_" + startTime + "_" + policyId + "_"
                        + types[i];
                value = WnsRedisFactory.get(key);
                if (value == null)
                {
                    map.put(tags[i], 0);
                }
                else
                {
                    map.put(tags[i], Integer.parseInt(value));
                }
            }
            mapList.add(map);

        }
        Collections.sort(mapList, new Comparator()
        {

            public int compare(Object o1, Object o2)
            {
                Map<String, Object> map1 = (Map<String, Object>) o1;
                Map<String, Object> map2 = (Map<String, Object>) o2;
                Integer value1 = (Integer) map1.get(WnsTag.countTag);
                Integer value2 = (Integer) map2.get(WnsTag.countTag);
                return value2.intValue() - value1.intValue();
            }

        });
        retBean.addDataSet(WnsTag.rowsTag, mapList);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadModel(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        long uid = Long.parseLong(request.getValue(WnsTag.session_uidTag));
        WnsChannelOrgDao dao = (WnsChannelOrgDao) WnsSpringHelper
                .getBean("dchannelOrgDao");
        List<WnsChannelOrg> retList = new ArrayList<WnsChannelOrg>();
        List<WnsChannelOrg> list = dao.findAll();
        for (WnsChannelOrg item : list)
        {

            retList.add(item);
        }

        Map<String, Object> map;
        for (WnsChannelOrg item : retList)
        {
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.channelsTag, map);
        }

        retBean.add(WnsTag.is_adminTag, 1);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadMsgCount(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);

        WnsPager page = request.getPager();
        String start = request.getValue(WnsTag.startDateTag);

        Date startDate = WnsDateUtil.StringToDate(start);
        Date endDate = WnsDateUtil.addTime(startDate, 1, 0, 0);
        pushPolicyDao dao = (pushPolicyDao) WnsSpringHelper
                .getBean("dpushPolicyDao");
        pushHistoryDao recordDao = (pushHistoryDao) WnsSpringHelper
                .getBean("dpushHistoryDao");
        List<push_policy> list = dao.findAllByDate(WnsDateUtil
                .NgsteamToString(startDate), WnsDateUtil
                .NgsteamToString(endDate));

        for (push_policy item : list)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            Integer count = recordDao.findCountByDate(WnsDateUtil
                    .NgsteamToString(startDate), WnsDateUtil
                    .NgsteamToString(endDate), item.getId());
            map.put(WnsTag.dateTag, start);
            map.put(WnsTag.nameTag, item.getTitle() + "(" + item.getId()
                    + ")");
            map.put(WnsTag.countTag, count);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }
        page.setTotalRows(list.size());
        retBean.setPager(page);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean cleanTask(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        WnsPushTaskManager.cleanTask();
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadArea(RequestBean request)
    {
    
        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        AreaDao dao = (AreaDao) WnsSpringHelper.getBean("dareaDao");
        List<Area> list = dao.findAll();
        for (Area item : list)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }
        int    role = WnsEnum.PUSH_ROLE_ADMIN;
        if (role == WnsEnum.PUSH_ROLE_ADMIN)
        {
            retBean.add(WnsTag.is_adminTag, 1);
        }
        else
        {
            retBean.add(WnsTag.is_adminTag, 0);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadChannelArea(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        ChannelAreaDao dao = (ChannelAreaDao) WnsSpringHelper
                .getBean("dchannelAreaDao");
        List<ChannelArea> list = dao.findAll();
        for (ChannelArea item : list)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean insertChannelArea(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String channel = request.getValue(WnsTag.channelTag);
        String area = request.getValue(WnsTag.areaTag);
        ChannelAreaDao dao = (ChannelAreaDao) WnsSpringHelper
                .getBean("dchannelAreaDao");
        ChannelArea item = new ChannelArea();
        item.setArea(area);
        item.setChannel(channel);
        item.setStatus(0);
        dao.insert(item);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean editChannelArea(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String area = request.getValue(WnsTag.areaTag);
        String id = request.getValue(WnsTag.idTag);
        ChannelAreaDao dao = (ChannelAreaDao) WnsSpringHelper
                .getBean("dchannelAreaDao");
        ChannelArea item = dao.findSingle(Long.parseLong(id));
        item.setArea(area);
        item.setStatus(0);
        dao.update(item);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadWhiteChannel(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        WhiteChannelDao dao = (WhiteChannelDao) WnsSpringHelper
                .getBean("dwhiteChannelDao");
        List<WhiteChannel> list = dao.findAllByAdmin();
        for (WhiteChannel item : list)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean insertWhiteChannel(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String channel = request.getValue(WnsTag.channelTag);
        WhiteChannelDao dao = (WhiteChannelDao) WnsSpringHelper
                .getBean("dwhiteChannelDao");
        WhiteChannel item = new WhiteChannel();
        item.setChannel(channel);
        item.setStatus(0);
        dao.insert(item);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean editWhiteChannel(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String id = request.getValue(WnsTag.idTag);
        String status = request.getValue(WnsTag.statusTag);
        String channel = request.getValue(WnsTag.channelTag);
        WhiteChannelDao dao = (WhiteChannelDao) WnsSpringHelper
                .getBean("dwhiteChannelDao");
        WhiteChannel item = dao.findSingleByAdmin(Long.parseLong(id));
        if (item == null)
        {
            item = dao.findSingleByChannel(channel);
        }
        if (item != null)
        {
            item.setStatus(Integer.parseInt(status));
            dao.update(item);
        }
        else
        {
            item = new WhiteChannel();
            item.setChannel(channel);
            item.setStatus(Integer.parseInt(status));
            dao.insert(item);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadApp(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
//         List<WnsApp> list = WnsBase.ngsteamFindAllApp();
         for (int i=0;i<4;i++)
         {
        	 
        	 WnsApp item=new WnsApp();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put(WnsTag.app_idTag, i);
         map.put(WnsTag.nameTag, "test"+1);
         retBean.addDataSetDate(WnsTag.rowsTag, map);
         }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean loadAppSwitch(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        AppSwitchDao dao = (AppSwitchDao) WnsSpringHelper
                .getBean("dappSwitchDao");
        List<AppSwitch> list = dao.findAllByAdmin();
        for (AppSwitch item : list)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map = WnsUtil.modelToMap(item);
            retBean.addDataSetDate(WnsTag.rowsTag, map);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean insertAppSwitch(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String appid = request.getValue(WnsTag.app_idTag);
        AppSwitchDao dao = (AppSwitchDao) WnsSpringHelper
                .getBean("dappSwitchDao");
        AppSwitch item = new AppSwitch();
        item.setApp_id(appid);
        item.setStatus(0);
        dao.insert(item);
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean editAppSwitch(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String status = request.getValue(WnsTag.statusTag);
        String id = request.getValue(WnsTag.idTag);
        String appid = request.getValue(WnsTag.app_idTag);
        AppSwitchDao dao = (AppSwitchDao) WnsSpringHelper
                .getBean("dappSwitchDao");
        AppSwitch item = dao.findSingleByAdmin(Long.parseLong(id));
        if (item == null)
        {
            item = dao.findSingleByAppid(appid);
        }
        if (item != null)
        {
            item.setStatus(Integer.parseInt(status));
            dao.update(item);
        }
        else
        {
            item = new AppSwitch();
            item.setApp_id(appid);
            item.setStatus(Integer.parseInt(status));
            dao.insert(item);
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean statisClient(RequestBean request)
    {
    	System.out.println("------statisClient--------");
        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String date = request.getValue(WnsTag.dateTag);
        Date start = WnsDateUtil.StringToDate(date);

        Calendar calendar = Calendar.getInstance();
        if (start == null)
        {
            start = new Date(WnsDateUtil.startTime());
            calendar.setTime(start);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            start = calendar.getTime();
        }
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date end = calendar.getTime();

        ClientDao clientDao = (ClientDao) WnsSpringHelper
                .getBean("dclientDao");
        StatisClientDao statisClientDao = (StatisClientDao) WnsSpringHelper
                .getBean("dclientStatisDao");

        // int num = clientDao.statisClientNum(start, end);
        int startRow = 0;
        while (true)
        {
            List<StatisClientQuery> list = clientDao.statisClient(start, end,
                    startRow, 1000);
     
            System.out.println("---" +
            		""+list.size());
            if ((list != null) && list.size() > 0)
            {
                List<StatisClient> clientList = new ArrayList<StatisClient>();
                for (StatisClientQuery item : list)
                {
                    StatisClient statisClient = new StatisClient();
                    statisClient.setChannel(item.getChannel());
                    statisClient.setCount(item.getCount());
                    statisClient.setCreatedate(start);
                    clientList.add(statisClient);
                }
                statisClientDao.insertBatch(clientList);
                startRow += list.size();
            }
            else
            {
                break;
            }
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }

    private ResponseBean statisMsg(RequestBean request)
    {

        ResponseBean retBean = new ResponseBean(
                WnsResultCodeFactory.SUCCESS);
        String date = request.getValue(WnsTag.dateTag);
        Date start = WnsDateUtil.StringToDate(date);

        Calendar calendar = Calendar.getInstance();
        if (start == null)
        {
            start = new Date(WnsDateUtil.startTime());
            calendar.setTime(start);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            start = calendar.getTime();
        }
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date end = calendar.getTime();

        pushPolicyDao msgDao = (pushPolicyDao) WnsSpringHelper
                .getBean("dpushPolicyDao");
        pushHistoryDao recordDao = (pushHistoryDao) WnsSpringHelper
                .getBean("dpushHistoryDao");
        StatisMsgDao statisMsgDao = (StatisMsgDao) WnsSpringHelper
                .getBean("dmsgStatisDao");
        Map<Long, String> msgMap = new HashMap<Long, String>();

        // int num = clientDao.statisClientNum(start, end);
        int startRow = 0;
        while (true)
        {
            List<StatisMsgQuery> list = recordDao.statisMsg(start, end,
                    startRow, 1000);
            if ((list != null) && list.size() > 0)
            {
                List<StatisMsg> msgList = new ArrayList<StatisMsg>();
                for (StatisMsgQuery item : list)
                {
                    String[] ids = item.getMsgid().split(":");
                    for (String id : ids)
                    {
                        StatisMsg statismsg = new StatisMsg();
                        statismsg.setMsgId(Long.parseLong(id));
                        if (msgMap.get(statismsg.getMsgId()) == null)
                        {
                            push_policy policy = msgDao
                                    .findSingleByStatis(statismsg.getMsgId()
                                            .intValue());
                            if (policy != null)
                            {
                                msgMap.put(statismsg.getMsgId(), policy
                                        .getTitle());
                            }
                        }
                        statismsg.setMsgName(msgMap.get(statismsg.getMsgId()));
                        statismsg.setChannel(item.getChannel());
                        statismsg.setCount(item.getCount());
                        statismsg.setCreatedate(start);
                        msgList.add(statismsg);
                    }
                }
                statisMsgDao.insertBatch(msgList);
                startRow += list.size();
            }
            else
            {
                break;
            }
        }
        retBean.add(WnsTag.resultTag,
                WnsResultCodeFactory.REQUEST_ADMIN_SUCCESS);
        retBean.add(WnsTag.statusTag, WnsResultCodeFactory.SUCCESS);
        return retBean;
    }
}
