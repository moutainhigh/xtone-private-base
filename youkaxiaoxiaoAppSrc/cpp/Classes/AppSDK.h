#ifndef  _AppSDK_H_
#define  _AppSDK_H_

#include "cocos2d.h"
USING_NS_CC;

#define     MSG_CALLJNI             "calljni"


#define     MSG_CALLJNI_MAIN             "calljni_main"

class AppSDK{

public :
	static AppSDK *instance();

	void login();

	/***
	 Ö§¸¶ ½ð¶î ºÍinfo
	***/
	void pay(int price,const char* string);


private:
	static AppSDK *m_instance;


};



#ifdef __cplusplus
extern "C" {
#endif
	//C ==> Java
	extern void AppSDK_login(int flag,const char * str);
	extern void AppSDK_pay(int price,const char* string);

	

//jni
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include "platform/android/jni/JniHelper.h"

// Java ==> C
JNIEXPORT void JNICALL Java_com_huaxuan_heart_AppSDK_loginSuccess
  (JNIEnv * , jobject  , jstring  ,jint  ,jint  );


JNIEXPORT void JNICALL Java_com_huaxuan_heart_MainActivity_paySuccess
(JNIEnv * , jobject  , jint  );


JNIEXPORT void JNICALL Java_com_huaxuan_heart_MainActivity_showGift
(JNIEnv * , jobject  , jint  ,jstring );






#endif  //#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

#ifdef __cplusplus
}
#endif




#endif