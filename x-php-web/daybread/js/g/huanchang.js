/**
 *
 */
var hyData = function(){ 
	var pcode = "pcode";
	var smscode = 'smsCode';
	var initPath = urlData.getBbs()+'forum/api/token.action';  //初始化接口地址
	var codePath = urlData.getBbs()+'forum/api/captcha.action';//验证码地址
	var regPath = urlData.getBbs()+'forum/api/register.action';//注册接口地址
	var loginPath = urlData.getBbs()+'forum/api/login.action';//登录接口地址
	var upUserPath = urlData.getBbs()+'forum/api/profile.action';//用户资料录入地址
	var gscFrontUrl = urlData.getGsscms();//GSCFRONT前端地址
	var gscUploadHeadImgUrl = 'http://image.upload.gamebean.net/gscfront/cms/upload.do';
	var billingCenter = urlData.getBillingUrl();//计费中心地址
	var uPic = 'upic'; //用户头像id
	var mode = '0002'; //默认机型
	var cid = '1169'; //默认栏目
	var type = '2'; //默认类型
	var pid = '';//产品id
	var areaid = null; //区服列表元素id
	var key = '8fdda3906f9792ea499ee7aef6842c04';
	var aliPay='210391000312000051014300';
	var shenzhoufuPay='210489000012013651014300';
	var mo9Pay='210549000012000051014300';

	function getMo9Pay(){
		return mo9Pay;
	}

	function getShenZhouFuPay(){
		return shenzhoufuPay;
	}

	function getAliPay(){
		return aliPay;
	}

	function getKey(){
		return key;
	}
	function getGscFrontUrl(){
		return gscFrontUrl;
	}
	function getBillingCenterUrl(){
		return billingCenter;
	}
	function getCode(){
		return code;
	}
	function getTokenPath(){
		return
	}
	function setPCode(newCode){
		pcode = newCode;
	}
	function getPCode(){
		return pcode;
	}
	function getInitPath(){
		return initPath;
	}
	function getCodePath(){
		return codePath;
	}
	function getRegPath(){
		return regPath;
	}
	function getLoginPath(){
		return loginPath;
	}
	function getUpUserPath(){
		return upUserPath;
	}
	function getUpic(){
		return uPic;
	}
	function getMode(){
		return mode;
	}
	function setMode(newMode){
		mode = newMode;
	}
	function setCid(newCid){
		cid = newCid;
	}
	function getCid(){
		return cid;
	}
	function setType(newType){
		type = newType;
	}
	function getType(){
		return type;
	}
	function getPid(){
		var uri = window.location.pathname;
		var params = uri.split("/");
		var pid = params[2];
		return pid;
	}
	function setAreaid(newId){
		areaid = newId;
	}
	function getAreaid(){
		return areaid;
	}
	function getGscUploadHeadImgUrl(){
		return gscUploadHeadImgUrl;
	}
	return {
		getShenZhouFuPay:getShenZhouFuPay,
		getMo9Pay:getMo9Pay,
		getAliPay:getAliPay,
		getKey:getKey,
		getPCode:getPCode,
		getCodePath:getCodePath,
		getInitPath:getInitPath,
		getRegPath:getRegPath,
		getLoginPath:getLoginPath,
		getUpUserPath:getUpUserPath,
		getUpic:getUpic,
		getMode:getMode,
		setMode:setMode,
		setCid:setCid,
		getCid:getCid,
		setType:setType,
		getType:getType,
		getPid:getPid,
		setAreaid:setAreaid,
		getAreaid:getAreaid,
		getBillingCenterUrl:getBillingCenterUrl,
		getGscUploadHeadImgUrl:getGscUploadHeadImgUrl,
		getGscFrontUrl:getGscFrontUrl
	}
}();

//初始化回调接口
var initBack_hc = function(data){
	if(data.status == 0){
		saveToken(data.content.tokenId);
		getPCode_hc(hyData.getPCode());
	}else{
		alert('初始化失败');
	}
}

//欢畅初始化
function init_hc(code){
	var url = hyData.getInitPath();
	var data = '';
	data += '"tokenId":""'
	data = '{'+data+'}';
	data = 'json='+data;
	data += '&paramFormat=json'
	sendRequestPost(url,data,initBack_hc,fail_hc);
}


//欢畅失败回调
var fail_hc = function(data){

}

//************************************************** [礼包详情] ************************************************************//

function copyToClipBoard( id ){
	var content = $( '#' + id ).html();
	window.clipboardData.setData('text', content);
	alert( '复制成功！' );
}
//************************************************** [礼包详情] ************************************************************//

//************************************************** [淘号] ************************************************************//
function amoySignals(){
	var url = hyData.getGscFrontUrl();
	var tokenId = getNull( getToken() ) == null ? '' : getToken();
	
	//var isLogin = {t:message  property="needLogin" bussiness="package" /}
	var isLogin = '1'; 
	
	if( isLogin == '1' && tokenId == '' ){
		window.location.href = 'http://hc.gamebean.com/t2/48/329.html';
		return false;
	}
	
	var netSrc = hyData.getType(),
		phone = '',
		roleId = '',
		roleName = '',
		logicServerId = '',
		serviceId = '1000053831014300000',//$( '.' + className ).attr('data_serviceId'),
		channelId = '3101430031014300',//$( '.' + className ).attr('data_channelId'),
		deviceGroupId = '0006',//$( '.' + className ).attr('data_deviceGroupId'),
		localeId = '01',//$( '.' + className ).attr('data_localeId'),
		packageId = '32';//$( '.' + className ).attr('data_packageId'),
	var data = 'jsonStr={"interfaceId":"3004",'
				+ '"netSrc":"' + netSrc + '",'
				+ '"serviceId":"' + serviceId + '",'
				+ '"channelId":"' + channelId + '",'
				+ '"deviceGroupId":"' + deviceGroupId + '",'
				+ '"localeId":"' + localeId + '",'
				+ '"MAC":"",'
				+ '"IDFA":"",'
				+ '"deviceUniqueId":"",'
				+ '"packageRequest":'
				+ '{'
				+ '"packageId":"' + packageId + '"'
				+ '}}';	
	sendRequestPost(url,data,amoySignalsSuccess_hc,fail_hc);	
}

function amoySignalsSuccess_hc(data){
//{"desc":"成功","discoverResult":{"codes":["WXLB03S68FT66X01","WXLB02MAXWJBLX01","WXLB015LMH4WCM01"]},"reset":"1000","status":"0"}
	if( data.status == 0 && data.discoverResult && data.discoverResult.codes){
	    $( '#JS_TAOHAO_NUM_LABEL' ).html( data.discoverCount );
		$( '#JS_TAOHAO_SUM_TRUNK_LABEL' ).html( data.discoverCount );
		var dataHtml = '';
		$.each(data.discoverResult.codes,function( index, item ){
			dataHtml += '<li><span class="code-num" id="code-num-' + index + '">激活码:' + item + '</span><a onclick=\'javascript:copyToClipBoard("code-num-' + index + '")\'; href="javascript:void(0);" class="copy-button" title="复制卡号">复制卡号</a></li>';
		});
		
		$('#JS_AMOYSIGNALS_LAYER').html(dataHtml);
		showBg();
	}else{
		alert(data.desc);
	}
}
//************************************************** [淘号] ************************************************************//

//************************************************** [发号] ************************************************************//
function sendSignals(){
	var url = hyData.getGscFrontUrl();
	var tokenId = getNull( getToken() ) == null ? '' : getToken();
	
	//var isLogin = {t:message  property="needLogin" bussiness="package" /}
	var isLogin = '1'; 
	
	if( isLogin == '1' && tokenId == '' ){
		window.location.href = 'http://www.gamebean.com/t2/48/329.html';
		return false;
	}
	var netSrc = hyData.getType(),
		phone = '',
		roleId = '',
		roleName = '',
		logicServerId = '',
		serviceId = '1000053831014300000',//$( '.' + className ).attr('data_serviceId'),
		channelId = '3101430031014300',//$( '.' + className ).attr('data_channelId'),
		deviceGroupId = '0006',//$( '.' + className ).attr('data_deviceGroupId'),
		localeId = '01',//$( '.' + className ).attr('data_localeId'),
		packageId = '117';//$( '.' + className ).attr('data_packageId'),
		
	var data = 'jsonStr={'
			+ '"interfaceId":"3002",'
			+ '"netSrc":"' + netSrc + '",'
			+ '"tokenId":"' + tokenId + '",'
			+ '"phone":"' + phone + '",'
			+ '"roleId":"' + roleId + '",'
			+ '"roleName":"' + roleName + '",'
			+ '"logicServerId":"' + logicServerId + '",'
			+ '"serviceId":"' + serviceId + '",'
			+ '"channelId":"' + channelId + '",'
			+ '"deviceGroupId":"' + deviceGroupId + '",'
			+ '"localeId":"' + localeId + '",'
			+ '"MAC":"",'
			+ '"IDFA":"",'
			+ '"deviceUniqueId":"",'
			+ '"packageRequest":'
			+ '{'
			+ '"packageId":"' + packageId + '"'
			+ '}}';
	sendRequestPost(url,data,sendSignalsSuccess_hc,fail_hc);
}

