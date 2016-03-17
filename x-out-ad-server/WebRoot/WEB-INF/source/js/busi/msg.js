/*
 * 统计脚本
 */
/**全局变量*/


var initVariable = function()
{
	var date = new Date();
	date.setDate(date.getDate() + 1);
		
	$('#title').val("");
	$('#content').val("");
	$('#imgurl').val("");
	$('#apkurl').val("");
	$('#weight').val("100");
	$('#count').val("100000");
	$('#allpush').val("1");
	$('#allpush').val("1");
	$('#network').val("1"),
	$('#startDate').val(getDateStr(new Date()));
	$('#startHour').val("00");
	$('#endDate').val(getDateStr(date));
	$('#endHour').val("00");
	if (adminuser == 1)
	{
		$('#channel').val("ALL");
	}
	else
	{
		var ch = paramchannels[0]
		$('#channel').val(ch.id);
	}
	$('#area').val("ALL");
	$('#download').val("1");
	$('#showconfrim').val("1");
	$('#sex').val("ALL");
};


var doSave = function(pager) {

	var title=$('#title').val();
	var content=$('#content').val();
	var imgurl=$('#imgurl').val();
	var apkurl=$('#apkurl').val();
    if(!title||$.trim(title).length==0){
		alert('请输入推送的标题');
		return;
    }
	if(!content||$.trim(content).length==0){
		alert('请输入推送的内容');
		return;
	}
	if(!imgurl||$.trim(imgurl).length==0){
		alert('请输入推送的图标下载地址');
		return;
	}
	if(!apkurl||$.trim(apkurl).length==0){
		alert('请输入推送的apk下载地址');
		return;
	}
	//if (cpid != '' && parseInt(cpid) > 0){
	pager = getPager(pager);
	$.reqPostData(SERVERURL + '105', {
		reqType : 'F9902009',
		title : $('#title').val(),
		content : $('#content').val(),
		imgurl : $('#imgurl').val(),
		apkurl: $('#apkurl').val(),
		weight : $('#weight').val(),
		count : $('#count').val(),
		allpush: $('#allpush').val(),
		network : $('#network').val(),
		startDate:$('#startDate').val(),
		startHour:$('#startHour').val(),
		endDate:$('#endDate').val(),
		endHour:$('#endHour').val(),
		channel: $('#channel').val(),
		area: $('#area').val(),
		download: $('#download').val(),
		showconfrim: $('#showconfrim').val(),
		sex: $('#sex').val(),
		pagesize : pager.pagesize,
		currentpage : pager.currentpage,
		totalRows : pager.totalrows
	}, function(data) {
		alert('保存成功');
		setTimeout('$("#calendar").fullCalendar("refetchEvents")', 2000);
	});
};

var doEdit = function(id, pager) {

	var title=$('#title').val();
	var content=$('#content').val();
	var imgurl=$('#imgurl').val();
	var apkurl=$('#apkurl').val();
    if(!title||$.trim(title).length==0){
		alert('请输入推送的标题');
		return;
    }
	if(!content||$.trim(content).length==0){
		alert('请输入推送的内容');
		return;
	}
	if(!imgurl||$.trim(imgurl).length==0){
		alert('请输入推送的图标下载地址');
		return;
	}
	if(!apkurl||$.trim(apkurl).length==0){
		alert('请输入推送的apk下载地址');
		return;
	}
	//if (cpid != '' && parseInt(cpid) > 0){
	pager = getPager(pager);
	$.reqPostData(SERVERURL + '105', {
		reqType : 'F9902009',
		title : $('#title').val(),
		content : $('#content').val(),
		imgurl : $('#imgurl').val(),
		apkurl: $('#apkurl').val(),
		weight : $('#weight').val(),
		count : $('#count').val(),
		allpush: $('#allpush').val(),
		network : $('#network').val(),
		startDate:$('#startDate').val(),
		startHour:$('#startHour').val(),
		endDate:$('#endDate').val(),
		endHour:$('#endHour').val(),
		channel: $('#channel').val(),
		area: $('#area').val(),
		download: $('#download').val(),
		showconfrim: $('#showconfrim').val(),
		sex: $('#sex').val(),
		id : id,
		pagesize : pager.pagesize,
		currentpage : pager.currentpage,
		totalRows : pager.totalrows
	}, function(data) {
		alert('编辑成功');
	});
};

