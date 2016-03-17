/**
 * 
 */


//调用初始化方法
//$(function jqInit(){
//	init();
//}) 
 
// 发送jsop请求
function sendRequestPost(url, data, successfn, errorfn) {
	$.ajax({
		type : "GET",
		dataType : "jsonp",
//		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data : data,
		url : url,
		jsonp : 'callback',
		success : function(d) {
			successfn(d);
		},
		error : function(e) {
			errorfn(e);
		}
	});
}

var userData = function(){
	var token = null;
	function getUserToken(){
		return token;
	}
	function setUserToken(mytoken){
		token = mytoken;
	}
	return {
		getUserToken:getUserToken,
		setUserToken:setUserToken
	}
}();




var urlData = function(){
	var centerUrl = 'http://auth.gamebean.net/ucenter/uMember/cms/index.do';
//	var centerUrl = 'http://113.31.91.163:8889/ucenter/uMember/cms/index.do';//用户中心
	
	var logOutUrl = 'http://113.31.91.163:8889/ucenter//sdk/logout.do';//退出登录
	
	var gssc = 'http://113.31.91.163:8895/gscfront/sdk/index.do'; //gssc签到

	//var gsscms = 'http://113.31.91.163:8895/gscfront/cms/index.do'; //gssc录入用户
	var gsscms = 'http://gscservice.gamebean.net/gscfront/cms/index.do';
	
	var bbs = 'http://113.31.128.2/'; //bbs测试地址
	
//	var misUrl = 'http://192.168.91.74:8080/mis/sdk/sdklist_js.htm'; //mis
	var misUrl = 'http://sdk.gamebean.com/download/channel/sdklist_js.htm';
	
	var billingCenter = "http://113.31.91.163:8892/billingcenter2.0/webOffical/main.do"; //计费中心地址
	//var billingCenter = "http://pay.0708.com/billingcenter2.0/webOffical/main.do"; //计费中心地址
	function getBillingUrl(){
		return billingCenter;
	}
	
	function getCenterUrl(){
		if(window.location.host == '10.8.2.232:8893'){
			return 'http://auth02.gamebean.net/ucenter/uMember/cms/index.do';
		}
		return centerUrl;
	}
	function getmisUrl(){
		return '';
	}
	function getLogOutUrl(){
		return logOutUrl;
	}
	function getGssc(){
		return gssc;
	}
	function getGsscms(){
		return gsscms;
	}
	function getMisUrl(){
		return misUrl;
	}
	function getBbs(){
		return bbs;
	}
	return {
		getBillingUrl:getBillingUrl,
		getCenterUrl:getCenterUrl,
		getmisUrl:getmisUrl,
		getLogOutUrl:getLogOutUrl,
		getGssc:getGssc,
		getGsscms:getGsscms,
		getMisUrl:getMisUrl,
		getBbs:getBbs
	}
}();


//页面的公共数据
var pageData = function (){
	var nickName = null;
	var score = null;
	var curNum = 0;
	var myCurNum = 0;
	function setNickName(userNick){
		nickName = userNick;
	}
	function getNickName(){
		return nickName;
	}
	function setScore(myScore){
		score = myScore;
	}
	function getScore(){
		return score;
	}
	function setCurNum(num,type){
		if(type == '0'){
			curNum = num;
		}else if(type == '1'){
			myCurNum = num;
		}
		
	}
	function getCurNum(type){
		if(type == '0'){
			return curNum;
		}else if(type == '1'){
			return myCurNum;
		}
	}
	function addNum(num,type){
		if(type == '0'){
			curNum += num;
		}else{
			myCurNum += num;
		}
		
	}
	return {
		setNickName:setNickName,
		getNickName:getNickName,
		setScore:setScore,
		getScore:getScore,
		setCurNum:setCurNum,
		getCurNum:getCurNum,
		addNum:addNum
	}
}();


