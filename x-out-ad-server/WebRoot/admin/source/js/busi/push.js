
var paramchannels = new Array();
var channelsmap = {};
var paramareas= new Array();
var areasmap={};
var adminuser = 0;

var loadEvents = function(start, end, type, callback) {
	pager = getPager();
	var events = [];
	$.reqPostData(SERVERURL + '130', {
		reqType : 'F9901015',
		type : type,
		start: $.fullCalendar.formatDate(start, "yyyy-MM-dd hh:mm"),
		end: $.fullCalendar.formatDate(end, "yyyy-MM-dd hh:mm"),
		pagesize : pager.pagesize,
		currentpage : pager.currentpage,
		totalRows : pager.totalrows
	}, function(data) {
		if (data && data.rows) {
			$.each(data.rows, function(i, row) {
				events.push({
                        sid: row.id,
                        uid: row.id,
                        title: row.title,
                        start: $.fullCalendar.parseDate(row.begintime),
                        end: $.fullCalendar.parseDate(row.endtime),
                        fullname: row.link,// 此处改变，列表和明细都会改变
                        //confname: row.channel,
                        //groupPersons: row.link,
                        //confshortname: row.link,
                        confcolor: '#ff3f3f',
                        //confid: 'test1',
                        allDay: false,
                        topic: row.title,
                        description : row.channel,
                        id: row.id
                    });
					}
					)
					
					callback(events);//
					}
					
					$('#calendar').fullCalendar('updateEvent', events);
					//setTimeout('$("#calendar").fullCalendar("refetchEvents")', 2000);
				});                        
};

var deleteEvent = function(id) {
	//$('#dataList').empty();
	$.reqPostData(SERVERURL + '110', {
		reqType : 'F9901015',
		id : id
	}, function(data) {
		alert('删除成功')
		//loadMsg()
	});
};

/*
 * 校验角色并初始化cp 渠道列表 roleid
 * 当前cp 返回cpid 
 * cp管理员 返回 cplist
 * 运营返回 src列表
 * 
 * **/
var loadChannel = function() {
    loadArea();
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
						'<option value="ALL" class="goods_select">全部</option>');
				}
				cpOptionHtml = '';
				$.each(data.channels, function(i, channel) {
					paramchannels.push({id:channel.channel,name:channel.channelname});
					channelsmap[channel.channel] = channel.channelname;
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

	$.reqPostData(
	SERVERURL + '190', 
	{
		reqType : 'F9902007'
	}, 
	function(data) 
	{
		if (data) {

			if (data.rows) {//渠道列表
				$('#area').empty();
				if (data.is_admin == 1)
				{
					adminuser = 1;
					$('#area').append(
						'<option value="ALL" class="goods_select">全部</option>');
				}
				cpOptionHtml = '';
				$.each(data.rows, function(i, area) {
					paramareas.push({id:area.id,name:area.province});
					areasmap[area.area] = area.province;
					cpOptionHtml += "<option value='" + area.id
							+ "'>" + area.province + "</option>";
				});
				$('#area').append(cpOptionHtml);

			} else {//获取当前cp

			}
			
			
		}
	});
};

/***
 * 根据加载app列表
 */
var loadAppList = function() {


	$('#appList').empty();

	$
			.reqPostData(
					SERVERURL + "220",
					{
						reqType : 'F9902011'
					},
					function(data) {
						if ($('#appList').is(":hidden")) {
							$('#appList').show();
						} else {
							$('#appList').hide();
						}
						if (data) {
							html = '';
							html += "<h1 class='app_h1'><span class='modify_layer_left'>选择应用</span><span class='modify_img modify_layer_right'><a href='javascript:void(0)' onclick='closeAppList()'><img src='./source/images/close.jpg' /></a></span></h1>";
							
							//html += "<td align='right'>选择应用</td>";
							//html += "<td colspan=5>";

							html += "<div class='content' id='content'>";
							if (data && data.priapps) {
								alert("私有产品");
								html += "<h1 class='title', id='title'>私有产品</h1>";
								html += "<ul class='appul'>";
								$
										.each(
												data.priapps,
												function(i, app) {
													html += "<li>";
													html += "<a href='javascript:void(0)' onclick='selectApp(\""+app.file_url+"\",\"" + app.icon_url+"\")'><img src='"
															+ app.icon_url
															+ "' width='72' height='72'></img> </a>";
													html += "<span>"
															+ app.app_name
															+ "<br /></span>";
													html += "</li>";
												});

								html += "</ul>";
							}

							html += "<ul class='lay_ul none' id='privUl'>";

							html += "</ul>";

							if (data && data.pubapps) {
								alert("公有产品");
								html += "<h1 class='title' id ='title'>公有产品</h1>";
								html += "<ul class='appul'>";
								$
										.each(
												data.pubapps,
												function(i, app) {
													html += "<li>";
													html += "<a href='javascript:void(0)' onclick='selectApp(\""+app.file_url+"\",\"" + app.icon_url+"\")'><img src='"
															+ app.icon_url
															+ "' width='72' height='72'></img> </a>";
													html += "<span>"
															+ app.app_name
															+ "<br /></span>";
													html += "</li>";
												});

								html += "</ul>";
							}
							html += "</div>";
							//html += "</td>";
							$('#appList').append(html);
						}
					});

};

var closeAppList = function() 
{
	$('#appList').empty();
	$('#appList').hide();
}

var selectApp = function(fileurl, iconurl) 
{
	$('#appList').empty();
	$('#appList').hide();
	var imgurl = $('#imgurl').val();
	var apkurl = $('#apkurl').val();
	//if (!imgurl || $.trim(imgurl).length == 0) 
	{
		$('#imgurl').val(iconurl);
	}
	//if (!apkurl || $.trim(apkurl).length == 0) 
	{
		$('#apkurl').val(fileurl);
	}
}
