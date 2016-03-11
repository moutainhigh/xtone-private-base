var currentelement;
jQuery.reqPostData = function (httpUrl, rdata,sfunction) {
	try{
      $.ajax({
           type:"POST", 
           url:httpUrl, 
           data:encodeURI(stringify(rdata)),
           timeout: 50000,
           success:function (json) {
           if (json.result == REQUEST_SUCESS) {
        	   if(sfunction){
                 sfunction(json);
               }
              } else {
                  $.each(REQUEST_RESULT, function (i) {
                        if (i == json.result) {
                           if(i=='-4'){
                             window.location.href='/hlpads/admin/login.jsp';
                           }else{
                        	   alert(REQUEST_RESULT[i]);
                           }
                        }
                  });
        }
      },
      error:function(json){
    	  alert('连接超时!');
      },dataType:"json"});
    }catch(e){
    	alert(e);
    }
};
jQuery.expPostData = function (rdata) {
	var param='t='+new Date().getTime();
	$.each(rdata,function(key,value){
		if(value&&$.trim(value).length>0){
		 param+='&'+key+'='+value;
		}
	});
	window.open(SERVERURL+'?'+param)
};
jQuery.pager = function (pager,sfunction) {
	$('.classify').remove();
	if(!pager||!pager.totalrows){
		return;
	}
	if(!pager.pagesize){
		pager.pagesize=PAGESIZE;
	}
	if(!pager.currentpage){
		pager.currentpage=1;
	}
	if(!pager.id){
		pager.id='goods';
	}
	var pageno=Math.ceil(pager.totalrows/pager.pagesize);
	var html='<div class="classify"><div class="page_left sec_modify_label"><label>每页显示：</label><select id="pagesize" class="select select62"><option value="10" '+(pager.pagesize==10?"selected":"")+'>10</option><option value="20" '+(pager.pagesize==20?"selected":"")+'>20</option><option value="30" '+(pager.pagesize==30?"selected":"")+'>30</option></select><label>条</label></div><div class="page_right sec_modify_label02"><label>共'+pager.totalrows+'条</label> '+(pager.currentpage>1?'<a href="####" id="pager_up">上一页</a> ':'')+'<label>第'+pager.currentpage+'/'+pageno+'页</label> '+(pager.currentpage<pageno?'<a href="####" id="pager_down">下一页</a>':'')+' <label>跳转到第</label> <span class="sec_input_width"><input type="text" id="pageno" class="sec_modify_int" /></span> <label>页</label> <a href="####" class=" text confirm sec_confirm" id="pager_select">确定</a></div><div class="clear"></div></div>';
	$('.'+pager.id).append(html);
	$('#pagesize').change(function(){
		    sfunction({currentpage:1,pagesize:$('#pagesize').val(),totalrows:pager.totalrows});
	});
	$('#pager_up').click(function(){
		if(pager.currentpage==1){
		    alert('现在已是首页');return;
	    }else{
			//pager.currentpage = pager.currentpage-1;
		    sfunction({currentpage:pager.currentpage - 1,pagesize:$('#pagesize').val(),totalrows:pager.totalrows});
		}
	});
	$('#pager_down').click(function(){
		if(pager.currentpage==pageno){
		    alert('现在已是尾页');return;
	    }else{
			//pager.currentpage = pager.currentpage+1;
		    sfunction({currentpage:pager.currentpage + 1,pagesize:$('#pagesize').val(),totalrows:pager.totalrows});
		}
	});
	$('#pager_select').click(function(){
		if(!isInt($('#pageno').val())){
			alert('请输入你要跳转到的页码');return;
		}else if($('#pageno').val()>pageno){
			alert('输入的页码不能大于'+pageno);return;
		}else if($('#pageno').val()<1){
			alert('输入的页码不能小于1');return;
		}else{
			//pager.currentpage = $('#pageno').val();
			sfunction({currentpage:$('#pageno').val(),pagesize:$('#pagesize').val(),totalrows:pager.totalrows});
		}
	});
};
function stringify(obj) {
    var t = typeof (obj); 
    if (t != "object" || obj == null) { 
        if (t == "string") obj = '"'+obj+'"'; 
        obj = obj.replace(/[\n\r]/gi,"");
        return String(obj); 
    }else { 
        var n, v, json = [], arr = (obj && obj.constructor == Array);
        if(!arr){
           for (n in obj) { 
              v = obj[n]; 
              t = typeof(v); 
              if (t == "string") {
            	   if(v.match(/[\"\'\<\>]/gi)){throw('输入的内容不能包含\" \'<>字符');}
            	   v = '"'+v+'"';
            	   //v = v.replace(/[\n\r]/gi,"");
              }else if (t == "object" && v !== null) {
            	  v = stringify(v);
              } 
              json.push( '"' + n + '":' + String(v)); 
        }
        }else{
        	for(var i=0;i<obj.length;i++)
        	{
        		json.push(stringify(obj[i])); 
        	}
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}"); 
    } 
};
jQuery.fn.extend({
   getForm:function(options){  
        var settings = jQuery.extend({  
            key:'',
            data:{},
            functionid:'',
            src:REQUEST_SRC
        },options);  
        var ret =settings.data;
        var args=[];
        if(this.get(0).tagName.toLowerCase()=='form'){  
            var that =jQuery(this);
            if(settings.data){
            $.each(settings.data, function(key, val) {
             ret[key] = val;
            });
            }
            that.find('input,select,radio,textarea').each(function(){  
                var el = jQuery(this);
                var fieldName = el.attr('name');
                if(fieldName){
                  if(settings.key&&settings.key==el.val()){
                     var temp ={};
                     temp[fieldName] = el.val();  
                     args.push(temp);
                  }else{
                     ret[fieldName] = el.val();
                  }
                }
            });
        if(settings.key&&args.length>0)
          ret[settings.key] = args;
        }
        return ret;  
    }  
});
function loadMenu(data){
   $.each(data.menus, function(i, menus) {
	    var menustr = '<div class="tive"><h1 class="title_h1"><a href="#" class="add'+(i==0?" add2":"")+'">';
        menustr+=menus.name;
        menustr+='</a></h1><div class="auto'+(i==0?" block":"")+'"><ul class="oul">';
        if(menus.children){
        var children=eval(menus.children);
        $.each(children, function(j, ssm) {
        if(ssm&&ssm.pid==menus.id){
        	if(ssm.url){
               if(ssm.url.indexOf("?")<=0){
        	    	ssm.url=ssm.url+'?t='+new Date().getTime();
               }else{
        	        ssm.url=ssm.url+"&t="+new Date().getTime();
               }
            }
			// class="a'+(i%3+1)+'_'+(j%5+1)+'"
           menustr += '<li class="a'+(i%3+1)+'_'+(j%5+1)+'" id="mentu_'+ssm.id+'"><span class="sp"><a href="#" onclick="addTab(\''+ssm.id+'\',\''+ssm.name+'\',\''+ssm.url+'\')" >' + ssm.name
                    + '</a></span></li>';
         }
        });        
        menustr += '</ul></div>';
        }
         $("#left").append(menustr);
    });
    bindLefts();
}
function initMenu(){
$.reqPostData(SERVERURL+'102', {reqType:'F9900005'},function(data){
	if(data){
		if(data.username){
			$('.tal_right').find('span').html('您好，'+data.username);
		}
		if(data.lastlogin){
			$('.motel').find('strong').html(data.lastlogin);
		}
	}
	$.reqPostData(SERVERURL+'103',{reqType:'F9900002'},loadMenu);
});	
}
function bindLefts(){
	var tives=$('#left .tive');
	$.each(tives, function(i, tive) {
		 $(tive).find('h1').bind('click',function(event){
			  var classname=$(this).find('a').attr('class');
              if(classname){
                  if(classname=="add add2"){
        	          $(this).find('a').removeClass('add2')
                  	  if($(this).next()){
        	              $(this).next().removeClass().addClass('auto');
        	          }
		           }else if(classname=="add"){
			          $(this).find('a').addClass('add2')
			          if($(this).next()){
        		        $(this).next().removeClass().addClass('auto block');
        	          }
		           }
              }
		 });
	});
}
function addTab(tabCounter,tabTitle,href){
	var element=$('#mentu_'+tabCounter).find('span')[0];
	if(element){
		if(currentelement){
			currentelement.removeClass('b');
		}
		currentelement=$(element);
		currentelement.addClass('b');
	}
	$('.default').hide;
	if (!href.startWith("http")) {
		$("#right").load('/hlpads/admin/html/'+href,function(responseText,textStatus,XMLHttpRequest){if(textStatus=='timeout'||textStatus=='error'){alert('连接超时');}});
	} else {
		$("#right").load(href+'&username=admin',function(responseText,textStatus,XMLHttpRequest){if(textStatus=='timeout'||textStatus=='error'){alert('连接超时');}});
	}
	//
}
function remove_tab (evt) {
		if (confirm("Close this tab?") ) {
			var	idx = $(this).closest("li").index();
			$Tabs.tabs("remove", idx);
		}
};
//退出登录
function logout(){
       $.reqPostData(
	   SERVERURL + '200',
	   {reqType:'F9900004'},
	   function(data){
	      if(data&&data.result==REQUEST_SUCESS){
			  window.location.href='/hlpads/admin/login.jsp';
		  }else{
		   	  alert('操作失败');
	      }
       });	
}
function refreshTab(cfg){  
    var refresh_tab = $('#tabs').tabs('getTab',cfg.tabTitle);  
    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
    var _refresh_ifram = refresh_tab.find('iframe')[0];  
    var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
    _refresh_ifram.contentWindow.location.href=refresh_url;  
    }  
}
//测试url地址
function testUrl(url){
	if(!url||!url.startWith("http")){
		alert("信息同步地址必须以http开头");
		return;
	}
	 $.reqPostData({reqType:'F9901013',url:url},function(data){
	      if(data&&data.result==REQUEST_SUCESS){
			  alert('测试成功');
		  }else{
		   	  alert('测试失败');
	      }
     });	

}