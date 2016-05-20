#ifndef __HTTPTEST_SCENE_H__
#define __HTTPTEST_SCENE_H__
#include "cocos2d.h"
#include "cocos-ext.h"
#include <vector>
#include "Config.h"
USING_NS_CC;
USING_NS_CC_EXT;
using namespace std;
class HttpTest : public cocos2d::CCObject
{
public:
	bool init();
	//∑¢ÀÕ«Î«Û
	void sendReq(std::string uid,std::string cherryNum);
	void onHttpRequestCompleted(cocos2d::CCNode *sender ,void *data);
	 //CREATE_FUNC(HttpTest);
};  

#endif // __HTTPTEST_SCENE_H__