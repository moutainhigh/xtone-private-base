<?php
/**
  * wechat php test
  */

//define your token
define("TOKEN", "huisen");
$wechatObj = new wechatCallbackapiTest();
$wechatObj->valid();

class wechatCallbackapiTest
{
	public function valid()
    {
        $echoStr = $_GET["echostr"];
		
        //valid signature , option
        if($this->checkSignature()){
			$this->responseMsg();
        	echo $echoStr;
        	exit;
        }
    }

    public function responseMsg()
    {
		//get post data, May be due to the different environments
		$postStr = $GLOBALS["HTTP_RAW_POST_DATA"];
		$this->logFile('log.txt',$postStr);
		
      	//extract post data
		if (!empty($postStr)){
                /* libxml_disable_entity_loader is to prevent XML eXternal Entity Injection,
                   the best way is to check the validity of xml by yourself */
                libxml_disable_entity_loader(true);
              	$postObj = simplexml_load_string($postStr, 'SimpleXMLElement', LIBXML_NOCDATA);
                $fromUsername = $postObj->FromUserName;
                $toUsername = $postObj->ToUserName;
                $keyword = trim($postObj->Content);
				
				
                $time = time();
               /* $textTpl = "<xml>
							<ToUserName><![CDATA[%s]]></ToUserName>
							<FromUserName><![CDATA[%s]]></FromUserName>
							<CreateTime>%s</CreateTime>
							<MsgType><![CDATA[%s]]></MsgType>
							<Content><![CDATA[%s]]></Content>
							<FuncFlag>0</FuncFlag>
							</xml>";  */
							
				$textTpl = "<xml>
							<ToUserName><![CDATA[%s]]></ToUserName>
							<FromUserName><![CDATA[%s]]></FromUserName>
							<CreateTime>%s</CreateTime>
							<MsgType><![CDATA[%s]]></MsgType>
							<ArticleCount>1</ArticleCount>
							<Articles>
							<item>
							<Title><![CDATA[战雷神、赢iphone、体验奇妙太空飞行之旅，丰富奖品下载即有惊喜！]]></Title> 
							<Description><![CDATA[%s]]></Description>
							<PicUrl><![CDATA[https://mmbiz.qlogo.cn/mmbiz/GWF7Rmpe9WicA0ia1XcBjmxibNeMPziaibgUibdpXkPYviaPJzCP7JqicDVJe19KU6HOjvNZHSJGq8Kl8bVmIGzpibAUibHw/0]]></PicUrl>
							<Url><![CDATA[http://www.128digital.com/game/index.php]]></Url>
							</item>
							</Articles>
							</xml>";  
							       
				if(!empty( $keyword ))
                {
              		$msgType = "news";
                	$contentStr = "点击直接进入游戏详情页面";
                	$resultStr = sprintf($textTpl, $fromUsername, $toUsername, $time, $msgType, $contentStr);
                	echo $resultStr;
                }else{
                	echo "Input something...";
                }

        }else {
        	echo "";
        	exit;
        }
    }
		
	private function checkSignature()
	{
        // you must define TOKEN by yourself
        if (!defined("TOKEN")) {
            throw new Exception('TOKEN is not defined!');
        }
        
        $signature = $_GET["signature"];
        $timestamp = $_GET["timestamp"];
        $nonce = $_GET["nonce"];
        		
		$token = TOKEN;
		$tmpArr = array($token, $timestamp, $nonce);
        // use SORT_STRING rule
		sort($tmpArr, SORT_STRING);
		$tmpStr = implode( $tmpArr );
		$tmpStr = sha1( $tmpStr );
		
		if( $tmpStr == $signature ){
			return true;
		}else{
			return false;
		}
	}
	
	
	function logFile($filename,$msg){
		$fd = fopen($filename,"a");
		$str = "[".date("Y/m/d h:i:s",time())."]".$msg;
		fwrite($fd, $str."\r\n");
		fclose($fd);
	}


}

?>