//page结构
var pageBean = function (){
	var curPage = 1;
	var curCount = 0;
	function getPage(){
		return curPage;
	}
	function getCount(){
		return curCount;
	}
	function setPage(page){
		curPage = page;
	}
	return { 
		getPage:getPage,
		getCount:getCount,
		setPage:setPage
	}
}();

function cutPage(count,pid){
	var page = pageBean.getPage() + 1;
	pageBean.setPage(page);
	showPage(pageBean.getPage(),count,pid);
}

//分页
function showPage(page,count,pid){
	for(var i = 1;i<(page*count+1);i++){
		$('#'+pid+'_'+i).show();
	}
}


//json字符串转json object
function jsonStrToObject(str){
	var m = eval("("+str+")");
	return m;
}


//创建json独享 by 字符串
function creatJsonByStr(s){
	var dataArray = s.split("&");
	var jsonStr = '';
	for(var i = 0;i<dataArray.length;i++){
		var jsonData = dataArray[i].split("=");
		jsonStr += '"'+jsonData[0]+"':'"+jsonData[1]+'",'
	}
	jsonStr = jsonStr.substring(0,jsonStr.length-1);
	jsonStr = "{"+jsonStr+"}";
	return jsonStrToObject(jsonStr);
}

//创建json字符串
function creatInnerJson(s){
	var dataArray = s.split("&");
	var jsonStr = '';
	for(var i = 0;i<dataArray.length;i++){
		var jsonData = dataArray[i].split("=");
		jsonStr += '"'+jsonData[0]+'":"'+jsonData[1]+'",'
	}
	jsonStr = jsonStr.substring(0,jsonStr.length-1);
	return jsonStr;
}



//保存token
function saveToken(token){
//		setCookie("token_ourplam",tokenEncrypt(token));
	setCookie('ourpalm_token',token);
	userData.setUserToken(token);
}

// 登录成功方法
var loginSuccess = function (data) {
	if(data.reset == "1000"){
		saveToken(data.tokenId); //保存token
		saveUserName(data.userInfo.userName);
		var url = getSuccessUrl();
		location.href = url;	
	}else{
		alert(data.desc);
	}
}

// 请求用户中心接口失败方法
var loginError = function (data) {
	// history.go(-2);
}
//获取用户成功方法
var getUserSuccess = function(data){
	userBean(data);
}

//未登录用户获取token
var getUnLoginToken = function(data){
	var userData = data;
	if(getNull(userData.tokenId) != null){
		saveToken(userData.tokenId); //保存token	
	}
}

//未登录用户获取token并获取验证码
var getUnLoginTokenCaptcha = function(data){
	getUnLoginToken(data);
	getCaptcha(data.userInfo.returnJson.id,0);
}

//回调后获取验证码图片
var getCaptchaImgIo = function(data){
	var user = data.userInfo
	var id = user.returnJson.id;
//	var url = 'http://113.31.91.163:8889/ucenter/uMember/cms/index.do?';
//	var url = 'http://192.168.1.102:82/ucenter-china/uMember/cms/index.do?';
//	var url = 'http://auth.gamebean.net/ucenter/uMember/cms/index.do?';
	var url = urlData.getCenterUrl()+'?';
	url += 'jsonStr='
	var jtr = '{"interfaceId":"2003",';
	jtr += '"tokenId":"'+getToken()+'"}';
//	var ary = encrypt(jtr);
//	url += ary[0]+'&dk='+ary[1];
	url += jtr;
	var k = url;
	url += '&temp='+getTimeTamp();
    $("#"+id).attr("src",url);
    var m = $("#"+id).attr('src'); 
}
//链接追加参数
function addParam(id){
	var v = $('#'+id).val();
	var param = window.location.search;
	if(v.indexOf('?') <= 0 ){
		
	}
//	$('#'+id).attr('href',v+'?'+param); 
}


