package com.wns2.util;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wns.location.ip.IPLocation;
import com.wns.location.ip.IPSeeker;
import com.wns.push.bean.SmsCenter;
import com.wns.push.dao.SmsCenterDao;
import com.wns.push.util.WnsCacheFactory;
import com.wns2.log.WnsLog;

public class WnsUtil
{
    private static final List<String>    EXCLUDE_METHODS = new ArrayList<String>();

    static
    {
        EXCLUDE_METHODS.add("getHibernateLazyInitializer");
        EXCLUDE_METHODS.add("getCallbacks");
        EXCLUDE_METHODS.add("getClass");
    }

    public static final SimpleDateFormat sdf1            = new SimpleDateFormat(
                                                                 "yyyyMMdd");
    public static final SimpleDateFormat sdf2            = new SimpleDateFormat(
                                                                 "yyyyMMddHHmmss");
    public static final SimpleDateFormat sdf3            = new SimpleDateFormat(
                                                                 "yyyy年MM月dd日");
    public static final SimpleDateFormat sdf4            = new SimpleDateFormat(
                                                                 "yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat sdf5            = new SimpleDateFormat(
                                                                 "yyyy");

    public static final SimpleDateFormat sdf6            = new SimpleDateFormat(
                                                                 "MM/dd");
    public static final SimpleDateFormat sdf7            = new SimpleDateFormat(
                                                                 "yyyy.MM.dd");

    private static SimpleDateFormat getSdf(int type)
    {
        switch (type)
        {
            case 1:
                return sdf1;
            case 2:
                return sdf2;
            case 3:
                return sdf3;
            case 4:
                return sdf4;
            case 5:
                return sdf5;
            case 6:
                return sdf6;
            case 7:
                return sdf7;
            default:
                return sdf1;
        }
    }

    public static int random(int low, int high)
    {
        return (int) (Math.random() * (high - low)) + low;
    }

    public static String age_list(String age)
    {

        try
        {

            if (!age.equals("") && !age.equals("未知"))
            {
                int age_int = Integer.parseInt(age);
                if (age_int >= 4 && age_int < 16)
                {
                    age = "5~15";
                }
                else if (age_int > 14 && age_int < 26)
                {
                    age = "15~25";
                }
                else if (age_int > 24 && age_int < 36)
                {
                    age = "25~35";
                }
                else if (age_int > 34 && age_int < 46)
                {
                    age = "35~45";
                }
                else if (age_int > 45 && age_int < 56)
                {
                    age = "45~55";
                }
                else if (age_int > 54 && age_int < 66)
                {
                    age = "55~65";
                }
                else
                {
                    age = "other";
                }
            }
        }
        catch (Exception e)
        {
            age = "other";
        }
        return age; 
    }

    public static int parseVersion(String version)
    {
        int ret = 0;
        try
        {
            String[] strs = version.split("\\.");
            ret = Integer.parseInt(strs[0]) * 1000;
            if (strs.length > 1)
            {
                ret += Integer.parseInt(strs[1]) * 100;
            }
            if (strs.length > 2)
            {
                ret += +Integer.parseInt(strs[2]);
            }
        }
        catch (Exception e)
        {

        }
        return ret;
    }

