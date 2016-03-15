document.domain = 'kunlun.com';
var js = document.getElementsByTagName('script');
var jspath = js[js.length-1].src.substring(0,js[js.length-1].src.lastIndexOf("/")+1);
var iframediv = {
	id:Math.random(),
	show:function(file,param){
		var p = document.createElement('DIV');
		p.id=this.id;
		var scrollWidth = document.documentElement.scrollWidth || document.body.scrollWidth;
		var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
		p.style.cssText = "z-index:999999;width:"+scrollWidth+"px;height:"+scrollHeight+"px;position:absolute;left:0;top:0;background-color:#666;filter:alpha(opacity=80);-moz-opacity:0.8;opacity:0.8;";
		document.body.appendChild(p);
		var p1=document.createElement("DIV");
		p1.id="box"+this.id;
		p1.style.cssText = "z-index:9999999;width:453px;position:absolute;top:0;left:50%;margin-left:-235px;";
		document.body.appendChild(p1);
		var query = '?v=1.4';
		if( !param['returl'] ) param['returl'] = top.location.href;
		if(getCookie('KUNLUN_KLUN'))
		{
			param['u3'] = getCookie('KUNLUN_KLUN');
		}
		if(param) for(var str in param) query += '&' +str +'='+encodeURIComponent(param[str]);
		p1.innerHTML = '<iframe width="453" height="510" marginWidth=0 marginHeight=0 frameBorder=0 width="100%" scrolling="no" allowTransparency="true" src="'+jspath+file+'.html'+query+'"></iframe><a style="position:absolute;left:644px;top:8px;height:35px;width:35px;background-color:red; filter:alpha(opacity=0);-moz-opacity:0;opacity:0;" href="javascript:closeIframediv()" title="\u5173\u95ed">&nbsp;</a>'
		this.iframescroll();
	},
	iframescroll:function(){
		if(!document.getElementById("box"+this.id) ) return;
		var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
		var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
		document.getElementById("box"+this.id).style.top = parseInt(clientHeight*0.08) + parseInt(scrollTop)+"px";
	},
	close:function(){
		document.body.removeChild(document.getElementById("box"+this.id));
		document.body.removeChild(document.getElementById(this.id));
	}
}
if( window.addEventListener){
    window.addEventListener('scroll',function(){ iframediv.iframescroll(); },false);
}else if(window.attachEvent){
    window.attachEvent('onscroll',function(){ iframediv.iframescroll(); });
}
window.closeIframediv = function(){ iframediv.close(); };
//window.showReg = function(param){ iframediv.show('register',param); };
window.showLogin = function(param){ iframediv.show('login',param); };

//读取cookie中指定的内容
function getCookieVal(offset) {
    var endstr = document.cookie.indexOf (";", offset);
    if (endstr == -1) endstr = document.cookie.length;
    return unescape(document.cookie.substring(offset, endstr));
}

function getCookie(name) {
	 var arg = name + "=";
	 var alen = arg.length;
	 var clen = document.cookie.length;
	 var i = 0;
	 while (i < clen) {
		 var j = i + alen;
		 if (document.cookie.substring(i, j) == arg) return getCookieVal (j);
		 i = document.cookie.indexOf(" ", i) + 1;
		 if (i == 0) break;
	 }
	return null;
}