function sendSignalsSuccess_hc(data){
//{"codeInfo":{"code":"22V22223B4MU8W"},"desc":"领号成功","reset":"1000","status":"0"}
	if( data.status == 0 && data.codeInfo && data.codeInfo.code ){
	    $('JS_LINGHAO_BUTTON').unbind('click');
		$('#JS_LINGHAO_BUTTON').html('已领号');
		$('#JS_LINGHAO_BUTTON').attr('disabled',true).attr('color','#BEBEBE');
		$('#JS_LINGHAO_REMAINDER_LBEL').html( data.codeInfo.remainder  + '%')
		$('#JS_LINGHAO_NUM_LABEL').html(data);
		var dataHtml = '<li><span class="code-num" id="code-num-0">激活码:' + data.codeInfo.code + '</span><a onclick=\'javascript:copyToClipBoard("code-num-0")\' href="javascript:void(0);" class="copy-button" title="复制卡号">复制卡号</a></li>';
		$('#JS_FAHAO_LAYER').html(dataHtml);
		showBg();
	}else{
		alert(data.desc);
	}
}
//************************************************** [发号] ************************************************************//

//************************************************** [预约领号] ************************************************************//

function showLayerForMakeAnAppointmentNumber(){
		showBg();
}

/**
 * 预约领号
 */
 function makeAnAppointmentNumber(className){
	
	var loginFlag = checkVisitorIsLogin();
	
	//var isLogin = {t:message  property="needLogin" bussiness="package" /}
	var isLogin = '1'; 
	 
	//判断是否登陆，如未登陆去往登陆页面
	if( isLogin == '1' && !loginFlag ){
		window.location.href = 'http://www.gamebean.com/t2/48/329.html';
		return false;
	}
	
	var phone = $('#JS_ORDER_PHONE_INPUT').val();
	if( isLogin != '1' && phone == '' ) {
		//弹层
		showLayerForMakeAnAppointmentNumber();
		return false;
	}
	
	var url = hyData.getGscFrontUrl();
	var tokenId = !loginFlag ? '' : loginFlag,
		netSrc = hyData.getType(),
		serviceId = '1000053831014300000';//$( '.' + className ).attr('data_serviceId'),
		channelId = '3101430031014300';//$( '.' + className ).attr('data_channelId'),
		deviceGroupId = '0006';//$( '.' + className ).attr('data_deviceGroupId'),
		localeId = '01';//$( '.' + className ).attr('data_localeId'),
		packageId = '117';//$( '.' + className ).attr('data_packageId'),
		type = 'user',
		imgCode = '',
		phoneCode = '';
	
	//未登录用户，要弹出手机号码输入层
	if( tokenId == '' ){
		
		tokenId = $('#JS_ORDER_PHONE_INPUT').val();
		type = 'phone';
		
		imgCode = $('#JS_ORDER_IMAGE_CODE_INPUT').val();
		if( getNull(imgCode) == null ){
			alert('请输入图片验证码!');
			return ;
		}
		
		phoneCode = $('#JS_ORDER_PHONE_CODE_INPUT').val();
		if( getNull(phoneCode) == null ){
			alert('请输入短信验证码!');
			return ;
		}
		
	}
	
	if( !tokenId || tokenId == '' ){
		alert('请登录或者请输入手机号！');
		return ;
	}
	//{"interfaceId":"3001","netSrc":"1","type":"phone","id":"13552586267","tokenId":"","grhCaptcha":"","smsCaptcha":"","serviceId":"1000053831015600000","channelId":"3101560031015600","deviceGroupId":"0000","localeId":"01","MAC":"4c:aa:16:02:c6:1f","IDFA":"IDFA","deviceUniqueId":"deviceUniqueId","packageRequest":{"packageId":"32"}}

	var data = 'jsonStr={'
			+ '"interfaceId":"3001",'
			+ '"netSrc":"' + netSrc + '",'
			+ '"type":"' + type + '",'
			+ '"id":"' + tokenId + '",'
			+ '"tokenId":"' + getNotUserTokenId() + '",'
			+ '"smsCaptcha":"' + phoneCode +  '",'
			+ '"grhCaptcha":"' + imgCode +  '",'
			+ '"serviceId":"' + serviceId + '",'
			+ '"channelId":"' + channelId + '",'
			+ '"deviceGroupId":"' + deviceGroupId + '",'
			+ '"localeId":"' + localeId + '",'
			+ '"MAC":"",'
			+ '"IDFA":"",'
			+ '"deviceUniqueId":"",'
			+ '"packageRequest":'
			+ '{'
			+ '"packageId":"' + packageId + '"'
			+ '}}';
	sendRequestPost(url,data,makeAnAppointmentNumberSuccess_hc,fail_hc);
	
 }
 
 /**
  * 预约领号成功回调函数
  */
 function makeAnAppointmentNumberSuccess_hc(data){
	if( data.status == 0 ){
		$('#JS_ORDER_PHONE_BUTTON').html('已预订');
		$('#JS_ORDER_PHONE_BUTTON').attr('disabled',true).attr('color','#BEBEBE');
		$('#JS_ORDER_PHONE_BUTTON').unbind('click');
		alert('预约成功！')
	}else{
		alert(data.desc);
	}
 }
 
 /**
  * 判断当前访客是否登录，根据tokenId判断
  */
 function checkVisitorIsLogin(){
	return getNull( getToken() ) == null ? false : getToken();
 }
 
 /**
  *直接初始化
  * 在页面加载完成后运行
  */
  function fetchNotUserTokenId(){
	if( getNotUserTokenId() && ( getNotUserTokenId() != null || getNotUserTokenId() != '' ) ){
		return false;
	}
	var url = hyData.getGscFrontUrl();
	var data = 'jsonStr={"interfaceId":"2008","tokenId":""}';
	//{"content":{"tokenId":"f69d5580-75bd-4e51-a052-0fd0402926c0"},"desc":"成功获取token","reset":"1000","status":"0"} 
	sendRequestPost(url,data,function(data){
		if( data.status == 0 && data.content && data.content.tokenId )
			setNotUserTokenId(data.content.tokenId);
	},fail_hc);
 }
 
 function setNotUserTokenId(token){
	setCookie('ourpalm_notlogin_token',token);
 }
 function getNotUserTokenId(){
	return getCookie('ourpalm_notlogin_token');
 }
 
 /**
  * 获取图形验证码 ，GSCFRONT
  */
function getImgCode(imgId){

	var url = hyData.getGscFrontUrl();
	var tokenId = getNotUserTokenId();
	var data = '{"interfaceId":"2009","tokenId":"' + tokenId + '","type":"grh","phone":""}';
	url = url + '?jsonStr=' + data + '&r=' + Math.random();
	$( '#' + imgId ).attr('src',url);

}

/**
 * 获取手机验证码 ，GSCFRONT
 */
function getPhoneCode(phoneId){
	
	var phone = $( '#' + phoneId ).val();
	if( getNull(phone) == null ){
		alert('请输入手机号');
		return ;
	}
	
	var url = hyData.getGscFrontUrl();
	var data = 'jsonStr={"interfaceId":"2009","tokenId":"' + getNotUserTokenId() + '","type":"usms","phone":"' + phone + '"}';
	sendRequestPost(url,data,getSMSCodeBack_hc,fail_hc);
	
} 

 //************************************************** [预约领号] ************************************************************//
 
 //************************************************** [支付充值] ************************************************************//
/**
 * 支付宝数据验证且返回数据
 */
 function checkAliPayData(){
	var data = {}; 
	var selectedOption = $( '#JS_SATISFIEDUNITS option:selected');
	var gameServerIdNode = $('#myarea option:selected');
	var gameServerId = $(gameServerIdNode).attr('value');
	if( getNull( gameServerId ) == null 
		|| gameServerId == '-1'  ) {
		alert('请选择角色服！');
		return;
	}
	data.gameServerId = gameServerId; 
	//设置数据缓存，保存游戏服信息
	$.data(document.body,'OURPALM_GAMESERVERNAME',$(gameServerIdNode).html() );
	
	var roleName = $(JS_ROLENAME).val();
	if( getNull( roleName ) == null ) {
		alert('请填写角色名称！');
		return ;
	}
	data.roleName = roleName;

	var chargeCash = $(selectedOption).attr('value');
	if( getNull(chargeCash) == null 
		|| chargeCash == '-1'){
		alert('请选择充值金额！');
		return;
	}
	data.chargeCash = chargeCash;
	//设置数据缓存，保存充值金额信息
	$.data(document.body,'OURPALM_CHARGECASH',chargeCash );

	var validateCode = $('#JS_INPUT_PAYCODE').val();
	if( getNull(validateCode) == null ){
		alert('请输入验证码！');
		return;
	}
	data.validateCode = validateCode;
	
	data.pCode = getPCodeFromParam();
	data.netSource = hyData.getType();
	data.currencyType = $(selectedOption).attr('data_currencyType');
	data.payChannelType = $(selectedOption).attr('data_payChannelType');
	data.validateType = $(selectedOption).attr('data_validatetype');
	data.bankCode = '';
	data.cardNo = '';
	data.cardPasswd = '';
	data.cardType = '';
	
	return data;
 }

