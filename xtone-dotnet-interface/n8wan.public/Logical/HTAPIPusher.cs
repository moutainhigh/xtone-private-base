using LightDataModel;
using Shotgun.Model.Filter;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace n8wan.Public.Logical
{
    public class HTAPIPusher : CPPusher
    {
        /// <summary>
        /// 主要用于API订单匹配验证
        /// </summary>
        tbl_sp_trone_apiItem _apiMatchAPI;
        private tbl_api_orderItem _apiOrder;

        /// <summary>
        /// 加载浩天API回调匹配信息
        /// </summary>
        /// <returns></returns>
        public override bool LoadCPAPI()
        {
            tbl_sp_trone_apiItem m = tbl_sp_trone_apiItem.GetRowByTroneId(dBase, TroneId);
            if (m == null)
                return false;
            _apiMatchAPI = m;
            return true;
        }


        public override bool DoPush()
        {

            if (_apiMatchAPI == null)
                return false;

            if (PushObject.cp_id > 0 && PushObject.cp_id != 34)
                return true; //已经处理的数据

            var l = tbl_api_orderItem.GetQueries(dBase);
            //l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.trone_id, TroneId);
            switch (_apiMatchAPI.match_field_E)
            {//订单匹配条件生成
                case tbl_sp_trone_apiItem.EMathcField.Cpprams: l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.api_exdata, this.PushObject.GetValue(EPushField.cpParam)); break;
                case tbl_sp_trone_apiItem.EMathcField.LinkId: l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.sp_linkid, this.PushObject.GetValue(EPushField.LinkID)); break;
                case tbl_sp_trone_apiItem.EMathcField.Msg:
                    l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.msg, this.PushObject.GetValue(EPushField.Msg));
                    l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.port, this.PushObject.GetValue(EPushField.port));
                    break;
                case tbl_sp_trone_apiItem.EMathcField.Msg_Not_Equal://同步指令与上行指令不一至时，使用“port,msg”拼接用逗号分隔，并在sp透传查找
                    l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.api_exdata,
                        string.Format("{0},{1}", PushObject.GetValue(EPushField.port), this.PushObject.GetValue(EPushField.Msg)));
                    break;
            }
            l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.status, 1011);

            _apiOrder = l.GetRowByFilters();//查到订单号
            if (_apiOrder == null)
                return false;

            var tOrder = tbl_trone_orderItem.GetQueries(dBase);
            tOrder.Filter.AndFilters.Add(tbl_trone_orderItem.Fields.id, _apiOrder.trone_order_id);
            tOrder.Filter.AndFilters.Add(tbl_trone_orderItem.Fields.disable, false);

            var m = tOrder.GetRowByFilters();
            if (m == null)
                return false;
            this.CP_Id = m.cp_id;
            SetConfig(m);//找到对应的渠道上量(相当于执行 base.LoadCPAPI())
            if (PushObject is tbl_mrItem)
            {
                ((tbl_mrItem)PushObject).api_order_id = _apiOrder.id;
            }
            //if (!LoadCPAPI())
            //    return false;



            return base.DoPush();
        }

        protected override void SendQuery()
        {
            if (string.IsNullOrEmpty(API_PushUrl))
            {
                WriteLog(-1, "No Push URL");
                return;
            }

            var ptrs = new Dictionary<string, string>();
            ptrs.Add("mobile", PushObject.GetValue(Logical.EPushField.Mobile));
            ptrs.Add("servicecode", PushObject.GetValue(Logical.EPushField.ServiceCode));
            ptrs.Add("linkid", PushObject.GetValue(Logical.EPushField.LinkID));
            ptrs.Add("msg", PushObject.GetValue(Logical.EPushField.Msg));
            //ptrs.Add("status", PushObject.GetValue(Logical.EPushField.Status));
            ptrs.Add("port", PushObject.GetValue(Logical.EPushField.port));

            ptrs.Add("price", PushObject.GetValue(Logical.EPushField.price));
            ptrs.Add("cpparam", _apiOrder.ExtrData);
            ptrs.Add("provinceId", PushObject.GetValue(EPushField.province));
            ptrs.Add("paycode", _apiOrder.trone_order_id.ToString("100000"));
            string qs = UrlEncode(ptrs);


            string url;
            if (API_PushUrl.Contains('?'))
                url = API_PushUrl + "&" + qs;
            else
                url = API_PushUrl + "?" + qs;

            asyncSendData(url);

        }



    }
}
