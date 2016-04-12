function login(){
	var name=$('#username').val();
	var passwd=$('#passwd').val();
	var code=$('#code').val();
   if(!name||$.trim(name).length==0){
	   alert('请输入用户名');
	   return;
   }
   if(!passwd||$.trim(passwd).length==0){
	   alert('请输入密码');
	   return;
   }
   
   $.ajax({
           type:"POST", 
           url:'/hlpads/adminmanager/101', 
           data:'{"reqType":"F9900001","username":"'+name+'","passwd":"'+passwd+'"}',
           timeout: 5000,
           success:function (json) {
           if (json.result == '1') {
        	   window.location.href="layout.jsp"; 
           }else if(json.result == '-1'){
        	   alert('用户名密码错误');
        	   //$('.logo_p').find('img').attr('src',"/smspay/imgcode?"+Math.random());
          }
    },
      error:function(json){
    	  alert('连接超时!');
      },dataType:"json"});
}