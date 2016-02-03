using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace n8wan.Public
{
    public static class Library
    {
        /// <summary>
        /// 通配符转正则，默认不区分大小写
        /// </summary>
        /// <param name="mStr"></param>
        /// <returns></returns>
        public static Regex GetRegex(string mStr)
        {
            mStr = Regex.Replace(mStr, @"([\.\[\]\|\^\$\<\>])", @"\$1");

            if (mStr.Contains('?'))
                mStr = Regex.Replace(mStr, @"\?{1,}", e => string.Format(".{{0,{0}}}", e.Value.Length));

            if (mStr.Contains('*'))
                mStr = Regex.Replace(mStr, @"\*{1,}", ".{0,}");
            mStr = "^" + mStr + "$";
            return new Regex(mStr, RegexOptions.IgnoreCase);
        }


        /// <summary>
        ///反编译IMSI获得手机前7位 判断区域
        /// </summary>
        ///<param name="imsi">Imsi</param>
        public static string GetPhoneByImsi(string imsi)
        {

            string strReturn = null;
            const string s56789 = "56789";

            if (imsi == null || imsi.Length < 10)
            {
                return null;
            }
            string h0;
            string h1;
            string h2;
            string h3;
            if (imsi.StartsWith("46000"))
            {

                h1 = imsi.Substring(5, 1);
                h2 = imsi.Substring(6, 1);
                h3 = imsi.Substring(7, 1);
                var st = imsi.Substring(8, 1);
                h0 = imsi.Substring(9, 1);

                if (s56789.IndexOf(st, StringComparison.Ordinal) >= 0)
                {
                    var tempStrReturn = "13" + st + "0" + h1 + h2 + h3;
                    strReturn = tempStrReturn;
                }
                else
                {
                    var tempint = (int.Parse(st) + 5);
                    var tempStrReturn = "13" + tempint + h0 + h1 + h2 + h3;
                    strReturn = tempStrReturn;
                }
            }
            else
            {
                string strDigit;
                if (imsi.StartsWith("46002"))
                {
                    strDigit = imsi.Substring(5, 1);
                    h0 = imsi.Substring(6, 1);
                    h1 = imsi.Substring(7, 1);
                    h2 = imsi.Substring(8, 1);
                    h3 = imsi.Substring(9, 1);
                    if (strDigit.Equals("0"))
                    {
                        var tempStrReturn = "134" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("1"))
                    {
                        var tempStrReturn = "151" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("2"))
                    {
                        var tempStrReturn = "152" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("3"))
                    {
                        var tempStrReturn = "150" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("7"))
                    {
                        var tempStrReturn = "187" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("8"))
                    {
                        var tempStrReturn = "158" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("9"))
                    {
                        var tempStrReturn = "159" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                }
                else if (imsi.StartsWith("46007"))
                {
                    strDigit = imsi.Substring(5, 1);
                    h0 = imsi.Substring(6, 1);
                    h1 = imsi.Substring(7, 1);
                    h2 = imsi.Substring(8, 1);
                    h3 = imsi.Substring(9, 1);
                    if (strDigit.Equals("7"))
                    {
                        var tempStrReturn = "157" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("8"))
                    {
                        var tempStrReturn = "188" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("9"))
                    {
                        var tempStrReturn = "147" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                }
                else if (imsi.StartsWith("46001"))
                {
                    //中国联通，只有46001这一个IMSI号码段
                    h1 = imsi.Substring(5, 1);
                    h2 = imsi.Substring(6, 1);
                    h3 = imsi.Substring(7, 1);
                    h0 = imsi.Substring(8, 1);
                    strDigit = imsi.Substring(9, 1);	//for A
                    if (strDigit.Equals("0") || strDigit.Equals("1"))
                    {
                        var tempStrReturn = "130" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("9"))
                    {
                        var tempStrReturn = "131" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("2"))
                    {
                        var tempStrReturn = "132" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("4"))
                    {
                        var tempStrReturn = "155" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("3"))
                    {
                        var tempStrReturn = "156" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                    else if (strDigit.Equals("6"))
                    {
                        var tempStrReturn = "186" + h0 + h1 + h2 + h3;
                        strReturn = tempStrReturn;
                    }
                }
                else
                {
                    strReturn = "";
                }
            }
            return strReturn;
        }


        /// <summary>
        /// 下载远程代码
        /// </summary>
        /// <param name="url">目标网址</param>
        /// <param name="postdata">post数据,NULL时为GET</param>
        /// <param name="timeout">超时时间,单位为毫秒,默认:3秒</param>
        /// <param name="encode">编码方式,默认utf8</param>
        /// <returns></returns>
        public static string DownloadHTML(string url, string postdata, int timeout, string encode)
        {

            Encoding ec = null;
            if (string.IsNullOrEmpty(encode))
                ec = ASCIIEncoding.UTF8;
            else
                ec = ASCIIEncoding.GetEncoding(encode);

            System.Net.HttpWebRequest web = null;
            web = (System.Net.HttpWebRequest)System.Net.WebRequest.Create(url);
            System.Net.HttpWebResponse rsp = null;
            web.Timeout = timeout < 1 ? 2888 : timeout;
            web.AllowAutoRedirect = false;
            web.AutomaticDecompression = System.Net.DecompressionMethods.GZip;
            web.ServicePoint.UseNagleAlgorithm = false;

            if (postdata != null)
            {
                web.ServicePoint.Expect100Continue = false;
                web.Method = "POST";
                var bin = ec.GetBytes(postdata);
                using (var stm = web.GetRequestStream())
                {
                    stm.Write(bin, 0, bin.Length);
                }
            }

            try
            {
                rsp = (System.Net.HttpWebResponse)web.GetResponse();
            }
            catch
            {
                return null;
            }
            try
            {
                using (var stm = rsp.GetResponseStream())
                {
                    using (var rd = new System.IO.StreamReader(stm, ec))
                        return rd.ReadToEnd();
                }
            }
            catch //(Exception ex)
            {
                //msg = ex.Message;
            }
            return null;

        }

    }
}