/**
 * 神州付数据验证且返回数据
 */
 function checkShenzhoufuPayData(){
	var data = {}; 
	var selectedOption = $( '#JS_SATISFIEDUNITS option:selected');
	var gameServerIdNode = $('#myarea option:selected');
	var gameServerId = $(gameServerIdNode).attr('value');
	if( getNull( gameServerId ) == null 
		|| gameServerId == '-1' ) {
		alert('请选择角色服！');
		return;
	}
	data.gameServerId = gameServerId;
	//设置数据缓存，保存游戏服信息
	$.data(document.body,'OURPALM_GAMESERVERNAME',$(gameServerIdNode).html() );
	
	var roleName = $(JS_ROLENAME).val();
	if( getNull( roleName ) == null ) {
		alert('请填写角色名称！');
		return ;
	}
	data.roleName = roleName;

	var chargeCash = $(selectedOption).attr('value');
	if( getNull(chargeCash) == null 
		|| chargeCash == '-1' ){
		alert('请选择充值金额！');
		return;
	}
	data.chargeCash = chargeCash;
	//设置数据缓存，保存充值金额信息
	$.data(document.body,'OURPALM_CHARGECASH',chargeCash );
	
	var cardNo = $('#JS_CARDNO').val();
	if( getNull(cardNo) == null ){
		alert('请填写充值卡序列号！');
		return;
	}
	data.cardNo = cardNo;
	
	var cardPasswd = $('#JS_CARDPASSWORD').val();
	if( getNull(cardPasswd) == null ){
		alert('请填写充值卡密码！');
		return;
	}
	data.cardPasswd = cardPasswd;
	
	var cardTypeNode = $('#JS_CARDTYPE option:selected');
	var cardType = $(cardTypeNode).attr('value');
	if( getNull(cardType) == null 
		|| cardType == '-1' ){
		alert('请选择运营商！');
		return;
	}
	data.cardType = cardType;
	
	var validateCode = $('#JS_INPUT_PAYCODE').val();
	if( getNull(validateCode) == null ){
		alert('请输入验证码！');
		return;
	}
	data.validateCode = validateCode;
	
	data.pCode = getPCodeFromParam();
	data.netSource = hyData.getType();
	data.currencyType = $(selectedOption).attr('data_currencyType');
	data.payChannelType = $(selectedOption).attr('data_payChannelType');
	data.validateType = $(selectedOption).attr('data_validatetype');
	data.bankCode = '';
	
	return data;
 } 
 
 /**
 * Mo9数据验证且返回数据
 */
 function checkMo9PayData(){
	var data = {}; 
	var selectedOption = $( '#JS_SATISFIEDUNITS option:selected');
	var gameServerIdNode = $('#myarea option:selected');
	var gameServerId = $(gameServerIdNode).attr('value');
	if( getNull( gameServerId ) == null 
		|| gameServerId == '-1') {
		alert('请选择角色服！');
		return;
	}
	data.gameServerId = gameServerId;
	//设置数据缓存，保存游戏服信息
	$.data(document.body,'OURPALM_GAMESERVERNAME',$(gameServerIdNode).html() );
	
	var roleName = $(JS_ROLENAME).val();
	if( getNull( roleName ) == null ) {
		alert('请填写角色名称！');
		return ;
	}
	data.roleName = roleName;

	var chargeCash = $(selectedOption).attr('value');
	if( getNull(chargeCash) == null ){
		alert('请选择充值金额！');
		return;
	}
	data.chargeCash = chargeCash;
	//设置数据缓存，保存充值金额信息
	$.data(document.body,'OURPALM_CHARGECASH',chargeCash );
	
	var validateCode = $('#JS_INPUT_PAYCODE').val();
	if( getNull(validateCode) == null ){
		alert('请输入验证码！');
		return;
	}
	data.validateCode = validateCode;
	
	data.pCode = getPCodeFromParam();
	data.netSource = hyData.getType();
	data.currencyType = $(selectedOption).attr('data_currencyType');
	data.payChannelType = $(selectedOption).attr('data_payChannelType');
	data.validateType = $(selectedOption).attr('data_validatetype');
	data.bankCode = '';
	data.cardNo = '';
	data.cardPasswd = '';
	data.cardType = '';
	
	return data;
 }
 
 /**
  * 获得支付总数据
  */
function getPayData(){
	var driveId = $('#JS_DRIVEID').val();
	switch( driveId ){
			case 'ZHIFUBAO' : return checkAliPayData();
			case 'SHENZHOUFU' : return checkShenzhoufuPayData();
			case 'MO9' : return checkMo9PayData();
			default : return {}; 
			}
  }
 
/**
 * 计费中心：支付
 */
function billingPay_hc(){
	var url = hyData.getBillingCenterUrl();
	var payData = getPayData();

	var data = 'jsonStr={'
			+ '"common":{'
			+ '"interfaceId":"0002",'
			+ '"pCode":"' + payData.pCode + '",'
			+ '"netSource":"' + payData.netSource + '"'
			+ '},'
			+ '"options":{'
			+ '"roleName":"' + payData.roleName + '",'
			+ '"gameServerId":"' + payData.gameServerId + '",'
			+ '"currencyType":"' + payData.currencyType + '",'
			+ '"chargeCash":"' + payData.chargeCash + '",'
			+ '"payChannelType":"' + payData.payChannelType + '",'
			+ '"validateType":"' + payData.validateType + '",'
			+ '"validateCode":"' + payData.validateCode + '",'
			+ '"payChannelParams":{'
			+ '"bankCode":"' + payData.bankCode + '",'
			+ '"cardNo":"' + payData.cardNo + '",'
			+ '"cardPasswd":"' + payData.cardPasswd + '",'
			+ '"cardType":"' + payData.cardType + '",'
			+ '}'
			+ '}'
			+ '}';
	sendRequestPost(url,data,billingPaySuccess_hc,fail_hc);
}

/**
 * 支付中心：支付成功回调
 */
function billingPaySuccess_hc(data){
	if( data.common.status == 0
			&& data.options
			&& data.options.orderId
			&& getNull(data.options.orderId) != null ){
		//支付结果校验成功之后的步骤
		$.data( document.body, 'OURPALM_ORDERID' , data.options.orderId );
		
		switch( data.options.payChannelType ){
			case 'ZHIFUBAO' : 
				afterBillindPopLayer();//弹层
				doAiPaySuccessHandler(data);
				return ;
			case 'SHENZHOUFU' : 
				doShenzhoufuPaySuccessHandler(data);
				return ;
			case 'MO9' : 
				afterBillindPopLayer();//弹层
				doMo9PaySuccessHandler(data);
				return ;
			default : return ; 
		}
	}else{
		alert( data.common.desc );
		//缓存计费失败的原因
		$.data( document.body, 'OURPALM_EXCEPTION' , data.common.desc );
	}
}

function doAiPaySuccessHandler(data){
	$.data( document.body, 'OURPALM_EXCEPTION' , data.common.desc );
	var aliPayUrl = data.options.payChannelProtocol.requestUrl +
			'?' + data.options.payChannelProtocol.requestParam;
	window.open( aliPayUrl );
		
}

function doShenzhoufuPaySuccessHandler(data){
	$.data( document.body, 'OURPALM_EXCEPTION' , data.common.desc );
	if( data.options.payChannelProtocol.payCode == '1' ){
		doPaySuccessJumpHandler();
		//alert('充值成功！');
	}else if( data.options.payChannelProtocol.payCode == '2' ){
		showQueryLayer();
		var flag = setInterval('doInternalQueryResultHandler()',5*1000);
		$.data(document.body,'OURPALM_INTERVAL_FLAG',flag);
	} else {
		doPayFailJumpHandler();
		//alert('充值失败，请重试！');
	}

}

/**
 * 轮询神州付充值结果
 */
function doInternalQueryResultHandler(){
	var url = hyData.getBillingCenterUrl();
	var data = 'jsonStr={'
		+ '"common":{'
		+ '"interfaceId":"0003",'
		+ '"pCode":"' + getPCodeFromParam() + '",'
	    + '"netSource":"' + hyData.getType() + '"'
		+ '},'
		+ '"options":{'
		+ '"orderId":"' + getOrderId() + '"'
		+ '}'
		+ '}';
	sendRequestPost(url,data,function(data){
		if( data.common.status == 0
			&& data.options.payCode == 1 ){
			$.data(document.body,'OURPALM_SHENZHOUFU_INDEX',1 );
			clearInterval( $.data( document.body , 'OURPALM_INTERVAL_FLAG' ) )
			//支付结果校验成功之后的步骤
			doPaySuccessJumpHandler();
		}
		var index = $.data( document.body,'OURPALM_SHENZHOUFU_INDEX' );
		if( isNaN( index ) )  index = 1;
		if( index >= 6 )//超过6次，直接跳转到充值记录页面
		{
			$.data(document.body,'OURPALM_SHENZHOUFU_INDEX',1 );
			clearInterval( $.data( document.body , 'OURPALM_INTERVAL_FLAG' ) )
			window.location.href = 'http://www.gamebean.com/t2/48/393.html';
			return false;
		}
		$.data(document.body,'OURPALM_SHENZHOUFU_INDEX',parseInt( index ) + 1 );
	},new Function() );
}