//插入排行榜数据
var insertScoreOrder = function(data){
	if(data.status == "1"){
		alert('暂无数据');
		return;
	}
	var ids = data.otherParams.ids;
	var nameId = ids[0];
	var scoreId = ids[1];
	var parentId = ids[2];
	var rank = ids[3];
	var total = data.total;
	var count = data.count;
	var userList = data.scoreInfo;
	var lsize = userList.length;
	var m = lsize < parseInt(count)?true:false;	
	if(data.type == '1'){
		insertScoreOrderPre(data);
		return;
	}
	for(var i = 1;i<parseInt(total)+1;i++){
		if(i > lsize){
			continue;
		}
		if(getNull($('#'+nameId+'_'+i)) != null && getNull($('#'+scoreId+'_'+i)) != null){
			$('#'+nameId+'_'+i).html(userList[i-1].userName);
			$('#'+scoreId+'_'+i).html(userList[i-1].score);
			$('#'+rank+'_'+i).html(userList[i-1].order);
			$('#'+parentId+'_'+i).show();
		}
		if(i >= parseInt(count)+1 || (m && i >lsize)){
			$('#'+parentId+'_'+i).hide();
		}
	}
   
}


//我的排行
function insertScoreOrderPre(data){
	var ids = data.otherParams.ids;
	var nameId = ids[0];
	var scoreId = ids[1];
	var parentId = ids[2];
	var rank = ids[3];
	var total = parseInt(data.total); //页面总数
	var count = parseInt(data.count);
	var userList = data.scoreInfo;
	var lsize = userList.length;  //实际总数
	var middle = count+1;  //中间位置
	var myOrder = 0;  //用户排行
	var pageRow = 0; //页面当前行
	for(var i = 1;i<total+1;i++){
		pageRow++;
		if(i > lsize){
			break;
		}
		if(userList[i-1].isMy == '1'){
			pageRow = count+1;
			pageData.setNickName(userList[i-1].userName);  //设置用户昵称
			pageData.setScore(userList[i-1].score);  //设置用户分数
		}
		if(getNull($('#'+nameId+'_'+pageRow)) != null && getNull($('#'+scoreId+'_'+pageRow)) != null){
			$('#'+nameId+'_'+pageRow).html(userList[i-1].userName);
			$('#'+scoreId+'_'+pageRow).html(userList[i-1].score);
			$('#'+rank+'_'+pageRow).html(userList[i-1].order);
			$('#'+parentId+'_'+pageRow).show();
		}
	}
   insertNickName(ids[4],ids[5],ids[6]);
	
}

//获取礼包码回调接口
var callBackPackage = function (data){
	if(data.status == '0'){
		alert(data.desc);
	}
}



//转换为用户中心请求参数格式
function changeUCenterData(data,flag,interfaceCode){
	var postData = data;
	var interfaceId = '2002';
	if(interfaceCode){
		interfaceId = interfaceCode;
	}
	var centerData = '"interfaceId":"'+interfaceId+'","gameKey":"","serviceId":"1000002331038800000","channelId":"","localeId":"01","deviceGroupId":""';
	var code = getForm(data,'code');
	centerData += ',"tokenId":"'+ (getNull(getToken()) != null ?getToken():'')+'"';
	postData = creatInnerJson(postData);
	postData = '{'+postData+'}';
	centerData += ',"userInfo":'+postData;
	centerData += ',"code":"'+code+'"';
	centerData += ',"loginFlag":"'+flag+'"';
	centerData += ',"otherParams":{}';
	centerData += ',"mac":""';
	centerData += ',"idfa":""';
	centerData += ',"deviceUniqueID":""';
	centerData = "{"+centerData+"}";
//	var ary = encrypt(centerData);  //加密 暂是不用
//	centerData = "jsonStr="+ary[0]+'&dk='+ary[1]; //加密 暂是不用
    centerData = "jsonStr="+centerData
	return centerData;
}


