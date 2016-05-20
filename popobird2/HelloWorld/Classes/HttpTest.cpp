#include "HttpTest.h"

 bool HttpTest::init()
{
	

	CCHttpRequest *req = new CCHttpRequest();
	const  char * url = "http://www.baidu.com";
	req->setUrl(url);
	//req->setRequestType(CCHttpRequest::kHttpPost);
	req->setRequestType(CCHttpRequest::kHttpGet);
	const char* data = "name=zhang&page=2";
	req->setRequestData(data,strlen(data));
	req->setResponseCallback(this,callfuncND_selector(HttpTest::onHttpRequestCompleted));
	CCHttpClient::getInstance()->send(req);
	req->release();
	return true;
}

 void HttpTest::sendReq(std::string uid,std::string cherryNum)
 {
	CCHttpRequest *req = new CCHttpRequest();
	
	//const  char * url = "http://192.168.0.111:8080/PopoBird/BirdService";
	 
	/*
	*popobird.n8wan.com:29141/
	*/
	const  char * url = "http://popobird.n8wan.com:29141/BirdService";

	req->setUrl(url);
	//?uid=143&cherryNum=4554
	req->setRequestType(CCHttpRequest::kHttpPost);
	//req->setRequestType(CCHttpRequest::kHttpGet);
	//const char* data = "name=zhang&page=2";
	std::string strData;
	strData.append("uid=");
	strData.append(uid);
	if(cherryNum.length()>0)
	{
		strData.append("&cherryNum=");
		strData.append(cherryNum);
	}
	
	const char* data = strData.c_str();
	req->setRequestData(data,strlen(data));
	req->setResponseCallback(this,callfuncND_selector(HttpTest::onHttpRequestCompleted));
	CCHttpClient::getInstance()->send(req);
	req->release();
 }



 void HttpTest::onHttpRequestCompleted(cocos2d::CCNode *sender ,void *data)
 {
	 CCHttpResponse *res = (CCHttpResponse*)data;
	 int code = res->getResponseCode();
	 vector<char> *buff = res->getResponseData();
	 std::stringstream oss;
	 for (int i = 0; i < buff->size(); i++)
	 {
		 oss<<buff->at(i);
	 }

	 std::string temp = oss.str();

	 const char * buf = temp.c_str();
	 int cherryNum = atoi(buf);
	 if(cherryNum>0)
	 {
		 Config::instance()->setCherry(cherryNum);
	 }


	/* std::string  result;
	 for (int i = 0; i < buff->size(); i++)
	 {
		 char c = buff->at(i);
		 char ch[20]={0};
		 sprintf(ch,"%c",c);
		 int n
		 result.append(ch);
		 
		
	 }*/
	 
	 //std::cout<<result<<std::endl;
	 
 }