function doMo9PaySuccessHandler( data ){
	$.data( document.body, 'OURPALM_EXCEPTION' , data.common.desc );
	var mo9PayUrl = data.options.payChannelProtocol.requestUrl +
		'?' + data.options.payChannelProtocol.requestParam;
	window.open( mo9PayUrl );
}

/**
 * 充值成功跳转成功页面函数
 */
function doPaySuccessJumpHandler(){
	//http://www.gamebean.com/t2/48/394.html?g=%E7%8E%A9%E5%85%B7%E6%88%98%E5%9C%BA&s=%E5%86%B2%E4%B8%8A%E4%BA%91%E9%9C%84&o=9a3423afded-dc33dde&f=10%E5%85%83%E4%BA%BA%E6%B0%91%E5%B8%81&r=1
	var gameServerName = $.data( document.body, 'OURPALM_GAMESERVERNAME');
	var chargeCash = $.data( document.body, 'OURPALM_CHARGECASH');
	var game = $('#JS_GAMENAME').html();
	var orderId = $.data( document.body, 'OURPALM_ORDERID');
	var url = 'http://www.gamebean.com/t2/48/394.html?g=' + game + '&s=' + gameServerName + '&f=' + chargeCash + '&o=' + orderId + '&r=' + Math.random();
	window.location.href = url;
	}

/**
 * 充值失败跳转失败页面函数
 */
function doPayFailJumpHandler(){
	//http://www.gamebean.com/t2/48/395.html?g=%E7%8E%A9%E5%85%B7%E6%88%98%E5%9C%BA&s=%E5%86%B2%E4%B8%8A%E4%BA%91%E9%9C%84&e=%E8%AF%A5%E5%85%85%E5%80%BC%E5%8D%A1%E5%B7%B2%E8%A2%AB%E4%BD%BF%E7%94%A8%E8%BF%87%E4%BA%86&f=10%E5%85%83%E4%BA%BA%E6%B0%91%E5%B8%81&r=1
	var gameServerName = $.data( document.body, 'OURPALM_GAMESERVERNAME');
	var chargeCash = $.data( document.body, 'OURPALM_CHARGECASH');
	var game = $('#JS_GAMENAME').html();
	var errorDesc = $.data( document.body, 'OURPALM_EXCEPTION');
	var url = 'http://www.gamebean.com/t2/48/395.html?g=' + game + '&s=' + gameServerName + '&e=' + errorDesc + '&r=' + Math.random();
	window.location.href = url;
 	}

/**
 * 计费中心：计费回调成功后弹出层
 */
 function afterBillindPopLayer(){
		var h = $(document).height();
		$('#screen').css({ 'height': h });
		$('#screen').show();
		$('.pop-box').center();
		$('.pop-box').fadeIn();
		return false;
	}

/**
 * 计费中心：弹层关闭函数
 */
 function afterBillingCloseLayer(){
	$('.pop-box').fadeOut(function(){ $('#screen').hide(); });
	return false;
 }

 //************************************************** [支付充值] ************************************************************//
 
 //************************************************** [计费点筛选] ************************************************************//
/**
 * GSCFRONT：计费点筛选
 */
function selectBillingPoint_hc(){
	var url = hyData.getGscFrontUrl();
	var data = 'jsonStr={'
		 + '"interfaceId":"2007",'
		 + '"payWay":"' + getDriveId() + '",'
		 + '"netSrc":"' + hyData.getType() + '",'
		 + '"pCode":"' + getPCodeFromParam() + '"'
		 + '}';
	sendRequestPost(url,data,selectBillingPointSuccess_hc,fail_hc);
}

/**
 * 获取支付方式ID hyData.getAliPay()
 */
 function getDriveId(){
	var driveId = $('#JS_DRIVEID').val();
	switch( driveId  ){
		case 'ZHIFUBAO' : return hyData.getAliPay();
		case 'SHENZHOUFU' : return hyData.getShenZhouFuPay();
		case 'MO9' : return hyData.getMo9Pay();
		default : return '';
		}

 }

/**
 * GSCFRONT:计费点筛选成功回调函数
 */
function selectBillingPointSuccess_hc(data){
	if( data.status == 0
			&& data.satisfiedUnits
			&& data.satisfiedUnits.length > 0
			){
		//支付结果校验成功之后的步骤
			doSatisfiedUnitsHandler(data.satisfiedUnits)
	}else{
	alert( data.desc );
	}
}

/**
 * GSCFRONT:计费点筛选成功，处理回调数据函数
 */
function doSatisfiedUnitsHandler(satisfiedUnits){
	if( satisfiedUnits && satisfiedUnits.length > 0 ){
		var len = satisfiedUnits.length;
		var item,cardType;
		var html = '<option value="-1">请选择充值道具</option>';
		for(var i = 0; i < len; i++ ){
			item = satisfiedUnits[i];
			if( typeof item.params.cardType  == 'undefined' ) cardType = '';
			else cardType = item.params.cardType;
			html += '<option value="' + item.cost + '" data_mobileProperties="' + cardType + '" data_currencyType="' + item.moneyType + '" data_payChannelType="' + getPayChannelType() + '" data_validateType="1">' + item.gamePropName + '</option>'
		}
		$('#JS_SATISFIEDUNITS').html(html);
	}
}

function getPayChannelType(){
	var driveId = $('#JS_DRIVEID').val();
	return driveId;
}

/**
 * 计费点筛选，支持运营商，主要是神州付
 */
 function selectChargeUnit(){
	var driveId = $('#JS_DRIVEID').val();
	switch( driveId  ){
		case 'ZHIFUBAO' : return false;
		case 'SHENZHOUFU' : return shenzhoufuSelectChargeUnit();
		case 'MO9' : return false;
		default : return false;
		}

 }
 
 function shenzhoufuSelectChargeUnit(){
    var cardTypeNode = $('#JS_CARDTYPE option:selected');
	var cardType = $(cardTypeNode).attr('value');
	//0-移动 1-联通 2-电信
	$.each( $( '#JS_SATISFIEDUNITS option:gt(0)' ),function(index,item){
		if( $(item).attr('data_mobileProperties') == cardType ) {
			$(item).show();
		}else{
			$(item).hide();
		}
	});
 }

 //************************************************** [计费点筛选] ************************************************************//
 
 //************************************************** [支付验证码] ************************************************************//

/**
 * 计费中心：支付验证码获取接口
 * @params id : 使用支付验证码的IMG标签的ID
 */
function getBillingCode_hc(id){
	var url = hyData.getBillingCenterUrl();
	var data = '?jsonStr={'
			+ '"common":{'
			+ '"interfaceId":"0001",'
			+ '"pCode":"' + getPCodeFromParam() + '",'
    	    + '"netSource":"' + hyData.getType() + '"'
    		+ '},'
    		+ '"options":{}'
			+ '}';
		url += data + '&random=' + Math.random();
		$('#' + id).attr("src",url);
		$('#' + id).load()
}

 //************************************************** [支付验证码] ************************************************************//
 
 //************************************************** [支付结果校验] ************************************************************//

/**
 * 计费中心：支付结果校验
 */
function checkBillingResult_hc(){
	var url = hyData.getBillingCenterUrl();
	var data = 'jsonStr={'
		+ '"common":{'
		+ '"interfaceId":"0003",'
		+ '"pCode":"' + getPCodeFromParam() + '",'
	    + '"netSource":"' + hyData.getType() + '"'
		+ '},'
		+ '"options":{'
		+ '"orderId":"' + getOrderId() + '"'
		+ '}'
		+ '}';
	sendRequestPost(url,data,billingResultSuccess_hc,fail_hc);

}

/**
 * 计费中心：获取订单号
 */
function getOrderId(){
	var orderId = $.data(  document.body, 'OURPALM_ORDERID' );
	return orderId;
}

/**
 * 计费中心：支付结果校验成功回调函数
 */
function billingResultSuccess_hc(data){
	if( data.common.status == 0
			&& data.options.payCode == 1 ){
		//支付结果校验成功之后的步骤
		doPaySuccessJumpHandler();
	}else{
		//支付结果校验失败之后的步骤
		doPayFailJumpHandler();
	}
	afterBillingCloseLayer();
}

function initFailureInfo(){
	var gameName = getParameter('g') == null || getParameter('g') == '' ? '' : getParameter('g');
	var error = getParameter('e') == null || getParameter('e') == '' ? '' : getParameter('e');
	var server = getParameter('s') == null || getParameter('s') == '' ? '' : getParameter('s');	
			  
	$('#JS_GAME_TD').html( decodeURI( gameName ) );
	$('#JS_SERVER_TD').html( decodeURI( server ) );	
	$('#JS_FAILURE_TD').html( decodeURI( error) );		
}