// 提交表单 ids 1.用户账号 2.用户密码  3.验证码
function postForm(ids,url,flag) {
//	var data = $('#' + id).serialize();
	var userName = $('#'+ids[0]).val();
	var userPwd = $('#'+ids[1]).val();
	var mac = '';
	var IDFA = '';
	var deviceUniqueID = '';
	var data = 'userName='+userName+'&userPwd='+userPwd;
	data += (ids.length > 2?'&code='+$('#'+ids[2]).val():'');
	data = changeUCenterData(data,flag);
	sendRequestPost(url, data, loginSuccess, loginError);
}

//提交表达 ids 1.用户账号 2.用户密码  3.验证码  success 请求成功回调方法 fail 请求失败回调方法

function postData(ids,url,flag,success,fail){
	var userName = $('#'+ids[0]).val();
	var userPwd = $('#'+ids[1]).val();
	var data = 'userName='+userName+'&userPwd='+userPwd;
	data += (ids.length > 2?'&code='+$('#'+ids[2]).val():'');
	data = changeUCenterData(data,flag);
	sendRequestPost(url, data, success, fail);
}




// 提交表单 ids 1.用户账号 2.用户密码 
function postFormNoCode(ids,url,flag) {
//	var data = $('#' + id).serialize();
	var userName = $('#'+ids[0]).val();
	var userPwd = $('#'+ids[1]).val();
	var data = 'userName='+userName+'&userPwd='+userPwd;
	data = changeUCenterData(data,flag,'2004');
	sendRequestPost(url, data, loginSuccess, loginError);
}

//设置用户名
function saveUserName(value){
	setCookie("userName_ourplam",value);
}
//获取用户名
function getUserName(){
	var name = getCookie("userName_ourplam");
	return name;
}

// 设置cookie
function setCookie(name, value) {
//	var exp = new Date();
	var exp = new Date();
        exp.setTime( exp.getTime() + 180 * 60 * 1000 );//过期时间 2分钟
	//exp.setTime(0);//过期时间 无
	
//	exp.setTime(exp.getTime() + 10 * 60 * 1000);
	document.cookie = name + "=" + escape(value)+';path=/;';
//	document.cookie = name + "=" + escape(value) + ";expires="
//			+ exp.toGMTString();
}

// 读取cookie
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return (arr[2]);
	else
		return null;
}

// 删除cookies
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null) {
		document.cookie = name + "=" + cval + ";path=/;expires=" + exp.toGMTString();
	}
}

// 获取session id
function getSessionId() {
	var c_name = 'JSESSIONID';
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
}

function saveSuccessUrl(){
	var success = getCookie("ourpalm_success");
	if(success == null){
		setCookie("ourplam_success",window.location.href);
	}
}

