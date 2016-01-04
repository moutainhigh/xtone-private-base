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

        tbl_sp_trone_apiItem _apiMatchAPI;

        public override bool LoadCPAPI()
        {
            if (_apiMatchAPI == null)
                return true;
            return base.LoadCPAPI();
        }

        /// <summary>
        /// 加载浩天API回调匹配信息
        /// </summary>
        /// <returns></returns>
        public bool LoadHTAPIConfig()
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

            var l = tbl_api_orderItem.GetQueries(dBase);
            l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.trone_id, TroneId);
            switch (_apiMatchAPI.match_field_E)
            {
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
            l.Filter.AndFilters.Add(tbl_api_orderItem.Fields.state, 0);

            var item = l.GetRowByFilters();
            if (item == null)
                return false;

            this.CP_Id = item.cp_id;

            var tOrder = tbl_trone_orderItem.GetQueries(dBase);
            tOrder.Filter.AndFilters.Add(tbl_trone_orderItem.Fields.cp_id, item.cp_id);
            tOrder.Filter.AndFilters.Add(tbl_trone_orderItem.Fields.trone_id, TroneId);
            tOrder.Filter.AndFilters.Add(tbl_trone_orderItem.Fields.disable, false);
            var m = tOrder.GetRowByFilters();
            if (m == null)
                return false;
            SetConfig(m);

            if (!LoadCPAPI())
                return false;

            return base.DoPush();
        }

    }
}
