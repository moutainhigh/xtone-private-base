<%@ page language="java" pageEncoding="utf-8"%>
<link href="./source/css/jquery-plugin/smoothness/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.core.js"></script>
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker-zh-CN.js"></script>
<script src="./source/js/highcharts.js"></script>
<script type="text/javascript" src="./source/js/Calendar.js"></script>
<script type="text/javascript" src="./source/js/busi/push.js"></script>
<script type="text/javascript" src="./source/js/busi/appswitch.js"></script>
<style type="text/css">
	*{
		font-size:14px;
	}
	.customquery{
		color:#6F6FDC;
		font-size:14px;
		background:#f9f9f9;
		border:1px #d2d2d2 solid;
		border-top:none;
		padding:4px;
	}
	.customquery table{
	
	    color: #333333;
	    width: 100%;
		word-wrap:break-word;
	}
	
	.customquery table td{
		height:30px;
		line-height:30px;
	}
	.self-table {
    border-collapse: collapse;
    color: #333333;
    width: 100%;
	table-layout: fixed;
	word-wrap:break-word;
}
	.self-table th {
		background: none repeat scroll 0 0 #438945;
	    border-top: 1px solid #FFFFFF;
	    padding: 5px 0;
		color: #ffffff;
	    font-size: 12px;
	    font-weight: bolder;
	    line-height: 20px;
	    text-decoration: none;
	    vertical-align: middle;
	}
	.self-table tr {
	    background: none repeat scroll 0 0 #F7FBFF;
	}
	.self-table th, .self-table td {
	    border: 1px solid #E4E4E4;
		text-align:center;
	}
	.self-table td a {
	    color: #0E5B96;
	    font-size: 12px;
	    text-align: center;
	    text-decoration: none;
	}
	.self-table td a:hover {
	    text-decoration: underline;
	}
	.goods_select {
	    color: #51565A;
	    font-size: 14px;
	    height: 22px;
	    padding-top: 1px;
	    width: 215px;
	}
	.mwui-switch-btn{
    width:84px; 
    display:block;
    padding:1px;
    background:#3B75FD;
    overflow:hidden;
    margin-bottom:5px;
    border:1px solid #2E58C1;
    border-radius:18px;
    cursor: pointer;
}
.mwui-switch-btn span{
    width:32px;
    font-size:14px;
    height:18px;
    padding:4px 5px 2px 5px;
    display:block;
    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#f6f6f6,endColorstr=#eeeeee,grandientType=1);
    background:-webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6), to(#eeeeee));
    background:-moz-linear-gradient(top, #f6f6f6, #eeeeee);
    border-radius:18px;
    float:left;
    color:#3B75FD;
    text-align:center;
}
.mwui-switch-btn:hover span{
    background:#fff;
}
.mwui-switch-btn span.off{float:right;}
</style>
<div class="goods" id="datainfo">
	<div class="section_head">
		<span style="display:block;float:left;font-size:20px;color: #ffffff;font-weight: bold;">应用开关</span>
	</div>
	<div class="section_entry">
		<div class="customquery">
		<input type="hidden" id="cpid" />
		<table>
				<tr>
					<td align="right">应用:</td>
					<td colspan=5>
					<select id="app" class="goods_select">
							<option value="-1">--</option>
							<option value="ALL">全部</option>
					</select>
					</td>
					<td valign="bottom"  align="center">
						<input type="button" value="新增" onclick="addApp();" style="padding:0.4em 1em;margin-right:2.4em;font-size:14px;"/>
						<input type="button" value="查询" onclick="doQuery();" style="padding:0.4em 1em;margin-right:2.4em;font-size:14px;"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="result">
			<table class="self-table">
				<thead>
					<tr>
						<th width="40%">应用名称</th>
						<th width="30%">应用编号</th>
						<th width="30%">开关</th>
					</tr>
				</thead>
				<tbody id="dataList">
				</tbody>
			</table>
		</div>
	</div>
</div>