function getSuccessUrl(){
	var url = decodeURIComponent(getCookie("ourpalm_success"));
	url = url.replace(/\"/g,''); 
	return url;
}

//获取用户信息 by token
function getUserByToken(){
	var token = getToken();
	if(token != null){
//		var url = 'http://113.31.91.163:8889/ucenter/uMember/cms/index.do';
//		var url = 'http://192.168.1.102:82/ucenter-china/uMember/cms/index.do';
//		var url = 'http://auth.gamebean.net/ucenter/uMember/cms/index.do';
		var url = urlData.getCenterUrl();
		var data = "{"+creatInnerJson("interfaceId=2001&flag=0&token="+token)+"}";
		sendRequestPost(url, data, getUserSuccess, loginError);	
	}
}

//获取验证码
function getCaptcha(id,flag){
	var imgId = id;
	var optFlag = flag;
	
//	var url = 'http://113.31.91.163:8889/ucenter/uMember/cms/index.do?jsonStr='
//	var url = 'http://192.168.1.102:82/ucenter-china/uMember/cms/index.do?jsonStr=';
//	var url = 'http://auth.gamebean.net/ucenter/uMember/cms/index.do?jsonStr=';
	var url = urlData.getCenterUrl()+'?jsonStr=';
	var jtr = '{"interfaceId":"2001","initFlag":"'+flag+'","otherParams":{';
	jtr += '"id":"'+id+'"},';
	jtr += '"tokenId":"'+getToken()+'"';
	jtr += '}';
//	var ary = encrypt(jtr);
//	url += ary[0]+'&dk='+ary[1];
	url += jtr;
//	$("#"+id).attr("src",url);
	sendRequestPost(url,'',getCaptchaImgIo,loginError);
}

//获取时间戳
function getTimeTamp(){
	var tamp = new Date().getTime();
	return tamp;  
}

//初始化用户中心信息
function init(id){
	//链接中获取token
	var token = getParameter('tokenId');
//	var url = 'http://113.31.91.163:8889/ucenter/uMember/cms/index.do';
//	var url = 'http://192.168.1.102:82/ucenter-china/uMember/cms/index.do';
//	var url = 'http://auth.gamebean.net/ucenter/uMember/cms/index.do';
	var url = urlData.getCenterUrl();
//	var url = 'http://192.168.1.102:82/ucenter-china/uMember/cms/index.do';
	var data = 'jsonStr='
	var jtr = '{"interfaceId":"2001","loginFlag":"0"';
	if(getNull(id) != null){
		jtr += ',"otherParams":{"id":"'+id+'"}}';
	}else{
		jtr += '}';
	}
	//链接中获取token
	if(getNull(token) != null){
		saveToken(token);
		if(getNull(id) != null){  //初始化验证码
			getCaptcha(id,0);
		}
		return;
	}
	//cookie获取token
	token = getToken();
	if(getNull(token) != null ){
		if(getNull(id) != null){ //初始化验证码
			getCaptcha(id,0);
		}
		return;
	}
	//向用户中心发请求
	if(getNull(token) == null){
//		var ary = encrypt(jtr); //加密 暂时不用
//		data += ary[0]+'&dk='+ary[1]; //加密 暂时不用
        data += jtr;
		if(getNull(id) == null){
			sendRequestPost(url,data,getUnLoginToken,loginError);
		}else{
			sendRequestPost(url,data,getUnLoginTokenCaptcha,loginError);
		}
	}
}

function getNull(v){
	if(v == null || v == "" || typeof(v) == 'undefined'){
		return null;
	}else{
		return v;
	}
}

//获取参数值
function getParameter(param){
	var v = getValueByName(window.location.search,param);
	return v;
}

//获取表单值
function getForm(data,param){
	var v = getValueByName(data,param);
	return v;
}

//获取链接值
function getValueByName(query,param){
	var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1) return "";
	iStart += iLen + 1;
	var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1) return query.substring(iStart); 
	return query.substring(iStart, iEnd);
}

function generateMixed(n) {
    var jschars = ['0','1','2','3','4','5','6','7','8','9'];
    var res = "";
    for(var i = 0; i < n ; i ++) {
        var id = Math.ceil(Math.random()*10);
        res += jschars[id-1];
    }
    return res;
}

//请求加密
function encrypt(str){
//	var keys = new Array()
//	var key = 'ourpalm^';
//	var rd = generateMixed(8);
//	var dk = strEnc(rd,key,null,null);
//	var enc = strEnc(str,dk,null,null);
//	keys.push(enc);
//	keys.push(dk);
	keys.push(str);
	keys.push('');
	return keys;
}

function saveKey(key){
	setCookie('key_ourpalm',key);
}

function getKey(){
	getCookie('key_ourpalm');
}

//token加密
function tokenEncrypt(str){
	return strEnc(str,'ourpalm_admin*@#');
}
//token解密
function tokenStrDec(str){
	return strDec(str,'ourpalm_admin*@#');
}

