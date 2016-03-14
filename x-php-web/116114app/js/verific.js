/**
 * 
 */
//验证手机号
function verificMobile(id){
	var mobile = $('#'+id).val();
	var re =  /(13|14|15|17|18)[0-9]{9}/;
	return re.test(mobile);
}
//验证空串
function verificBlank(id){
	var v = $('#'+id).val();
	if(v == null || v == '' || v.length == 0){
		return false;
	}else{
		return true;
	}
}


//6-20字母 字符串
function verificUser(id){
	var passwd = $('#'+id).val();
	var re =  /^[a-zA-Z\d]{6,20}$/;
	return re.test(passwd);
}


//6-12字母 字符串
function verificPasswd(id){
	var passwd = $('#'+id).val();
	var re =  /^[a-zA-Z\d]{6,12}$/;
	return re.test(passwd);
}

//6位验证码
function verificCode(id){
	var code = $('#'+id).val();
	var re =  /^[a-zA-Z\d]{6,12}$/;
	return re.test(code);
}


//4位验证码
function verificCode4(id){
	var code = $('#'+id).val();
	var re =  /^[a-zA-Z\d]{4}$/;
	return re.test(code);
}

//6位验证码
function verificCode6(id){
	var code = $('#'+id).val();
	var re =  /^[a-zA-Z\d]{6}$/;
	return re.test(code);
}

//邮箱
function verificMail(id){
	var code = $('#'+id).val();
	var re =  /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	return re.test(code);
}






