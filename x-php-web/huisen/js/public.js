//判断空值
function MM_popupMsg(msg) { //v1.0
  alert(msg);
}


//复制地址
function copyToClipBoard(obj){
	var clipBoardContent=this.location.href;
    window.clipboardData.setData("Text",clipBoardContent);
    alert("复制成功，请粘贴到你的QQ/MSN上推荐给你的好友");
}


//企业邮局
var gmail;
function show(obj){
	//隐藏前一个
	if(gmail!=null){
		document.getElementById("gon"+gmail).style.display="none";
		document.getElementById("g"+gmail).className="";
	}
	
	//显示这一个
	document.getElementById("gon"+obj).style.display="block";
	document.getElementById("g"+obj).className="now";
	
	gmail=obj;
}

//便民导航
function head(h,css){
	window.onscroll=function(){
		 var iheight=document.body.scrollTop+document.documentElement.scrollTop;
		 if(iheight>h){
			 document.getElementById("head-"+css).style.display="block";
		 }else{
			 document.getElementById("head-"+css).style.display="none";
		 }
	}
}


//案例动画
$(document).ready(function(){	
	$('.boxgrid.caption').hover(function(){
		$(".cover", this).stop().animate({top:'130px'},{queue:false,duration:150});
	}, function() {
		$(".cover", this).stop().animate({top:'160px'},{queue:false,duration:150});
	});
});

//鼠标滑过弹出层
function iDIV(obj, sType) { 
var oDiv = document.getElementById(obj); 
	if (sType=='show') { oDiv.style.display='block';} 
	if (sType=='hide') { oDiv.style.display='none';} 
} 


//域名查询
//function domaincheck(){
//	if(document.domainsear.domainkeys.value=="" || document.domainsear.domainkeys.value=="请输入您要查询的域名"){
//      alert("请输入您要查询的域名！");
//	  document.domainsear.domainkeys.focus();
//	  return false;
//	  }
//}

//Jquery.Ajax提交表单所有项数据===============================================
function jAjaxSubmit(){
	//先更改提交按钮的状态
	$("#button").css("display","none");
	$("#dostate").css("display","inline-block");
	//Ajax
	$.ajax({
		type:"POST", dataType:"html", url:$("#formfeedback").attr("action"), data:$('#formfeedback').serialize(),
		success:function(data){
			if(data.split("#")[0]=="ok"){//已输出成功标志
				location.href=data.split("#")[1];
			}else{//程序出错
				$("#dostate").css("display","none");	
				$("#button").css("display","inline-block");
				alert("Run-time Error：\n------------------------------------------\n"+data);
			}
		},error:function(XMLHttpRequest,textStatus,errorThrown){//其它非200状态
			$("#dostate").css("display","none");
			$("#button").css("display","inline-block");
			alert("Error：\n------------------------------------------\n"+XMLHttpRequest.status);
		}
	});
}


//在线留言
function feedbackcheck(){
	if(document.formfeedback.name.value=="" || document.formfeedback.name.value=="您的称呼"){
      alert("请输入您的姓名！");
	  document.formfeedback.name.focus();
	  return false;
	  }
	if(document.formfeedback.company.value=="" || document.formfeedback.company.value=="您所代表的公司"){
      alert("请输入您的公司！");
	  document.formfeedback.company.focus();
	  return false;
	  }
	if(document.formfeedback.tel.value=="" || document.formfeedback.tel.value=="您的联系电话"){
      alert("请输入您的联系电话，方便我们可以联系到您！");
	  document.formfeedback.tel.focus();
	  return false;
	  }
	if(document.formfeedback.content.value=="" || document.formfeedback.content.value=="请填写您的需求描述，我们将尽快给您回复！"){
      alert("填写您的留言内容！");
	  document.formfeedback.content.focus();
	  return false;
	}
	if(document.formfeedback.code.value=="" || document.formfeedback.code.value=="验证码不能为空！"){
      alert("输入验证码！");
	  document.formfeedback.code.focus();
	  return false;
	}
}
