package com.wns2.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.wns.push.admin.bean.WnsApkFeature;
import com.wns.push.admin.bean.WnsApkInfo;
import com.wns.push.admin.bean.WnsApp;
import com.wns2.base.bean.WnsSysProperty;
import com.wns2.factory.WnsFileFactory;

public class WnsApkUtil
{
    public static final String  VERSION_CODE         = "versionCode";
    public static final String  VERSION_NAME         = "versionName";
    public static final String  SDK_VERSION          = "sdkVersion";
    public static final String  TARGET_SDK_VERSION   = "targetSdkVersion";
    public static final String  USES_PERMISSION      = "uses-permission";
    public static final String  APPLICATION_LABEL    = "application-label";
    public static final String  APPLICATION_ICON     = "application-icon";
    public static final String  USES_FEATURE         = "uses-feature";
    public static final String  USES_IMPLIED_FEATURE = "uses-implied-feature";
    public static final String  SUPPORTS_SCREENS     = "supports-screens";
    public static final String  SUPPORTS_ANY_DENSITY = "supports-any-density";
    public static final String  DENSITIES            = "densities";
    public static final String  PACKAGE              = "package";
    public static final String  APPLICATION          = "application:";

    private ProcessBuilder      mBuilder;
    private String              aapt;
    private static final String SPLIT_REGEX          = "(: )|(=')|(' )|'";
    private static final String FEATURE_SPLIT_REGEX  = "(:')|(',')|'";

    public WnsApkUtil()
    {
        mBuilder = new ProcessBuilder();
        mBuilder.redirectErrorStream(true);
        aapt = ((WnsSysProperty) WnsSpringHelper
                .getBean("bSysProperty")).getAaptPath();
    }

