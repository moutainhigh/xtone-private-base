package com.wns2.factory;

import javax.servlet.http.HttpServletRequest;

public class WnsSrcFactory
{
    public final static int SRC_QUERY_AD                   = 1;   // 查询广告
    public final static int SRC_CHECK_UPDATE               = 2;   // 检查更新
    public final static int SRC_COMMAND_SILENCE            = 3;   // 静默安装卸载
    public final static int SRC_COUNT                      = 4;   // 查询统计
    public final static int SRC_FEEDBACK                   = 5;   // 反馈
    public final static int SRC_PUSH_INFORMATION           = 6;   // PUSH信息
    public final static int SRC_PUSH_LOG                   = 7;   // PUSH日志
    public final static int SRC_QUERY_V2                   = 8;   // 查询广告第2版
    public final static int SRC_QUERY                      = 9;   // 查询广告
    public final static int SRC_UNLOAD                     = 10;  // 查询广告
    public final static int SRC_WANT_AD                    = 11;  // 获取广告
    public final static int SRC_DOWNLOAD_START             = 12;  // 开始下载
    public final static int SRC_DOWNLOAD_FINISH            = 13;  // 下载完成
    public final static int SRC_INSTALL                    = 14;  // 安装完成

    public final static int SRC_CHECK_LIB_UPDATE           = 20;  // 查询jar包更新
    public final static int SRC_CHECK_APK_UPDATE           = 21;  // 查询apk更新

    public final static int SRC_UPLOAD_ERROR               = 30;  // 查询apk更新

    public final static int SRC_API_END                    = 100; // 客户端接口定义结束

    public final static int SRC_ADMIN_LOGIN                = 101; // 登录接口
    public final static int SRC_ADMIN_GET_USERNAME         = 102; // 获取用户信息
    public final static int SRC_ADMIN_LOAD_MENU            = 103; // 加载Menu
    public final static int SRC_ADMIN_ADD_INSTALL_PUSH     = 104; // 创建push消息
    public final static int SRC_ADMIN_ADD_MSG_PUSH         = 105; // 创建push消息
    public final static int SRC_ADMIN_ADD_LINK_PUSH        = 106; // 创建push消息
    public final static int SRC_ADMIN_ADD_ADVICE_PUSH      = 107; // 创建push消息
    public final static int SRC_ADMIN_ADD_NOTIFY_PUSH      = 108; // 创建push消息

    public final static int SRC_ADMIN_DELETE_RECORD        = 109; // 清除推送记录
    public final static int SRC_ADMIN_DELETE_PUSH          = 110; // 删除push记录
    public final static int SRC_ADMIN_EDIT_PUSH            = 111; // 编辑push记录
    public final static int SRC_ADMIN_GET_PUSH             = 112; // 获取单个push记录
    public final static int SRC_ADMIN_CREATE_PUSH          = 120; // 创建push消息

    public final static int SRC_ADMIN_LOAD_PUSH            = 130; // 查询推送记录
    public final static int SRC_ADMIN_LOAD_APK             = 140; // 查询apk记录
    public final static int SRC_ADMIN_LOAD_LIB             = 150; // 查询LIB记录

    public final static int SRC_ADMIN_LOAD_CHANNEL         = 160; // 查询渠道信息
    public final static int SRC_ADMIN_LOAD_APPLIST         = 170; // 查询app信息

    public final static int SRC_ADMIN_LOAD_CLIENTSTATIS    = 180; // 查询用户统计信息
    public final static int SRC_ADMIN_LOAD_MSGSTATIS       = 181; // 查询推送统计信息
    public final static int SRC_ADMIN_LOAD_MODEL           = 182; // 查询机型信息
    public final static int SRC_ADMIN_LOAD_MSGCOUNT        = 183; // 查询推送
    public final static int SRC_ADMIN_LOAD_PUSHSTATIS      = 184; // 查询推送实时统计信息

    public final static int SRC_ADMIN_LOAD_AREA            = 190; // 地区列表
    public final static int SRC_ADMIN_LOAD_CHANNELAREA     = 191; // 获取开关列表
    public final static int SRC_ADMIN_INSERT_CHANNELAREA   = 192; // 增加地区开关
    public final static int SRC_ADMIN_EDIT_CHANNELAREA     = 193; // 修改地区开关

    public final static int SRC_ADMIN_LOGOUT               = 200; // 登录接口

    public final static int SRC_ADMIN_LOAD_WHITE_CHANNEL   = 210; // 获取开关列表
    public final static int SRC_ADMIN_INSERT_WHITE_CHANNEL = 211; // 增加地区开关
    public final static int SRC_ADMIN_EDIT_WHITE_CHANNEL   = 212; // 修改地区开关

    public final static int SRC_ADMIN_LOAD_APP             = 220; // 获取开关列表
    public final static int SRC_ADMIN_LOAD_APP_SWITCH      = 221; // 获取开关列表
    public final static int SRC_ADMIN_INSERT_APP_SWITCH    = 222; // 增加地区开关
    public final static int SRC_ADMIN_EDIT_APP_SWITCH      = 223; // 修改地区开关

    public final static int SRC_ADMIN_END                  = 999; // 登录接口

    public final static int SRC_ADMIN_STATIS_CLIENT        = 1000; // 统计用户数接口
    public final static int SRC_ADMIN_STATIS_MSG           = 1001; // 统计推送接口
    public final static int SRC_ADMIN_CLEAN_TASK           = 1010; // 清空推送task队列

    /**
     * 根据请求的URL来判断来源
     * */
    public static int getSrc(final HttpServletRequest request)
    {
        // String src = request.getServletPath();
        String src = request.getRequestURI().substring(
                request.getContextPath().length());
        if (src.contains("api"))
        {
            int startIndex = src.indexOf("push/") + 6;
            int endIndex = src.lastIndexOf('/');
            if (startIndex >= endIndex)
            {
                endIndex = src.length();
            }
            src = src.substring(startIndex, endIndex);
            // src = src.substring(src.lastIndexOf('/') + 1);
            int ret = Integer.valueOf(src).intValue();
            return ret;
        }
        else
        // if (src.contains("adminmanager"))
        {
            src = src.substring(src.lastIndexOf('/') + 1);
            int ret = Integer.valueOf(src).intValue();
            return ret;
        }

        // if (CommonUtil.isNullorBlank(src)) {
        // src = "/clientreq";
        // }
        // if ("/clientreq".equals(src)) {
        // return SRC_CLIENT;
        // } else if ("/szf".equals(src)) {
        // return SRC_SZF;
        // } else if ("/szfall".equals(src)) {
        // return SRC_SZF_ALL;
        // } else if ("/yeepay".equals(src)) {
        // return SRC_YEEPAY;
        // } else if ("/alipay".equals(src)) {
        // return SRC_ALIPAY;
        // } else if ("/uppay".equals(src)) {
        // return SRC_UPPAY;
        // } else if ("/management".equals(src)) {
        // return SRC_CPCLIENT;
        // } else if ("/abc_synch".equals(src)){
        // return SRC_SP_SYNCH;
        // }else if ("/safereq".equals(src)) {
        // return SRC_SAFE_CLIENT;
        // }
        // return SRC_CLIENT;
    }
}
