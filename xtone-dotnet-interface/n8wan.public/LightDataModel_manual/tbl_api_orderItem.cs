﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LightDataModel
{
    /// <summary>
    /// tbl_api_order_201511数据模型
    /// </summary>
    public partial class tbl_api_orderItem : Shotgun.Model.Logical.DynamicDataItem
    {
        /// <summary>
        /// 数据表字段列表对像
        /// </summary>
        public sealed class Fields
        {
            private Fields() { }
            #region 表字段名称

            public const string id = "id";
            ///<summary>
            ///主键
            ///</summary>
            public const string PrimaryKey = "id";


            public const string cp_id = "cp_id";
            /// <summary>
            /// daily_config.tbl_trone.id,具体的计费通道,金额
            /// </summary>
            public const string trone_id = "trone_id";

            public const string imsi = "imsi";

            public const string imei = "imei";

            public const string mobile = "mobile";

            public const string lac = "lac";

            public const string cid = "cid";

            public const string ExtrData = "ExtrData";
            /// <summary>
            /// sp产生的订单号，通常在二次http或回传时匹配使用
            /// </summary>
            public const string sp_linkid = "sp_linkid";

            public const string sp_exField = "sp_exField";

            public const string cp_verifyCode = "cp_verifyCode";
            /// <summary>
            /// 首次请求的时间
            /// </summary>
            public const string FirstDate = "FirstDate";
            /// <summary>
            /// 二次请求时间
            /// </summary>
            public const string SecondDate = "SecondDate";
            /// <summary>
            /// 一次指令，上行端口
            /// </summary>
            public const string port = "port";
            /// <summary>
            /// 一次指令，上行指令
            /// </summary>
            public const string msg = "msg";
            /// <summary>
            /// 此会传递给SP，并在SP计费成功时回传。用于api回传匹配使用透传参数
            /// </summary>
            public const string api_exdata = "api_exdata";
            /// <summary>
            /// 0:API反回成功 1:验证码下发成功 2:屏蔽地区 3:操作超时 4:取指令失败
            /// </summary>
            public const string state = "state";
            /// <summary>
            /// 是否直接扣量
            /// </summary>
            public const string is_hidden = "is_hidden";

            #endregion

        }
        #region 表字段变量定义

        private int _cp_id;
        /// <summary>
        /// daily_config.tbl_trone.id,具体的计费通道,金额
        /// </summary>
        private int _trone_id;

        private string _imsi;

        private string _imei;

        private string _mobile;

        private int _lac;

        private int _cid;

        private string _ExtrData;
        /// <summary>
        /// sp产生的订单号，通常在二次http或回传时匹配使用
        /// </summary>
        private string _sp_linkid;

        private string _sp_exField;

        private string _cp_verifyCode;
        /// <summary>
        /// 首次请求的时间
        /// </summary>
        private DateTime _FirstDate;
        /// <summary>
        /// 二次请求时间
        /// </summary>
        private DateTime _SecondDate;
        /// <summary>
        /// 一次指令，上行端口
        /// </summary>
        private string _port;
        /// <summary>
        /// 一次指令，上行指令
        /// </summary>
        private string _msg;
        /// <summary>
        /// 此会传递给SP，并在SP计费成功时回传。用于api回传匹配使用透传参数
        /// </summary>
        private string _api_exdata;
        /// <summary>
        /// 0:API反回成功 1:验证码下发成功 2:屏蔽地区 3:操作超时 4:取指令失败
        /// </summary>
        private string _state;
        /// <summary>
        /// 是否直接扣量
        /// </summary>
        private string _is_hidden;

        #endregion

        public override string IdentifyField { get { return identifyField; } }

        public static readonly string identifyField = "id";


        public int id
        {
            get;
            set;
        }

        #region 表字段值存取

        public int cp_id
        {
            get { return this._cp_id; }
            set
            {
#if true && true
                RemoveNullFlag(Fields.cp_id);
#elif !true
			    if (value == null)
                    SetNullFlag(Fields.cp_id);
                else
                    RemoveNullFlag(Fields.cp_id);
#endif

                SetFieldHasUpdate(Fields.cp_id, this._cp_id, value);
                this._cp_id = value;
            }
        }
        /// <summary>
        /// daily_config.tbl_trone.id,具体的计费通道,金额
        /// </summary>
        public int trone_id
        {
            get { return this._trone_id; }
            set
            {
#if true && true
                RemoveNullFlag(Fields.trone_id);
#elif !true
			    if (value == null)
                    SetNullFlag(Fields.trone_id);
                else
                    RemoveNullFlag(Fields.trone_id);
#endif

                SetFieldHasUpdate(Fields.trone_id, this._trone_id, value);
                this._trone_id = value;
            }
        }

        public string imsi
        {
            get { return this._imsi; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.imsi);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.imsi);
                else
                    RemoveNullFlag(Fields.imsi);
#endif

                SetFieldHasUpdate(Fields.imsi, this._imsi, value);
                this._imsi = value;
            }
        }

        public string imei
        {
            get { return this._imei; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.imei);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.imei);
                else
                    RemoveNullFlag(Fields.imei);