/*初始化**/
$(document).ready(function() {
	/* initialize the external events
		-----------------------------------------------------------------*/
	    // #external-events div.external-event
		var date = new Date();
		date.setDate(date.getDate() + 1);
		$('#startDate').val(getDateStr(new Date()));
		$('#endDate').val(getDateStr(date));
		
		/*
		$('#auto-block a').each(function() {
		
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};
			
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
			
		});
		*/
	    loadChannel();
		
		/* initialize the calendar
		-----------------------------------------------------------------*/
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month'
			},
			editable: true,
			//droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped
			
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				
				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
				
			},
			//事件函数
			events:  function(start, end , callback){//生成日历
				loadEvents(start, end, "1", callback);                
			},
			//单日点击
			dayClick: function(date, allDay, jsEvent, view) {
				var selectdate = $.fullCalendar.formatDate(date, "yyyy-MM-dd");
				initVariable();
				var dialogParent = $("#section_entry").parent();
				var dialogOwn = $("#section_entry").clone();
				$('#startDate').val(getDateStr($.fullCalendar.parseDate(date, "yyyy-MM-dd")));
				$('#endDate').val(getDateStr($.fullCalendar.parseDate(date, "yyyy-MM-dd")));
				dialogOwn.hide(); 
				$( "#section_entry" ).dialog({
					autoOpen: false,
					height: 570,
					width:800,
					title: '任务 ' + selectdate,
					modal: true,
					position: "center",
					draggable: false,
					close: function() {		
						dialogOwn.appendTo(dialogParent); 
						$(this).dialog("destroy").remove();},
					buttons: {
						"关闭": function() {
							$(this).dialog( "close" );
						},
						"确认": 
						function() {
							var title=$('#title').val();
							var content=$('#content').val();
							var imgurl=$('#imgurl').val();
							var apkurl=$('#apkurl').val();
							if(!title||$.trim(title).length==0){
								alert('请输入推送的标题');
								return;
							}
							if(!content||$.trim(content).length==0){
								alert('请输入推送的内容');
								return;
							}
							if(!imgurl||$.trim(imgurl).length==0){
								alert('请输入推送的图标下载地址');
								return;
							}
							if(!apkurl||$.trim(apkurl).length==0){
								alert('请输入推送的apk下载地址');
								return;
							}
							doSave();
							$(this).dialog( "close" );
						}
					}
				});
				$("#section_entry").dialog( "open" );
				return false;
			},
			//事件点击
			eventClick: function(event, jsEvent, view) {
				if (event.url) {
					return 1;
				}
				pager = getPager();
				$.reqPostData(SERVERURL + '112', {
				reqType : 'F9901015',
				type : '1',
				id: event.id,
				pagesize : pager.pagesize,
				currentpage : pager.currentpage,
				totalRows : pager.totalrows
				}, function(data) {
					if (data && data.event) {
						$('#title').val(data.event.title);
						$('#content').val(data.event.content);
						$('#imgurl').val(data.event.img_link);
						$('#apkurl').val(data.event.link);
						$('#weight').val(data.event.weight);
						$('#count').val(data.event.push_count);
						$('#allpush').val(data.event.all_push);
						$('#network').val(data.event.network),
						$('#startDate').val(getDateStr($.fullCalendar.parseDate(data.event.begintime, "yyyy-MM-dd")));
						$('#startHour').val(getHourStr($.fullCalendar.parseDate(data.event.begintime, "HH")));
						$('#endDate').val(getDateStr($.fullCalendar.parseDate(data.event.endtime, "yyyy-MM-dd")));
						$('#endHour').val(getHourStr($.fullCalendar.parseDate(data.event.endtime, "HH")));
						$('#channel').val(data.event.channel);
						$('#area').val(data.event.area);
						$('#download').val(data.event.down_direct);
						$('#showconfrim').val(data.event.open);
						$('#sex').val(data.event.sex);
					}
					
				});
				var selectdate = $.fullCalendar.formatDate(event.start, "yyyy-MM-dd");
				var dialogParent = $("#section_entry").parent();
				var dialogOwn = $("#section_entry").clone();
				dialogOwn.hide(); 
				$( "#section_entry" ).dialog({
					autoOpen: false,
					height: 570,
					width:800,
					title: '任务 ' + selectdate,
					modal: true,
					position: "center",
					draggable: false,
					close: function() {		
						dialogOwn.appendTo(dialogParent); 
						$(this).dialog("destroy").remove();},
					buttons: {
						"关闭": function() {
							$(this).dialog( "close" );
						},
						"编辑": function() {
							var title=$('#title').val();
							var content=$('#content').val();
							var imgurl=$('#imgurl').val();
							var apkurl=$('#apkurl').val();
							if(!title||$.trim(title).length==0){
								alert('请输入推送的标题');
								return;
							}
							if(!content||$.trim(content).length==0){
								alert('请输入推送的内容');
								return;
							}
							if(!imgurl||$.trim(imgurl).length==0){
								alert('请输入推送的图标下载地址');
								return;
							}
							if(!apkurl||$.trim(apkurl).length==0){
								alert('请输入推送的apk下载地址');
								return;
							}
							doEdit(event.id);
							event.title = $('#title').val();
							event.start = $.fullCalendar.parseDate($('#startDate').val() + "T" + $('#startHour').val() +":00:00+08:00");
							event.end = $.fullCalendar.parseDate($('#endDate').val() + "T" + $('#endHour').val()+":00:00+08:00");
							$('#calendar').fullCalendar('updateEvent', event);
							$(this).dialog( "close" );
						},
						"删除": function() {
							if (confirm('确认删除?')) {
								deleteEvent(event.id);
								$(this).dialog( "close" );
								$("#calendar").fullCalendar("removeEvents",event.id);
							}
						}
					}
				});
				$("#section_entry").dialog( "open" );
				return false;
				
			},
			eventRender: function(event, element) {
				var start = $.fullCalendar.formatDate(event.start, "HH");
				var end = $.fullCalendar.formatDate(event.end, "HH");
				element.html("<div class='fc-event-inner'><span class='fc-event-title'>"+start+"~" + end + " "+event.title+ "<br>" + event.description + "</span></div><div class='fc-event-bg'></div>");
			},
			//事件移动
			eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
				/*
				timeChanged = (dayDelta * 24 * 60 + minuteDelta) * 60;
				$.ajax({
				   type: "POST",
				   url: "http://test.uacool.com/newserver/edit",
				   data: "id="+event.id+"&timeChanged="+timeChanged,
				   dataType: 'json',
				   success: function(msg){
				   		if (msg.error>0) alert('失败 ' + msg.msg);
				   }
				});
				// TODO : 更新事件
				setTimeout('$("#calendar").fullCalendar("refetchEvents")', 2000);
				*/
			},
			//事件缩放
			eventResize: function( event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ) {
			},
			
			 eventSources: [

        // your event source
				{
					events: function(start, end, callback) {
                // ...
				},
				color: 'yellow',   // an option!
				textColor: 'black' // an option!
				}

        // any other sources...

			]
		});
	});
