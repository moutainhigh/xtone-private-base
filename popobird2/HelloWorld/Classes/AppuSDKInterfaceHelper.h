#ifndef __AppuSDKInterfaceHelper_H__
#define __AppuSDKInterfaceHelper_H__

#include "cocos2d.h"

struct IAppuSDKInterfaceHelper
{
	IAppuSDKInterfaceHelper() {}
	virtual ~IAppuSDKInterfaceHelper() {}
	virtual void onSuccess() = 0;
	virtual void onFailure() = 0;
};

class AppuSDKInterfaceHelper
{
public:
	AppuSDKInterfaceHelper();
	virtual ~AppuSDKInterfaceHelper() {};
	static AppuSDKInterfaceHelper* instance();

	void init();
	void pay(int price);
	void statistics(int type, int price, const char *desc);
	void exit();

	void logout();
	void payTag(int price,const char* tag);

	void addDelegate(IAppuSDKInterfaceHelper *pDelegate){ m_delegate = pDelegate; };
	void removeDelegate() { m_delegate = NULL; };

	//彩票
	void lottery();
	
private:
	static AppuSDKInterfaceHelper *m_instance;
	IAppuSDKInterfaceHelper *m_delegate;
};


#ifdef __cplusplus
extern "C" {
#endif
	//C ==> Java
	extern void AppuSDK_onInit();
	extern void AppuSDK_pay(int price);
	extern void AppuSDK_pay2(int price);
	extern void AppuSDK_statistics(int type, int price, const char *payDesc);
	extern void AppuSDK_onExit();
	extern void AppuSDK_logout();
	extern void AppuSDK_payTag(int peice,const char* tag);
	extern void AppuSDK_lottery();




	//jni
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include "platform/android/jni/JniHelper.h"
	// Java ==> C
	//JNIEXPORT void JNICALL Java_com_appu_sdk_pay_AppuSdk_payResultNative(JNIEnv* env, jobject thiz, jint code, jint diamonds);
//彩票结束
//lotteryNative
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_lotteryNative
  (JNIEnv * , jobject  , jstring);


/*登陆成功
 * Class:     com_example_HelloWorld_HelloWorld
 * Method:    loginSuccess
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_loginSuccess
  (JNIEnv * , jobject  , jstring  ,jint);

/*
*登陆成功
*/
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_loginSuccess2
  (JNIEnv * , jobject  , jstring  ,jint  ,jint  );

/*支付成功
 * Class:     com_example_HelloWorld_HelloWorld
 * Method:    paynSuccess
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_paySuccess
  (JNIEnv * , jobject , jstring );

/*支付失败
 * Class:     com_example_HelloWorld_HelloWorld
 * Method:    payFail 
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_payFail
  (JNIEnv * , jobject , jstring ); 


#endif  //#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

#ifdef __cplusplus
}
#endif


#endif //#ifndef __AppuSDKInterfaceHelper_H__