    public static long getUrlFileSize(String address)
    {
        try
        {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            long len = urlConnection.getContentLength();
            if (len <= 0)
            {
                return 0;
            }
            else
            {
                return len;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 时间段选择
     * 
     * // @param Url url //
     */
    //
    // /** @return
    // */
    // public static String[] s_day(String select_time)
    // {
    // String[] dayName = new String[2];
    // //C# TO JAVA CONVERTER NOTE: The following 'switch' operated on a string
    // member and was converted to Java 'if-else' logic:
    // // switch (select_time)
    // //ORIGINAL LINE: case "常用时间":
    // if (select_time.equals("常用时间"))
    // {
    // dayName[0] = new java.util.Date().ToShortDateString();
    // dayName[1] = new java.util.Date().ToShortDateString();
    // }
    // //ORIGINAL LINE: case "昨天":
    // else if (select_time.equals("昨天"))
    // {
    // dayName[0] = new java.util.Date().AddDays(-1).ToShortDateString();
    // dayName[1] = new java.util.Date().AddDays(-1).ToShortDateString();
    //
    // }
    // //ORIGINAL LINE: case "今天":
    // else if (select_time.equals("今天"))
    // {
    // dayName[0] = new java.util.Date().ToShortDateString();
    // dayName[1] = new java.util.Date().ToShortDateString();
    // }
    // //ORIGINAL LINE: case "上周":
    // else if (select_time.equals("上周"))
    // {
    // dayName[0] = new java.util.Date().AddDays(Double.parseDouble((0 -
    // Short.parseShort(new java.util.Date().DayOfWeek))) -
    // 6).ToShortDateString();
    // dayName[1] = new java.util.Date().AddDays(Double.parseDouble((6 -
    // Short.parseShort(new java.util.Date().DayOfWeek))) -
    // 6).ToShortDateString();
    // ;
    // }
    // //ORIGINAL LINE: case "本周":
    // else if (select_time.equals("本周"))
    // {
    // dayName[0] = new java.util.Date().AddDays(Double.parseDouble((0 -
    // Short.parseShort(new java.util.Date().DayOfWeek))) +
    // 1).ToShortDateString();
    // dayName[1] = new java.util.Date().AddDays(Double.parseDouble((6 -
    // Short.parseShort(new java.util.Date().DayOfWeek))) +
    // 1).ToShortDateString();
    // }
    // //ORIGINAL LINE: case "上月":
    // else if (select_time.equals("上月"))
    // {
    // dayName[0] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-MM-01"))).AddMonths(-1).ToShortDateString();
    // dayName[1] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-MM-01"))).AddDays(-1).ToShortDateString();
    // }
    // //ORIGINAL LINE: case "本月":
    // else if (select_time.equals("本月"))
    // {
    // dayName[0] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-MM-01"))).ToShortDateString();
    // dayName[1] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-MM-01"))).AddMonths(+1).AddDays(-1).ToShortDateString();
    // }
    // //ORIGINAL LINE: case "上季":
    // else if (select_time.equals("上季"))
    // {
    // dayName[0] = new java.util.Date().AddMonths(-3 - ((new
    // java.util.Date().Month - 1) % 3)).ToString("yyyy-MM-01");
    // dayName[1] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().AddMonths(0 - ((new java.util.Date().Month - 1) %
    // 3)).ToString("yyyy-MM-01"))).AddDays(-1).ToShortDateString();
    // }
    // //ORIGINAL LINE: case "本季":
    // else if (select_time.equals("本季"))
    // {
    // dayName[0] = new java.util.Date().AddMonths(-3 - ((new
    // java.util.Date().Month) % 3)).ToString("yyyy-MM-01");
    // dayName[1] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().AddMonths(0 - ((new java.util.Date().Month) %
    // 3)).ToString("yyyy-MM-01"))).AddDays(-1).ToShortDateString();
    // }
    // //ORIGINAL LINE: case "上半年":
    // else if (select_time.equals("上半年"))
    // {
    // dayName[0] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-01-01"))).ToShortDateString();
    // dayName[1] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-01-01"))).AddMonths(6).AddDays(-1).ToShortDateString();
    // }
    // //ORIGINAL LINE: case "下半年":
    // else if (select_time.equals("下半年"))
    // {
    // dayName[0] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-01-01"))).AddMonths(6).ToShortDateString();
    // dayName[1] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-01-01"))).AddMonths(12).AddDays(-1).ToShortDateString();
    // }
    // //ORIGINAL LINE: case "本年":
    // else if (select_time.equals("本年"))
    // {
    // dayName[0] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-01-01"))).ToShortDateString();
    // dayName[1] = new java.util.Date(java.util.Date.parse(new
    // java.util.Date().ToString("yyyy-01-01"))).AddYears(1).AddDays(-1).ToShortDateString();
    // }
    // return dayName;
    // }
    // //C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
    // ///#endregion
    //
    //
    // public static Object Json2Obj(String json, java.lang.Class t)
    // {
    // try
    // {
    // System.Runtime.Serialization.Json.DataContractJsonSerializer serializer =
    // new System.Runtime.Serialization.Json.DataContractJsonSerializer(t);
    // //C# TO JAVA CONVERTER NOTE: The following 'using' block is replaced by
    // its Java equivalent:
    // // using (MemoryStream ms = new
    // MemoryStream(Encoding.UTF8.GetBytes(json)))
    // MemoryStream ms = new MemoryStream(Encoding.UTF8.GetBytes(json));
    // try
    // {
    //
    // return serializer.ReadObject(ms);
    // }
    // finally
    // {
    // ms.dispose();
    // }
    // }
    // catch (java.lang.Exception e)
    // {
    // return null;
    // }
    // }

    // 字节换算
    private static final int MB = 1024 * 1024; // 定义MB的计算常量

    public static double ByteConversionMB(long KSize)
    {
        return Math.round(KSize / (float) MB); // 将其转换成MB

    }

    public static long genPushCmdId()
    {
        String id = new Date().getTime() + "" + random(0, 100);
        long cmdId = Long.parseLong(id);
        return cmdId;
    }

    /**
     * 检查IP地址格式
     * 
     * @param ip
     * @return
     */
    public static boolean IsIP(String ip)
    {
        Pattern pattern = Pattern
                .compile("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
        Matcher matcher = pattern.matcher(ip);
        boolean b = matcher.matches();
        return b;
        // return System.Text.RegularExpressions.Regex.IsMatch(ip,
        // "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
    }

 
    public static String getLocationBySms(String smsCenter)
    {

        String ret = null;
        if ((smsCenter != null) && !smsCenter.contains("null"))
        {
            if (smsCenter.startsWith("+86"))
            {
                smsCenter = smsCenter.substring(3);
            }
            else if (smsCenter.startsWith("86"))
            {
                smsCenter = smsCenter.substring(2);
            }
            else if (smsCenter.startsWith("086"))
            {
                smsCenter = smsCenter.substring(3);
            }
            
            String key = WnsUtil.class.getName() + "_smsCenter_" + smsCenter;
            Object obj = WnsCacheFactory.get(key);
            if (obj != null && obj instanceof String)
            {
                return (String) obj;
            }
            else
            {
                SmsCenterDao dao = (SmsCenterDao) WnsSpringHelper
                        .getBean("dsmsCenterDao");

                SmsCenter item;
                item = dao.findSingleBySmsCenter(smsCenter);
                if (item != null)
                {
                    if (item.getProvince().contains("广东")
                            && (item.getCity().contains("深圳")
                            || item.getCity().contains("广州")))
                    {
                        ret = item.getProvince() + item.getCity();
                    }
                    else
                    {
                        ret = item.getProvince();
                    }
                }
                else
                {
                    ret = "未知";
                }

                WnsCacheFactory.add(key, ret, WnsCacheFactory.ONE_MONTH);
                WnsLog.mobileLog(smsCenter + ":" + ret);
                return ret;
            }
        }
        return null;
    }
    
    /**
     * 获得当前星期一 数字形式
     * 
     * @param ip
     * @return
     */
    public static String weekstr()
    {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        int day_of_week = c.get(Calendar.DAY_OF_WEEK);
        return String.valueOf(day_of_week);
        // String weekstr = new java.util.Date().;
        //
        // if (weekstr.equals("Monday"))
        // {
        // weekstr = "1";
        // }
        // else if (weekstr.equals("Tuesday"))
        // {
        // weekstr = "2";
        // }
        // else if (weekstr.equals("Wednesday"))
        // {
        // weekstr = "3";
        // }
        // else if (weekstr.equals("Thursday"))
        // {
        // weekstr = "4";
        // }
        // else if (weekstr.equals("Friday"))
        // {
        // weekstr = "5";
        // }
        // else if (weekstr.equals("Saturday"))
        // {
        // weekstr = "6";
        // }
        // else if (weekstr.equals("Sunday"))
        // {
        // weekstr = "7";
        // }
        // return weekstr;

    }

    // public static SqlString getCity(SqlString ip)
    // {

    // string[] stringip = ip.Value.Split(new string[] { "." },
    // StringSplitOptions.None);
    // long ipnum = Convert.ToInt64((stringip[0])) * 16777216 +
    // Convert.ToInt64(stringip[1]) * 65536 + Convert.ToInt64(stringip[2]) * 256
    // + Convert.ToInt64(stringip[3]);

    // //connection.Open();
    // //SqlCommand cmd = new SqlCommand();
    // //cmd.Connection = connection;
    // //cmd.CommandType = System.Data.CommandType.Text;

    // cmd.CommandText = "Select top 1 city,province from ip_address where " +
    // ipnum.ToString() + ">=ip1 and " + ipnum.ToString() +
    // "<=ip2 order by id desc";
    // SqlDataReader dr = cmd.ExecuteReader();
    // using (dr)
    // {
    // if (dr.Read())
    // {
    // return dr.GetString(1);
    // }
    // else
    // {
    // return "未知";
    // }
    // }
    // }

    // System.Net.IPAddress ipaddress =
    // System.Net.IPAddress.Parse("72.167.124.214");
    // long dreamduip = ipaddress.Address;

    // public static void 上级统计(int 上级ID)//信息提醒框
    // {

    // xu_system.BLL.上级通道 s_bll = new xu_system.BLL.上级通道();
    // xu_system.Model.上级通道 s_model = new xu_system.Model.上级通道();
    // s_model = s_bll.GetModel(上级ID);
    // s_model.同步数 = s_model.同步数 + 1;
    // s_model.结算 = s_model.同步数 * Convert.ToDecimal(s_model.每条结算);
    // s_bll.Update(s_model);//同步统计

    // }
    // public static void 上级统计(int 上级ID,int ivr_num)//信息提醒框
    // {

    // xu_system.BLL.上级通道 s_bll = new xu_system.BLL.上级通道();
    // xu_system.Model.上级通道 s_model = new xu_system.Model.上级通道();
    // s_model = s_bll.GetModel(上级ID);
    // s_model.同步数 = s_model.同步数 + ivr_num;
    // s_model.结算 = s_model.同步数 * Convert.ToDecimal(s_model.每条结算);
    // s_bll.Update(s_model);//同步统计

    // }
    // public static void 下级统计(int 下级ID,bool bl)//信息提醒框
    // {

    // xu_system.BLL.下级通道 s_bll = new xu_system.BLL.下级通道();
    // xu_system.Model.下级通道 s_model = new xu_system.Model.下级通道();
    // s_model = s_bll.GetModel(下级ID);
    // s_model.成功数 = s_model.成功数 + 1;//结算数
    // if (bl == false)//为true就是不扣量
    // {
    // s_model.结算数 = s_model.结算数 + 1;
    // }
    // s_bll.Update(s_model);//同步统计

    // }

    // public static void 下级统计(int 下级ID, bool bl,int ivr_num)//信息提醒框
    // {

    // xu_system.BLL.下级通道 s_bll = new xu_system.BLL.下级通道();
    // xu_system.Model.下级通道 s_model = new xu_system.Model.下级通道();
    // s_model = s_bll.GetModel(下级ID);
    // s_model.成功数 = s_model.成功数 + ivr_num;//结算数
    // if (bl == false)//为true就是不扣量
    // {
    // s_model.结算数 = s_model.结算数 + ivr_num;
    // }
    // s_bll.Update(s_model);//同步统计

    // }

    // public static void 上级通道数统计(int ID)//信息提醒框
    // {
    // xu_system.BLL.上级公司 g_bll = new xu_system.BLL.上级公司();
    // xu_system.Model.上级公司 g_model = new xu_system.Model.上级公司();

    // g_model = g_bll.GetModel(ID);
    // xu_system.BLL.上级通道 t_bll = new xu_system.BLL.上级通道();
    // //int z_num = 0;
    // //int num = 0;
    // int z_num = t_bll.GetList("归属id=" + ID +
    // " and 通道状态='正常'").Tables[0].Rows.Count; //统计在用通道数资料
    // int num = t_bll.GetList("归属id=" + ID + " ").Tables[0].Rows.Count;
    // //统计全通道数资料

    // g_model.通道状态比 = z_num.ToString() + "/" + num.ToString();
    // g_bll.Update(g_model);

    // }
    // public static void 下级通道数统计(int ID)//信息提醒框
    // {
    // xu_system.BLL.上级通道 g_bll = new xu_system.BLL.上级通道();
    // xu_system.Model.上级通道 g_model = new xu_system.Model.上级通道();

    // g_model = g_bll.GetModel(ID);
    // xu_system.BLL.下级通道 t_bll = new xu_system.BLL.下级通道();
    // //int z_num = 0;
    // //int num = 0;
    // int z_num = t_bll.GetList("归属通道id=" + ID +
    // " and 状态=1 ").Tables[0].Rows.Count; //统计在用通道数资料
    // int num = t_bll.GetList("归属通道id=" + ID + " ").Tables[0].Rows.Count;
    // //统计全通道数资料

    // g_model.下级 = z_num.ToString() + "/" + num.ToString();
    // g_bll.Update(g_model);

    // }

    // public static void 下级公司通道数统计(int ID)//信息提醒框
    // {
    // xu_system.BLL.下级公司 g_bll = new xu_system.BLL.下级公司();
    // xu_system.Model.下级公司 g_model = new xu_system.Model.下级公司();

    // g_model = g_bll.GetModel(ID);
    // xu_system.BLL.下级通道 t_bll = new xu_system.BLL.下级通道();
    // //int z_num = 0;
    // //int num = 0;
    // int z_num = t_bll.GetList("归属下级ID=" + ID +
    // " and 状态=1 ").Tables[0].Rows.Count; //统计在用通道数资料
    // int num = t_bll.GetList("归属下级ID=" + ID + " ").Tables[0].Rows.Count;
    // //统计全通道数资料
    // g_model.通道数 = num;
    // g_model.通道比例 = z_num.ToString() + "/" + num.ToString();
    // g_bll.Update(g_model);

    // }

    // public static void 数据统计(string spid, string 上级ID, string 上级公司, int 下级ID,
    // string 下级公司, int 归属ID, string 通道号, string 指令, bool bl_type, string 结算金额,
    // string 手机号码, string 地区, string linkid)
    // {

    // ////////////////////////结算单处理///////////////////
    // xu_system.BLL.其他操作类 sb_bll = new xu_system.BLL.其他操作类();
    // xu_system.Model.结算单 sb_model = new xu_system.Model.结算单();
    // xu_system.BLL.结算单 sb_bll_l = new xu_system.BLL.结算单();
    // int 结算单ID;

    // sb_model = sb_bll.GetModel("通道号='" + 通道号 + "' and 日期='" +
    // DateTime.Now.ToShortDateString() + "' and 结算时间='待结算'");//获得
    // if (sb_model != null)//是否今天存在今天记录 无进行新记录添加
    // {
    // sb_model.同步数 = sb_model.同步数 + 1;//增加一记录
    // if (bl_type == false)
    // {
    // sb_model.成功条数 = sb_model.成功条数 + 1;
    // sb_model.结算金额 = (sb_model.结算金额 + Convert.ToDouble(结算金额)).ToString();
    // }
    // sb_bll_l.Update(sb_model);
    // 结算单ID = sb_model.id;

    // }
    // else
    // {
    // sb_model = new xu_system.Model.结算单();
    // //当日无记录做新记录处理
    // sb_model.成功条数 = 0;
    // sb_model.归属下级ID = 下级ID;
    // sb_model.归属下级公司 = 下级公司;
    // sb_model.日期 = DateTime.Now.ToShortDateString();
    // sb_model.通道号 = 通道号;
    // sb_model.同步数 = 1;
    // sb_model.成功条数 = 1;
    // sb_model.结算金额 = (sb_model.成功条数 * Convert.ToDouble(结算金额)).ToString();

    // 结算单ID = sb_bll_l.Add(sb_model);

    // }
    // ////////////////////////同步明细记录/////////////////
    // xu_system.BLL.同步明细 cp_bll = new xu_system.BLL.同步明细();
    // xu_system.Model.同步明细 cp_model = new xu_system.Model.同步明细();

    // cp_model.手机号码 = 手机号码;
    // cp_model.地区 = 地区;
    // cp_model.linkid = linkid;//获得URL
    // if (bl_type == false)
    // {
    // cp_model.二次同步 = "同步成功";
    // cp_model.一次同步 = "同步成功";
    // cp_model.状态 = true;
    // }
    // else
    // {
    // cp_model.一次同步 = "扣量";
    // cp_model.二次同步 = "扣量";
    // cp_model.状态 = false;

    // }
    // cp_model.归属公司 = 下级公司;
    // cp_model.结算单ID = 结算单ID;
    // cp_model.结算金额 = 结算金额;
    // cp_model.通道号 = 通道号;
    // cp_model.通道指令 = 指令;
    // cp_model.同步时间 = DateTime.Now;

    // // cp_model.一次同步 = "同步成功";
    // //cp_model.状态 = false;//扣量时显示扣量true

    // // sp_model.状态报告 = true;
    // //
    // cp_bll.Add(cp_model);//记录添加

    // /////////////////////////////////通道数据分布统计/////////////////////////

    // xu_system.BLL.通道数据分布统计 tds_bll = new xu_system.BLL.通道数据分布统计();
    // xu_system.Model.通道数据分布统计 tds_model = new xu_system.Model.通道数据分布统计();

    // if (地区.Substring(1, 1) != "<")//不为未知
    // {
    // tds_model = sb_bll.GetModel_F("归属通道名称='" + 通道号 + "'and 省份= '" +
    // 地区.Substring(0, 2) + "' and 城市 ='" + 地区.Substring(3, 2) + "'and 日期='" +
    // DateTime.Now.ToShortDateString() + "'");
    // if (tds_model != null)//通道为不空
    // {
    // tds_model.数量 = tds_model.数量 + 1;
    // tds_bll.Update(tds_model);

    // }
    // else
    // {
    // tds_model = new xu_system.Model.通道数据分布统计();
    // tds_model.城市 = 地区.Substring(3, 2);
    // tds_model.省份 = 地区.Substring(0, 2);
    // //tds_model.数量 = "1;
    // tds_model.数量 = 1;
    // tds_model.日期 = DateTime.Now.ToShortDateString();
    // tds_model.归属通道名称 = 通道号;
    // tds_model.归属公司 = 下级公司;
    // tds_model.归属公司ID = 下级ID;
    // tds_model.归属ID = 归属ID;
    // tds_bll.Add(tds_model);

    // }
    // }
    // else
    // {
    // tds_model = sb_bll.GetModel_F("归属通道名称='" + 通道号+ "'and 省份= '未知' and 日期='"
    // +
    // DateTime.Now.ToShortDateString() + "'");
    // if (tds_model != null)//通道为不空
    // {
    // tds_model.数量 = tds_model.数量 + 1;
    // tds_bll.Update(tds_model);

    // }
    // else
    // {
    // tds_model.城市 = "未知";
    // tds_model.省份 = "未知";
    // //tds_model.数量 = "1;
    // tds_model.数量 = 1;
    // tds_model.日期 = DateTime.Now.ToShortDateString();
    // tds_model.归属通道名称 = 通道号;
    // tds_model.归属公司 = 下级公司;
    // tds_model.归属公司ID = 下级ID;
    // tds_model.归属ID = 归属ID;
    // tds_bll.Add(tds_model);

    // }

    // }

    // //////////////利润统计(扣量未处理）//////////////
    // xu_system.BLL.利润统计 lr_bll = new xu_system.BLL.利润统计();
    // xu_system.Model.利润统计 lr_model = new xu_system.Model.利润统计();

    // lr_model = sb_bll.GetModel_li("通道号='" + 通道号 + "'and 下级公司= '" + 下级公司 +
    // "' and  datediff(d,cast('" + DateTime.Now.ToShortDateString() +
    // "'  as  datetime),统计日期)=0 ");
    // if (lr_model != null)//通道为不空
    // {
    // //扣量的未处理）（）（
    // lr_model.同步数量 = lr_model.同步数量 + 1;

    // if (bl_type == false)//不扣量计算
    // {
    // lr_model.结算数量 = lr_model.结算数量 + 1;
    // }
    // lr_model.同步金额 = (lr_model.同步数量 * Convert.ToDouble(结算金额)).ToString();
    // lr_model.结算金额 = (lr_model.结算数量 * Convert.ToDouble(结算金额)).ToString();
    // lr_model.盈利金额 = (Convert.ToDouble(lr_model.同步金额) -
    // Convert.ToDouble(lr_model.结算金额)).ToString();
    // lr_bll.Update(lr_model);

    // }
    // else
    // {
    // lr_model = new xu_system.Model.利润统计();
    // lr_model.归属下级ID = 下级ID.ToString();
    // lr_model.归属上级ID = 上级ID;
    // lr_model.同步数量 = 1;
    // lr_model.结算数量 = 1;
    // lr_model.同步金额 = (lr_model.同步数量 * Convert.ToDouble(结算金额)).ToString();
    // lr_model.结算金额 = (lr_model.结算数量 * Convert.ToDouble(结算金额)).ToString();
    // lr_model.盈利金额 = (Convert.ToDouble(lr_model.同步金额) -
    // Convert.ToDouble(lr_model.结算金额)).ToString();
    // lr_model.上级公司 = 上级公司;
    // lr_model.下级公司 = 下级公司;
    // lr_model.通道号 = 通道号;
    // //lr_model.统计日期 = DateTime.Now;
    // //lr_model.
    // lr_bll.Add(lr_model);

    // }
    // }

    // public static void 通道生成(string spid,int i)
    // {
    // string mbPath =
    // System.Web.HttpContext.Current.Server.MapPath("Receiver.htm");
    // string fileName = "Receiver.aspx";
    // if (i == 1)
    // {
    // mbPath = System.Web.HttpContext.Current.Server.MapPath("mr.htm");
    // fileName = "mr.aspx";
    // }

    // else if (i == 2)
    // {
    // mbPath = System.Web.HttpContext.Current.Server.MapPath("mo.htm");
    // fileName = "mo.aspx";
    // }
    // else if (i == 3)
    // {
    // mbPath = System.Web.HttpContext.Current.Server.MapPath("ivr.htm");
    // fileName = "ivr.aspx";

    // }

    // Encoding code = Encoding.GetEncoding("gb2312");
    // StreamReader sr = null;
    // StreamWriter sw = null;
    // string str = null;

    // //读取
    // try
    // {
    // sr = new StreamReader(mbPath, code);
    // str = sr.ReadToEnd();

    // }
    // catch (Exception ex)
    // {
    // throw ex;
    // }
    // finally
    // {
    // sr.Close();
    // }

    // //根据时间自动重命名，扩展名也可以自行修改
    // //string fileName = DateTime.Now.ToString("yyyyMMddHHmmss") + ".htm";

    // //生成静态文件
    // try
    // {
    // if
    // (!Directory.Exists(System.Web.HttpContext.Current.Server.MapPath("sp_html/"
    // +
    // spid + "/")))
    // {
    // Directory.CreateDirectory(System.Web.HttpContext.Current.Server.MapPath("sp_html/"
    // + spid + "/"));
    // }
    // sw = new
    // StreamWriter(System.Web.HttpContext.Current.Server.MapPath("sp_html/" +
    // spid
    // + "/") + fileName, false, code);
    // sw.Write(str);
    // sw.Flush();

    // }
    // catch (Exception ex)
    // {
    // throw ex;
    // }
    // finally
    // {
    // sw.Close();
    // // Response.Write("恭喜<a href=htm/" + fileName + " target=_blank>" +
    // fileName
    // + "</a>已经生成，保存在htm文件夹下！");
    // }

    // }

    public static Map<String, Object> modelToMap(final Object modelObj)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Method[] methods = modelObj.getClass().getMethods();
        Method method;
        for (int i = 0; i < methods.length; i++)
        {
            method = methods[i];
            fillFieldToMap(method, modelObj, map);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    private static void fillFieldToMap(final Method m, final Object obj,
            Map<String, Object> result)
    {
        String methodName = m.getName();
        try
        {
            if (methodName.startsWith("get") && methodName.length() > 3
                    && m.getParameterTypes().length == 0
                    && !EXCLUDE_METHODS.contains(methodName))
            {
                String key = String.valueOf(Character.toLowerCase(methodName
                        .charAt(3)));
                if (methodName.length() > 4)
                {
                    key += methodName.substring(4);
                }
                boolean isset = true;
                if (isset)
                {
                    Object valueObj = m.invoke(obj, new Object[] {});
                    if (valueObj != null)
                    {
                        String value = null;
                        if (valueObj instanceof Date)
                        {
                            value = sdf4.format((Date) valueObj);
                            result.put(key, value);
                        }
                        else if (valueObj instanceof List)
                        {
                            List<Map<String, Object>> retlist = new ArrayList<Map<String, Object>>();
                            List list = (List) valueObj;
                            for (int i = 0, size = list.size(); i < size; i++)
                            {
                                retlist.add(modelToMap(list.get(i)));
                            }
                            result.put(key, retlist);
                        }
                        else if (valueObj instanceof Boolean)
                        {
                            // boolean返回给前台是统一为1 true,0 false
                            if ((Boolean) valueObj)
                            {
                                value = "1";
                            }
                            else
                            {
                                value = "0";
                            }
                            result.put(key, value);
                        }
                        else
                        {
                            value = valueObj.toString();
                            result.put(key, value);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定格式的日期
     * 
     * @type 日期格式 1 yyyyMMdd 2yyyyMMddHHmmss 3yyyy年MM月dd日 4yyyy-MM-dd HH:mm:ss
     */
    public static String getDateFormatString(Date date, int type)
    {
        if (date == null)
        {
            return "";
        }
        SimpleDateFormat sdf = getSdf(type);
        try
        {
            return sdf.format(date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] NgsteamIntegerToInt(List<Integer> list)
    {
        if (list != null)
        {
            int[] ret = new int[list.size()];
            int index = 0;
            for (Integer i : list)
            {
                ret[index] = i;
                index++;
            }
            return ret;
        }
        return null;
    }
    
    
    public static String getProvinceByDat(String ip)
    {
    	try
    	{
    		return IPSeeker.getInstance().getArea(ip);
    	}catch(Exception e){
    		System.out.println("IP获取信息异常======>"+ip);
    		System.out.println("IP异常信息======>"+e.toString());
    	}
    	return "IP Exception";
   
    }
    /**
     * 更具IP返回省市
     * 
     * @param ip
     * @return
     */
    public static String getProvince(String ip)
    {
          return getProvinceByDat(ip);
//        String ret = null;
////        ip = "113.106.203.166";
//        String[] stringip = ip.split("\\.");
//        long ipnum = Long.parseLong((stringip[0])) * 16777216
//                + Long.parseLong(stringip[1]) * 65536
//                + Long.parseLong(stringip[2]) * 256
//                + Long.parseLong(stringip[3]);
//
//        String key = WnsUtil.class.getName() + "_ip_" + ipnum;
//        Object obj = WnsCacheFactory.get(key);
//        if (obj != null && obj instanceof String)
//        {
//            return (String) obj;
//        }
//        else
//        {
//            ipTableDao dao = (ipTableDao) WnsSpringHelper
//                    .getBean("dipTableDao");
//
//            IPTABLE ipTable;
//            ipTable = dao.fingByIp(ipnum);
//
//            // IpTableMongoDao dao = (IpTableMongoDao) NgsteamSpringHelper
//            // .getBean("dipTableMongoDao");
//            // IPTABLE ipTable = dao.getIpTableByIp(ipnum);
//            if (ipTable.getCountry() != null)
//            {
//                if (ipTable.getCountry().length() > 3
//                        && !ipTable.getCountry().contains("广东省深圳市")
//                        && !ipTable.getCountry().contains("广东省广州市"))
//                {
//                    ipTable.setCountry(ipTable.getCountry().substring(0, 3));
//                }
//                ret = ipTable.getCountry();
//            }
//            else
//            {
//                ret = "未知";
//            }
//
//            WnsCacheFactory.add(key, ret, WnsCacheFactory.ONE_MONTH);
//            return ret;
//        }

    }

}
