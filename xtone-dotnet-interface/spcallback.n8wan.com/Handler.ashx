<%@ WebHandler Language="C#" Class="Handler" %>

using System;
using System.Web;

public class Handler : Shotgun.PagePlus.SimpleHttpHandler<Shotgun.Database.MySqlDBClass>
{




    public override void BeginProcess()
    {
        var m = LightDataModel.tbl_mrItem.GetRowById(dBase, 2);
        var cp = new n8wan.Public.Logical.AutoMapPush();
        cp.dBase = dBase;
        cp.TroneId = m.trone_id;
        //cp.UnionUserId = -1;
        cp.LogFile = Server.MapPath(string.Format("~/PushLog/{0:yyyyMMdd}.log", DateTime.Today));

        if (!cp.LoadCPAPI())
            return;

        cp.PushObject = m;
        cp.DoPush();

    }
}