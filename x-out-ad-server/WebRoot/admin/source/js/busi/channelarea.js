/*
 * 统计脚本
 */
/**全局变量*/
var paramchannel = {};
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

/*
 * 校验角色并初始化cp 渠道列表 roleid
 * 当前cp 返回cpid 
 * cp管理员 返回 cplist
 * 运营返回 src列表
 * 
 * **/
var loadChannel2 = function() {
    alert('---loadChannel2-----');
	$.reqPostData(
	SERVERURL + '160', 
	{
		reqType : 'F9902007'
	}, 
	function(data) 
	{
		if (data) {
			if (data.channels) {//渠道列表
				$('#channel').empty();
				if (data.is_admin == 1)
				{
					adminuser = 1;
					$('#channel').append(
						'<option value="-1" >--</option>');
					$('#channel').append(
						'<option value="ALL">全部</option>');
				}
				cpOptionHtml = '';
				$.each(data.channels, function(i, channel) {
					paramchannels.push({id:channel.channel,name:channel.channelname});
					cpOptionHtml += "<option value='" + channel.channel
							+ "'>" + channel.channelname + "</option>";
				});
				$('#channel').append(cpOptionHtml);

			} else {//获取当前cp

			}
			
			
		}
	});
};

var loadArea = function() {
    alert('---loadArea-----');
	$.reqPostData(
	SERVERURL + '190', 
	{
		reqType : 'F9902007'
	}, 
	function(data) 
	{
		if (data) {
			if (data.areas) {//渠道列表
				$('#area').empty();
				if (data.is_admin == 1)
				{
					adminuser = 1;
					$('#area').append(
						'<option value="ALL">全部</option>');
				}
				cpOptionHtml = '';
				$.each(data.areas, function(i, area) {
					paramchannels.push({id:area.id,name:area.city});
					cpOptionHtml += "<option value='" + area.id
							+ "'>" + area.city + "</option>";
				});
				$('#channel').append(cpOptionHtml);

			} else {//获取当前cp

			}
			
			
		}
	});
};

var loadChannelArea = function() {

	$('#dataList').empty();
	$.reqPostData(
	SERVERURL + '191', 
	{
		reqType : 'F9902007'
	}, 
	function(data) 
	{
		if (data) {
			if (data.rows) {//渠道列表
				$('#dataList').empty();
				$.each(data.rows, function(i, row) {
					$('#dataList').append(
						'<tr><td>' + defaultstr(channelsmap[row.channel]) + '</td><td align="left">' + row.channel
								+ '</td><td align="left">' + addArea(row.area, row.id)
								+ '</td><td><a href="#" onclick="editArea(' + row.id + ');">保存</a>'
								+ '</td></tr>');
				});

			} else {//获取当前cp
				$('#dataList').append('<tr><td colspan="4">暂无数据</td></tr>');
			}	
		}
	});
};

var addChannelArea = function() {
	var channelid = $('#channel').val();
	$('#dataList').empty();
	/*$('#dataList').append('<tr><td>' + channelsmap[channelid] + '</td><td align="left">' + channelid
								+ '</td><td>' + 地区
								+ '</td><td>' + 保存
								+ '</td></tr>');
	*/
	
	$('#dataList').append('<tr><td>' + defaultstr(channelsmap[channelid]) + '</td><td align="left">' + defaultstr(channelid)
								+ '</td><td align="left">' + addArea("", 0)
								+ '</td><td><a href="#" onclick="insertArea(\'' + channelid + '\',0);">保存</a>'
								+ '</td></tr>');								
};

var addArea = function(areastr, id)
{
	var ret = '';
	var index = 0;
	for(var key in area)
	{
		if (areastr.indexOf(key) >= 0)
		{
			ret += '<label><input name="province'+id+'" type="checkbox" value="'+key+'" checked="checked"/>'+key+'  </label>';
		}
		else
		{
			ret += '<label><input name="province'+id+'" type="checkbox" value="'+key+'" />'+key+'  </label>';
		}
		index ++;
		var mod = index % 8;
		if (mod ==0)
		{
			ret += '<br /><br />';
		}
	}
	return ret;
}

var getArea = function(id)
{
	var ret = "";
	var c = document.getElementsByName("province" +id);
	for(i=0;i<c.length;i++)
	{
	   if(c[i].checked == true )
	   {
			v  = c[i].value;
			ret += defaultstr(c[i].value) + "#";
	   }
	}
	return ret;
}

var insertArea = function(channel, id) {
	$.reqPostData(
	SERVERURL + '192', 
	{
		reqType : 'F9902007',
		channel: channel,
		area: getArea(id)
	}, 
	function(data) 
	{
		alert("保存成功");
	});
};

var editArea = function(id) {
	$.reqPostData(
	SERVERURL + '193', 
	{
		reqType : 'F9902007',
		id : id,
		area: getArea(id)
	}, 
	function(data) 
	{
		alert("保存成功");
	});
};

//var pager={id:"result",totalrows:0,pagesize:10,currentpage:0};
/*
 * 加载支付方式
 * 
 */
