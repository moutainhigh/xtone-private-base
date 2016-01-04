using System;
using System.Collections.Generic;
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

    }
}
