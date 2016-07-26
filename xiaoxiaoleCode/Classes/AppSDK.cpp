#include "AppSDK.h"


AppSDK *AppSDK::m_instance = NULL;

AppSDK *AppSDK::instance()
{
	if(!m_instance)
	{
		m_instance = new AppSDK();
	}
	return m_instance;
}


void AppSDK::login()
{
	AppSDK_login(100,"start login");
}

void AppSDK::pay(int price,const char* string)
{
	AppSDK_pay(price,string);
}



#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

#define JAVA_PACKAGE_NAME "com/huaxuan/heart/MainActivity"

//
//C==>JAVA
//
void AppSDK_login(int flag,const char * str)
{
	JniMethodInfo method; 
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
	  jobject jobj;
	  jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);
	  if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"login","(ILjava/lang/String;)V"))
	  {
		 jstring strTag =  method.env->NewStringUTF(str);
	     method.env->CallVoidMethod(jobj,method.methodID,11,strTag);
	     method.env->DeleteLocalRef(strTag);
			
		 method.env->DeleteLocalRef(method.classID);
	  }
	
	}

	CCLOG("login %d >> %s",flag,str);
}


void AppSDK_pay(int price,const char* string)
{
	
	JniMethodInfo method; 
	if(JniHelper::getStaticMethodInfo(method,JAVA_PACKAGE_NAME,"rtnObject","()Ljava/lang/Object;"))
	{
	  jobject jobj;
	  jobj = method.env->CallStaticObjectMethod(method.classID,method.methodID);
	  if(JniHelper::getMethodInfo(method,JAVA_PACKAGE_NAME,"pay","(ILjava/lang/String;)V"))
	  {
		 jstring strTag =  method.env->NewStringUTF(string);
	     method.env->CallVoidMethod(jobj,method.methodID,11,strTag);
	     method.env->DeleteLocalRef(strTag);
			
		 method.env->DeleteLocalRef(method.classID);
	  }
	
	}

}


#ifdef __cplusplus
extern "C" {
#endif
//
//JAVA==>C
//
JNIEXPORT void JNICALL Java_com_huaxuan_heart_AppSDK_loginSuccess
  (JNIEnv * env , jobject  obj , jstring  str ,jint a ,jint b )
{

}

JNIEXPORT void JNICALL Java_com_huaxuan_heart_MainActivity_paySuccess
(JNIEnv *env , jobject  obj, jint  flag)
{
	CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI, (CCObject *)(CCInteger::create(flag)));
}


JNIEXPORT void JNICALL Java_com_huaxuan_heart_MainActivity_showGift
(JNIEnv *env , jobject  obj, jint  flag ,jstring  str)
{
	CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI_MAIN, (CCObject *)(CCInteger::create(flag)));
}


#ifdef __cplusplus
}
#endif

#else //win32

void AppSDK_login(int flag,const char * str)
{
	CCLOG("login %d >> %s",flag,str);
}


void AppSDK_pay(int price,const char* string)
{
	CCNotificationCenter::sharedNotificationCenter()->postNotification(
					MSG_CALLJNI, (CCObject *)(CCInteger::create(1)));
	CCLOG("login %d >> %s",price,string);
}

#endif //#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)



