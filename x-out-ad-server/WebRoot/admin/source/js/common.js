if(!Array.indexOf){
   Array.prototype.indexOf = function(Object){  
     for(var i = 0;i<this.length;i++){  
        if(this[i] == Object){  
           return i;  
         }  
     }  
     return -1;  
   } 
}
if(!Array.remove){
   Array.prototype.remove = function (dx) { 
     if (!dx) {   
         return false;   
     }
     for (var i = 0; i < this.length; i++) {   
         if (this[i] == dx) {   
            this.splice(i,1);
         }
     }   
   };
}
String.prototype.startWith=function(str){     
  var reg=new RegExp("^"+str);     
  return reg.test(this);        
}
//检验input中是否有值
function getInputValue(id){
	if($('#'+id)&&$('#'+id).val()){
		var str = $.trim($('#'+id).val());
		if(str&&str.length>0){
		    return str;
	    }else{
		    return '';
	    }
	}
	return '';
}
//对element进行class操作 如果存在则删除没有则添加 ，arr为保存element id的数组
function addcls(classname,element,arr){
	if($(element).attr('id')){
	    if($(element).hasClass(classname)){
	    	$(element).removeClass(classname);
	    	if(arr.indexOf($(element).attr('id'))>=0){
		    	arr.remove($(element).attr('id'));
		    }
	    }else{
	    	$(element).addClass(classname);
	    	if(arr.indexOf($(element).attr('id'))<0){
		    	arr.push($(element).attr('id'));
		    }
	    } 
	}
}
//对element进行class操作 如果存在则删除没有则添加 
function addclass(classname,id){
	if($('#'+id)){
		if($('#'+id).hasClass(classname)){
			$('#'+id).removeClass(classname);
		}else{
			$('#'+id).addClass(classname);
		}
	}
}
//状态的下拉框
function getStatusSelected(id,selected){
	if(!selected){
		selected=1;
	}
	return '<select id="'+id+'" class="sec_select_on" ><option value="0"' +(selected==0?" selected":"")+'>开启</option><option value="-1"'+(selected==-1?" selected":"")+'>暂停</option><option value="-2"' +(selected==-2?" selected":"")+'>删除</option></select>';
}
//计费点类型的下拉框
function getPointTypeSelected(id,selected){
	if(!selected){
		selected=1;
	}
	return '<select id="'+id+'" class="sec_select_on"><option value="1"'+(selected==1?" selected":"")+'>按次</option><option value="2"'+(selected==2?" selected":"")+'>买断</option><option value="3"'+(selected==3?" selected":"")+'>免费</option></select>';
}
//清空输入框
function clearInputvalue(id,value){
	if($('#'+id)&&$('#'+id).val()&&value){
		if($('#'+id).val()==value){
			$('#'+id).val('');
		}
	}
}
//判断是否为整数
function isInt(str){
	str=$.trim(str);
    var result=str.match(/^(-|\+)?\d+$/); 
    if(result==null) return false; 
    return true; 
}
//判断是否为整数
function isNumic(str){
	str=$.trim(str);
    var result=str.match(/^[0-9]+(.[0-9]{0,8})?$/); 
    if(result==null) return false; 
    return true; 
}
//判断是否为字符串
function isStr(str){
	str=$.trim(str);
    var result=str.match(/^\w{0,10}?$/);
    if(result==null) return false; 
    return true; 
}
//判断是否为邮箱地址
function isEmail(str){
	str=$.trim(str);
    var result=str.match(/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/); 
    if(result==null) return false; 
    return true; 
}
//获取字符串的长度
function getLength(str) {
	str=$.trim(str);
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};
//获取固定格式的日期字符串
function getDateStr(date) {
	var now = "";
	now = date.getFullYear()+"-"; //读英文就行了
	now = now + ((date.getMonth()+1)<10?'0'+(date.getMonth()+1):(date.getMonth()+1))+"-";//取月的时候取的是当前月-1如果想取当前月+1就可以了
	now = now + date.getDate();
    return now;
};

//获取固定格式的日期字符串
function getHourStr(date) {
	var now = date.getHours();
	//now = date.getFullYear()+"-"; //读英文就行了
	//now = now + ((date.getMonth()+1)<10?'0'+(date.getMonth()+1):(date.getMonth()+1))+"-";//取月的时候取的是当前月-1如果想取当前月+1就可以了
	//now = now + date.getDate();
    return now > 9 ? now : "0"+now;
};

//默认字符串
function defaultstr(str) {
	if(!str){
	   str='';
    }
	str=$.trim(str);
	return str;
};
//默认字符串
function defaulthtmlstr(str) {
	if(!str||$.trim(str)==''){
	   str='&nbsp;';
    }
	str=$.trim(str);
	return str;
};
//默认数字
function defaultint(str) {
	if(!str){
	   str=0;
    }
	str=$.trim(str);
	return parseInt(str);
};
function defaultfloat(str) {
	if(!str){
	   str=0;
    }
	str=$.trim(str);
	return parseFloat(str);
};
//获取分页对象
function getPager(pager) {
	if(!pager){
		pager={pagesize:PAGESIZE,currentpage:1,totalrows:0};
	}
	if(!pager.pagesize){
		pager.pagesize=PAGESIZE;
	}
	if(!pager.currentpage){
		pager.currentpage=1;
	}
	if(!pager.totalrows){
		pager.totalrows=0;
	}
	return pager;
};
//隐藏类节点
function hideclass(clas) {
	$('.'+clas).hide();
};
//显示类节点
function showclass(clas) {
	$('.'+clas).show();
};
function hideelement(element) {
	$($(element).find('a')[0]).show();
	$($(element).find('div')[0]).hide();
};
//隐藏类节点
function showelement(element) {
	$($(element).find('a')[0]).hide();
	$($(element).find('div')[0]).show();
};
//获取指定长度的字符串
function getLenStr(str,length) {
	str=$.trim(str);
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    if(realLength<length){
    	return str;
    }else{
    	return str.substring(0,length);
    }
};
function initSelect(key,para,id) {
	if(para&&key){
		$('#'+key).empty();
		 $.each(para, function (i) {
				  $('#'+key).append("<option value='"+i+"'>"+para[i]+"</option>");
                       
          });
		  if(id){
		    $("#"+key+"  option[value='"+id+"']").attr("selected",true)
		  }
	}
};
function initProvince(key,para) {
	if(para&&key){
		$('#'+key).empty();
		 var html='<span class="sec_checkbox"><input onclick="checkall(this)" type="checkbox" value="0" /><label>全选</label></span>';
		 html+='<div class="sec_class">';
            $.each(para, function (i) {
            	html+='<span><input type="checkbox" value="'+i+'"/><label>'+para[i]+'</label></span>';
            });
            html+='</div>';
		$('#'+key).html(html);
	}
};
function checkall(obj){
	if(obj.checked){
		$(".sec_class input[type='checkbox']").attr('checked',true);
	}else{
		$(".sec_class input[type='checkbox']").attr('checked',false);
	}

}