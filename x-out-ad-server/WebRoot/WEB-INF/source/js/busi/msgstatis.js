/*
 * 统计脚本
 */
/**全局变量*/
var paramchannel = {};
var paramproduct = {};
var parampaytype = {};
var parampaypoint = {};
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

//var pager={id:"result",totalrows:0,pagesize:10,currentpage:0};
/*
 * 加载支付方式
 * 
 */
var loadData = function(pager) {
	pager = getPager(pager);

	$('#dataList').empty();
	$.reqPostData(SERVERURL + '181', {
		reqType : 'F9901015',
		startDate : $('#startDate').val(),
		endDate : $('#endDate').val(),
		channel : $('#channel').val(),
		pagesize : pager.pagesize,
		currentpage : pager.currentpage,
		totalRows : pager.totalrows
	}, function(data) {
		$('#dataList').empty();
		if (data && data.rows) {
			$.each(data.rows, function(i, row) {
				$('#dataList').append(
						'<tr><td>' + row.date + '</td><td align="left">' + row.msgid + ":" +row.msgname
								+ '</td><td>' + row.count
								+ '</td><td>' + row.channel + '</td></tr>');
				//$('#dataList').append('<tr><td>'+row.date+'</td><td>'+paramchannel[defaultstr(row.cpcid)]+'</td><td>'+parampro//duct[defaultstr(row.cppid)]+'</td><td>'+parampaypoint[defaultstr(row.cppaypoint)]+'</td><td>'+parampaytype[def//aultstr(row.paytype)]+'</td><td>'+row.usercount+'</td><td>'+row.paycount+'</td><td>'+defaultfloat(row.money)+'//</td><td><a href="#" //onclick="initdetail(\''+row.date+'\','+row.cpcid+','+row.cppid+','+row.cppaypoint+','+row.paytype+')">详细</a>//</td></tr>');
				});
		} else {
			$('#dataList').append('<tr><td colspan="4">暂无数据</td></tr>');
		}

		$.pager( {
			id : "result",
			totalrows : data.totalrows,
			pagesize : data.pagesize,
			currentpage : data.currentpage
		}, loadData);
	});
};

var deleteItem = function(id) {
	//$('#dataList').empty();
	$.reqPostData(SERVERURL + '110', {
		reqType : 'F9901015',
		type : '2',
		id : id
	}, function(data) {
		alert('删除成功');
		loadMsg()
	});
};


/***
 * 根据渠道联动cp选择
 */
var showCpList = function() {

	var value = $('#src').val();
	$('#cp').empty();
	$('#cp').append('<option value="-1" class="goods_select">全部</option>');
	if (value != '-1') {
		$.reqPostData( {
			reqType : 'F9902011',
			"srcId" : value
		}, function(data) {

			if (data && data.cpList) {
				cpOptionHtml = '';
				$.each(data.cpList, function(i, cp) {
					var text = cp.name;
					if (cp.level)
						text = cp.name + '';
					cpOptionHtml += "<option value='" + cp.id + "'>" + text
							+ "</option>";
				});
				$('#cp').append(cpOptionHtml);
			}
		});

	}
	setCpValue();

};
/***
 * 设置cp
 */
var setCpValue = function() {

	var value = $('#cp').val();
	var cpid = $('#cpid').val();
	if (value != '-1') {
		if (cpid != value) {
			$('#cpid').val(value);
			//初始化
			loadCpCondtion(value);
		}
	} else {
		$('#cpid').val('');
		$('#channel').empty();
		$('#channel').append(
				'<option value="-1" class="goods_select">全部</option>');
		$('#paytype').empty();
		$('#paytype').append(
				'<option value="-1" class="goods_select">全部</option>');
		$('#point').empty();
		$('#point').append(
				'<option value="-1" class="goods_select">全部</option>');
		$('#product').empty();
		$('#product').append(
				'<option value="-1" class="goods_select">全部</option>');
	}

};
/****
 * 根据特定的cp初始化查询条件
 *
 */