function initSuccessInfo(){
	var gameName = getParameter('g') == null || getParameter('g') == '' ? '' : getParameter('g');
	var server = getParameter('s') == null || getParameter('s') == '' ? '' : getParameter('s');
	var order = getParameter('o') == null || getParameter('o') == '' ? '' : getParameter('o');	
	var fee = getParameter('f') == null || getParameter('f') == '' ? '' : getParameter('f');	
			  
	$('#JS_GAME_TD').html( decodeURI( gameName ) );
	$('#JS_SERVER_TD').html( decodeURI(server ) );	
	$('#JS_ORDER_SPAN').html( decodeURI(order ) );	
	$('#JS_CASH_TD').html( ( decodeURI( fee ) / 100 ) + ' 元人民币' );		
}
//************************************************** [支付结果校验] ************************************************************//

//************************************************** [点击充值页面跳转] ************************************************************//
/**
 * 产品充值跳转办法
 */
function goToPayPage(){
	$('.recharge-button01').on( 'click' , function(){
		var that = this;
		var href = $(that).attr('data_href'),
			pcode = $(that).attr('data_pCode');
		href = href + '?pcode=' + pcode;	
		
		var game = $(that).siblings('.rec-input').html();
		saveGameName(game);
		savePCodeFromParam(pcode);
		
		window.location.href = href;
	});
	
}

function initGameName(){
	var game = getGameName() ;
	var dgame = decodeURI( game );
	$('#JS_GAMENAME').html( dgame );
}

function saveGameName(game){
	setCookie( 'ourpalm_game',encodeURI( game ) );
}

function getGameName(){
	return getCookie('ourpalm_game');
}

function savePCodeFromParam(pcode){
	setCookie('ourpalm_pcode',pcode);
}


function getPCodeFromParam(){
	return getParameter('pcode') == null || getParameter('pcode') == '' ? getCookie('ourpalm_pcode') : getParameter('pcode') ;
}

//************************************************** [点击充值页面跳转] ************************************************************//

