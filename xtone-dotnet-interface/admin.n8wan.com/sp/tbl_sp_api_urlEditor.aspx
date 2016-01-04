<%@ Page Title="同步URL配置" Language="C#" AutoEventWireup="true" CodeFile="tbl_sp_api_urlEditor.aspx.cs" Inherits="tbl_sp_api_url_Editor" ValidateRequest="false" MasterPageFile="~/MasterPage.master" %>

<asp:Content runat="server" ContentPlaceHolderID="head">
    <style type="text/css">
        input[type="text"] { width: 350px; }
    </style>
    <script type="text/javascript">
        function frm_onsubmit(sender, e) {
            var obj = sender["ddlSp_id"];
            if (obj.value == "" || obj.value == "0") {
                alert("请选择SP");
                return false;
            }
            return true;
        }
    </script>
</asp:Content>
<asp:Content runat="server" ContentPlaceHolderID="body">
    <form id="form1" runat="server" onsubmit="return frm_onsubmit(this,null);">
        <table class="datagrid">
            <tr>
                <th>sp_id:</th>
                <td colspan="2">
                    <asp:DropDownList runat="server" ID="ddlSp_id" DataTextField="full_name" DataValueField="id">
                        <asp:ListItem Value="0" Text="*请选择*" />
                    </asp:DropDownList>
                </td>
            </tr>

            <tr>
                <th class="red">名称:</th>
                <td>
                    <asp:TextBox ID="txtName" runat="server" /></td>
                <td>同步地址备注信息</td>
            </tr>

            <tr>
                <th class="red">virtual_page:</th>
                <td>
                    <asp:TextBox ID="txtvirtual_page" runat="server" /></td>
                <td>（虛似）文件名(不需要后缀)</td>
            </tr>

            <tr>
                <th>MoCheck:</th>
                <td>
                    <asp:TextBox ID="txtMoCheck" runat="server" /></td>
                <td>检查不是为MO同步,格式:FieldName:Regex</td>
            </tr>

            <tr>
                <th>MoLink:</th>
                <td>
                    <asp:TextBox ID="txtMoLink" runat="server" /></td>
                <td></td>
            </tr>

            <tr>
                <th class="red">MrLink:</th>
                <td>
                    <asp:TextBox ID="txtMrLink" runat="server" /></td>
                <td></td>
            </tr>

            <tr>
                <th>MoToMr:</th>
                <td>
                    <asp:TextBox ID="txtMoToMr" runat="server" /></td>
                <td>Mo记录同步到Mr的字段;格式:SqlField1,SqlField2</td>
            </tr>

            <tr>
                <th>MoFieldMap:</th>
                <td>
                    <asp:TextBox ID="txtMoFieldMap" runat="server" /></td>
                <td>字段映射(不含:link字段) 格式:urlField1:sqlField1,.....</td>
            </tr>

            <tr>
                <th class="red">MrFieldMap:</th>
                <td>
                    <asp:TextBox ID="txtMrFidldMap" runat="server" Text="imei,imsi,ori_trone,ori_order,mobile,mcc,linkid,cp_param,service_code" /></td>
                <td>字段映射(不含:link字段) 格式:urlField1:sqlField1,....</td>
            </tr>

            <tr>
                <th>MoStatus:</th>
                <td>
                    <asp:TextBox ID="txtMoStatus" runat="server" /></td>
                <td>Mo同步时,可接收的状态值.格式,正则表达式</td>
            </tr>

            <tr>
                <th>MrStatus:</th>
                <td>
                    <asp:TextBox ID="txtMrStatus" runat="server" /></td>
                <td>Mr同步时,可接收的状态值.格式,正则表达式</td>
            </tr>
            <tr>
                <th>MoPrice:</th>
                <td>
                    <asp:TextBox ID="txtMoPrice" runat="server" /></td>
                <td>SP同步回传价格，主要用于生成虚似指令。格式：urlFile,0（0:分/1:元/3:角/2:配对模式[传入值:分,传入值:分...])</td>
            </tr>
            <tr>
                <th>MrPrice:</th>
                <td>
                    <asp:TextBox ID="txtMrPrice" runat="server" /></td>
                <td>同上</td>
            </tr>
            <tr>
                <th>MsgOutput:</th>
                <td>
                    <asp:TextBox ID="txtMsgOutput" runat="server" /></td>
                <td>同步结果输出:格式:ok/error/existed</td>
            </tr>

            <tr>
                <th>其它:</th>
                <td>
                    <asp:CheckBox runat="server" ID="chkDisable" Text="停用" />
                    <asp:CheckBox runat="server" ID="chkphy_file" Text="真实文件" />

                </td>
                <td>&nbsp;</td>
            </tr>


        </table>
        <input type="submit" value="确定" />
    </form>
</asp:Content>