/*
var loadCpCondtion = function (cpid){

//设置隐藏域
$('#cpid').val(cpid);
$.reqPostData({
	"reqType":"F9902008",
	"cpid":cpid
},function(data){
	
	if (data){
		if(data.channels){
		    $.each(data.channels,function(i,channel){
		       if(channel.id&&channel.name){
		    	 paramchannel[channel.id]=channel.name;
		    	  if(channel.status>=0){
			        $('#channel').append('<option value="'+channel.id+'">'+channel.name+'</option>');
			      }
			   }
		    });
		}
		if(data.products){
		    $.each(data.products,function(i,product){
		       if(product.id&&product.name){
		    	  paramproduct[product.id]=product.name;
		    	   if(product.status>=0){
			         $('#product').append('<option value="'+product.id+'">'+product.name+'</option>');
			      }
			   }
		    });
		}
		if(data.paytype){
		    $.each(data.paytype,function(i,paytype){
		       if(paytype.id&&paytype.name){
		    	  parampaytype[paytype.id]=paytype.name;
		    	  if(paytype.status>=0){
			        $('#paytype').append('<option value="'+paytype.id+'">'+paytype.name+'</option>');
			      }
			   }
		    });
		}
		if(data.pointlist){
		    $.each(data.pointlist,function(i,point){
		       if(point.id&&point.name){
		    	  parampaypoint[point.id]=point.name;
		    	  if(point.status>=0){
			          $('#point').append('<option value="'+point.id+'">'+point.name+'</option>');
			          pontlist.push({cppid:point.cppid,id:point.id,name:point.name});
			      }
			   }
		    });
		}
	}
});
};

//切换商品
var changeProduct = function (productid) {
if(productid&&pontlist&&pontlist.length>0){
	$('#point').empty();
	$('#point').append('<option value="-1" class="goods_select">全部</option>');
	$.each(pontlist,function(i,point){
		if(productid==point.cppid||productid==0){
			 $('#point').append('<option value="'+point.id+'">'+point.name+'</option>');
		}
	});
}
};
 */
/***
 * 查询
 */
var doQuery = function(pager) {

	//if (cpid != '' && parseInt(cpid) > 0){
	pager = getPager(pager);
	$.reqPostData(SERVERURL + '181', {
		reqType : 'F9902009',
		startDate : $('#startDate').val(),
		endDate : $('#endDate').val(),
		channel : $('#channel').val(),
		pagesize : pager.pagesize,
		currentpage : pager.currentpage,
		totalRows : pager.totalrows
	}, function(data) {
		//alert('保存成功');
		$('#dataList').empty();
		if (data && data.rows) {
			$.each(data.rows, function(i, row) {
				$('#dataList').append(
						'<tr><td>' + row.date + '</td><td align="left">' + row.msgid + ":" + row.msgname
								+ '</td><td>' + row.count
								+ '</td><td>' + row.channel + '</td></tr>');
				//$('#dataList').append('<tr><td>'+row.date+'</td><td>'+paramchannel[defaultstr(row.cpcid)]+'</td><td>'+parampro//duct[defaultstr(row.cppid)]+'</td><td>'+parampaypoint[defaultstr(row.cppaypoint)]+'</td><td>'+parampaytype[def//aultstr(row.paytype)]+'</td><td>'+row.usercount+'</td><td>'+row.paycount+'</td><td>'+defaultfloat(row.money)+'//</td><td><a href="#" //onclick="initdetail(\''+row.date+'\','+row.cpcid+','+row.cppid+','+row.cppaypoint+','+row.paytype+')">详细</a>//</td></tr>');
				});
		} else {
			$('#dataList').append('<tr><td colspan="4">暂无数据</td></tr>');
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
		$('#endDate').val(getDateStr(new Date()));

		//$('#startDate').datepicker({
		//	 dateFormat: 'yy-mm-dd'	
		//});
		//$('#endDate').datepicker({
		//	dateFormat: 'yy-mm-dd'
		//});
		//初始化支付方式
		loadData();
		//根据角色初始化余下条件
		loadChannel2();
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
