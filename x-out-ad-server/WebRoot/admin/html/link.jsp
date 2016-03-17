<%@ page language="java" pageEncoding="utf-8"%>

<link href='./source/js/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='./source/js/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href="./source/css/jquery-plugin/smoothness/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css" media="all" />

<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="./source/js/highcharts.js"></script>
<script type="text/javascript" src="./source/js/Calendar.js"></script>
<script src='./source/js/jquery-plugin/jquery-ui-1.10.4.js'></script>
<script type="text/javascript" src='./source/js/fullcalendar/fullcalendar.js'></script>
<script type="text/javascript" src="./source/js/busi/push.js"></script>
<script type="text/javascript" src="./source/js/busi/link.js"></script>
<script type="text/javascript" src="./source/js/ajaxfileupload.js"></script>
<style>
	*{
		font-size:14px;
	}
	.customquery{
		color:#6F6FDC;
		font-size:14px;
		/* background:#f9f9f9;
		border:1px #d2d2d2 solid;
		border-top:none; */
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
	
	#wrap {
		width: 900px;
		margin: 0 auto;
		}

	#calendar {
		float: left;
		width: 900px;
		}

</style>
<div id='wrap'>

<div id='calendar'></div>
<div style="display: none" class="section_entry" id = "section_entry">
		<div class="customquery">
		<input type="hidden" id="cpid" />
		<table>
				<tr>
					<td align="right">推送标题:</td>
					<td  colspan=5>
						<input type="text" onclick="clearInputvalue('productname','输入推送标题')" id="title" class="text first_text" placeholder ="输入推送标题" /></td>
					</td>
				</tr>
				<tr>
					<td align="right">推送图片 URL:</td>
					<td colspan=5>
						<input type="text" onclick="clearInputvalue('productname','输入推送链接的icon下载地址')" id="imgurl" class="text first_text" placeholder="输入推送链接的icon下载地址(包含http://)" />
						<form action='/hlpads/upload' enctype="multipart/form-data" method="post" id="imageForm" name="imageForm">  
                           <input type="file" name="imageToUpload" id="imageToUpload">  
                           <input type="button" value="上传图片" onclick="uploadImage();" style="padding:0.4em 1em;margin-left:0.4em;font-size:14px;"/>
                        </form>
					</td>
 
					</td>
				</tr>
				<tr>
					<td align="right">推送链接URL:</td>
					<td colspan=5>
						<input type="text" onclick="clearInputvalue('productname','点击后的链接地址URL')" id="apkurl" class="text first_text" placeholder="点击后的链接地址URL(包含http://)" />
						<input type="button" value="选择应用" onclick="loadAppList();" style="padding:0.4em 1em;margin-left:0.4em;font-size:14px;"/>
						<form action='/hlpads/upload' enctype="multipart/form-data" method="post" id="fileForm" name="fileForm">  
                           <input type="file" name="fileToUpload" id="fileToUpload">  
                           <input type="button" value="上传应用" onclick="uploadApp();" style="padding:0.4em 1em;margin-left:0.4em;font-size:14px;"/>
                        </form> 
					</td>
				</tr>
				<tr id = "appList" class="appList" style="display:none">
				</tr>
				<tr>
					<td align="right">任务权重:</td>
					<td  colspan=5>
						<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" onclick="clearInputvalue('productname','输入任务权重')" id="weight" class="text first_text" placeholder ="输入任务权重" /></td>
					</td>
				</tr>
				<tr>
					<td align="right">推送次数:</td>
					<td  colspan=5>
						<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" onclick="clearInputvalue('productname','输入推送次数')" id="count" class="text first_text" placeholder ="输入推送次数" /></td>
					</td>
				</tr>
				<tr>
					<td align="right">全局推送:</td>
					<td colspan=5>
						<select id="allpush" class="goods_select">
							<option value="1">开启</option>
							<option value="0">关闭</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">网络:</td>
					<td colspan=5>
						<select id="network" class="goods_select">
							<option value="0">WIFI</option>
							<option value="1" selected="selected">2G/3G</option>
							<option value="2">全部网络</option>
						</select>
					</td>
				</tr>
				<tr>
                   <td align="right">开始时间:</td>
				   
                   <td><div class="sec_data"><input id="startDate" class="sec_data_input" type="text" onclick="SelectDate(this,'yyyy-MM-dd')" value="" readonly="readonly" /><span onclick="SelectDate($(this).prev()[0],'yyyy-MM-dd')" class="sec_data_btn"></span></div></td>
                   <td>
						<select id="startHour" class="goods_select">
							<option value="00">00</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
						</select>
					</td>
				</tr>
				<tr>
                   <td align="right">结束时间:</td>
				   
                   <td><div class="sec_data"><input id="endDate" class="sec_data_input" type="text" onclick="SelectDate(this,'yyyy-MM-dd')" value="" readonly="readonly" /><span onclick="SelectDate($(this).prev()[0],'yyyy-MM-dd')" class="sec_data_btn"></span></div></td>
                   <td>
						<select id="endHour" class="goods_select">
							<option value="00">00</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">渠道:</td>
					<td>
						<select id="channel" class="goods_select">
							<option value="ALL">全部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">地区:</td>
					<td>
						<select id="area" class="goods_select">
							<option value="ALL">全部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">安装提示框:</td>
					<td>
						<select id="showconfrim" class="goods_select">
							<option value="1">不提示</option>
							<option value="0">提示</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">性别:</td>
					<td>
						<select id="sex" class="goods_select">
							<option value="ALL">全部</option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
	</div>
<div style='clear:both'></div>

