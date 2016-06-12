#include "AppuSDKInterfaceHelper.h"
#include "Contants.h"
#include "Config.h"
using namespace cocos2d;

AppuSDKInterfaceHelper* AppuSDKInterfaceHelper::m_instance = NULL;

AppuSDKInterfaceHelper::AppuSDKInterfaceHelper()
{
	
}

AppuSDKInterfaceHelper* AppuSDKInterfaceHelper::instance()
{
	if (!m_instance)
		m_instance = new AppuSDKInterfaceHelper;

	return m_instance;
}


void AppuSDKInterfaceHelper::lottery()
{
	AppuSDK_lottery();
}


void AppuSDKInterfaceHelper::init()
{
	AppuSDK_onInit();
}

void AppuSDKInterfaceHelper::pay(int price)
{

	AppuSDK_pay(price);
	/*if(6 == price)
	{
		AppuSDK_pay2(price);
	}else
	{
		AppuSDK_pay(price);
	}*/
	
}

//登出
void AppuSDKInterfaceHelper::logout()
{
	AppuSDK_logout();
}

//支付 和 标志
void AppuSDKInterfaceHelper::payTag(int price,const char* tag)
{
	AppuSDK_payTag( price,tag);
}

void AppuSDKInterfaceHelper::statistics(int type, int price, const char *desc)
{
	AppuSDK_statistics(type, price, desc);
}

void AppuSDKInterfaceHelper::exit()
{
	AppuSDK_onExit();
}

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

//#define JAVA_PACKAGE_NAME "com/appu/sdk/pay/AppuSdk"
#define JAVA_PACKAGE_NAME  "com/example/HelloWorld/HelloWorld"

#ifdef __cplusplus
extern "C" {
#endif

//
//C==>JAVA
//
void AppuSDK_lottery()
{

	//定义Jni函数信息结构体;  
	JniMethodInfo method; 
	//返回一个bool值表示是否找到此函数;
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
		jobject jobj;
		jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);

		//定义Jni函数信息结构体;
		if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"lottery","(Ljava/lang/String;)V"))
		{
			const char*tag = "C==>JAVA";
			jstring strTag =  method.env->NewStringUTF(tag);
			//调用函数，并传入参数
			method.env->CallVoidMethod(jobj,method.methodID,strTag);

			method.env->DeleteLocalRef(strTag);
			
			method.env->DeleteLocalRef(method.classID);
			return ;
		}
	}

}


//#include "platform\android\jni\JniHelper.h"
//#include "platform\android\jni\jni.h"
void AppuSDK_onInit()
{
	/*JniMethodInfo minfo;
	bool isHave = JniHelper::getStaticMethodInfo(minfo, JAVA_PACKAGE_NAME, "init", "()V");

	if(!isHave) {
		CCLog("jni:init not find");
	} else {
		minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID);
		minfo.env->DeleteLocalRef(minfo.classID);
		CCLog("jni:init ok"); 
	}*/
	//定义Jni函数信息结构体;  
	JniMethodInfo method; 
	//返回一个bool值表示是否找到此函数;
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
		jobject jobj;
		jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);

		//定义Jni函数信息结构体;
		if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"doLogin","()V"))
		{
			//调用函数，并传入参数
			method.env->CallVoidMethod(jobj,method.methodID);
			 method.env->DeleteLocalRef(method.classID);
			return ;
		}
	}



}

void AppuSDK_pay(int price)
{
	/*JniMethodInfo minfo;
	bool isHave = JniHelper::getStaticMethodInfo(minfo, JAVA_PACKAGE_NAME, "pay", "(I)V");

	if(!isHave) {
		CCLog("jni:pay not find");
	} else {
		minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID, price);
		minfo.env->DeleteLocalRef(minfo.classID);
		CCLog("jni:pay ok"); 
	}*/


	//定义Jni函数信息结构体;  
	JniMethodInfo method; 
	//返回一个bool值表示是否找到此函数;
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
		jobject jobj;
		jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);

		//定义Jni函数信息结构体;
		if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"pay","(I)V"))
		{
			//调用函数，并传入参数
			method.env->CallVoidMethod(jobj,method.methodID,price);
			 method.env->DeleteLocalRef(method.classID);
			return ;
		}
	}



}

