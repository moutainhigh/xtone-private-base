/*
 * 统计脚本
 */
/**全局变量*/
var paramapps = {};
var paramproduct = {};
var parampaytype = {};
var parampaypoint = {};
var area = {};
var pontlist = new Array();
var detaildate, detailcpcid, detailcppid, detailcppaypoint, detailpaytype;
var detailtime = {
	"00" : "00:00~01:00",
	"01" : "01:00~02:00",
	"02" : "02:00~03:00",
	"03" : "03:00~04:00",
	"04" : "04:00~05:00",
	"05" : "05:00~06:00",
	"06" : "06:00~07:00",
	"07" : "07:00~08:00",
	"08" : "08:00~09:00",
	"09" : "09:00~10:00",
	"10" : "10:00~11:00",
	"11" : "11:00~12:00",
	"12" : "12:00~13:00",
	"13" : "13:00~14:00",
	"14" : "14:00~15:00",
	"15" : "15:00~16:00",
	"16" : "16:00~17:00",
	"17" : "17:00~18:00",
	"18" : "18:00~19:00",
	"19" : "19:00~20:00",
	"20" : "20:00~21:00",
	"21" : "21:00~22:00",
	"22" : "22:00~23:00",
	"23" : "23:00~24:00"
};

var loadApp = function() {

	$.reqPostData(
	SERVERURL + '220', 
	{
		reqType : 'F9902007'
	}, 
	function(data) 
	{
		if (data) {
			if (data.rows) {//渠道列表
				$('#app').empty();
				if (data.is_admin == 1)
				{
					adminuser = 1;
					$('#app').append(
						'<option value="ALL" class="goods_select">全部</option>');
				}
				cpOptionHtml = '';
				$.each(data.rows, function(i, app) {
					paramapps[app.app_id] = app.name;
					cpOptionHtml += "<option value='" + app.app_id
							+ "'>" + app.name + "</option>";
				});
				$('#app').append(cpOptionHtml);

			} else {//获取当前cp

			}

		}
	});
};


var onToggle = function(obj, id, appid) {
	var btn = $(obj).find("span");
	var change = btn.attr("change");
	btn.toggleClass('off');

	if (btn.attr("class") == 'off') {
		$(obj).find("input").val("1");
		btn.attr("change", btn.html());
		btn.html(change);
		editApp(id, 1, appid);
	} else {
		$(obj).find("input").val("0");
		btn.attr("change", btn.html());
		btn.html(change);
		editApp(id, 0, appid);
	}

	return false;
};

var addApp = function() {
	var app_id = $('#app').val();
	$('#dataList').empty();
	/*$('#dataList').append('<tr><td>' + channelsmap[channelid] + '</td><td align="left">' + channelid
								+ '</td><td>' + 地区
								+ '</td><td>' + 保存
								+ '</td></tr>');
	 */

	$('#dataList').append(
			'<tr><td>' + defaultstr(paramapps[app_id])
					+ '</td><td align="left">' + defaultstr(app_id)
					+ '</td><td align = "center" ><button class="mwui-switch-btn" onClick="onToggle(this, 0, ' + app_id + ')"><span change="关" class="">开</span><input type="hidden" name="show_icon" value="0" /></button>'
							+ '</td></tr>');
};


var editApp = function(id, status, app_id) {
	$.reqPostData(SERVERURL + '223', {
		reqType : 'F9902007',
		id : id,
		status : status,
		app_id : app_id
	}, function(data) {
		alert("保存成功");
	});
};


/***
 * 查询
 */
var doQuery = function(pager) {

	//if (cpid != '' && parseInt(cpid) > 0){
	pager = getPager(pager);
	$.reqPostData(SERVERURL + '221', {
		reqType : 'F9902009'
	}, function(data) {
		//alert('保存成功');
			$('#dataList').empty();
			if (data && data.rows) {
				$.each(data.rows, function(i, row) {
					var onoff;
					if (row.status == 0)
					{
						onoff = '<span change="关" class="">开</span><input type="hidden" name="show_icon" value="0" />';
					}
					else
					{
						onoff = '<span change="开" class="off">关</span><input type="hidden" name="show_icon" value="1" />' ;
					}
					$('#dataList').append(
							'<tr><td>'
							+ defaultstr(paramapps[row.app_id])
							+ '</td><td align="left">'
							+ row.app_id
							+ '</td><td align = "center" ><button class="mwui-switch-btn" onClick="onToggle(this, '
								+ row.id
							+ ', ' + row.app_id + ')">' +
							onoff
							+ '</button>'
							+ '</td></tr>');
				});
			} else {
				$('#dataList').append('<tr><td colspan="3">暂无数据</td></tr>');
			}

			$.pager( {
				id : "result",
				totalrows : data.totalrows,
				pagesize : data.pagesize,
				currentpage : data.currentpage
			}, loadData);
		});
	//}else{
	//	alert("请至少选择一个cp分析!");
	//}

};

/*初始化**/
$(document).ready(function() {
	//初始化日期插件

		//$('#startDate').datepicker({
		//	 dateFormat: 'yy-mm-dd'	
		//});
		//$('#endDate').datepicker({
		//	dateFormat: 'yy-mm-dd'
		//});
		//初始化支付方式
		loadApp();
		//初始化
		options = {
			chart : {
				renderTo : 'graphic',
				type : 'spline'
			},
			title : {
				text : '24小时交易明细'
			},
			xAxis : {
				min : 0,
				categories : [ '00~01', '01~02', '02~03', '03~04', '04~05',
						'05~06', '06~07', '07~08', '08~09', '09~10', '10~11',
						'11~12', '12~13', '13~14', '14~15', '15~16', '16~17',
						'17~18', '18~19', '19~20', '20~21', '21~22', '22~23',
						'23~00' ],
				labels : {
					align : 'left',
					step : 2
				}

			},
			yAxis : {
				title : {
					text : '金额(元)'
				},
				min : 0
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.series.name + '</b><br/>' + this.x
							+ ': ' + this.y + '元';
				}
			}
		};
		$('#product').change(function() {
			changeProduct($(this).children('option:selected').val());
		});
	});