#endif

                SetFieldHasUpdate(Fields.imei, this._imei, value);
                this._imei = value;
            }
        }

        public string mobile
        {
            get { return this._mobile; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.mobile);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.mobile);
                else
                    RemoveNullFlag(Fields.mobile);
#endif

                SetFieldHasUpdate(Fields.mobile, this._mobile, value);
                this._mobile = value;
            }
        }

        public int lac
        {
            get { return this._lac; }
            set
            {
#if true && true
                RemoveNullFlag(Fields.lac);
#elif !true
			    if (value == null)
                    SetNullFlag(Fields.lac);
                else
                    RemoveNullFlag(Fields.lac);
#endif

                SetFieldHasUpdate(Fields.lac, this._lac, value);
                this._lac = value;
            }
        }

        public int cid
        {
            get { return this._cid; }
            set
            {
#if true && true
                RemoveNullFlag(Fields.cid);
#elif !true
			    if (value == null)
                    SetNullFlag(Fields.cid);
                else
                    RemoveNullFlag(Fields.cid);
#endif

                SetFieldHasUpdate(Fields.cid, this._cid, value);
                this._cid = value;
            }
        }

        public string ExtrData
        {
            get { return this._ExtrData; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.ExtrData);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.ExtrData);
                else
                    RemoveNullFlag(Fields.ExtrData);
#endif

                SetFieldHasUpdate(Fields.ExtrData, this._ExtrData, value);
                this._ExtrData = value;
            }
        }
        /// <summary>
        /// sp产生的订单号，通常在二次http或回传时匹配使用
        /// </summary>
        public string sp_linkid
        {
            get { return this._sp_linkid; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.sp_linkid);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.sp_linkid);
                else
                    RemoveNullFlag(Fields.sp_linkid);
#endif

                SetFieldHasUpdate(Fields.sp_linkid, this._sp_linkid, value);
                this._sp_linkid = value;
            }
        }

        public string sp_exField
        {
            get { return this._sp_exField; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.sp_exField);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.sp_exField);
                else
                    RemoveNullFlag(Fields.sp_exField);
#endif

                SetFieldHasUpdate(Fields.sp_exField, this._sp_exField, value);
                this._sp_exField = value;
            }
        }

        public string cp_verifyCode
        {
            get { return this._cp_verifyCode; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.cp_verifyCode);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.cp_verifyCode);
                else
                    RemoveNullFlag(Fields.cp_verifyCode);
#endif

                SetFieldHasUpdate(Fields.cp_verifyCode, this._cp_verifyCode, value);
                this._cp_verifyCode = value;
            }
        }
        /// <summary>
        /// 首次请求的时间
        /// </summary>
        public DateTime FirstDate
        {
            get { return this._FirstDate; }
            set
            {
#if true && true
                RemoveNullFlag(Fields.FirstDate);
#elif !true
			    if (value == null)
                    SetNullFlag(Fields.FirstDate);
                else
                    RemoveNullFlag(Fields.FirstDate);
#endif

                SetFieldHasUpdate(Fields.FirstDate, this._FirstDate, value);
                this._FirstDate = value;
            }
        }
        /// <summary>
        /// 二次请求时间
        /// </summary>
        public DateTime SecondDate
        {
            get { return this._SecondDate; }
            set
            {
#if true && true
                RemoveNullFlag(Fields.SecondDate);
#elif !true
			    if (value == null)
                    SetNullFlag(Fields.SecondDate);
                else
                    RemoveNullFlag(Fields.SecondDate);
#endif

                SetFieldHasUpdate(Fields.SecondDate, this._SecondDate, value);
                this._SecondDate = value;
            }
        }
        /// <summary>
        /// 一次指令，上行端口
        /// </summary>
        public string port
        {
            get { return this._port; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.port);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.port);
                else
                    RemoveNullFlag(Fields.port);
#endif

                SetFieldHasUpdate(Fields.port, this._port, value);
                this._port = value;
            }
        }
        /// <summary>
        /// 一次指令，上行指令
        /// </summary>
        public string msg
        {
            get { return this._msg; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.msg);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.msg);
                else
                    RemoveNullFlag(Fields.msg);