//获取token
function getToken(){
	//优先链接 cookie 页面
	if(getNull(getParameter('tokenId')) != null){
		return getParameter('tokenId')
	}else if(getNull(getCookie("ourpalm_token")) != null){
//		return tokenStrDec(getCookie("token_ourplam"));
		return getCookie("ourpalm_token");
	}else{
		return userData.getUserToken();
	}
}


//获取特征串
function getFeature(){
	return getNull(getCookie("ourpalm_feature"));
}

//获取排行榜 
function getScoreOrder(count,total,ids,listType){
	var url = 'http://gscservice.gamebean.net/gscfront/cms/index.do';
	var data = '{"interfaceId":"2001"';
	if(getNull(getParameter('tokenId')) == null){
		return;
	}
	var token = getNull(getParameter('tokenId')) != null?getParameter('tokenId'):'';
	var localeId = getNull(getParameter('localeId')) != null?getParameter('localeId'):'';
	var platformId = getNull(getParameter('platformId')) != null?getParameter('platformId'):'';
	var serviceId = getNull(getParameter('serviceId')) != null?getParameter('serviceId'):'';
	var actId = getNull(getParameter('actId')) != null?getParameter('actId'):'';
	var channelId = getNull(getParameter('channelId')) != null?getParameter('channelId'):'';
	var m = 'tokenId='+token+'&localeId='+localeId+'&platformId='+platformId+'&serviceId='+serviceId+'&actId='+actId+'&channelId='+channelId;
	var innerJson = creatInnerJson(m);
	data += ','+innerJson+',"type":"'+listType+'"';
	
	data += ',"otherParams":{"ids":["'+ids[0]+'","'+ids[1]+'","'+ids[2]+'","'+ids[3]+'","'+ids[4]+'","'+ids[5]+'","'+ids[6]+'"]}';
	data += ',"count":"'+count+'"';
	data += ',"total":"'+total+'"';
	data += '}';
	var send = 'jsonStr='+data;
	saveToken(token); //保存token信息
	sendRequestPost(url,send,insertScoreOrder,loginError);
}





//关闭链接
function closeWin(){
//	var m = window.location.href+'#?event=close';
//	window.ranklist.jscloseDialog();
//    BrowserEvent.close();
	window.location.href = '#?event=close';
}

//获取礼包码 1.老区服 2.老用户id 3.老用户手机id 4.新区服 5.新用户id 6.新用户手机id 
function getPackageCode(ids,fn){
    var url = 'http://gscservice.gamebean.net/gscfront/cms/index.do';
//	var url = 'http://localhost:8080/testsjs';
	var areaId = ids[0];
	var userId = ids[1];
	var userPhoneId = ids[2];
	
	var newAreaId = null;
	var newUserId = null
	var newUserPhoneId = null;
	
	var user = null;
	var userPhone = null;
	var area = null;
	
	var newArea = null;
	var newUser = null;
	var newUserPhone = null;
	
	var role = null;
	var friendRole = null;
	
	var actId = null;
	if(ids.length == 6){
		actId = 55;
	}else if(ids.length == 3){
		actId = 54;
	}
	
	
	if(ids.length == 6 ){
		newAreaId = ids[3];
		newUserId = ids[4];
		newUserPhoneId = ids[5];
		newArea = $('#'+newAreaId).val();
		newUser = $('#'+newUserId).val();
		newUserPhone = $('#'+newUserPhoneId).val();
		friendRole = 'phone='+newUserPhone+'&roleArea='+newArea+'&roleName='+encodeURIComponent(newUser);
		friendRole = creatInnerJson(friendRole);
	}
	area = $('#'+areaId).val();
	user = $('#'+userId).val();
	userPhone = $('#'+userPhoneId).val();
	role = 'phone='+userPhone+'&roleArea='+area+'&roleName='+encodeURIComponent(user);
	role = creatInnerJson(role);
	data = '"interfaceId":2002';
	data += ',"actId":"'+actId+'"';
	
	data += ',"role":{'+role+'}';
	data += ',"friendRole":{'+(friendRole != null?friendRole:'')+'}';
	data = '{'+data+'}';
	data = 'jsonStr='+data;
	if(ids.length == 3){
		if(!verificBlank(areaId)){
			$('#tip').html('角色游戏服不可为空');
			return;
		}
		if(!verificBlank(userId)){
			$('#tip').html('角色名不可为空');
			return;
		}
		if(!verificMobile(userPhoneId)){
			$('#tip').html('请检查手机号');
			return;
		}
		if(verificBlank(areaId) && verificBlank(userId) && verificMobile(userPhoneId)){
			$('#tip').html('');
		}
	}
	if(ids.length == 6){
		if(!verificBlank(areaId)){
			$('#tip1').html('角色游戏服不可为空');
			return;
		}
		
		if(!verificBlank(userId)){
			$('#tip1').html('角色名不可为空');
			return;
		}
		if(!verificMobile(userPhoneId)){
			$('#tip1').html('请检查手机号');
			return;
		}
		if(verificBlank(areaId) && verificBlank(userId) && verificMobile(userPhoneId)){
			$('#tip1').html('');
		}
		
		if(!verificBlank(newAreaId)){
			$('#tip2').html('好友角色游戏服不可为空');
			return;
		}
		
		if(!verificBlank(newUserId)){
			$('#tip2').html('好友角色名不可以为空');
			return;
		}
		if(!verificMobile(newUserPhoneId)){
			$('#tip2').html('请检查好友手机号');
			return;
		}
		if(verificBlank(newAreaId) && verificBlank(newUserId) && verificMobile(newUserPhoneId)){
			$('#tip2').html('');
		}
	}
	sendRequestPost(url,data,fn,null);
}


