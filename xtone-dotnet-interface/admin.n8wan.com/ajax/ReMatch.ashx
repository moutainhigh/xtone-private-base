<%@ WebHandler Language="C#" Class="ReMatch" %>

using System;
using System.Web;

public class ReMatch : Shotgun.PagePlus.SimpleHttpHandler<Shotgun.Database.MySqlDBClass>
{
    public override void BeginProcess()
    {
        Ajax = new Shotgun.Library.simpleAjaxResponser();
        int id = int.Parse(Request["id"]);

        var m = LightDataModel.tbl_mrItem.GetRowById(dBase, id);
        if (!m.IsMatch)
        {

            if (!n8wan.Public.Logical.BaseSPCallback.FillToneId(dBase, m))
            {
                Ajax.message = "匹对失败";
                return;
            }
            //m.trone_id = tid;
            //m.IsMatch = true;
            dBase.SaveData(m);
        }

        var apiPush = new n8wan.Public.Logical.HTAPIPusher()
        {
            dBase = dBase,
            TroneId = m.trone_id,
            LogFile = Server.MapPath(string.Format("~/PushLog/{0:yyyyMMdd}.log", DateTime.Today))
        };
        if (apiPush.LoadCPAPI())
        {
            apiPush.PushObject = m;
            if (apiPush.DoPush())
            {
                Ajax.state = Shotgun.Library.emAjaxResponseState.ok;
                return;
            }
        }

        var cp = new n8wan.Public.Logical.AutoMapPush();
        cp.dBase = dBase;
        cp.TroneId = m.trone_id;
        //cp.UnionUserId = -1;
        cp.LogFile = Server.MapPath(string.Format("~/PushLog/{0:yyyyMMdd}.log", DateTime.Today));

        if (!cp.LoadCPAPI())
            return;

        cp.PushObject = m;
        cp.DoPush();
        Ajax.state = Shotgun.Library.emAjaxResponseState.ok;
    }
}
 