var loadData = function(pager) {
	pager = getPager(pager);

	$('#dataList').empty();
	$.reqPostData(SERVERURL + '183', {
		reqType : 'F9901015',
		startDate : $('#startDate').val(),
		pagesize : pager.pagesize,
		currentpage : pager.currentpage,
		totalRows : pager.totalrows
	}, function(data) {
		$('#dataList').empty();
		if (data && data.rows) {
			$.each(data.rows, function(i, row) {
				$('#dataList').append(
						'<tr><td>' + row.date + '</td><td align="left">' + row.name
								+ '</td><td>' + row.count
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
};


/***
 * 查询
 */
var doQuery = function(pager) {

	//if (cpid != '' && parseInt(cpid) > 0){
	pager = getPager(pager);
	$.reqPostData(SERVERURL + '183', {
		reqType : 'F9902009',
		startDate : $('#startDate').val(),
		pagesize : pager.pagesize,
		currentpage : pager.currentpage,
		totalRows : pager.totalrows
	}, function(data) {
		//alert('保存成功');
		$('#dataList').empty();
		if (data && data.rows) {
			$.each(data.rows, function(i, row) {
				$('#dataList').append(
						'<tr><td>' + row.date + '</td><td align="left">' + row.name
								+ '</td><td>' + row.count
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
/***
 * 导出
 */
var doExport = function() {

	var cpid = $('#cpid').val();
	if (cpid != '' && parseInt(cpid) > 0) {
		$.expPostData( {
			reqType : 'F9999003',
			cpid : $('#cpid').val(),
			startDate : $('#startDate').val(),
			endDate : $('#endDate').val(),
			cpcid : $('#channel').val(),
			cppid : $('#product').val(),
			cpppoint : $('#point').val(),
			paytype : $('#paytype').val()
		});
	} else {
		alert("请至少选择一个cp分析!");
	}

};
/***
 * 返回上一步
 */
var doGoBack = function() {

	$('#datainfo').removeClass('none');
	$('#detailinfo').addClass('none');
};
//初始化明细数据
function initdetail(date, cpcid, cppid, cppaypoint, paytype) {

	$('#datainfo').addClass('none');
	$('#detailinfo').removeClass('none');
	var html = defaultstr(paramchannel[cpcid]);
	if (defaultstr(paramproduct[cppid])) {
		if (html != '') {
			html += '——';
		}
		html += defaultstr(paramproduct[cppid]);
	}
	if (defaultstr(parampaypoint[cppaypoint])) {
		if (html != '') {
			html += '——';
		}
		html += defaultstr(parampaypoint[cppaypoint]);
	}
	if (defaultstr(parampaytype[paytype])) {
		if (html != '') {
			html += '——';
		}
		html += defaultstr(parampaytype[paytype]);
	}
	detaildate = defaultstr(date);
	detailcpcid = defaultstr(cpcid);
	detailcppid = defaultstr(cppid);
	detailcppaypoint = defaultstr(cppaypoint);
	detailpaytype = defaultstr(paytype);
	$('.interval').html('<span>日期：' + date + '</span>统计类型：' + html);
	$.reqPostData( {
		reqType : 'F9902010',
		cpid : $('#cpid').val(),
		date : defaultstr(date),
		cpcid : defaultstr(cpcid),
		cppid : defaultstr(cppid),
		cpppoint : defaultstr(cppaypoint),
		paytype : defaultstr(paytype)
	}, loaddetail);
}

//加载明细数据
var loaddetail = function(data) {

	$('#showdetaildata').empty();
	$('#sumdetaildata').empty();
	if (data && data.rows) {
		var sumusercount = 0;
		var sumcount = 0;
		var sumuserpay = 0;
		var arr = new Array();
		$
				.each(
						data.rows,
						function(i, row) {
							sumusercount += defaultint(row.usercount);
							sumcount += defaultint(row.paycount);
							sumuserpay += defaultfloat(row.money);
							arr.push(defaultfloat(row.money));
							$('#showdetaildata')
									.append(
											'<tr><td>'
													+ detailtime[row.date]
													+ '</td><td>'
													+ defaultint(row.usercount)
													+ '</td><td>'
													+ (defaultint(row.usercount) > 0 ? (defaultfloat(row.money) / defaultint(row.usercount))
															.toFixed(2)
															: 0)
													+ '</td><td>'
													+ defaultint(row.paycount)
													+ '</td><td>'
													+ (defaultint(row.paycount) > 0 ? (defaultfloat(row.money) / defaultint(row.paycount))
															.toFixed(2)
															: 0) + '</td><td>'
													+ defaultfloat(row.money)
													+ '</td>');
						});
		$('#sumdetaildata')
				.append(
						'<tr><td>24</td><td>'
								+ defaultint(sumusercount)
								+ '</td><td>'
								+ (defaultint(sumusercount) > 0 ? (defaultfloat(sumuserpay) / defaultint(sumusercount))
										.toFixed(2)
										: 0)
								+ '</td><td>'
								+ defaultint(sumcount)
								+ '</td><td>'
								+ (defaultint(sumcount) > 0 ? (defaultfloat(sumuserpay) / defaultint(sumcount))
										.toFixed(2)
										: 0) + '</td><td>'
								+ defaultfloat(sumuserpay).toFixed(2) + '</td>');
		var series = [ {
			name : '交易金额',
			data : arr
		} ];
		options['series'] = series;
		new Highcharts.Chart(options);
	}
};
/*初始化**/
$(document).ready(function() {
	//初始化日期插件
		var date = new Date();
		date.setDate(date.getDate() - 1);
		$('#startDate').val(getDateStr(date));

		//$('#startDate').datepicker({
		//	 dateFormat: 'yy-mm-dd'	
		//});
		//$('#endDate').datepicker({
		//	dateFormat: 'yy-mm-dd'
		//});
		//初始化支付方式
		loadChannel();
		loadArea();
		loadChannelArea();
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
