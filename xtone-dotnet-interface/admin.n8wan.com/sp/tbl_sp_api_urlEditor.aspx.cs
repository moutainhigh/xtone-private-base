using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Shotgun.Database;
using Shotgun.Library;
using LightDataModel;

public partial class tbl_sp_api_url_Editor : Shotgun.PagePlus.ShotgunPage
{
    tbl_sp_api_urlItem Row;
    int id;
    protected void Page_Load(object sender, EventArgs e)
    {
        LoadDefault();
        LoadSPInfo();
        if (IsPostBack)
        {
            var msg = SaveData();
            if (string.IsNullOrEmpty(msg))
                RedirectFromPage(id == 0 ? "添加成功" : "修改成功");
            Static.alert(msg);
        }
    }

    void LoadSPInfo()
    {
        if (IsPostBack)
            return;
        var l = tbl_spItem.GetQueries(dBase);
        l.Fields = new string[] { tbl_spItem.Fields.id, tbl_spItem.Fields.full_name };
        l.PageSize = int.MaxValue;
        l.Filter.AndFilters.Add(tbl_spItem.Fields.status, 1);
        ddlSp_id.Enabled = false;

        if (Row != null)
            l.Filter.AndFilters.Add(tbl_spItem.Fields.id, Row.sp_id);
        else
        {
            int spId;
            int.TryParse(Request["spId"], out spId);
            if (spId != 0)
                l.Filter.AndFilters.Add(tbl_spItem.Fields.id, spId);
            else
            {
                ddlSp_id.Enabled = true;
                ddlSp_id.AppendDataBoundItems = true;
                l.SortKey.Add(tbl_spItem.Fields.full_name, Shotgun.Model.Filter.EM_SortKeyWord.asc);
            }
        }
        var dt = l.GetDataList();
        ddlSp_id.DataSource = dt;
        ddlSp_id.DataBind();


    }



    private void LoadDefault()
    {
        var idStr = Request["id"];
        if (string.IsNullOrEmpty(idStr))
            return;

        if (int.TryParse(Request["id"], out id))
            Row = tbl_sp_api_urlItem.GetRowById(dBase, id);

        if (Row == null)
        {
            form1.Controls.Clear();
            form1.InnerHtml = "<h1>ID无效！</h1>";
            return;
        }
        if (IsPostBack)
            return;

        txtvirtual_page.Text = Row.virtual_page;
        chkphy_file.Checked = Row.phy_file;
        txtMoCheck.Text = Row.MoCheck;
        txtMoLink.Text = Row.MoLink;
        txtMrLink.Text = Row.MrLink;
        txtMoToMr.Text = Row.MoToMr;
        txtMoFieldMap.Text = Row.MoFieldMap;
        txtMrFidldMap.Text = Row.MrFidldMap;
        txtMoStatus.Text = Row.MoStatus;
        txtMrStatus.Text = Row.MrStatus;
        txtMsgOutput.Text = Row.MsgOutput;
        txtMrPrice.Text = Row.MrPrice;
        txtMoPrice.Text = Row.MoPrice;
        txtName.Text = Row.name;
        this.chkDisable.Checked = Row.Disable;


    }

    private string SaveData()
    {
        bool isNew = Row == null;
        if (isNew)
        {
            Row = new tbl_sp_api_urlItem();
            Row.sp_id = int.Parse(ddlSp_id.SelectedValue);
        }



        Row.virtual_page = txtvirtual_page.Text;
        Row.phy_file = chkphy_file.Checked;
        Row.MoCheck = txtMoCheck.Text;
        Row.MoLink = txtMoLink.Text;
        Row.MrLink = txtMrLink.Text;
        Row.MoToMr = txtMoToMr.Text;
        Row.MoFieldMap = txtMoFieldMap.Text;
        Row.MrFidldMap = txtMrFidldMap.Text;
        Row.MoStatus = txtMoStatus.Text;
        Row.MrStatus = txtMrStatus.Text;
        Row.MsgOutput = txtMsgOutput.Text;
        Row.Disable = chkDisable.Checked;
        Row.name = txtName.Text;
        Row.MoPrice = txtMoPrice.Text;
        Row.MrPrice = txtMrPrice.Text;
        if (!string.IsNullOrEmpty(Row.MoCheck))
        {
            if (string.IsNullOrEmpty(Row.MoLink))
                return "MoLink不能为空";
            if (string.IsNullOrEmpty(Row.MoFieldMap))
                return "MoFieldMap不能为空";
            Row.MoFieldMap = Row.MoFieldMap.Replace(" ", string.Empty);
            if (string.IsNullOrEmpty(Row.MoToMr) && string.IsNullOrEmpty(Row.MrFidldMap))
                return "MoToMr 与 MrFidldMap 不能同时为空";
            if (!string.IsNullOrEmpty(Row.MoToMr))
                Row.MoToMr = Row.MoToMr.Replace(" ", string.Empty);
            if (string.IsNullOrEmpty(Row.MrFidldMap))
                Row.MrFidldMap = Row.MrFidldMap.Replace(" ", string.Empty);
        }
        else
        {
            if (string.IsNullOrEmpty(Row.MrFidldMap))
                return "MrFidldMap不能为空";
            Row.MrFidldMap = Row.MrFidldMap.Replace(" ", string.Empty);
        }

        if (Row.phy_file && NameExisted())
            return "virtual_page已经存在，不能重复";

        if (isNew || Row.phy_file)
            Row.urlPath = string.Format("/sp/{0}.ashx", Row.virtual_page);
        else
            Row.urlPath = string.Format("/sp/{0}/{1}.ashx", Row.id, Row.virtual_page);


        try
        {
            Row.SaveToDatabase(dBase);
            if (isNew && !Row.phy_file)
                Row.urlPath = string.Format("/sp/{0}/{1}.ashx", Row.id, Row.virtual_page);
            Row.SaveToDatabase(dBase);

        }
        catch (Exception ex)
        {
            return "保存出错\n" + ex.Message;
        }
        return null;
    }

    private bool NameExisted()
    {
        var l = tbl_sp_api_urlItem.GetQueries(dBase);
        l.Filter.AndFilters.Add(tbl_sp_api_urlItem.Fields.virtual_page, Row.virtual_page);
        l.Filter.AndFilters.Add(tbl_sp_api_urlItem.Fields.Disable, false);
        if (Row.id > 0)
            l.Filter.AndFilters.Add(tbl_sp_api_urlItem.Fields.id, Row.id, Shotgun.Model.Filter.EM_DataFiler_Operator.Not_Equal);
        return l.ExecuteScalar(tbl_sp_api_urlItem.Fields.sp_id) != null;
    }
}