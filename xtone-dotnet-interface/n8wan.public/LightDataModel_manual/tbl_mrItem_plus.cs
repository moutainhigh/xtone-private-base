﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using n8wan.Public.Logical;

namespace LightDataModel
{
    partial class tbl_mrItem : n8wan.Public.Logical.ISMS_DataItem, ICPPushModel
    {
        public const string FIX_TABLE_NAME = "tbl_mr_";
        public const string SCHEMA = "daily_log";

        public tbl_mrItem()
        {
            Schema = "daily_log";
            base.SetTableName(DateTime.Today);

        }

        protected override string FixTableName
        {
            get { return FIX_TABLE_NAME; }
        }

        #region 静态方法
        /// <summary>
        /// 创建一个新的查询器
        /// </summary>
        public static Shotgun.Model.List.DynamicTableList<tbl_mrItem> GetQueries(Shotgun.Database.IBaseDataClass2 dBase)
        {
            return new Shotgun.Model.List.DynamicTableList<tbl_mrItem>(dBase, SCHEMA, FIX_TABLE_NAME);
        }

        /// <summary>
        /// 创建一个新的查询器
        /// </summary>
        public static Shotgun.Model.List.DynamicTableList<tbl_mrItem> GetQueries()
        {
            return new Shotgun.Model.List.DynamicTableList<tbl_mrItem>(null, SCHEMA, FIX_TABLE_NAME);

        }

        /// <summary>
        /// 根据主键查找指定的行,返回指定字段
        /// </summary>
        /// <param name="dBase"></param>
        /// <param name="id">主键值</param>
        /// <param name="fields">返回字段</param>
        /// <returns></returns>
        public static tbl_mrItem GetRowById(Shotgun.Database.IBaseDataClass2 dBase, int id, string[] fields)
        {
            var q = GetQueries(dBase);
            q.Fields = fields;
            q.Filter.AndFilters.Add(identifyField, id);
            return q.GetRowByFilters();
        }

        /// <summary>
        /// 根据主键查找指定的行,返回所有字段
        /// </summary>
        /// <param name="dBase"></param>
        /// <param name="id">主键值</param>
        /// <returns></returns>
        public static tbl_mrItem GetRowById(Shotgun.Database.IBaseDataClass2 dBase, int id)
        {
            return GetRowById(dBase, id, null);
        }

        #endregion


        public DateTime recdate
        {
            get
            {
                return this.mr_date;
            }
            set
            {
                this.mr_date = value;
            }
        }

        public tbl_tmp_mrItem CopyToNewTmpMr()
        {
            tbl_tmp_mrItem tmp = new tbl_tmp_mrItem();
            tmp.imei = this.imei;
            tmp.imsi = this.imsi;
            tmp.mobile = this.mobile;
            tmp.mcc = this.mcc;
            tmp.province_id = this.province_id;
            tmp.city_id = this.city_id;
            tmp.ori_trone = this.ori_trone;
            tmp.ori_order = this.ori_order;
            tmp.linkid = this.linkid;
            tmp.cp_param = this.cp_param;
            tmp.service_code = this.service_code;
            tmp.price = this.price;
            tmp.ip = this.ip;
            tmp.status = this.status;
            tmp.mr_date = DateTime.Now;
            tmp.create_date = DateTime.Now;
            return tmp;
        }

        string ICPPushModel.GetValue(EPushField f)
        {
            switch (f)
            {
                case EPushField.cpParam: return this.cp_param;
                case EPushField.LinkID: return this.linkid;
                case EPushField.Mobile: return this.mobile;
                case EPushField.Msg: return this.ori_order;
                case EPushField.port: return this.ori_trone;
                case EPushField.price: return this.price.ToString();
                case EPushField.ServiceCode: return this.service_code;
                case EPushField.Status: return this.status;
                case EPushField.province: return this.province_id.ToString();
            }
            throw new NotImplementedException();
        }

        LightDataModel.tbl_cp_mrItem ICPPushModel.SetPushed(Shotgun.Database.IBaseDataClass2 dBase, tbl_trone_orderItem tCfg)
        {
            this.syn_flag = 1;
            this.trone_order_id = tCfg.id;
            this.cp_id = tCfg.cp_id;
            dBase.SaveData(this);
            return this.CopyToCP();
        }

        void ICPPushModel.SetHidden(Shotgun.Database.IBaseDataClass2 dBase, tbl_trone_orderItem tCfg)
        {
            this.syn_flag = 0;
            this.trone_order_id = tCfg.id;
            this.cp_id = tCfg.cp_id;
            dBase.SaveData(this);
        }

        public tbl_cp_mrItem CopyToCP()
        {
            var cpmr = new tbl_cp_mrItem();

            cpmr.imei = this.imei;
            cpmr.imsi = this.imsi;
            cpmr.mobile = this.mobile;
            cpmr.mcc = this.mcc;
            cpmr.province_id = this.province_id;
            cpmr.city_id = this.city_id;
            cpmr.trone_order_id = this.trone_order_id;
            cpmr.ori_trone = this.ori_trone;
            cpmr.ori_order = this.ori_order;
            cpmr.linkid = this.linkid;
            cpmr.cp_param = this.cp_param;
            cpmr.service_code = this.service_code;
            cpmr.price = this.price;
            cpmr.ip = this.ip;
            cpmr.mr_table = this.TableName;
            cpmr.mr_id = this.id;
            cpmr.mr_date = this.mr_date;
            cpmr.trone_type = this.trone_type;
            cpmr.ivr_time = this.ivr_time;
            return cpmr;
        }
    }
}
