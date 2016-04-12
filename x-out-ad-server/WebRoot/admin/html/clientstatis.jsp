<%@ page language="java" pageEncoding="utf-8"%>
<link href="./source/css/jquery-plugin/smoothness/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.core.js"></script>
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker-zh-CN.js"></script>
<script src="./source/js/highcharts.js"></script>
<script type="text/javascript" src="./source/js/Calendar.js"></script>
<script type="text/javascript" src="./source/js/busi/push.js"></script>
<script type="text/javascript" src="./source/js/busi/clientstatis.js"></script>
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
</style>
<div class="goods" id="datainfo">
	<div class="section_head">
		<span style="display:block;float:left;font-size:20px;color: #ffffff;font-weight: bold;">用户统计</span>
	</div>
	<div class="section_entry">
		<div class="customquery">
		<input type="hidden" id="cpid" />
		<table>
				<tr>
                   <td align="right">开始日期:</td>
                   <td><div class="sec_data"><input id="startDate" class="sec_data_input" type="text" onclick="SelectDate(this,'yyyy-MM-dd')" value="" readonly="readonly" /><span onclick="SelectDate($(this).prev()[0],'yyyy-MM-dd')" class="sec_data_btn"></span></div></td>
					<td align="right">结束日期:</td>
					<td><div class="sec_data"><input id="endDate" class="sec_data_input" type="text" onclick="SelectDate(this,'yyyy-MM-dd')" value="" readonly="readonly" /><span onclick="SelectDate($(this).prev()[0],'yyyy-MM-dd')" class="sec_data_btn"></span></div></td>
					<td>
					<td valign="bottom"  align="center">
						<input type="button" value="查询" onclick="doQuery();" style="padding:0.4em 1em;margin-right:2.4em;font-size:14px;"/>
					</td>
				</tr>
				<tr>
					<td align="right">渠道:</td>
					<td colspan=5>
					<select id="channel" class="goods_select">
							<option value="-1">--</option>
							<option value="ALL">全部</option>
					</select>
					</td>
				</tr>
			</table>
		</div>
		<div class="result">
			<table class="self-table">
				<thead>
					<tr>
						<th>日期</th>
						<th>用户数</th>
						<th>渠道</th> 
					</tr>
				</thead>
				<tbody id="dataList">
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- 时段统计 -->
<!--
<div class="goods none" id="detailinfo">
    <p class="derive"><a href="####" class="text" onclick="doExport();">导出<em>excel</em></a></p>
  	<h1 class="goods_h1">时段统计</h1>
    <p class="interval"></p>
      <div class="add_goods new_top">
          <table border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
              <th width="100">时段</th>
              <th width="105">付费用户数</th>
              <th width="105">用户均价</th>
              <th width="105">付费笔数</th>
              <th width="132">单笔金额（均价）</th>
              <th width="126">交易金额（元）</th>
            </tr>
            </tbody>
            <tbody id="showdetaildata">
            </tbody>
          </table>
          <table border="0" class="tabl_borderTop" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
              <th width="100">统计时数</th>
              <th width="105">付费用户数</th>
              <th width="105">用户均价</th>
              <th width="105">付费笔数</th>
              <th width="132">单笔金额（均价）</th>
              <th width="126">交易金额（元）</th>
            </tr>
            </tbody>
            <tbody id="sumdetaildata">
            </tbody>
          </table>
      </div>
      <div  id="graphic" class="graphic"></div>
     <span class="back"><a href="#" class="text first_address" onclick="doGoBack();">返回上一页</a></span>
 
  </div>
  -->