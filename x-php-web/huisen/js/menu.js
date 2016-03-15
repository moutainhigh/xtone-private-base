// JavaScript Document
var menucon=document.getElementById("menu").getElementsByTagName("a");
var menuson=document.getElementById("menu-sub").getElementsByTagName("div")[0].getElementsByTagName("div");
var smenucon=document.getElementById("menu-sub").getElementsByTagName("div");
var smenu=document.getElementById("menu-sub");
var switcher=0;

for(var i=0;i<menucon.length;i++){
	menucon[i].setAttribute("tab",i);
	menuson[i].setAttribute("tab",i);
	menucon[i].onmouseover=function(){
		clear();
		menucon[this.getAttribute("tab")].className="now";
		menuson[this.getAttribute("tab")].className="now";
		mover();
	}
	menuson[i].onmouseover=function(){
		clear();
		menucon[this.getAttribute("tab")].className="now";
		menuson[this.getAttribute("tab")].className="now";
	}
	menucon[i].onmouseout=function(){mout();}
}
for(var i=0;i<smenucon.length;i++){
	if(smenucon[i].className=="sub"){
		smenucon[i].onmouseover=function(){movers();}
		smenucon[i].onmouseout=function(){mout();}
}
}

function clear(){
	for(var i=0;i<menucon.length;i++){
		menucon[i].className="";
		menuson[i].className="";
	}
}

function mover(){switcher=1;}
function movers(){switcher=1;}
function mout(){switcher=0;}
setInterval(function(){
	if(switcher==1){
		$("#menu-sub").stop().animate({height:"270px"},700,'easeOutExpo');
		$("#menu-sub-bg").stop().animate({height:"270px"},700,'easeOutExpo');
	}else{
		$("#menu-sub").stop().animate({height:"0px"},400,'easeOutExpo');
		$("#menu-sub-bg").stop().animate({height:"0px"},400,'easeOutExpo');
		clear();
	}
},100);









