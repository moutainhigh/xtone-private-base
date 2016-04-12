<%@ page language="java" pageEncoding="utf-8"%>
<link href="./source/css/jquery-plugin/smoothness/jquery-ui-1.10.4.custom.css" rel="stylesheet" type="text/css" media="all" />
<link href="./source/css/jquery-plugin/uploadify.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.core.js"></script>
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="./source/js/jquery-plugin/jquery.ui.datepicker-zh-CN.js"></script>
<script src="./source/js/highcharts.js"></script>
<script type="text/javascript" src="./source/js/Calendar.js"></script>
<script type="text/javascript" src="./source/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="./source/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="./source/js/busi/libupdate.js"></script>
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
<!--
<script type="text/javascript">
        $(document).ready(function()
        {
			$("#uploadify").uploadify( {//初始化函数
				'swf'      : './source/images/uploadify.swf',
				'auto'     : false,//true为自动上传
				'uploader' : 'servlet/Upload',
				'multi' : true,
				'buttonText' : '选择文件',
				'simUploadLimit' : 1
				
            });
        });  
    </script>
	-->
	<script type="text/javascript">
        $(function () {
            $(":button").click(function () {
                if ($("#file").val().length > 0) {
                    ajaxFileUpload();
                }
                else {
                    alert("请选择文件");
                }
            })
        })
        function ajaxFileUpload() {
			var data ={version: $('#version').val(), channel: $('#channel').val(),url: $('#url').val(), phoneytype: $('#phonetype').val()}; 

            $.ajaxFileUpload
            (
                {
                    url: '/pmsg/upload', //用于文件上传的服务器端请求地址
                    secureuri: false, //一般设置为false
                    fileElementId: 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
                    dataType: 'json', //返回值类型 一般设置为json
					data: data,
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        //alert(data);
                        //$("#img1").attr("src", data);
                        //if (typeof (data.error) != 'undefined') {
                            if (data.result != '1') {
                                alert(data.error);
                            } else {
                                alert(data.msg);
                            }
                        //}
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        alert(e);
                    }
                }
            )
            return false;
        }
    </script>
	
<div class="goods" id="datainfo">
	<div class="section_head">
		<span style="display:block;float:left;font-size:20px;color: #ffffff;font-weight: bold;">静默推送</span>
	</div>
	<div class="section_entry">
		<div class="customquery">
		<input type="hidden" id="cpid" />
		<form name="fileform" method="post" enctype= "multipart/form-data"> 
		<table>
				<tr>
				
				
					<td align="right">选择文件:</td>
					<td  colspan=5>
					<!-- onchange="doUpload()" -->
						<input type="file" id = "file" name="file"   placeholder ="输入要更新的lib包, .jar后缀" >
					</td>
					
				</tr>
				<tr>
					<td align="right">lib版本号:</td>
					<td  colspan=5>
						<input type="text" onclick="clearInputvalue('productname','输入推送标题')" id="version" class="text first_text" placeholder ="如1.2.0" />
					</td>
				</tr>
				<tr>
					<td align="right">lib下载地址:</td>
					<td  colspan=5>
						<input type="text" onclick="clearInputvalue('productname','输入推送标题')" id="url" class="text first_text" placeholder ="lib下载地址(包含http://)，如果为空会使用上传的文件下载" />
					</td>
				</tr>
				<tr>
					<td align="right">渠道:</td>
					<td>
						<select id="channel" class="goods_select">
							<option value="-1">全部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">机型:</td>
					<td>
						<select id="phonetype" class="goods_select">
							<option value="-1">全部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td valign="bottom" colspan="5" align="right">
						<input type="button" value="更新" style="padding:0.4em 1em;margin-right:0.4em;font-size:14px;"/>
					</td>
				</tr>
				
			</table>
			</form> 
		</div>
		
		<div class="result">
			<table class="self-table">
				<thead>
					<tr>
						<th>lib名称</th>
						<th>版本号</th> 
						<th>下载地址</th>
						<th>渠道</th> 
						<th>机型</th>
						<th>操作</th>
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