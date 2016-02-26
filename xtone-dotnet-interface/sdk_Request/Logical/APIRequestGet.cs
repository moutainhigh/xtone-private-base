﻿using LightDataModel;
using Newtonsoft.Json.Linq;
using sdk_Request.Model;
using Shotgun.Model.Filter;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace sdk_Request.Logical
{
    public abstract class APIRequestGet : Shotgun.PagePlus.SimpleHttpHandler<Shotgun.Database.MySqlDBClass>, Shotgun.Model.Logical.ILogical
    {
        private sdk_Request.Model.APIRquestModel _aqm;
        /// <summary>
        /// sp通道业务
        /// </summary>
        private LightDataModel.tbl_sp_troneItem _trone;
        private string _errMsg;
        private API_ERROR _errCode;
        private LightDataModel.tbl_trone_paycodeItem _paymodel;
        private StringBuilder _sbLog;

        /// <summary>
        ///写日志的文件锁对像
        /// </summary>
        private static object logLocker = new object();

        public override void BeginProcess()
        {
            Response.ContentType = "text/plain";
            //var ticks = new System.Diagnostics.Stopwatch();
            //ticks.Start();
            if (!OnInit())
            {
                WriteError();
                return;
            }

            Model.SP_RESULT result = null;
            try
            {
                result = GetSpCmd();
            }
            catch (WebException webex)
            {
                SetError(API_ERROR.INNER_ERROR, webex.Message);
                WriteLog(webex.ToString());
            }
#if !DEBUG
            catch (Exception ex)
            {
                WriteLog(ex.ToString());
                SetError(API_ERROR.INNER_ERROR, "内部错误");
            }
#endif
            finally { }

            if (result == null)
            {
                WriteError();
            }
            else
            {
                if (_aqm == null)
                {
                    Response.Write("{}");
                    return;
                }
                _aqm.status = result.status;// == API_ERROR.OK ? API_ERROR.OK : API_ERROR.UNKONW_ERROR;
                var json = new Model.APIResponseModel(result, _aqm);

                Response.Write(json.ToJson());
            }
            FlushLog();


        }

        //protected virtual Model.SP_RESULT GetSpCmd()
        //{
        //    if (PayModel == null)
        //    {
        //        SetError(API_ERROR.INNER_CONFIG_ERROR, "PayModel未配置");
        //        return null;
        //    }
        //    var url = "http://218.242.153.106:4000/mgdmv1a/cert?ppid=" + PayModel.paycode + "&imei=357513056367783&imsi=460028602777674";
        //    WriteLog(url);
        //    using (var wc = new WebClient())
        //    {
        //        wc.Encoding = ASCIIEncoding.UTF8;
        //        var html = wc.DownloadString(url);
        //        WriteLog(html);
        //        var ar = html.Split(new string[] { "##" }, StringSplitOptions.RemoveEmptyEntries);
        //        if (ar[0] != "100")
        //        {
        //            SetError(API_ERROR.UNKONW_ERROR, ar[0]);
        //            return null;
        //        }

        //        var result = new Model.SP_SMS_Result();
        //        result.port = "1065842230";
        //        result.msg = ar[1];
        //        result.SMSType = E_SMS_TYPE.Data;
        //        _aqm.spLinkId = ar[2];
        //        result.status = API_ERROR.OK;
        //        return result;
        //    }

        //}

        protected abstract Model.SP_RESULT GetSpCmd();

        bool OnInit()
        {
            if (!InitOrder())
                return SetError("初始化错误");

            return true;

        }

        /// <summary>
        /// 计费点
        /// </summary>
        protected LightDataModel.tbl_trone_paycodeItem PayModel
        {
            get
            {
                if (_paymodel != null)
                    return _paymodel;
                var l = LightDataModel.tbl_trone_paycodeItem.GetQueries(dBase);
                if (OrderInfo.troneId > 0)
                    l.Filter.AndFilters.Add(LightDataModel.tbl_trone_paycodeItem.Fields.trone_id, OrderInfo.troneId);
                else
                {
                    var tf = new TableFilter(tbl_trone_paycodeItem.Fields.trone_id, tbl_trone_orderItem.tableName, tbl_trone_orderItem.Fields.trone_id);
                    l.Filter.AndFilters.Add(tf);
                    tf.TableFilters.AndFilters.Add(tbl_trone_orderItem.Fields.id, _aqm.tbl_trone_order_id);
                }
                _paymodel = l.GetRowByFilters();
                if (_paymodel == null)
                {
                    WriteLog("paycode 获取失败，请检查paycode是否有配置");
                    //WriteLog(l.LastSqlExecute);
                }
                return _paymodel;
            }
        }


        private bool InitOrder()
        {
#if DEBUG
            _aqm = new Model.APIRquestModel()
            {
                cid = 123,
                imei = "866568022922909",
                imsi = "460023192787105",
                price = 1000,
                lac = 456,
                mobile = "13570830935",

            };
#else
            if (Request.TotalBytes < 10)
                return false;

            var bin = new byte[Request.TotalBytes];
            Request.InputStream.Read(bin, 0, Request.TotalBytes);
            WriteLog("Request:" + ASCIIEncoding.UTF8.GetString(bin));
            Request.InputStream.Seek(0, SeekOrigin.Begin);

            _aqm = Shotgun.Library.Static.JsonParser<sdk_Request.Model.APIRquestModel>(Request.InputStream);

#endif
            return true;
        }

        //bool CheckOrder()
        //{
        //    _trone = LightDataModel.tbl_sp_troneItem.GetRowById(dBase, _aqm.spTroneId);
        //    if (_trone == null)
        //        return SetError(API_ERROR.TRONE_NOT_FOUND);

        //    var fields = _trone.api_fields.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);

        //    string err = string.Empty;
        //    foreach (var f in fields)
        //    {
        //        #region 字段检查
        //        switch (f.ToLower())
        //        {
        //            case "imsi":
        //            case "imei":
        //            case "mobile":
        //            case "lac":
        //            case "cid":
        //            case "":
        //            default:
        //                break;
        //        }
        //        #endregion
        //    }
        //    if (string.IsNullOrEmpty(err))
        //    {
        //        SetError(API_ERROR.FIELD_MISS, err.Substring(1));
        //        return false;
        //    }


        //    return true;
        //}

        protected APIRquestModel OrderInfo { get { return _aqm; } }

        #region 基础服务
        public bool SetError(API_ERROR Error)
        {
            SetError(Error, Error.ToString());
            return false;
        }
        public bool SetError(string Msg)
        {
            SetError(API_ERROR.UNKONW_ERROR, Msg);
            return false;
        }
        protected bool SetError(API_ERROR Error, string msg)
        {
            _errCode = Error;
            _errMsg = msg;
            return false;
        }

        private void WriteError()
        {
            var sp = new Model.APIResponseModel(new Model.SP_RESULT() { status = _errCode, description = _errMsg }, _aqm);
            Response.Write(sp.ToJson());
        }

        public string ErrorMesage { get { return _errMsg; } }

        public bool IsSuccess { get; private set; }



        /// <summary>
        /// 写入日志文件
        /// </summary>
        private void FlushLog()
        {
            if (_sbLog == null || _sbLog.Length == 0)
                return;
            string FileName = string.Format("~/splog/{0:yyyyMMdd}/{1:4000}-{0:HH}.log", DateTime.Now, _aqm.tbl_sp_trone_api_id);
            var fi = new FileInfo(Request.MapPath(FileName));
            if (!fi.Directory.Exists)
                fi.Directory.Create();
            lock (logLocker)
            {
                using (var stm = new StreamWriter(fi.FullName, true))
                {
                    stm.WriteLine(_sbLog.ToString());
                }
            }
            _sbLog.Clear();
        }

        protected void WriteLog(string msg)
        {
            if (_sbLog == null)
                _sbLog = new StringBuilder();
            else if (_sbLog.Length != 0)
                _sbLog.AppendLine();
            _sbLog.AppendFormat("{0:HH:mm:ss.fff} {1}", DateTime.Now, msg);
        }



        int Shotgun.Model.Logical.ILogical.lastUpdateCount { get { throw new NotImplementedException(); } }

        Shotgun.Database.IBaseDataClass2 Shotgun.Model.Logical.ILogical.dBase { get { throw new NotImplementedException(); } set { throw new NotImplementedException(); } }
        #endregion

        #region 远程HTML获取

        /// <summary>
        /// 下载远程代码,带日志
        /// </summary>
        /// <param name="url">目标网址</param>
        /// <param name="timeout">超时时间,单位为毫秒,默认3秒</param>
        /// <param name="encode">编码方式,utf8/gb2312等,默认utf8</param>
        /// <returns></returns>
        protected string GetHTML(string url, int timeout, string encode)
        {
            return DownloadHTML(url, null, timeout, encode);
        }

        /// <summary>
        /// 下载远程代码 utf8/3秒超时 ,带日志
        /// </summary>
        /// <param name="url">目标网址</param>
        /// <param name="timeout">超时时间,单位为毫秒,默认3秒</param>
        /// <param name="encode">编码方式,utf8/gb2312等,默认utf8</param>
        /// <returns></returns>
        protected string GetHTML(string url)
        {
            return DownloadHTML(url, null, 0, null);
        }

        /// <summary>
        /// 下载远程代码,带日志
        /// </summary>
        /// <param name="url">目标网址</param>
        /// <param name="timeout">超时时间,单位为毫秒,默认3秒</param>
        /// <param name="encode">编码方式,utf8/gb2312等,默认utf8</param>
        /// <param name="data">传送的字符串</param>
        /// <returns></returns>
        protected string PostHTML(string url, string data, int timeout, string encode)
        {
            return DownloadHTML(url, data ?? string.Empty, timeout, encode);
        }

        /// <summary>
        /// 下载远程代码 utf8/3秒超时,带日志
        ///</summary>
        protected string PostHTML(string url, string data)
        {
            return DownloadHTML(url, data ?? string.Empty, 0, null);
        }

        protected string DownloadHTML(string url, string postdata, int timeout, string encode)
        {
            Stopwatch st = new Stopwatch();
            st.Start();
            string html = null;
            try
            {
                WriteLog(url);
                if (!String.IsNullOrEmpty(postdata))
                    WriteLog(postdata);
                html = n8wan.Public.Library.DownloadHTML(url, postdata, timeout, encode);
                return html;
            }
            catch (Exception ex)
            {
                html = ex.ToString();
                throw;
            }
            finally
            {
                st.Stop();
                WriteLog(string.Format("+{0}ms {1}", st.ElapsedMilliseconds, html));
            }

        }
        #endregion
    }
}
