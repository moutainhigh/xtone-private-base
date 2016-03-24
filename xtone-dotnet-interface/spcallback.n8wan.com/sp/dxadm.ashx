<%@ WebHandler Language="C#" Class="dxadm" %>

using System;
using System.Web;
using System.Xml;

/// <summary>
/// 电信爱动漫
/// </summary>
public class dxadm : n8wan.Public.Logical.BaseSPCallback {

    XmlElement root;
    protected override bool OnInit()
    {
        var xmlstr = Request["requestData"];
        if (xmlstr==null && xmlstr.Length<10)
            return false;
        var xml = new XmlDocument();
        try
        {
            xml.LoadXml(xmlstr);
        }
        catch
        {
            return false;
        }
        return base.OnInit();
    }

    public override string GetParamValue(string Field)
    {
        if (root == null)
            return null;
        foreach (XmlNode node in root)
        {
            if (node.NodeType != XmlNodeType.Element)
                continue;
            if (node.Name.Equals(Field, StringComparison.OrdinalIgnoreCase))
            {
                return node.InnerText;
            }
        }
        return base.GetParamValue(Field);
    }
    

}