#endif

                SetFieldHasUpdate(Fields.msg, this._msg, value);
                this._msg = value;
            }
        }
        /// <summary>
        /// 此会传递给SP，并在SP计费成功时回传。用于api回传匹配使用透传参数
        /// </summary>
        public string api_exdata
        {
            get { return this._api_exdata; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.api_exdata);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.api_exdata);
                else
                    RemoveNullFlag(Fields.api_exdata);
#endif

                SetFieldHasUpdate(Fields.api_exdata, this._api_exdata, value);
                this._api_exdata = value;
            }
        }
        /// <summary>
        /// 0:API反回成功 1:验证码下发成功 2:屏蔽地区 3:操作超时 4:取指令失败
        /// </summary>
        public string state
        {
            get { return this._state; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.state);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.state);
                else
                    RemoveNullFlag(Fields.state);
#endif

                SetFieldHasUpdate(Fields.state, this._state, value);
                this._state = value;
            }
        }
        /// <summary>
        /// 是否直接扣量
        /// </summary>
        public string is_hidden
        {
            get { return this._is_hidden; }
            set
            {
#if false && true
				RemoveNullFlag(Fields.is_hidden);
#elif !false
                if (value == null)
                    SetNullFlag(Fields.is_hidden);
                else
                    RemoveNullFlag(Fields.is_hidden);
#endif

                SetFieldHasUpdate(Fields.is_hidden, this._is_hidden, value);
                this._is_hidden = value;
            }
        }

        #endregion
        #region 空值相关方法
        protected override string[] GetNullableFields()
        {
            return new string[]{null
			,"cp_id"
,"trone_id"
,"imsi"
,"imei"
,"mobile"
,"lac"
,"cid"
,"ExtrData"
,"sp_linkid"
,"sp_exField"
,"cp_verifyCode"
,"FirstDate"
,"SecondDate"
,"port"
,"msg"
,"api_exdata"
,"state"
,"is_hidden"
};
        }
        public bool Iscp_idNull() { return IsNull(Fields.cp_id); }

        public void Setcp_idNull() { SetNull(Fields.cp_id); }
        public bool Istrone_idNull() { return IsNull(Fields.trone_id); }

        public void Settrone_idNull() { SetNull(Fields.trone_id); }
        public bool IsimsiNull() { return IsNull(Fields.imsi); }

        public void SetimsiNull() { SetNull(Fields.imsi); }
        public bool IsimeiNull() { return IsNull(Fields.imei); }

        public void SetimeiNull() { SetNull(Fields.imei); }
        public bool IsmobileNull() { return IsNull(Fields.mobile); }

        public void SetmobileNull() { SetNull(Fields.mobile); }
        public bool IslacNull() { return IsNull(Fields.lac); }

        public void SetlacNull() { SetNull(Fields.lac); }
        public bool IscidNull() { return IsNull(Fields.cid); }

        public void SetcidNull() { SetNull(Fields.cid); }
        public bool IsExtrDataNull() { return IsNull(Fields.ExtrData); }

        public void SetExtrDataNull() { SetNull(Fields.ExtrData); }
        public bool Issp_linkidNull() { return IsNull(Fields.sp_linkid); }

        public void Setsp_linkidNull() { SetNull(Fields.sp_linkid); }
        public bool Issp_exFieldNull() { return IsNull(Fields.sp_exField); }

        public void Setsp_exFieldNull() { SetNull(Fields.sp_exField); }
        public bool Iscp_verifyCodeNull() { return IsNull(Fields.cp_verifyCode); }

        public void Setcp_verifyCodeNull() { SetNull(Fields.cp_verifyCode); }
        public bool IsFirstDateNull() { return IsNull(Fields.FirstDate); }

        public void SetFirstDateNull() { SetNull(Fields.FirstDate); }
        public bool IsSecondDateNull() { return IsNull(Fields.SecondDate); }

        public void SetSecondDateNull() { SetNull(Fields.SecondDate); }
        public bool IsportNull() { return IsNull(Fields.port); }

        public void SetportNull() { SetNull(Fields.port); }
        public bool IsmsgNull() { return IsNull(Fields.msg); }

        public void SetmsgNull() { SetNull(Fields.msg); }
        public bool Isapi_exdataNull() { return IsNull(Fields.api_exdata); }

        public void Setapi_exdataNull() { SetNull(Fields.api_exdata); }
        public bool IsstateNull() { return IsNull(Fields.state); }

        public void SetstateNull() { SetNull(Fields.state); }
        public bool Isis_hiddenNull() { return IsNull(Fields.is_hidden); }

        public void Setis_hiddenNull() { SetNull(Fields.is_hidden); }

        #endregion

    }
}