//登出
void AppuSDK_logout()
{
	//定义Jni函数信息结构体;  
	JniMethodInfo method; 
	//返回一个bool值表示是否找到此函数;
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
		jobject jobj;
		jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);

		//定义Jni函数信息结构体;
		if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"Logout","()V"))
		{
			//调用函数，并传入参数
			method.env->CallVoidMethod(jobj,method.methodID);

			
		    method.env->DeleteLocalRef(method.classID);
			return ;
		}
	}
}

//支付标志
void AppuSDK_payTag(int price,const char* tag)
{
	
	//定义Jni函数信息结构体;  
	JniMethodInfo method; 
	//返回一个bool值表示是否找到此函数;
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
		jobject jobj;
		jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);

		//定义Jni函数信息结构体;
		if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"payTag","(ILjava/lang/String;)V"))
		{
			jstring strTag =  method.env->NewStringUTF(tag);
			//调用函数，并传入参数
			method.env->CallVoidMethod(jobj,method.methodID,price,strTag);

			//release
		    method.env->DeleteLocalRef(strTag);
		    method.env->DeleteLocalRef(method.classID);
			return ;
		}
	}
}



void AppuSDK_statistics( int type, int price, const char *payDesc )
{
	/*JniMethodInfo minfo;
	bool isHave = JniHelper::getStaticMethodInfo(minfo, JAVA_PACKAGE_NAME, "statistics", "(IILjava/lang/String;)V");

	jstring strArgPayDesc = minfo.env->NewStringUTF(payDesc);

	if(!isHave) {
		CCLog("jni:statistics not find");
	} else {
		minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID, type, price, strArgPayDesc);

		//release
		minfo.env->DeleteLocalRef(strArgPayDesc);
		minfo.env->DeleteLocalRef(minfo.classID);
		CCLog("jni:statistics ok"); 
	}*/
}

void AppuSDK_onExit()
{
	/*JniMethodInfo minfo;
	bool isHave = JniHelper::getStaticMethodInfo(minfo, JAVA_PACKAGE_NAME, "onExit", "()V");

	if(!isHave) {
		CCLog("jni:onExit not find");
	} else {
		minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID);
		minfo.env->DeleteLocalRef(minfo.classID);
		CCLog("jni:onExit ok"); 
	}*/
	//定义Jni函数信息结构体;  
	JniMethodInfo method; 
	//返回一个bool值表示是否找到此函数;
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
		jobject jobj;
		jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);

		//定义Jni函数信息结构体;
		if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"exit","()V"))
		{
			//调用函数，并传入参数
			method.env->CallVoidMethod(jobj,method.methodID);
			method.env->DeleteLocalRef(method.classID);
			return ;
		}
	}




}

//
//JAVA==>C
//
/*JNIEXPORT void JNICALL Java_com_appu_sdk_pay_AppuSdk_payResultNative(JNIEnv* env, jobject thiz, jint code, jint diamonds)
{
	CCLOG("JAVA-->C code = %d", code);
	CCLOG("JAVA-->C diamonds = %d", diamonds);

	//PayHelper
	//PayHelper::instance()->resultCallback(code, diamonds);
}*/