function tipMessage(reset,defMessage){
	switch(reset){
		case '1001':return '请求格式错误';
		case '1002':return defMessage;
		case '1111':return defMessage;
		case '1132':return '账号已封停';
		case '1133':return 'mac已封停';
		case '1138':return defMessage;
		case '1139':return defMessage;
		case '1140':return defMessage;
		case '1141':return defMessage;
		case '1151':return '产品黑名单异常';
		case '3653':return '注册失败';
		case '1099':return defMessage;
		case '1102':return '用户密码错误';
		case '1114':return '当前用户不存在';
		case '1115':return '新密码不能设置为空';
		case '1116':return '参数异常';
		case '1118':return '修改失败，新密码与原密码相同';
		case '1101':return '用户名错误';
		case '2301':return '该用户未绑定邮箱';
		case '2302':return '签名无效';
		case '2303':return '当前用户不存在';
		case '2304':return 'email格式错误';
		case '2305':return defMessage;
		case '2306':return '签名错误';
		case '2307':return '链接失效';
		case '2308':return '邮箱已绑定';
		case '1111':return defMessage;
		case '1115':return '新密码不能设置为空';
		case '1125':return '用户不存在';
		case '1126':return '账号已绑定';
		case '1008':return '用户名包含空格';
		case '1127':return '此手机号已被绑定，请更换一个手机号';
		case '1128':return '手机号有误';
		case '1134':return '账号不存在';
		case '1162':return '当前手机号已经被注册异常';
		case '1163':return '用户中心没有该手机号信息异常';
		case '1164':return '手机号没有与当前注册账号绑定异常';
		case '1017':return '当前用户已绑定到该手机号';
		case '1102':return '用户密码错误';
		case '1127':return '此手机号已被绑定，请更换一个手机号';
		case '1128':return '手机号有误';
		case '1129':return '验证码失效或有误，请重新获取';
		case '1135':return '该账户已绑定手机';
		case '1136':return '该账户未绑定手机';
		case '1143':return '找回密码次数超过当天次数限制';
		default:return defMessage;
	}
	
}


function tipMessageBBs(reset,defMessage){
	switch(reset){
		default:return defMessage;
	}
	
}




