//************************************************** [用户登陆第二版] ************************************************************//
function checkUserNameV2(){
	var userName = $('#JS_USERNAME_INPUT').val();
	if( userName == '' || userName == '用户名' ){
		$('#JS_USERNAME_INPUT').siblings('span').html('用户名请输入6-18位的字母、数字、_ 的组合');
		$('#JS_USERNAME_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}
	
	var reg=/^[a-zA-Z0-9_]{6,18}$/;
	if( !reg.test(userName) ){
		$('#JS_USERNAME_INPUT').siblings('span').html('用户名请输入6-18位的字母、数字、_ 的组合');
		$('#JS_USERNAME_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}	
	
	reg=/^[0-9]{6,18}$/;	
	if( reg.test(userName) ){
		$('#JS_USERNAME_INPUT').siblings('span').html('用户名不能为全数字');
		$('#JS_USERNAME_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}
	$('#JS_USERNAME_INPUT').siblings('span').hide();
	return true;
}

function checkUserNameV3(){
	var userName = $('#JS_USERNAME_INPUT').val();
	
	if( userName == '' || userName == '用户名' ){
		alert('请输入用户名');
		return fasle;
	}
	
	return true;
}

function checkUserName(){
	var userName = $('#JS_USERNAME_INPUT').val();
	if( userName == '用户名' ){
		$('#JS_USERNAME_INPUT').val('');
	}
}

function checkPassword(){
	
}

function checkPasswordV2(){
	var passWord = $('#JS_PASSWORD_INPUT').val();
	if( passWord == '' ){
		$('#JS_PASSWORD_INPUT').siblings('span').html('密码请输入6-14位字母+数字组合');
		$('#JS_PASSWORD_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}
	
	var reg=/^[a-zA-Z0-9]{6,14}$/;
	if( !reg.test(passWord) ){
		$('#JS_PASSWORD_INPUT').siblings('span').html('密码请输入6-14位字母+数字组合');
		$('#JS_PASSWORD_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}
	$('#JS_PASSWORD_INPUT').siblings('span').hide();
	return true;
}

function checkPasswordV4(){
	var passWord = $('#JS_PASSWORD_INPUT').val();
	
	var doublePassword = $('#JS_PASSWORD_DOUBLE_INPUT').val();
	if( getNull( doublePassword ) == null ){
		$('#JS_PASSWORD_DOUBLE_INPUT').siblings('span').html('请输入确认密码');
		$('#JS_PASSWORD_DOUBLE_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}
	if( passWord != doublePassword ){
		$('#JS_PASSWORD_DOUBLE_INPUT').siblings('span').html('两次填写的密码不一致');
		$('#JS_PASSWORD_DOUBLE_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}
	$('#JS_PASSWORD_DOUBLE_INPUT').siblings('span').hide();
	return true;
}

function checkPasswordV3(){
	var passWord = $('#JS_PASSWORD_INPUT').val();
	if( passWord == '' ){
		alert('请输入密码');
		return false;
	}
	return true;
}

function loginV2_hc(){
	var url = hyData.getGscFrontUrl();
	var tokenId = getNotUserTokenId();
	var userName = $('#JS_USERNAME_INPUT').val();
	
	if( !checkUserNameV3()) return false;
	
	if( !checkPasswordV3() ) return fasle;
	
	var password = $('#JS_PASSWORD_INPUT').val();
	
	var imgCode = $('#JS_ORDER_IMAGE_CODE_INPUT').val();
	if( getNull( imgCode ) == null ){
		return alert('请填写图形验证码！');
	}
	
	var data = 'jsonStr={'
				+ '"interfaceId":"2010",'
				+ '"tokenId":"' + tokenId + '",'
				+ '"captcha":"' + imgCode + '",'
				+ '"userName":"' + userName + '",'
				+ '"password":"' + password + '"'
				+ '}';
	sendRequestPost(url,data,loginV2Success_hc,fail_hc);
}

function loginV2Success_hc(data){
	if(data.status == 0 || data.status == '0'){
		saveToken(data.content.tokenId);
		loginBackUrl();
	}else{
		alert(data.desc);
	}
}

//登录注册成功返回地址
function loginBackUrl(){
	var referrer = document.referrer;
	if(getNull(referrer) != null && referrer.match('html|HTML') 
		&& !referrer.match('48/329') && !referrer.match('48/328') 
		&& !referrer.match('48/1195') ){
		//history.go(-1);
		location.replace(document.referrer);
	}else{
		location.href = "http://www.gamebean.com/t1/48";
	}
}

//************************************************** [用户登陆第二版] ************************************************************//


//************************************************** [用户注册第二版] ************************************************************//
function checkMobileOrEMail(){
	var data = $('#JS_BIND_INPUT').val();
	if( getNull(data) == null ) return false;
	if( checkMobile( data ) )  {
		$('#JS_CAPTCHA_MOBILE_BUTTON').show();
		$('#JS_CAPTCHA_IMAGE_BUTTON').hide();
		return $.data(document.body,'OURPALM_BIND_FLAG','1');//1：手机 2：邮箱
	}
	else if( checkEMail( data ) ) {
		$('#JS_CAPTCHA_MOBILE_BUTTON').hide();
		getImgCode('JS_CAPTCHA_IMAGE_BUTTON');
		$('#JS_CAPTCHA_IMAGE_BUTTON').show();
	
		return $.data( document.body,'OURPALM_BIND_FLAG','2' );//1：手机 2：邮箱
	}
	else 
		alert('您输入的手机或者邮箱格式不正确！');
		return $.data( document.body,'OURPALM_BIND_FLAG','0' );//,0:未绑定，1：手机 2：邮箱
}

function checkEMail(data){
	var reg=/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	if( !reg.test(data) )
		return false; 
	return true;
}

function checkMobile( data ){
	var reg=/(13|14|15|17|18)[0-9]{9}/;
	if( !reg.test(data) ){
		return false;
	}
	return true;
}

/**
 * 判断输入密码的类型 
 */ 
function CharMode(iN){  
	if (iN>=48 && iN <=57) //数字  
		return 1;  
	if (iN>=65 && iN <=90) //大写  
		return 2;  
	if (iN>=97 && iN <=122) //小写  
		return 4;  
	else  
	return 8;   
}  
/**
 * bitTotal函数  
 * 计算密码模式  
 */
function bitTotal(num){  
	var modes=0;  
	for (i=0;i<4;i++){  
		if (num & 1) modes++;  
		num>>>=1;  
	}  
	return modes;  
}  
/**
 * 返回强度级别  
 */
function checkStrong(sPW){  
	if (sPW.length<=4)  
		return 0; //密码太短  
	var Modes=0;  
	for (i=0;i<sPW.length;i++){  
		//密码模式  
		Modes|=CharMode(sPW.charCodeAt(i));  
	}  
	return bitTotal(Modes);  

}

/**
 * 密码强度样式变更
 */ 
 function changePWDStrong( obj ){
	var passWord = $(obj).val();
	var strong = checkStrong(passWord);
	switch(strong){
		case 0 : $('#JS_PASSWORD_STRONG_UL').children('em').html('弱');
			return $('#JS_PASSWORD_STRONG_UL').removeClass('zhong').removeClass('qiang').addClass('ruo');
		case 1 : $('#JS_PASSWORD_STRONG_UL').children('em').html('中');
			return $('#JS_PASSWORD_STRONG_UL').removeClass('ruo').removeClass('qiang').addClass('zhong');
		case 2 : $('#JS_PASSWORD_STRONG_UL').children('em').html('强');
			return $('#JS_PASSWORD_STRONG_UL').removeClass('zhong').removeClass('ruo').addClass('qiang');
		default : $('#JS_PASSWORD_STRONG_UL').children('em').html('强');
			return $('#JS_PASSWORD_STRONG_UL').removeClass('zhong').removeClass('ruo').addClass('qiang');
	}
 }

 function checkCaptcha(){
	var bindFlag= $.data( document.body , 'OURPALM_BIND_FLAG' );
	var captcha = $('#JS_CAPTCHA_INPUT').val();
	if( getNull( captcha ) == null ){
		if( bindFlag && bindFlag == '1' ){
			$('#JS_CAPTCHA_INPUT').siblings('span').html('请填写手机验证码');
			$('#JS_CAPTCHA_INPUT').siblings('span').removeClass('correct').addClass('error');
			return false;
		}
		if( bindFlag && bindFlag == '2' ){
		//走默认	
		}
		$('#JS_CAPTCHA_INPUT').siblings('span').html('请填写图片验证码');
		$('#JS_CAPTCHA_INPUT').siblings('span').removeClass('correct').addClass('error');
		return false;
	}
	$('#JS_CAPTCHA_INPUT').siblings('span').hide();
	return true;
 }

function registerV2_hc(){
	var url = hyData.getGscFrontUrl();
	var tokenId = getNotUserTokenId();
	var userName = $('#JS_USERNAME_INPUT').val();
	if( !checkUserNameV2() ) return false;
	
	
	
	var password = $('#JS_PASSWORD_INPUT').val();
	if( !checkPasswordV2() ) return false;
	
	var doublePassword = $('#JS_PASSWORD_DOUBLE_INPUT').val();
	if( !checkPasswordV4() ) return false;
	
	
	var bindValue = $('#JS_BIND_INPUT').val();
	$.data( document.body , 'OURPALM_BIND_VALUE' , bindValue );
	
	var bindFlag= $.data( document.body , 'OURPALM_BIND_FLAG' );
	var captcha = $('#JS_CAPTCHA_INPUT').val();
	if( !checkCaptcha()) return false;

	var data = 'jsonStr={'
				+ '"interfaceId":"2020",'
				+ '"tokenId":"' + tokenId + '",'
				+ '"captcha":"' + captcha + '",'
				+ '"userName":"' + userName + '",'
				+ '"password":"' + password + '",'
				+ '"confirmPassword":"' + doublePassword + '",'
			//	+ '"bindType":"' + bindFlag + '",'
				+ '"bindType":"' + '0' + '",'
				+ '"emailOrPhone":"' + bindValue + '"'
				+ '}';
	sendRequestPost(url,data,registerV2Success_hc,fail_hc);
}

function saveBindValue(data){
	setCookie('ourpalm_bindvalue',data)
}

function getBindValue(data){
	getCookie('ourpalm_bindvalue');
}
// bindingMode: "0"
// desc: "成功"
// loginType: "1"
// reset: "1000"
// status: "0"
// tokenId: "22e2e838-e093-4506-a6b6-39e48d828e9c"
// userInfo: Object
// id: "9900010000000000000000000000000000702002"
// returnJson: Object
// userName: "shiyuehe9"
function registerV2Success_hc(data){
	if(data.status == 0){
		saveBindValue( $.data( document.body , 'OURPALM_BIND_VALUE' ) );
		saveToken(data.tokenId);
		
		registerBackUrl();
	}else{
		alert(data.desc);
	}
}

function registerBackUrl(){
	var referrer = document.referrer;
	if(getNull(referrer) != null && referrer.match('html|HTML') 
		&& !referrer.match('48/329') && !referrer.match('48/328') ){
		document.referrer = referrer;
		location.href = "http://www.gamebean.com/t2/48/1416.html?referrer=" + referrer;
	}else{
		location.href = "http://www.gamebean.com/t2/48/1416.html";
	}
}

function registerGoto(){
	var referrer = getParameter('referrer');
	var gotoUrl = 'http://www.gamebean.com/t1/48';
	if( referrer != '' ){
		gotoUrl = referrer;
	}
	window.location.href = gotoUrl;
}
//************************************************** [用户注册第二版] ************************************************************//

/* 保证登入、注册和各个页面的信息展示； 这个上午我来做吧；
 
以下几个跳转，按下面的顺序，徐皓看看今天有时间帮忙调试下； 到时可以在qq上联系； 
基本资料修改及修改后的跳转；
头像修改及修改后的跳转；
手机找回密码及找回密码后的跳转；
邮箱找回密码及找回密码后的跳转；
修改密码及修改后的跳转；
密保邮箱以及密保手机的修改跳转； */

//************************************************** [用户修改密码第二版] ************************************************************//
function changePWDV2_hc(){
	var url = hyData.getGscFrontUrl();
	var tokenId = getToken();
	
	var oldPassword = $('#JS_OLD_PASSWORD_INPUT').val();
	if( getNull( oldPassword ) == null ){
		return alert('请填写原始密码！');
	}
	
	var password = $('#JS_PASSWORD_INPUT').val();
	
	if( !checkPasswordV2() ) return fasle;
	
	if( oldPassword == password ){
		return alert('新旧密码一致，请重新填写！');
	}
	
	var doublePassword = $('#JS_PASSWORD_DOUBLE_INPUT').val();
	if( getNull( doublePassword ) == null ){
		return alert('请填写确认密码！');
	}
	
	if( doublePassword != password ){
		return alert('两次填写的密码不一致！');
	}
	
	var data = 'jsonStr={'
				+ '"interfaceId":"2017",'
				+ '"tokenId":"' + tokenId + '",'
				+ '"old":"' + oldPassword + '",'
				+ '"new":"' + password + '",'
				+ '"newConfirm":"' + doublePassword + '"'
				+ '}';
	sendRequestPost(url,data,changePWDV2Success_hc,fail_hc);
}

function changePWDV2Success_hc(data){
	if(data.status == 0 || data.status == '0'){
		alert('修改密码成功！');
		changePWDBackUrl();
	}else{
		alert(data.desc);
	}
}

function changePWDBackUrl(){
	delCookie('ourpalm_token');
	delCookie('userName_ourplam');
	window.location.href = 'http://www.gamebean.com/t2/48/329.html?r=' + Math.random();
}

//************************************************** [用户修改密码第二版] ************************************************************//


//图片验证码获取接口
function getPCode_hc(code){
	var url = hyData.getCodePath();
	var data = '';
	data +='"tokenId":"'+(getNull(getToken()) != null?getToken():'')+'",'
	data += '"type":"'+'grh'+'"';
	data = '{'+data+'}';
	data = 'json='+data;
	data += '&paramFormat=json&temp='+Date.parse(new Date());
	url += '?'+data;
	$("#"+code).attr("src",url);
}

//短信验证码获取
function getSMSCode_hc(phone){
	var phone = $('#'+phone).val();
	var url = hyData.getCodePath();
	var data ='';
	data +='"tokenId":"'+(getNull(getToken()) != null?getToken():'')+'",';
	data += '"type":"'+'usms'+'",';
	data += '"phone":"'+phone+'"';
	data = '{'+data+'}';
	data = 'json='+data+'&paramFormat=json';
	sendRequestPost(url,data,getSMSCodeBack_hc,fail_hc);
}

//短信验证码回调
var getSMSCodeBack_hc = function(data){
	if(data.status == 0){
		alert('获取短信成功');
	}else{
		alert(data.desc);
	}
}

//个性账号注册
function registerAcctount(account,passWord,confirmPassWord,email,captcha,realName,idCardNo,agree,reg){
	var url = hyData.getRegPath();
	var data = '';
	data += '"account":"'+(getNull($('#'+account).val()) != null?$('#'+account).val():'')+'",';
	data += '"passWord":"'+(getNull($('#'+passWord).val()) != null?$('#'+passWord).val():'')+'",';
	data += '"confirmPassWord":"'+(getNull($('#'+confirmPassWord).val()) != null?$('#'+confirmPassWord).val():'')+'",';
	data += '"email":"'+(getNull($('#'+email).val()) != null?$('#'+email).val():'')+'",';
	data += '"captcha":"'+(getNull($('#'+captcha).val()) != null?$('#'+captcha).val():'')+'",';
	data += '"realName":"'+(getNull($('#'+realName).val()) != null?$('#'+realName).val():'')+'",';
	data += '"idCardNo":"'+(getNull($('#'+idCardNo).val()) != null?$('#'+idCardNo).val():'')+'",';
	data += '"agree":"'+($('#agree').attr('checked')?'1':'0')+'",';
	data += '"regFlag":"'+reg+'",';
	data += '"tokenId":"'+(getNull(getToken()) != null?getToken():'')+'"';
	data = '{'+data+'}';
	data = 'json='+data;
	data += '&paramFormat=json'
	sendRequestPost(url,data,registerAcctountBack,fail_hc);
}

//个性账号注册回调
var registerAcctountBack = function(data){
	if(data.status == 0){
		alert('注册成功');
		saveToken(data.content.tokenId);
		loginBackUrl();
	}else{
		alert(data.desc);
	}
}


//手机注册
function registerMobile(account,captcha,msgCode,passWord,confirmPassWord,realName,idCardNo,agree,reg){
	var url = hyData.getRegPath();
	var data = '';
	data += '"account":"'+(getNull($('#'+account).val()) != null?$('#'+account).val():'')+'",';
	data += '"captcha":"'+(getNull($('#'+captcha).val()) != null?$('#'+captcha).val():'')+'",';
	data += '"msgCode":"'+(getNull($('#'+msgCode).val()) != null?$('#'+msgCode).val():'')+'",';
	data += '"passWord":"'+(getNull($('#'+passWord).val()) != null?$('#'+passWord).val():'')+'",';
	data += '"confirmPassWord":"'+(getNull($('#'+confirmPassWord).val()) != null?$('#'+confirmPassWord).val():'')+'",';
	data += '"realName":"'+(getNull($('#'+realName).val()) != null?$('#'+realName).val():'')+'",';
	data += '"idCardNo":"'+(getNull($('#'+idCardNo).val()) != null?$('#'+idCardNo).val():'')+'",';
	data += '"agree":"'+($('#agree').attr('checked')?'1':'0')+'",';
	data += '"regFlag":"'+reg+'",';
	data += '"tokenId":"'+(getNull(getToken()) != null?getToken():'')+'"';
	data = '{'+data+'}';
	data = 'json='+data;
	data += '&paramFormat=json'
	sendRequestPost(url,data,registerMobileBack,fail_hc);
}

//手机注册回调
var registerMobileBack = function(data){
	if(data.status == 0){
		alert('注册成功');
		saveToken(data.content.tokenId);
		loginBackUrl();
	}else{
		alert(data.desc);
	}
}

//用户登录
function login_hc(account,passWord,captcha){
	var url = hyData.getLoginPath();
	var data = '';
	data += '"account":"'+(getNull($('#'+account).val()) != null?$('#'+account).val():'')+'",';
	data += '"passWord":"'+(getNull($('#'+passWord).val()) != null?$('#'+passWord).val():'')+'",';
	data += '"captcha":"'+(getNull($('#'+captcha).val()) != null?$('#'+captcha).val():'')+'",';
	data += '"tokenId":"'+(getNull(getToken()) != null?getToken():'')+'"';
	data = '{'+data+'}';
	data = 'json='+data;
	data += '&paramFormat=json';
	sendRequestPost(url,data,loginBack_hc,fail_hc);
}

//用户登录回调
var loginBack_hc = function(data){
	if(data.status == 0 || data.status == '0'){
		saveToken(data.content.tokenId);
		loginBackUrl();
	}else{
		alert(data.desc);
	}
}





//*****************************************************[用户详细资料]*********************************************************//
// {"interfaceId":"2021",
// "ourpalm_token":"6003033b-03c5-49eb-8eb3-fd5b8aa536a5",
// "action":"1",
// "nickName":"nickName",
// "birthday":"1999-01-01",
// "carrerId":"2",
// "gender":"1",
// "qq":"12368274",
// "location1":"1",
// "location2":"1"
// }


function updateUserV2_hc(){
	var url = hyData.getGscFrontUrl();
	
	var tokenId = getToken(),
		action = "2", //1查询2修改
		nickName = $('#JS_NICKNAME_INPUT').val();
	if( getNull(nickName) == null ){
		return alert('用户昵称不能为空，请填写');
	}
	var gender = $('input[type="radio"]:checked').val();//1男2女3保密
	if( typeof gender == 'undefined' ) gender = '3';
	var birthday = $('#JS_BIRTHDAY_INPUT').val(),
		location1 = $('#JS_PROVINCE_SELECT option:selected').val(),
		location2 = $('#JS_CITY_SELECT option:selected').val();
	var carrerId = $('#JS_PROFESSION_SELECT option:selected').val(),
		qq = $('#JS_QQ_INPUT').val();
	
	var data = 'jsonStr={"interfaceId":"2021",'
			+ '"ourpalm_token":"' + tokenId + '",'
			+ '"action":"' + action + '",'
			+ '"nickName":"' + nickName + '",'
			+ '"birthday":"' + birthday + '",'
			+ '"carrerId":"' + carrerId + '",'
			+ '"gender":"' + gender + '",'
			+ '"qq":"' + qq + '",'
			+ '"location1":"' + location1 + '",'
			+ '"location2":"' + location2 + '"'
			+ '}'
	sendRequestPost(url,data,updateUserV2Success,fail_hc);
}
//用户详细资料回调
function updateUserV2Success(data){
	if(data.status == 0 || data.status == '0'){
		alert('修改资料成功');
		window.location.href = window.location.href; 
	}else{
		alert(data.desc);
	}
}

/**
 * 初始化头像上传参数
 */
function initHeadImg(){
	var url = hyData.getGscUploadHeadImgUrl();
	$('#JS_HEADIMG_FORM').attr('action',url);
	$('#JS_TOKENID_HIDDEN').val(getToken);
	
}

function changeHeadImg(){
	var url = hyData.getGscUploadHeadImgUrl();
	
	var options = {
		target : url + '?interfaceId=2023&ourpalm_token=' + getToken() + '&r=' + Math.random().toString(16).substring(2), // target element(s)
		success : showResponse,
		url : url + '?interfaceId=2023&ourpalm_token=' + getToken() + '&r=' + Math.random().toString(16).substring(2), // override for
		dataType : 'json', // 'xml', 'script', or 'json' (expected server
		type:'jsonp'
	};
	$('#JS_HEADIMG_FORM').ajaxSubmit(options);
	return false;
	
	function showResponse(data){
		if(data.status == 0 || data.status == '0'){
			alert('上传头像成功');
			$('#JS_SHOW_HEAD_IMG,#JS_SELF_HEAD_IMG').attr('src',data.avatarInfo.avatar);
			$.each( $('#JS_SHOW_HEAD_IMG,#JS_SELF_HEAD_IMG') , function(index,item){ $(item).load();} );
			window.location.href = 'http://www.gamebean.com/t2/48/1185.html?r=' + Math.random(); 
		}else{
			alert(data.desc);
		}
	}
	
}

function updateUser(nickName,gender,birthday,address,carrer,qq,actionFlag){
	var url = hyData.getUpUserPath();
	var data = '';
	data += '"nickName":"'+(getNull($('#'+nickName).val()) != null?$('#'+nickName).val():'')+'",';
	data += '"gender":"'+(getNull( $("input[name='"+gender+"']:checked").val()) != null?$("input[name='"+gender+"']:checked").val():'')+'",';
	data += '"birthday":"'+(getNull($('#'+birthday).val()) != null?$('#'+birthday).val():'')+'",';
	data += '"address":"'+(getNull($('#'+address).val()) != null?$('#'+address).val():'')+'",';
	data += '"carrer":"'+(getNull($('#'+carrer).val()) != null?$('#'+carrer).val():'')+'",';
	data += '"qq":"'+(getNull($('#'+qq).val()) != null?$('#'+qq).val():'')+'",';
	data += '"actionFlag":"'+actionFlag+'",';
	data += '"tokenId":"'+(getNull(getToken()) != null?getToken():'')+'"';
	data = '{'+data+'}';
	data = 'json='+data;
	data += '&paramFormat=json';
	sendRequestPost(url,data,updateUserBack,fail_hc);
}
//用户详细资料回调
var updateUserBack = function(data){
	if(data.status == 0 || data.status == '0'){
		alert('修改资料成功');
	}else{
		alert(data.desc);
	}
}



//获取用户资料
function info_hc(upic){
	var url = hyData.getUpUserPath();
	var data = '';
	data += '"tokenId":"'+(getNull(getToken()) != null?getToken():'')+'",';
	data += '"actionFlag":"'+1+'"';
	data = '{'+data+'}';
	data = 'json='+data;
	data += '&paramFormat=json';
	sendRequestPost(url,data,infoBack_hc,fail_hc);
}

//获取用户资料回调
var infoBack_hc = function(data){
	if(data.status == 0 || data.status == '0'){
		if(getNull($('#'+hyData.getUpic())) != null){
			$('#'+hyData.getUpic()).attr('src',data.content.avatarUrl);
		}
	}else{
		alert(data.desc);
	}
}

/**
 * 我的欢畅
 */
function myHuanchang(){
	if( getNull( getToken() ) == null ){
		window.location.href = 'http://www.gamebean.com/t2/48/329.html?r=' + Math.random();
	} else {
		window.location.href = 'http://www.gamebean.com/t2/48/1185.html?r=' + Math.random();
	}
}

function locationHandler(city){
	var data = {"上海":["上海"],"云南":["保山","楚雄","大理","德宏","迪庆","红河","昆明","丽江","临沧","怒江","普洱","曲靖","思茅","文山","西双版纳","玉溪","昭通"],"内蒙古":["阿拉善盟","包头","巴彦浩特","巴彦淖尔","赤峰","鄂尔多斯","海拉尔","呼和浩特","呼伦贝尔","集宁","临河","通辽","乌海","乌兰察布","乌兰浩特","锡林郭勒","锡林浩特","兴安盟"],"北京":["北京"],"吉林":["白城","白山","珲春","吉林","辽源","梅河口","四平","松原","通化","延边","延吉","长春"],"四川":["阿坝","巴中","成都","达州","德阳","甘孜","广安","广元","乐山","凉山","泸州","眉山","绵阳","南充","内江","攀枝花","遂宁","雅安","宜宾","自贡","资阳"],"天津":["天津"],"宁夏":["固原","石嘴山","吴忠","银川","中卫"],"安徽":["安庆","蚌埠","亳州","巢湖","池州","滁州","阜阳","合肥","淮北","淮南","黄山","六安","马鞍山","宿州","铜陵","芜湖","宣城"],"山东":["滨州","德州","东营","菏泽","济南","济宁","莱芜","聊城","临沂","青岛","日照","泰安","潍坊","威海","烟台","枣庄","淄博"],"山西":["大同","晋城","晋中","临汾","吕梁","朔州","太原","忻州","阳泉","运城","长治"],"广东":["潮州","东莞","佛山","广州","河源","惠州","江门","揭阳","茂名","梅州","清远","汕头","汕尾","韶关","深圳","阳江","云浮","湛江","肇庆","中山","珠海"],"广西":["百色","北海","崇左","防城港","贵港","桂林","河池","贺州","来宾","柳州","南宁","钦州","梧州","玉林"],"新疆":["阿克苏","阿勒泰","巴音郭楞","博尔塔拉","博乐","昌吉","哈密","和田","喀什","克拉玛依","克州","克孜","库尔勒","奎屯","石河子","塔城","吐鲁番","乌鲁木齐","伊犁"],"江苏":["常州","淮安","连云港","南京","南通","宿迁","苏州","泰州","无锡","徐州","盐城","扬州","镇江"],"江西":["抚州","赣州","吉安","景德镇","九江","南昌","萍乡","上饶","新余","宜春","鹰潭"],"河北":["保定","沧州","承德","邯郸","衡水","廊坊","秦皇岛","石家庄","唐山","邢台","张家口"],"河南":["安阳","鹤壁","潢川","焦作","开封","漯河","洛阳","南阳","平顶山","濮阳","三门峡","商丘","新乡","信阳","许昌","郑州","周口","驻马店"],"浙江":["杭州","湖州","嘉兴","金华","丽水","宁波","衢州","绍兴","台州","温州","舟山"],"海南":["海口","三沙","三亚"],"湖北":["恩施","鄂州","黄冈","黄石","江汉","荆门","荆州","十堰","随州","武汉","襄樊","襄阳","咸宁","孝感","宜昌"],"湖南":["常德","郴州","衡阳","怀化","吉首","娄底","邵阳","湘潭","湘西","益阳","永州","岳阳","张家界","长沙","株洲"],"甘肃":["白银","定西","甘南","嘉峪关","金昌","酒泉","兰州","临夏","陇南","平凉","庆阳","天水","武威","张掖"],"福建":["福州","龙岩","南平","宁德","莆田","泉州","三明","厦门","漳州"],"西藏":["阿里","昌都","拉萨","林芝","那曲","日喀则","山南"],"贵州":["安顺","毕节","都匀","贵阳","凯里","六盘水","黔东南","黔南","黔西南","铜仁","兴义","遵义"],"辽宁":["鞍山","本溪","朝阳","大连","丹东","抚顺","阜新","葫芦岛","锦州","辽阳","盘锦","沈阳","铁岭","营口"],"重庆":["重庆"],"陕西":["安康","宝鸡","汉中","商洛","铜川","渭南","西安","咸阳","延安","榆林"],"青海":["德令哈","格尔木","共和","果洛","海北","海东","海南","海西","海晏","黄南","西宁","玉树"],"黑龙江":["大庆","大兴安岭","哈尔滨","鹤岗","黑河","佳木斯","鸡西","牡丹江","齐齐哈尔","七台河","双鸭山","绥化","伊春"]};
	var location1 = $('#JS_PROVINCE_SELECT option:selected').val();
	if( location1 == '' ) return false;
	var options = '<option value="">请选择城市</option>';
	var citys = data[location1];
	if(  !$.isArray( citys) ) return false;
	$.each( citys, function(idx,item){
		var selected = city == item ? 'selected=selected' : '';
		options += '<option value="' + item + '" ' + selected + ' >' + item + '</option>'
	} );
	$('#JS_CITY_SELECT').html(options);
}

function provinceHandler(province){
	var data = ["上海", "云南", "内蒙古", "北京", "吉林", "四川", "天津", "宁夏", "安徽", "山东", "山西", "广东", "广西", "新疆", "江苏", "江西", "河北", "河南", "浙江", "海南", "湖北", "湖南", "甘肃", "福建", "西藏", "贵州", "辽宁", "重庆", "陕西", "青海", "黑龙江"];
	var options = '<option value="">请选择省份</option>';
	$.each(data,function(index,item){
		var selected = province == item ? 'selected=selected' : '';
		options += '<option value="' + item + '" ' + selected + ' >' + item + '</option>'
	});
	$('#JS_PROVINCE_SELECT').html(options);
	
}

function sexHandler(sex){
	$.each( $('input[type="radio"]') , function( index , item ){
		if( $(item).val() == sex ) $(item).attr('checked','checked');
	});
}

function carrerIdHandler(carrerId){
	var options = '<option value="">请选择职业</option>';
	var data = ['在校学生','固定工作者','自由工作者','待业/无业/失业','退休','其他'];
	$.each(data,function(index,item){
		var selected = carrerId == index ? 'selected=selected' : '';
		options += '<option value="' + index + '" ' + selected + ' >' + item + '</option>'
	});
	$('#JS_PROFESSION_SELECT').html(options);
}
//******************************************************[用户详细资料]********************************************************//

//查询区服列表
function areaList_hc(areaid){
	var url = urlData.getGsscms();
	var feature = getPCodeFromParam();
	hyData.setAreaid(areaid);
	var timestamp = (new Date()).valueOf();
	var unixTime = Math.ceil(timestamp / 1000);
	var data = '';
	data += '"interfaceId":"'+'2005'+'",';
	data += '"pCode":"'+feature+'",';
	data += '"time":"'+unixTime+'",';
	var sign = '2005'+feature+unixTime + hyData.getKey();
	sign = $.md5(sign);
	data += '"sign":"'+sign+'"';
	data = '{'+data+'}';
	data = 'jsonStr='+data;
	sendRequestPost(url,data,areaListBack,fail_hc);
}

//查询区服列表回调
var areaListBack = function(data){
	if(data.reset == '1000'){
		var list = data.serverInfo;
		var selector=$('#'+hyData.getAreaid());
		for(var i=0;i<list.length;i++){
			var obj = list[i];
	    	selector.append('<option value="'+obj.serverId+'">'+obj.serverName+'</option>');
		};
	}else{
		alert(data.desc);
	}
}

//游戏筛选
function queryGame(cid,mode,type){
	var url = '';
	if(getNull(type) == null){
		type = '2';
	}
	if(getNull(cid) == null){
		cid = hyData.getCid();
	}
	if(getNull(mode) == null){
		mode = hyData.getMode();
	}
	url = window.location.protocol+'//';
	url += window.location.host+'/';
	url += 't'+type+'/';
	url += hyData.getPid()+'/';
	url += cid+'.html?mode='+mode;
	location.href = url;
}

//设置选中状态
function setSelected(){
	var uri = window.location.pathname;
	var params = uri.split('/');
	var mode = getParameter('mode');
	var cid = (params[3]).split('.')[0];
	var typeClass = $('#'+cid).attr('class');
	var modeClass = $('#'+mode).attr('class');
	hyData.setCid(cid);
	hyData.setMode(mode);
	$('#'+cid).addClass('selected');
	$('#'+mode).addClass('selected');
}

//注销
function outLogin(){
	delCookie('ourpalm_token');
	delCookie('userName_ourplam');
    window.location.href="/";
}

//用户登录
function showLogin(userLoginId,loginId,userParam){
	if(userParam == "" || userParam == "error" || userParam == "null" || userParam == "Null" || userParam == null){
		 $("#"+userLoginId).hide();
         $("#"+loginId).show();
	}else{
		 $("#"+userLoginId).show();
         $("#"+loginId).hide();
          var m = $('#userlogin').html();
         var s = "&nbsp;&nbsp;<a href=\"javascript:outLogin()\">注销</a>";
         if($.trim(m) == s ){
         	$('#userlogin').html("未设置昵称&nbsp;&nbsp;<a href=\"javascript:outLogin()\">注销</a>");
         }
	}
}