    /**
     * 返回一个apk程序的信息。
     * 
     * @param apkPath
     *            apk的路径。
     * @return apkInfo 一个Apk的信息。
     */
    public WnsApkInfo getApkInfo(String apkPath) throws Exception
    {

        Process process = mBuilder.command(aapt, "d", "badging", apkPath)
                .start();
        InputStream is = null;
        is = process.getInputStream();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is, "utf8"));
        String tmp = br.readLine();
        try
        {
            if (tmp == null || !tmp.startsWith("package"))
            {
                throw new Exception("参数不正确，无法正常解析APK包。输出结果为:" + tmp + "...");
            }
            WnsApkInfo apkInfo = new WnsApkInfo();
            do
            {
                setApkInfoProperty(apkInfo, tmp);
            }
            while ((tmp = br.readLine()) != null);
            return apkInfo;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            process.destroy();
            closeIO(is);
            closeIO(br);
        }
    }

    /**
     * 设置APK的属性信息。
     * 
     * @param apkInfo
     * @param line
     */
    private void setApkInfoProperty(WnsApkInfo apkInfo, String line)
    {
        if (line.startsWith(PACKAGE))
        {
            splitPackageInfo(apkInfo, line);
        }
        else if (line.startsWith(SDK_VERSION))
        {
            apkInfo.setSdkVersion(getPropertyInQuote(line));
        }
        else if (line.startsWith(TARGET_SDK_VERSION))
        {
            apkInfo.setTargetSdkVersion(getPropertyInQuote(line));
        }
        else if (line.startsWith(USES_PERMISSION))
        {
            apkInfo.addToUsesPermissions(getPropertyInQuote(line));
        }
        else if (line.startsWith(APPLICATION_LABEL))
        {
            apkInfo.setApplicationLable(getPropertyInQuote(line));
        }
        else if (line.startsWith(APPLICATION_ICON))
        {
            apkInfo.addToApplicationIcons(getKeyBeforeColon(line),
                    getPropertyInQuote(line));
        }
        else if (line.startsWith(APPLICATION))
        {
            String[] rs = line.split("( icon=')|'");
            apkInfo.setApplicationIcon(rs[rs.length - 1]);
        }
        else if (line.startsWith(USES_FEATURE))
        {
            apkInfo.addToFeatures(getPropertyInQuote(line));
        }
        else if (line.startsWith(USES_IMPLIED_FEATURE))
        {
            apkInfo.addToImpliedFeatures(getFeature(line));
        }
        else
        {
            // System.out.println(source);
        }
    }

    private WnsApkFeature getFeature(String line)
    {
        String[] result = line.split(FEATURE_SPLIT_REGEX);
        WnsApkFeature impliedFeature = new WnsApkFeature(result[1],
                result[2]);
        return impliedFeature;
    }

    /**
     * 返回出格式为name: 'value'中的value内容。
     * 
     * @param line
     * @return
     */
    private String getPropertyInQuote(String line)
    {
        return line.substring(line.indexOf("'") + 1, line.length() - 1);
    }

    /**
     * 返回冒号前的属性名称
     * 
     * @param line
     * @return
     */
    private String getKeyBeforeColon(String line)
    {
        return line.substring(0, line.indexOf(':'));
    }

    /**
     * 分离出包名、版本等信息。
     * 
     * @param apkInfo
     * @param line
     */
    private void splitPackageInfo(WnsApkInfo apkInfo, String line)
    {
        String[] strs = line.split(SPLIT_REGEX);
        apkInfo.setPackageName(strs[2]);
        apkInfo.setVersionCode(strs[4]);
        apkInfo.setVersionName(strs[6]);
    }

    /**
     * 释放资源。
     * 
     * @param c
     *            将关闭的资源
     */
    private final void closeIO(Closeable c)
    {
        if (c != null)
        {
            try
            {
                c.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public WnsApp parseApk(String path) throws Exception
    {
        String fileName = path.substring(path.lastIndexOf("/") + 1, path
                .length());

        if (fileName.lastIndexOf(".") > 0)
        {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        String icon = fileName + ".png";

        String[] ret = WnsFileFactory.addFile(icon, 0);
        icon = ret[0];

        WnsApkInfo apkInfo = null;
        try
        {
            apkInfo = new WnsApkUtil().getApkInfo(path);
        }
        catch (Exception e)
        {
            File temp = new File(path);
            if (temp.exists())
            {
                temp.delete();
            }
            return null;
        }
        List<String> list = WnsAnalysisApk.unZipIcon(path, icon, apkInfo
                .getApplicationIcon());
        if (list == null || list.size() != 1)
        {
            return null;
        }

        WnsApp app = new WnsApp();
        app.setName(apkInfo.getApplicationLable());
        app.setVersionname(apkInfo.getVersionName());
        app.setPkgname(apkInfo.getPackageName());
        app.setVersion(apkInfo.getVersionCode());
        app.setIcon("/download?id=" + ret[1]);
        app.setAndroidversion(apkInfo.getSdkVersion());
        app.setSign(getSign(path));
        // app.setAppName(fileName.substring(fileName.indexOf("_")+1,fileName.length()));
        // app.setApkSign(list.get(0));
        // if (Constant.isY.equals(list.get(0)))
        // {
        // app.setIconUrl("upload/cp_icon/"
        // + Md5Util.md5Encryp(fileName.substring(0, fileName
        // .lastIndexOf("."))) + ".png");
        // }
        return app;
    }

    // public NgsteamApp parseApk(String path, String logoUrl) throws Exception
    // {
    // String fileName = path.substring(path.lastIndexOf("/") + 1, path
    // .length());
    //
    // File dirFile = new File(logoUrl);
    // if (!dirFile.exists())
    // {
    // dirFile.mkdirs();
    // }
    // logoUrl = logoUrl + "/"
    // + fileName.substring(0, fileName.lastIndexOf("_")) + ".png";
    //
    // NgsteamApkInfo apkInfo = null;
    // try
    // {
    // apkInfo = new NgsteamApkUtil(mAaptPath).getApkInfo(path);
    // }
    // catch (Exception e)
    // {
    // File temp = new File(path);
    // if (temp.exists())
    // {
    // temp.delete();
    // }
    // return null;
    // }
    // List<String> list = NgsteamAnalysisApk.unZipIcon(path, logoUrl, apkInfo
    // .getApplicationIcon());
    // if (list == null || list.size() != 1)
    // {
    // return null;
    // }
    //
    // File temp = new File(path);
    // if (temp.exists())
    // {
    // temp.delete();
    // }
    //
    // NgsteamApp app = new AppInfo();
    // app.setApkName(apkInfo.getPackageName());
    // app.setAdVerName(apkInfo.getVersionName());
    // app.setState(Constant.userStsA);
    // app.setAppName(fileName.substring(fileName.indexOf("_") + 1, fileName
    // .length()));
    // // app.setApkSign(list.get(0));
    // if (Constant.isY.equals(list.get(0)))
    // {
    // app
    // .setIconUrl("upload/apk_icon/"
    // + fileName.substring(0, fileName.lastIndexOf("_"))
    // + ".png");
    // }
    // return app;
    // }

    public static String getSign(String path)
    {
        Process p;
        // test.bat中的命令是ipconfig/all
        String cmd = "jarsigner -verify -verbose -certs " + path;
        // String
        // cmd="jarsigner -verify -verbose -certs C:\\Users\\Administrator\\Desktop\\PandaClient.apk";
        String resultstr = null;
        try
        {
            // 执行命令
            p = Runtime.getRuntime().exec(cmd);
            // 取得命令结果的输出流
            InputStream fis = p.getInputStream();
            // 用一个读输出流类去读
            // 用缓冲器读行
            BufferedReader br = new BufferedReader(new InputStreamReader(fis,
                    "GB2312"));
            String line = null;
            // 直到读完为止
            int i = 0;
            while ((line = br.readLine()) != null)
            {
                if (line.contains("X.509"))
                { // 解析符合自己需要的內容，获取之后，直接返回。
                // System.out.println(line);
                    resultstr += line;
//                    break;
                }
                i++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return resultstr.replace("      X.509, ", "");
    }
}