/*登陆成功
 * Class:     com_example_HelloWorld_HelloWorld
 * Method:    loginSuccess
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_loginSuccess
  (JNIEnv *  env, jobject obj, jstring str,jint state)
{
	const char * pText = NULL;
	pText = env->GetStringUTFChars(str,NULL);
	std::string uid(pText);
	Config::instance()->addUid(uid);
	//int cheerys = state;
	//Config::instance()->setCherry(cheerys);
	CCLOG("JAVA-->C code = %s", pText);

	CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_LOGIN, (CCObject *)(CCInteger::create(state)));


	env->ReleaseStringUTFChars(str, pText); 

}


JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_loginSuccess2
  (JNIEnv * env, jobject  obj , jstring  str ,jint state,jint cheerys)
{

	const char * pText = NULL;
	pText = env->GetStringUTFChars(str,NULL);
	std::string uid(pText);
	Config::instance()->addUid(uid);
	int chs = cheerys;
	Config::instance()->setCherry(chs);
	CCLOG("JAVA-->C code = %s >>%d", pText,Config::instance()->cherryNum());

	CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_LOGIN, (CCObject *)(CCInteger::create(state)));


	env->ReleaseStringUTFChars(str, pText); 
}

//彩票native
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_lotteryNative
  (JNIEnv * env, jobject  obj , jstring  str)
{
	const char * pText = NULL;
	pText = env->GetStringUTFChars(str,NULL);
	CCLOG("JAVA-->C code = %s", pText);
	env->ReleaseStringUTFChars(str, pText);
}

/*支付成功
 * Class:     com_example_HelloWorld_HelloWorld
 * Method:    paynSuccess
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_paySuccess
  (JNIEnv * env, jobject obj, jstring str)
{
	const char * pText = NULL;
	pText = env->GetStringUTFChars(str,NULL);
	const char * cherry = "cherry";
	const char *pay6 = "6";
	if(strcmp(pText,cherry) == 0)
	{
	    CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_LOGIN, (CCObject *)(CCInteger::create(110)));
	}else if(strcmp(pText,pay6) == 0)
	{
		CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_BUTTON_PRESS_ID, (CCObject *)(CCInteger::create(999)));
		return ;
	}
	else{
	    CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_BACK_ID, (CCObject *)(CCInteger::create(0)));
	}
	CCLOG("JAVA-->C code = %s", pText);

	

	//CCNotificationCenter::sharedNotificationCenter()->postNotification(MSG_CALLJNI_BACK_ID);


	 env->ReleaseStringUTFChars(str, pText);  
}

/*支付失败
 * Class:     com_example_HelloWorld_HelloWorld
 * Method:    payFail 
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_example_HelloWorld_HelloWorld_payFail
  (JNIEnv * env, jobject obj, jstring str)
{
	const char * pText = NULL;
	pText = env->GetStringUTFChars(str,NULL);

	CCLOG("JAVA-->C code = %s", pText);
	CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_BACK_ID, (CCObject *)(CCInteger::create(-1)));




}


#ifdef __cplusplus
}
#endif

#else //win32


void AppuSDK_lottery()
{



}

void AppuSDK_onInit()
{
  Config::instance()->setCherry(10);
  CCNotificationCenter::sharedNotificationCenter()->postNotification(MSG_CALLJNI_LOGIN, (CCObject *)(CCInteger::create(1)));

 
}

void AppuSDK_pay(int price)
{
	//win32 支付 
	//CCNotificationCenter::sharedNotificationCenter()->postNotification(
	//				MSG_CALLJNI_BACK_ID, (CCObject *)(CCInteger::create(-1)));
	if(6 == price)
	{
		CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_BUTTON_PRESS_ID, (CCObject *)(CCInteger::create(999)));
	}else
	{
		CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_BACK_ID, (CCObject *)(CCInteger::create(0)));
	}
	

}

 void AppuSDK_pay2(int price)
{
	CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_BUTTON_PRESS_ID, (CCObject *)(CCInteger::create(999)));
}

//登出
void AppuSDK_logout()
{

}

//支付标志
void AppuSDK_payTag(int price,const char* tag)
{
	 CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_LOGIN, (CCObject *)(CCInteger::create(110)));
}


void AppuSDK_statistics( int type, int price, const char *payDesc )
{

}

void AppuSDK_onExit()
{
	
}


#endif //#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
