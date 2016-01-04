<%@ WebHandler Language="C#" Class="mingtiandongli_phy" %>

using System;
using System.Web;

public class mingtiandongli_phy : gzzyPublic.SPCallback.AutoMapCallback
{
    /// <summary>
    /// 经过处理的参数信息
    /// </summary>
    string _linkId = null, _cpp, _msg;

    public override string GetParamValue(string Field)
    {
        switch (Field.ToLower())
        {
            case "linkid":
                return _linkId;
            case "mobile":
                var m = Request["mobile"];
                if (string.IsNullOrEmpty(m))
                    m = Request["imei"];
                return m;
            case "msg":
                return _msg;
            case "cpparams":
                return _cpp;
        }
        return base.GetParamValue(Field);
    }


    protected override bool OnInit()
    {
        var channelNum = Request["channelNum"];
        var appId = Request["appId"];
        var cpp = Request["cpparams"];
        var price = Request["price"];
        if (string.IsNullOrEmpty(channelNum) || string.IsNullOrEmpty(cpp))
            return false;

        var i = cpp.LastIndexOf('#');
        if (i != -1)
        {
            _msg = cpp.Substring(0, i);
            _cpp = cpp.Substring(i + 1);
        }
        else
        {
            int p = int.Parse(price) / 100;
            _cpp = cpp;
            _msg = "A01" + channelNum + appId + p.ToString();
        }
        _linkId = string.Format("{0}{1}{2}{3}", channelNum, appId, _cpp, price);

        return true;

    }

}